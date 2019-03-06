/**   */
package cn.com.qingqfeng.archer.service.user.impl;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.qingqfeng.archer.dao.user.IUserDao;
import cn.com.qingqfeng.archer.pojo.user.UserDO;
import cn.com.qingqfeng.archer.pojo.user.UserDTO;
import cn.com.qingqfeng.archer.service.user.IUserService;
import cn.com.qingqfeng.archer.shiro.service.CryptogramService;

/**   
 * <p>类名称: UserServiceImpl </p> 
 * <p>描述: TODO  </p>
 * <p>创建时间 : 2019年2月23日 下午3:01:29 </p>
 * @author lijunliang
 * @version 1.0
 * 
 */
@Service
public class UserServiceImpl implements IUserService{
	
	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private CryptogramService cryptogramService;

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

	/** (non-Javadoc)
	 * @see cn.com.qingqfeng.archer.service.user.IUserService#addUser(cn.com.qingqfeng.archer.pojo.user.UserDTO)
	 */
	@Override
	public void addUser(UserDTO user) {
		if(null == user){
			return;
		}
		user.setCreateTime(new Date());
		user.setModifyTime(new Date());
		user.setId(UUID.randomUUID().toString());
		user.setSalt(UUID.randomUUID().toString());
		String password = cryptogramService.encryptPassword(user);
		user.setPassword(password);
		UserDO comrade = new UserDO();
		BeanUtils.copyProperties(user, comrade);
		this.userDao.insert(comrade);
	}

	/** (non-Javadoc)
	 * @see cn.com.qingqfeng.archer.service.user.IUserService#updateUser(cn.com.qingqfeng.archer.pojo.user.UserDTO)
	 */
	@Override
	public void updateUser(UserDTO user) {
		if(null == user || StringUtils.isBlank(user.getId())){
			return;
		}
		//更改密码
		if(StringUtils.isNotBlank(user.getPassword())){
			user.setSalt(UUID.randomUUID().toString());
			String password = cryptogramService.encryptPassword(user);
			user.setPassword(password);
		}
		UserDO comrade = new UserDO();
		BeanUtils.copyProperties(user, comrade);
		this.userDao.update(comrade);
	}

}
