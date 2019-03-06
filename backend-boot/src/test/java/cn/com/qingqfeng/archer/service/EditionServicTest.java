/**   */
package cn.com.qingqfeng.archer.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cn.com.qingqfeng.archer.pojo.edition.EditionDTO;
import cn.com.qingqfeng.archer.service.edition.IEditionService;

import org.springframework.test.context.junit4.SpringRunner;

/**   
 * <p>类名称: EditionServicTest </p> 
 * <p>描述: TODO  </p>
 * <p>创建时间 : 2019年3月6日 下午4:25:10 </p>
 * @author lijunliang
 * @version 1.0
 * 
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EditionServicTest {
	@Autowired
	private IEditionService editionService;
	
	@Test
	public void queryTest(){
		List<EditionDTO> editions = this.editionService.requestEditionsByUserId("bdbdd26f-5df8-4b4a-9b07-69a8b22ac327");
		System.out.println(editions);
	}
}
