/**   */
package cn.com.qingqfeng.archer.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.apache.shiro.subject.Subject;
import cn.com.qingqfeng.archer.enums.ApiCodeEnum;
import cn.com.qingqfeng.archer.pojo.Result;
import cn.com.qingqfeng.archer.utils.ArcherWebUtils;


/**   
 * <p>类名称: JwtPermFilter </p> 
 * <p>描述: TODO  </p>
 * <p>创建时间 : 2019年2月26日 上午11:57:46 </p>
 * @author lijunliang
 * @version 1.0
 * 
 */
public class JwtRoleFilter extends HmacFilter{
	/**
	 * (non-Javadoc)
	 * @see cn.com.qingqfeng.archer.shiro.filter.HmacFilter#isAccessAllowed(javax.servlet.ServletRequest, javax.servlet.ServletResponse, java.lang.Object)
	 */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, 
                                                    Object mappedValue) throws Exception {
        Subject subject = getSubject(request, response);
        String[] roles = (String[]) mappedValue;
        boolean isPermitted = true;
        if (roles != null && roles.length > 0) {
            if (roles.length == 1) {
                if (!subject.hasRole(roles[0])) {
                	Result rs = new Result();
                	rs.setCode(ApiCodeEnum.API_AUTHORITY);
                	rs.setMessage("没有权限");
                	ArcherWebUtils.handlerReturnJSON(response, rs);
                    isPermitted = false;
                }
            } else {
//                if (!subject.isPermittedAll(perms)) {
//                	WebUtils.toHttp(response).sendError(HttpServletResponse.SC_UNAUTHORIZED,"没有权限");
//                    isPermitted = false;
//                }
            }
        }
        return isPermitted;
    }
}
