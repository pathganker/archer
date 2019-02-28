/**   */
package cn.com.qingqfeng.archer.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.com.qingqfeng.archer.pojo.user.UserDTO;
import cn.com.qingqfeng.archer.service.user.IUserService;

/**   
 * <p>类名称: UserServiceTest </p> 
 * <p>描述: TODO  </p>
 * <p>创建时间 : 2019年2月28日 下午7:05:50 </p>
 * @author lijunliang
 * @version 1.0
 * 
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

	
	@Autowired 
	private IUserService userService;
	
	@Test
	public void addTest() {
		UserDTO user = new UserDTO();
		user.setUsername("zankokutenshi@yeah.net");
		user.setPassword("cankutianshi");
		this.userService.addUser(user);
	}

}

