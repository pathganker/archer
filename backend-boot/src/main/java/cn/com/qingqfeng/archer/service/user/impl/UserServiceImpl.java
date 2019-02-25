/**   */
package cn.com.qingqfeng.archer.service.user.impl;

import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.qingqfeng.archer.dao.user.IUserDao;
import cn.com.qingqfeng.archer.pojo.user.UserDO;
import cn.com.qingqfeng.archer.pojo.user.UserDTO;
import cn.com.qingqfeng.archer.service.user.IUserService;

/**   
 * <p>类名称: UserServiceImpl </p> 
 * <p>描述: TODO  </p>
 * <p>创建时间 : 2019年2月23日 下午3:01:29 </p>
 * @author lijunliang
 * @version 1.0
 * 
 */
public class UserServiceImpl implements IUserService{
	
	@Autowired
	private IUserDao userDao;

	/** (non-Javadoc)
	 * @see cn.com.qingqfeng.archer.service.user.IUserService#requestRoleIdByName(java.lang.String)
	 */
	@Override
	public Set<String> requestRoleIdByName(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	/** (non-Javadoc)
	 * @see cn.com.qingqfeng.archer.service.user.IUserService#requestPermiUrlsByName(java.lang.String)
	 */
	@Override
	public Set<String> requestPermiUrlsByName(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	/** (non-Javadoc)
	 * @see cn.com.qingqfeng.archer.service.user.IUserService#requestUserByName(java.lang.String)
	 */
	@Override
	public UserDTO requestUserByName(String username) {
		UserDTO user = new UserDTO();
		UserDO userDO = this.userDao.queryUserByName(username);
		if(null == userDO){
			return user;
		}
		BeanUtils.copyProperties(userDO, user);
		return user;
	}

}
