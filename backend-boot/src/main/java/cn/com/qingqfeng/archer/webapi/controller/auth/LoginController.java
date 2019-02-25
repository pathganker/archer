/**   */
package cn.com.qingqfeng.archer.webapi.controller.auth;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.qingqfeng.archer.enums.ApiCodeEnum;
import cn.com.qingqfeng.archer.pojo.Result;
import cn.com.qingqfeng.archer.pojo.user.UserDTO;

/**   
 * <p>类名称: LoginController </p> 
 * <p>描述: TODO  </p>
 * <p>创建时间 : 2019年2月25日 下午8:12:36 </p>
 * @author lijunliang
 * @version 1.0
 * 
 */
@RestController
@RequestMapping("login")
public class LoginController {
	
	private final static  Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
	
	@RequestMapping(value="siginin", method={RequestMethod.POST})
	public Result Siginin(@RequestBody UserDTO user, HttpServletRequest request) {
		Result rs = new Result();
		//String deviceId = req.getHeader(DEVICEID);
		//HttpSession session = req.getSession(true);
		//String token = RandomStringUtils.random(32, deviceId + System.currentTimeMillis() + ""); //生成token
		//if("zankokutenshi@yeah.net".equals(username) && "123456".equals(password)){
//			session.setAttribute("token", ARCHER_API_TOKEN_KEY_PREFIX+deviceId+token);
//			rs.setData(token);
//			rs.setCode(ApiCodeEnum.SUCCESS);
//		}else{
//			rs.setCode(ApiCodeEnum.USER_NAME_OR_PWD);
//		}
		if(null == user){
			rs.setCode(ApiCodeEnum.ARGS_WRONG);
			return rs;
		}
		Subject subject = SecurityUtils.getSubject();
		AuthenticationToken token = createAuthToken(user.getUsername(), user.getPassword(), subject.getSession().getHost());
		try {
			String curUser = (String) subject.getPrincipal();
			if(null != curUser &&  curUser.equals(user.getUsername())){
	            rs.setData(token.getPrincipal());
	            rs.setCode(ApiCodeEnum.API_AUTHORITY);
	            return rs;
			}
            subject.login(token);
            rs.setCode(ApiCodeEnum.SUCCESS);
            return rs;
		}catch (UnknownAccountException e) {
			LOGGER.info("登陆失败,非法用户,对应的用户名为:{}",user.getUsername());
			//登录错误，返回失败码
			rs.setCode(ApiCodeEnum.USER_NAME);
		}catch(IncorrectCredentialsException e) {
			LOGGER.info("登陆失败,密码错误,对应的用户名为:{}",user.getUsername());
			//登录错误，返回失败码
			rs.setCode(ApiCodeEnum.USER_PASSWORD);
		}
		return rs;
	}
	/**
	 * 
	 * <p>方法名:  logout </p> 
	 * <p>描述:    TODO </p>
	 * <p>创建时间:  2019年2月25日下午8:19:36 </p>
	 * @version 1.0
	 * @author lijunliang
	 * @return  
	 * Result
	 */
	@RequestMapping(value="/siginout")
	public Result logout() {
		Result rs = new Result();
		Subject subject = SecurityUtils.getSubject();
        try {
            subject.logout();
            LOGGER.info("用户{}登出系统",subject.getPrincipal());
        } catch (SessionException ise) {
        	LOGGER.debug("登出错误:{}", ise);
        }
		rs.setCode(ApiCodeEnum.SUCCESS);
		return rs;
	}
	
	/**
	 * 
	 * <p>方法名:  requestSnsLogins </p> 
	 * <p>描述:    TODO </p>
	 * <p>创建时间:  2019年2月25日下午8:19:39 </p>
	 * @version 1.0
	 * @author lijunliang
	 * @return  
	 * Result
	 */
	@RequestMapping("snsLogins")
	public Result requestSnsLogins() {
		Result result = new Result();
		//test
		result.setCode(ApiCodeEnum.SUCCESS);
		List<String> code = new ArrayList<>();
		code.add("test");
		result.setData(code);
		return result;
	}
	/**
	 * 
	 * <p>方法名:  createAuthToken </p> 
	 * <p>描述:    TODO </p>
	 * <p>创建时间:  2019年2月25日下午8:14:27 </p>
	 * @version 1.0
	 * @author lijunliang
	 * @param username
	 * @param password
	 * @param host
	 * @return  
	 * AuthenticationToken
	 */
	private AuthenticationToken createAuthToken(String username, String password, String host) {
		return new UsernamePasswordToken(username, password, true, host);
	}
	
}
