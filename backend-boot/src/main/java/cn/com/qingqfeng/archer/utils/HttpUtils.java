/**
 * 
 */
package cn.com.qingqfeng.archer.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**   
 * <p> 类名：  HttpUtils   </p>
 * <p> 描述：	TODO  </p>
 * <p> 创建时间： 2019年3月24日 上午3:21:55   </p>
 * @author lijunliang 
 * @version 1.0   
 */
public class HttpUtils {
	
    public static String client(String url, HttpMethod method, Map<String, String> params){
        RestTemplate client = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
        acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
        headers.setAccept(acceptableMediaTypes);
        HttpEntity<Map<String,String>> requestEntity = new HttpEntity<Map<String,String>>(null, headers);
        //  执行HTTP请求
        url+=jsonToUrlParams(params);
        ResponseEntity<String> response = client.exchange(url, method, requestEntity, String.class);
        return response.getBody();
    }
    
    private static String jsonToUrlParams(Map<String, String> params){
        StringBuilder sb = new StringBuilder();
        int i =0;
        for(String key : params.keySet()){
        	if(i==0){
        		sb.append("?").append(key).append("=").append(params.get(key));
        	}else{
        		sb.append("&").append(key).append("=").append(params.get(key));
        	}
            i++;
        }
        return sb.toString();
    }
}
