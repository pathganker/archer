/**
 * 
 */
package cn.com.qingqfeng.archer.webapi.controller.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
	private final static  Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	private final static String DEVICEID = "Auth-device";
	/** token的前缀 组成规则  + diviceId*/
	private final static String ARCHER_API_TOKEN_KEY_PREFIX = "archer.api.token.uid_";
	

	
}
