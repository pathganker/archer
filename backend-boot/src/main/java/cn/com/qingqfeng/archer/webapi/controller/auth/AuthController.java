/**   */
package cn.com.qingqfeng.archer.webapi.controller.auth;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

import cn.com.qingqfeng.archer.config.WeiboOauth;
import cn.com.qingqfeng.archer.enums.ApiCodeEnum;
import cn.com.qingqfeng.archer.enums.UserTypeEnum;
import cn.com.qingqfeng.archer.pojo.Result;
import cn.com.qingqfeng.archer.pojo.user.UserDTO;
import cn.com.qingqfeng.archer.service.user.IUserService;
import cn.com.qingqfeng.archer.utils.ArcherWebUtils;
import cn.com.qingqfeng.archer.utils.HttpUtils;
import cn.com.qingqfeng.archer.utils.JwtUtils;
import cn.com.qingqfeng.archer.utils.VerifyCodeUtils;
import io.jsonwebtoken.SignatureAlgorithm;

/**   
 * <p>类名称: AuthController </p> 
 * <p>描述: TODO  </p>
 * <p>创建时间 : 2019年2月22日 上午10:38:21 </p>
 * @author lijunliang
 * @version 1.0
 * 
 */
@RestController
@RequestMapping("auth")
public class AuthController {
	 @Autowired
	 private WeiboOauth weibo;
	
	 @Autowired
	 private StringRedisTemplate redisTemplate; 
	
	 @Autowired
	 private IUserService userService;

	 private final static String CAPTCHA_KEY = "captcha:key_";

