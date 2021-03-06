/**
 * 
 */
package cn.com.qingqfeng.archer.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.qingqfeng.archer.pojo.Result;

import com.alibaba.fastjson.JSON;

/**   
 * <p> 类名：  WebUtils   </p>
 * <p> 描述：	TODO  </p>
 * <p> 创建时间： 2019年2月23日 下午8:42:37   </p>
 * @author lijunliang 
 * @version 1.0   
 */
public class ArcherWebUtils {
	
	private static final Logger	LOGGER = LoggerFactory.getLogger(ArcherWebUtils.class);
	/**
	 * 
	 * <p> 方法名 ：getRemoteIpAddr  </p> 
	 * <p> 描述 ：TODO </p> 
	 * <p> 创建时间  ： 2019年2月23日 下午8:42:59 </p>  
	 * @param request
	 * @return           
	 * @author lijunliang 
	 * @version 1.0
	 */
	public static String getRemoteIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	/**
	 * 
	 * <p>方法名:  handlerReturnJSON </p> 
	 * <p>描述:    TODO </p>
	 * <p>创建时间:  2019年2月27日上午9:54:22 </p>
	 * @version 1.0
	 * @author lijunliang
	 * @param response
	 * @param rt  
	 * void
	 */
	public static void handlerReturnJSON(ServletResponse response, Result rs) {
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.append(JSON.toJSONString(rs));
		} catch (IOException e) {
			LOGGER.error("返回错误码异常:{}", e);		
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

}
