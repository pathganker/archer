/**   */
package cn.com.qingqfeng.archer.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

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
			PictureUtils.getPreview(imageUrl,40,40);
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
	
	@Test
	public void HttpTest(){
        RestTemplate client = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        Map<String, String> params = new HashMap<String, String>();
        params.put("username","test");
        params.put("password", "hello");
        HttpEntity<Map<String,String>> requestEntity = new HttpEntity<Map<String,String>>(params, headers);
        //  执行HTTP请求
        ResponseEntity<String> response = client.exchange("http://127.0.0.1:7000/login/siginin", HttpMethod.POST, requestEntity, String.class);
        String result = response.getBody();
        System.out.println(result); 
		
	}
}
