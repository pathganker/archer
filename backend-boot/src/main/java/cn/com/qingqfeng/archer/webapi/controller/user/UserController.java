/**
 * 
 */
package cn.com.qingqfeng.archer.webapi.controller.user;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cn.com.qingqfeng.archer.enums.ApiCodeEnum;
import cn.com.qingqfeng.archer.enums.UserTypeEnum;
import cn.com.qingqfeng.archer.pojo.Result;
import cn.com.qingqfeng.archer.pojo.user.UserDTO;
import cn.com.qingqfeng.archer.pojo.user.UserVO;
import cn.com.qingqfeng.archer.service.user.IUserService;
import cn.com.qingqfeng.archer.shiro.token.JwtPlayload;
import cn.com.qingqfeng.archer.utils.FileUtils;
import cn.com.qingqfeng.archer.utils.JwtUtils;
import cn.com.qingqfeng.archer.utils.PictureUtils;

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
		String userId = jwtPlayload.getId();
		UserDTO user = this.userService.requestUserById(userId);
		if(null == userId || !userId.equals(user.getId())) {
			rs.setCode(ApiCodeEnum.NO_RESULT);
			return rs;
		}
		UserVO userVO = new UserVO();
		BeanUtils.copyProperties(user, userVO);
		List<String> likes = this.userService.requestUserLikeByUserId(userId);
		userVO.setLikes(likes);
		Map<String, Object> data = new HashMap<String, Object>();
		if(UserTypeEnum.ADMIN.getCode().equals(user.getType())){
			data.put("admin", true);
		}else{
			data.put("admin", false);
		}
		data.put("user", userVO);
		rs.setCode(ApiCodeEnum.SUCCESS);
		rs.setData(data);
		return rs;
	}
	/**
	 * 
	 * <p>方法名:  updateUser </p> 
	 * <p>描述:    TODO </p>
	 * <p>创建时间:  2019年3月27日下午12:37:58 </p>
	 * @version 1.0
	 * @author lijunliang
	 * @param user
	 * @return  
	 * Result
	 */
	@RequestMapping(value="info/new", method={RequestMethod.POST})
	private Result updateUser(@RequestBody UserDTO user){
		Result rs = new Result();
		if(null == user || StringUtils.isBlank(user.getId())){
			rs.setCode(ApiCodeEnum.ARGS_WRONG);
			return rs;
		}
		String userId = JwtUtils.getCurrentUserId();
		if(StringUtils.isBlank(userId)|| !userId.equals(user.getId())){
			rs.setCode(ApiCodeEnum.API_AUTHORITY);
			return rs;
		}
		user.setModifyTime(new Date());
		this.userService.updateUser(user);	
		rs.setCode(ApiCodeEnum.SUCCESS);
		return rs;
	}
	
	/**
	 * 
	 * <p>方法名:  uploadAvatar </p> 
	 * <p>描述:    TODO </p>
	 * <p>创建时间:  2019年3月27日下午2:04:07 </p>
	 * @version 1.0
	 * @author lijunliang
	 * @param picture
	 * @param id
	 * @return  
	 * Result
	 */
	@RequestMapping(value="upload/avatar/{id}", method={RequestMethod.POST})
	private Result uploadAvatar(MultipartFile picture, @PathVariable String id){
		Result rs = new Result();
		if(null == picture || StringUtils.isBlank(id)){
			rs.setCode(ApiCodeEnum.ARGS_WRONG);
			return rs;
		}
		String url = FileUtils.handleAvatar(picture, id);
		String avatar=null;
		try {
			avatar = PictureUtils.getPreview(url, 120, 120);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rs.setCode(ApiCodeEnum.SUCCESS);
		rs.setData(avatar);
		return rs;
	}
	
	

	
}
