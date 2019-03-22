/**   */
package cn.com.qingqfeng.archer.utils;

import java.io.IOException;
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
	@Test
	public void jsonTest(){
		String[] data = {"478b174b-16ce-420b-b950-93a4ed04e914", "51bf6b7b-e0c6-4b80-9998-1abdba1d3648"};
		
		System.out.println(JSON.toJSONString(data));
		String json = JSON.toJSONString(data);
		
		List<String> param = JSON.parseArray(json,String.class);
		System.out.println(param);
	}
}
