/**   */
package cn.com.qingqfeng.archer.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;

/**   
 * <p>类名称: UtilsTest </p> 
 * <p>描述: TODO  </p>
 * <p>创建时间 : 2019年3月21日 下午5:30:15 </p>
 * @author lijunliang
 * @version 1.0
 * 
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UtilsTest {
	@Test
	public void pictureUtilsTest(){
		String imageUrl = "E:/ROOT/upload/picture/cover/5700b6db-d0c8-465a-88e7-b04464285d05/20190321111501_cover.png";
		try {
			PictureUtils.getPreview(imageUrl);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
