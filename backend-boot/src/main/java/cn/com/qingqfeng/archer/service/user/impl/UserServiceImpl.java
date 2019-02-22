/**   */
package cn.com.qingqfeng.archer.service.user.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import cn.com.qingqfeng.archer.service.user.IUserService;

/**   
 * <p>类名称: UserServcieImpl </p> 
 * <p>描述: TODO  </p>
 * <p>创建时间 : 2019年2月22日 下午8:44:04 </p>
 * @author lijunliang
 * @version 1.0
 * 
 */
public class UserServiceImpl implements IUserService{

	/** (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
