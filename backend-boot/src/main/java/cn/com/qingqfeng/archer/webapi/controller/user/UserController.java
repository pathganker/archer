/**
 * 
 */
package cn.com.qingqfeng.archer.webapi.controller.user;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.qingqfeng.archer.enums.ApiCodeEnum;
import cn.com.qingqfeng.archer.pojo.Result;

/**   
 * <p> 类名：  LoginController   </p>
 * <p> 描述：	TODO  </p>
 * <p> 创建时间： 2019年2月17日 下午5:24:22   </p>
 * @author lijunliang 
 * @version 1.0   
 */
@RestController
@RequestMapping("users")
public class UserController {
	
	private final static String DEVICEID = "Auth-device";
	/** token的前缀 组成规则  + diviceId*/
	private final static String ARCHER_API_TOKEN_KEY_PREFIX = "archer.api.token.uid_";
	
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
	
	@RequestMapping(value="login", method={RequestMethod.GET,RequestMethod.POST})
	public Result requestCaptcha(HttpServletRequest req, HttpServletResponse res) {
		Result rs = new Result();
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String deviceId = req.getHeader(DEVICEID);
		HttpSession session = req.getSession(true);
		String token = RandomStringUtils.random(32, deviceId + System.currentTimeMillis() + ""); //生成token
		if("zankokutenshi@yeah.net".equals(username) && "123456".equals(password)){
			session.setAttribute("token", ARCHER_API_TOKEN_KEY_PREFIX+deviceId+token);
			rs.setData(token);
			rs.setCode(ApiCodeEnum.SUCCESS);
		}else{
			rs.setCode(ApiCodeEnum.USER_NAME_OR_PWD);
		}
		return rs;
	}
	
}
