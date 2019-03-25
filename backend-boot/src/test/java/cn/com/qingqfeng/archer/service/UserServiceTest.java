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
	}
	@Test
	public void updateTest(){
		UserDTO user = this.userService.requestUserByName("zankokutenshi@yeah.net");
		UserDTO newuser = new UserDTO();
		newuser.setId(user.getId());
		newuser.setNickname("青青子");
		this.userService.updateUser(newuser);
	}

}

