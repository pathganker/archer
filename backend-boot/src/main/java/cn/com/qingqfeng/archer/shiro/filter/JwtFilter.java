/**   */
package cn.com.qingqfeng.archer.shiro.filter;

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

import cn.com.qingqfeng.archer.shiro.token.JwtToken;

/**   
 * <p>类名称: JwtFilter </p> 
 * <p>描述: TODO  </p>
 * <p>创建时间 : 2019年2月26日 上午11:54:05 </p>
 * @author lijunliang
 * @version 1.0
 * 
 */
public class JwtFilter extends AccessControlFilter {
	
    private static final Logger LOG = LoggerFactory.getLogger(JwtFilter.class);
    
    public static final String DEFAULT_JWT_PARAM = "jwt";

	/** (non-Javadoc)
	 * @see org.apache.shiro.web.filter.AccessControlFilter#isAccessAllowed(javax.servlet.ServletRequest, javax.servlet.ServletResponse, java.lang.Object)
	 */
	@Override
	protected boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object mappedValue) throws Exception {
        if (null != getSubject(request, response) 
                && getSubject(request, response).isAuthenticated()) {
            return true;
        }
        return false;
	}

	/** (non-Javadoc)
	 * @see org.apache.shiro.web.filter.AccessControlFilter#onAccessDenied(javax.servlet.ServletRequest, javax.servlet.ServletResponse)
	 */
	@Override
	protected boolean onAccessDenied(ServletRequest request,
			ServletResponse response) throws Exception {
        if(isJwtSubmission(request)){
            AuthenticationToken token = createToken(request, response);
            try {
                Subject subject = getSubject(request, response);
                subject.login(token);
                return true;
            } catch (AuthenticationException e) {
                LOG.error(e.getMessage());
                WebUtils.toHttp(response).sendError(HttpServletResponse.SC_UNAUTHORIZED,"Token错误");
                return false;
            } 
        }
        WebUtils.toHttp(response).sendError(HttpServletResponse.SC_UNAUTHORIZED,"Token丢失");
        return false;
	}
	
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        String jwt = request.getParameter(DEFAULT_JWT_PARAM);
        String host = request.getRemoteHost();
        LOG.debug("authenticate jwt token:"+jwt);
        return new JwtToken(jwt, host);
    }
    
    protected boolean isJwtSubmission(ServletRequest request) {
        String jwt = request.getParameter(DEFAULT_JWT_PARAM);
        return (request instanceof HttpServletRequest)
                                && StringUtils.isNotBlank(jwt);
    }
    

}
