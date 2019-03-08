/**   */
package cn.com.qingqfeng.archer.service.user;

import java.util.List;
import java.util.Set;

import cn.com.qingqfeng.archer.pojo.user.UserDTO;


/**   
 * <p>类名称: IUserService </p> 
 * <p>描述: TODO  </p>
 * <p>创建时间 : 2019年2月23日 下午2:57:19 </p>
 * @author lijunliang
 * @version 1.0
 * 
 */
public interface IUserService {

	/**   
	 * <p>方法名:  requestRoleIdByName </p> 
	 * <p>描述:    TODO </p>
	 * <p>创建时间:  2019年2月23日下午2:57:57 </p>
	 * @version 1.0
	 * @author lijunliang
	 * @param username
	 * @return  
	 * Set<String> 
	 */
	Set<String> requestRoleIdByName(String username);

	/**   
	 * <p>方法名:  requestPermiUrlsByName </p> 
	 * <p>描述:    TODO </p>
	 * <p>创建时间:  2019年2月23日下午2:58:04 </p>
	 * @version 1.0
	 * @author lijunliang
	 * @param username
	 * @return  
	 * Set<String> 
	 */
	Set<String> requestPermiUrlsByName(String username);

	/**   
	 * <p>方法名:  requestUserByName </p> 
	 * <p>描述:    TODO </p>
	 * <p>创建时间:  2019年2月23日下午2:58:12 </p>
	 * @version 1.0
	 * @author lijunliang
	 * @param username
	 * @return  
	 * UserDTO 
	 */
	UserDTO requestUserByName(String username);
	/**
	 * 
	 * <p>方法名:  addUser </p> 
	 * <p>描述:    TODO </p>
	 * <p>创建时间:  2019年3月7日下午1:42:28 </p>
	 * @version 1.0
	 * @author lijunliang
	 * @param user  
	 * void
	 */
	void addUser(UserDTO user);
	/**
	 * 
	 * <p>方法名:  updateUser </p> 
	 * <p>描述:    TODO </p>
	 * <p>创建时间:  2019年3月7日下午1:42:31 </p>
	 * @version 1.0
	 * @author lijunliang
	 * @param user  
	 * void
	 */
	void updateUser(UserDTO user);
	/**
	 * 
	 * <p>方法名:  requestUserLikeByUserId </p> 
	 * <p>描述:    TODO </p>
	 * <p>创建时间:  2019年3月7日下午1:46:37 </p>
	 * @version 1.0
	 * @author lijunliang
	 * @param id
	 * @return  
	 * List<String>
	 */
	List<String> requestUserLikeByUserId(String id);
	
}
