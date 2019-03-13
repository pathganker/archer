/**
 * 
 */
package cn.com.qingqfeng.archer.webapi.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

/**   
 * @ClassName:  CorsFilter   
 * @Description:TODO
 * @author: lijunliang 
 * @date:   2019年1月27日 上午12:47:05   
 *     
 */
@Component
public class CorsFilter implements Filter{

	/**   
	 * <p>Title: init</p>   
	 * <p>Description: </p>   
	 * @param filterConfig
	 * @throws ServletException   
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)   
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	/**   
	 * <p>Title: doFilter</p>   
	 * <p>Description: </p>   
	 * @param request
	 * @param response
	 * @param chain
	 * @throws IOException
	 * @throws ServletException   
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)   
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse res = (HttpServletResponse) response;
		HttpServletRequest req = (HttpServletRequest) request;
		String[] whiteList= {"http://106.12.198.62","http://localhost:3000","http://localhost:5000"};
		String origin = req.getHeader("origin");
		Boolean isValid = false;
		for(String domain : whiteList) {
			if(domain.equals(origin)) {
				isValid = true;
			}
		}
		response.reset(); 
		if(isValid) {
			res.addHeader("Access-Control-Allow-Origin", origin);
		}
        //res.addHeader("Access-Control-Allow-Origin", "http://106.12.198.62");
        res.addHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS, TRACES");
        res.addHeader("Access-Control-Max-Age", "3600");
        res.addHeader("Access-Control-Allow-Headers", "content-type, Auth-clientKey, Auth-jwt");
        res.addHeader("Access-Control-Allow-Credentials", "true");
        chain.doFilter(request, response);
	}

	/**   
	 * <p>Title: destroy</p>   
	 * <p>Description: </p>      
	 * @see javax.servlet.Filter#destroy()   
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

}
