/**   */
package cn.com.qingqfeng.archer.service.user.impl;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.qingqfeng.archer.dao.like.ILikeDao;
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
	private ILikeDao likeDao;
	
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

	/** (non-Javadoc)
	 * @see cn.com.qingqfeng.archer.service.user.IUserService#requestUserLikeByUserId(java.lang.String)
	 */
	@Override
	public List<String> requestUserLikeByUserId(String id) {
		return this.likeDao.queryLikeByUserId(id);
	}

	/** (non-Javadoc)
	 * @see cn.com.qingqfeng.archer.service.user.IUserService#addThirdParty(cn.com.qingqfeng.archer.pojo.user.UserDTO)
	 */
	@Override
	public void addThirdParty(UserDTO user) {
		if(null == user || StringUtils.isBlank(user.getId())){
			return;
		}
		UserDO udo = new UserDO();
		BeanUtils.copyProperties(user, udo);
		this.userDao.insert(udo);
	}

	/** (non-Javadoc)
	 * @see cn.com.qingqfeng.archer.service.user.IUserService#checkUserExist(java.lang.String)
	 */
	@Override
	public Boolean checkUserExist(String id) {
		if(StringUtils.isBlank(id)){
			return false;
		}
		UserDO user = this.userDao.queryUserById(id);
		if(null == user || !id.equals(user.getId())){
			return false;
		}
		return true;
	}

	/** (non-Javadoc)
	 * @see cn.com.qingqfeng.archer.service.user.IUserService#requestUserById(java.lang.String)
	 */
	@Override
	public UserDTO requestUserById(String id) {
		UserDTO result = new UserDTO();
		if(StringUtils.isBlank(id)){
			return result;
		}
		UserDO user = this.userDao.queryUserById(id);
		if(null == user){
			return result;
		}
		BeanUtils.copyProperties(user, result);
		return result;
	}

}
