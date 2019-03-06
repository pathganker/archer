/**   */
package cn.com.qingqfeng.archer.webapi.controller.auth;

import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;




import cn.com.qingqfeng.archer.enums.ApiCodeEnum;
import cn.com.qingqfeng.archer.pojo.Result;
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
	 private StringRedisTemplate redisTemplate; 
	

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
        // 签发一个Json Web Token
        // 令牌ID=uuid,用户=clientKey,签发者=token-server
        // token有效期=3分钟,用户角色=ordinary,用户权限=read
        String jwt = JwtUtils.issueJwt("4cb4f24f-ec20-4687-936d-b253f32a1b86", clientKey, 
                                    "token-server",180000L, "ordinary", "read", SignatureAlgorithm.HS256);
        rs.setCode(ApiCodeEnum.SUCCESS);
        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("jwt", jwt);
        data.put("expireTime", "180000");
        rs.setData(data);
        return rs;
    }
	
	
}