	 /**
	 * 
	 * <p>方法名:  getCaptcha </p> 
	 * <p>描述:    TODO </p>
	 * <p>创建时间:  2019年2月22日下午1:04:10 </p>
	 * @version 1.0
	 * @author lijunliang
	 * @param req
	 * @param res  
	 * void
	 */
	@RequestMapping(value="captcha/{key}", method={RequestMethod.GET})
	public void getCaptcha(HttpServletRequest req, HttpServletResponse res, @PathVariable String key) {
		// 生成随机字串
		String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
		this.redisTemplate.opsForValue().set(CAPTCHA_KEY + key, verifyCode.toLowerCase(), 180000L, TimeUnit.MILLISECONDS);
		// 生成图片
		int w = 200, h = 40;
		try {
			OutputStream out = res.getOutputStream();
			VerifyCodeUtils.outputImage(w, h, out, verifyCode);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/**
	 * 
	 * <p>方法名:  validateCaptcha </p> 
	 * <p>描述:    TODO </p>
	 * <p>创建时间:  2019年2月22日下午1:04:07 </p>
	 * @version 1.0
	 * @author lijunliang
	 * @param req
	 * @param res
	 * @return  
	 * Result
	 */
	@RequestMapping(value="vercode/{key}", method={RequestMethod.GET})
	public Result validateCaptcha(@RequestParam String captcha, @PathVariable String key) {
		Result rs = new Result();
		//大写转小写
		if(null != captcha){
			captcha=captcha.toLowerCase();
		}
		String verCode = this.redisTemplate.opsForValue().get(CAPTCHA_KEY+key);
		if (null != verCode && verCode.equals(captcha)){
			rs.setCode(ApiCodeEnum.SUCCESS);
		}else if(null == verCode){
			rs.setCode(ApiCodeEnum.CAPTCHA_TIMEOUT);
		}else{
			rs.setCode(ApiCodeEnum.CAPTCHA_WRONG);
		}
		return rs;
	}
	/**
	 * 
	 * <p>方法名:  applyToken </p> 
	 * <p>描述:    TODO </p>
	 * <p>创建时间:  2019年2月26日上午10:41:32 </p>
	 * @version 1.0
	 * @author lijunliang
	 * @param clientKey
	 * @return  
	 * Result
	 */
	@RequestMapping(value="jwttoken", method= {RequestMethod.POST})
    public Result applyToken(HttpServletRequest req, HttpServletResponse res) {
		Result rs = new Result();
		String clientKey = req.getHeader("Auth-clientKey");
		Subject user = SecurityUtils.getSubject();
		if(null != user && user.isAuthenticated()){
			rs.setCode(ApiCodeEnum.SUCCESS);
			rs.setData(req.getParameter("jwt"));
			return rs;
		}
		String host = ArcherWebUtils.getRemoteIpAddr(req);
        // 签发一个Json Web Token
        // 令牌ID=uuid,用户=clientKey,签发者=token-server
        // token有效期=3分钟,用户角色=ordinary,用户权限=read
        String jwt = JwtUtils.issueJwt(UUID.randomUUID().toString(), clientKey, 
                                    "token-server",180000L, "ordinary", "read", host, SignatureAlgorithm.HS256);
        rs.setCode(ApiCodeEnum.SUCCESS);
        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("jwt", jwt);
        rs.setData(data);
        return rs;
    }
	/**
	 * 
	 * <p>方法名:  oauth2Token </p> 
	 * <p>描述:    第三方授权 </p>
	 * <p>创建时间:  2019年3月25日下午1:17:42 </p>
	 * @version 1.0
	 * @author lijunliang
	 * @param provider
	 * @param code
	 * @param req
	 * @return  
	 * Result
	 */
	@RequestMapping(value="oauth2/callback",method={RequestMethod.GET})
	public Result oauth2Token(String provider, String code, HttpServletRequest req) {
		Result rs = new Result();
		if(StringUtils.isBlank(code)){
			rs.setCode(ApiCodeEnum.API_AUTHORITY);
			return rs;
		}
		String clientKey = req.getHeader("Auth-clientKey");
		String host = ArcherWebUtils.getRemoteIpAddr(req);
		if("weibo".equals(provider)){
			//access_token
			Map<String, String> params = new LinkedHashMap<String, String>();
			params.put("client_id",weibo.getClientId());
			params.put("client_secret",weibo.getClientSecret());
			params.put("grant_type","authorization_code");
			params.put("code",code);
			params.put("redirect_uri",weibo.getRedirectUri());
			String tokenJson = "";
			try{
				tokenJson=HttpUtils.client(weibo.getTokenUri(), HttpMethod.POST, params);
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
			@SuppressWarnings("unchecked")
			Map<String,String> tokenMap = (Map<String, String>) JSON.parse(tokenJson);
			if(null == tokenMap){
				rs.setCode(ApiCodeEnum.ARGS_WRONG);
				return rs;
			}
			String accessToken = tokenMap.get("access_token");
			if(StringUtils.isBlank(accessToken)){
				rs.setCode(ApiCodeEnum.ARGS_WRONG);
				return rs;
			}
			String uid = tokenMap.get("uid");
			//user_info
			params.clear();
			params.put("access_token",accessToken);
			params.put("uid",uid);
			//已注册 直接发放token
			if(this.userService.checkUserExist(uid)){
				String token = JwtUtils.issueJwt(uid, clientKey, "token-server", 24*3600*1000*7L, "ordinary", "comment", host, SignatureAlgorithm.HS256);
				rs.setData(token);
				rs.setCode(ApiCodeEnum.SUCCESS);
				return rs;
			}
			//新注册用户
			String userJson = HttpUtils.client(weibo.getUserInfoUri(), HttpMethod.GET, params);
			@SuppressWarnings("unchecked")
			Map<String,String> userMap = (Map<String, String>) JSON.parse(userJson);
			String userName = userMap.get("screen_name");
			String avatar = userMap.get("avatar_large");
			//添加用户
			UserDTO user = new UserDTO();
			user.setId(uid);
			user.setavatar(avatar);
			user.setNickname(userName);
			user.setType(UserTypeEnum.WEIBO.getCode());
			user.setCreateTime(new Date());
			user.setModifyTime(new Date());
			this.userService.addThirdParty(user);
			//发放token
			String token = JwtUtils.issueJwt(uid, clientKey, "token-server", 24*3600*1000*7L, "ordinary", "comment", host, SignatureAlgorithm.HS256);
			rs.setData(token);
			rs.setCode(ApiCodeEnum.SUCCESS);
			return rs;
		}
		rs.setCode(ApiCodeEnum.API_AUTHORITY);
		return rs;
	}
	
}
