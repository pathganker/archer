/**   */
package cn.com.qingqfeng.archer.webapi.controller.auth;

import io.jsonwebtoken.SignatureAlgorithm;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.support.DelegatingSubject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.qingqfeng.archer.enums.ApiCodeEnum;
import cn.com.qingqfeng.archer.pojo.Result;
import cn.com.qingqfeng.archer.pojo.user.UserDTO;
import cn.com.qingqfeng.archer.service.user.IUserService;
import cn.com.qingqfeng.archer.shiro.service.CryptogramService;
import cn.com.qingqfeng.archer.utils.JwtUtils;

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
	
	@Autowired
	private IUserService userService;
	@Autowired
	private CryptogramService cryptogramService;
	
	//private final static  Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
	 /**
	  * 
	  * <p> 方法名 ：checkToken  </p> 
	  * <p> 描述 ：TODO </p> 
	  * <p> 创建时间  ： 2019年3月9日 下午2:47:52 </p>  
	  * @return           
	  * @author lijunliang 
	  * @version 1.0
	  */
	 @RequestMapping(value="check",method= {RequestMethod.GET})
	 public Result checkToken() {
		 Result rs = new Result();
		 rs.setCode(ApiCodeEnum.SUCCESS);
		 return rs;
	 }
	
	/**
	 * 
	 * <p>方法名:  Signin </p> 
	 * <p>描述:    TODO </p>
	 * <p>创建时间:  2019年2月28日下午3:29:47 </p>
	 * @version 1.0
	 * @author lijunliang
	 * @param user
	 * @return  
	 * Result
	 */
	@RequestMapping(value="signin", method={RequestMethod.POST})
	public Result Siginin(@RequestBody UserDTO user) {
		Result rs = new Result();
		//简单参数校验
		if(null == user || StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword())){
			rs.setCode(ApiCodeEnum.ARGS_WRONG);
			return rs;
		}
		UserDTO comrade = this.userService.requestUserByName(user.getUsername());
		//用户名错误
		if(null == comrade){
			rs.setCode(ApiCodeEnum.USER_NAME);
			return rs;
		}
		//校验密码
		//传递盐值
		user.setSalt(comrade.getSalt());
		user.setId(comrade.getId());
		if(this.cryptogramService.passwordsMatch(user, comrade.getPassword())){
			rs.setCode(ApiCodeEnum.SUCCESS);
			DelegatingSubject test=  (DelegatingSubject) SecurityUtils.getSubject();
			String host = test.getHost();
			String jwt = JwtUtils.issueJwt(comrade.getId(), user.getUsername(), 
					"token-server", 24*3600*1000*7L, "ordinary,admin", "create", host, SignatureAlgorithm.HS256);
	        Map<String, Object> data = new LinkedHashMap<String, Object>();
	        data.put("jwt", jwt);
	        rs.setData(data);
		}else{
			//密码错误
			rs.setCode(ApiCodeEnum.USER_PASSWORD);
		}
		return rs;
	}


	
	
}
