/**
 * 
 */
package cn.com.qingqfeng.archer.utils;

import javax.servlet.http.HttpServletRequest;

/**   
 * <p> 类名：  WebUtils   </p>
 * <p> 描述：	TODO  </p>
 * <p> 创建时间： 2019年2月23日 下午8:42:37   </p>
 * @author lijunliang 
 * @version 1.0   
 */
public class WebUtils {
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

}
