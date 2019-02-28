/**
 * 
 */
package cn.com.qingqfeng.archer.webapi.controller.user;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.qingqfeng.archer.enums.ApiCodeEnum;
import cn.com.qingqfeng.archer.pojo.Result;
import cn.com.qingqfeng.archer.pojo.user.UserDTO;
import cn.com.qingqfeng.archer.pojo.user.UserVO;
import cn.com.qingqfeng.archer.service.user.IUserService;
import cn.com.qingqfeng.archer.shiro.token.JwtPlayload;

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
	
	@Autowired
	private IUserService userService;
	
	/**
	 * 
	 * <p>方法名:  getCurrentUser </p> 
	 * <p>描述:    TODO </p>
	 * <p>创建时间:  2019年2月28日下午6:06:18 </p>
	 * @version 1.0
	 * @author lijunliang
	 * @return  
	 * Result
	 */
	@RequestMapping(value="info/me", method={RequestMethod.GET})
	private Result getCurrentUser(){
		Result rs = new Result();
		JwtPlayload jwtPlayload = (JwtPlayload) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
		//TODO
		if(null == jwtPlayload) {
			rs.setCode(ApiCodeEnum.NO_RESULT);
			return rs;
		}
		UserDTO user = this.userService.requestUserByName(jwtPlayload.getUserId());
		UserVO userVO = new UserVO();
		BeanUtils.copyProperties(user, userVO);
		rs.setCode(ApiCodeEnum.SUCCESS);
		rs.setData(userVO);
		return rs;
	}
	
	

	
}
