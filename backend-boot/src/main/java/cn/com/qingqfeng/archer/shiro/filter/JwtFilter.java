/**   */
package cn.com.qingqfeng.archer.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.qingqfeng.archer.enums.ApiCodeEnum;
import cn.com.qingqfeng.archer.pojo.Result;
import cn.com.qingqfeng.archer.shiro.token.JwtToken;
import cn.com.qingqfeng.archer.utils.ArcherWebUtils;

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
    
    public static final String DEFAULT_JWT_PARAM = "Auth-jwt";

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
		Result rs = new Result();
        if(isJwtSubmission(request)){
            AuthenticationToken token = createToken(request, response);
            try {
                Subject subject = getSubject(request, response);
                subject.login(token);
                return true;
            } catch (AuthenticationException e) {
                LOG.error(e.getMessage());
                //WebUtils.toHttp(response).sendError(HttpServletResponse.SC_UNAUTHORIZED,"Token错误");
                rs.setCode(ApiCodeEnum.TOKEN_WRONG);
                rs.setMessage("Token错误");
                ArcherWebUtils.handlerReturnJSON(response, rs);
                return false;
            } 
        }
        //不处理OPTIONS
        HttpServletRequest req = (HttpServletRequest)request;
        if("OPTIONS".equals(req.getMethod())){
        	return false;
        }
        //WebUtils.toHttp(response).sendError(HttpServletResponse.SC_UNAUTHORIZED,"Token丢失");
        rs.setCode(ApiCodeEnum.TOKEN_LOST);
        rs.setMessage("Token丢失");
        ArcherWebUtils.handlerReturnJSON(response, rs);
        return false;
	}
	
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
    	HttpServletRequest req = (HttpServletRequest)request;
        String jwt = req.getHeader(DEFAULT_JWT_PARAM);
        String host = req.getRemoteHost();
        LOG.debug("authenticate jwt token:"+jwt);
        return new JwtToken(jwt, host);
    }
    
    protected boolean isJwtSubmission(ServletRequest request) {
    	HttpServletRequest req = (HttpServletRequest)request;
        String jwt = req.getHeader(DEFAULT_JWT_PARAM);
        return StringUtils.isNotBlank(jwt);
    }
    

}
