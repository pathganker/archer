/**   */
package cn.com.qingqfeng.archer.shiro.filter;

import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.qingqfeng.archer.shiro.token.HmacToken;
import eu.bitwalker.useragentutils.UserAgent;

/**   
 * <p>类名称: HmacFilter </p> 
 * <p>描述: TODO  </p>
 * <p>创建时间 : 2019年2月26日 上午10:24:59 </p>
 * @author lijunliang
 * @version 1.0
 * 
 */
public class HmacFilter extends AccessControlFilter{
	
    private static final Logger LOG = LoggerFactory.getLogger(HmacFilter.class);
    
    public static final String DEFAULT_CLIENTKEY_PARAM = "clientKey";
    public static final String DEFAULT_TIMESTAMP_PARAM = "timestamp";
    public static final String DEFAUL_DIGEST_PARAM = "digest";

	/** (non-Javadoc)
	 * @see org.apache.shiro.web.filter.AccessControlFilter#isAccessAllowed(javax.servlet.ServletRequest, javax.servlet.ServletResponse, java.lang.Object)
	 */
	@Override
	protected boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object mappedValue) throws Exception {
		
        if (null != getSubject(request, response) 
                && getSubject(request, response).isAuthenticated()) {
            return true;//已经认证过直接放行
        }
        return false;//转到拒绝访问处理逻辑
	}

	/** (non-Javadoc)
	 * @see org.apache.shiro.web.filter.AccessControlFilter#onAccessDenied(javax.servlet.ServletRequest, javax.servlet.ServletResponse)
	 */
	@Override
	protected boolean onAccessDenied(ServletRequest request,
			ServletResponse response) throws Exception {
        if(isHmacSubmission(request)){//如果是Hmac鉴权的请求
            //创建令牌
            AuthenticationToken token = createToken(request, response);
            try {
                Subject subject = getSubject(request, response);
                subject.login(token);//认证
                return true;//认证成功，过滤器链继续
            } catch (AuthenticationException e) {//认证失败，发送401状态并附带异常信息
                LOG.error(e.getMessage());
                WebUtils.toHttp(response).sendError(HttpServletResponse.SC_UNAUTHORIZED,e.getMessage());
            }
        }
        return false;//打住，访问到此为止
	}
	
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        String clientKey = request.getParameter(DEFAULT_CLIENTKEY_PARAM);
        String timestamp= request.getParameter(DEFAULT_TIMESTAMP_PARAM);
        String digest= request.getParameter(DEFAUL_DIGEST_PARAM);
        Map<String, String[]> parameters = request.getParameterMap();
        String host = request.getRemoteHost();
        HttpServletRequest req = (HttpServletRequest) request;
        UserAgent userAgent = UserAgent.parseUserAgentString(req.getHeader("User-Agent"));
        // 获取客户端操作系统
        String os = userAgent.getOperatingSystem().getName();
        // 获取客户端浏览器
        String browser = userAgent.getBrowser().getName();
        return new HmacToken(clientKey, digest, Long.parseLong(timestamp), host, os, browser, parameters);
    }
    
    protected boolean isHmacSubmission(ServletRequest request) {
        String clientKey = request.getParameter(DEFAULT_CLIENTKEY_PARAM);
        String timestamp= request.getParameter(DEFAULT_TIMESTAMP_PARAM);
        Long time = null;
        try {
        	time = Long.parseLong(timestamp);
        }catch(Exception e){
        	time = null;
        }
        String digest= request.getParameter(DEFAUL_DIGEST_PARAM);
        return (request instanceof HttpServletRequest)
                            && StringUtils.isNotBlank(clientKey)
                            && null != time
                            && StringUtils.isNotBlank(digest);
    }

}
