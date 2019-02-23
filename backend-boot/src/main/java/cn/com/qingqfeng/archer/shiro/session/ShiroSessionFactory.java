/**   */
package cn.com.qingqfeng.archer.shiro.session;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.session.mgt.SessionFactory;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.web.session.mgt.WebSessionContext;


/**   
 * <p>类名称: SessionFactory </p> 
 * <p>描述: TODO  </p>
 * <p>创建时间 : 2019年2月23日 下午4:02:28 </p>
 * @author lijunliang
 * @version 1.0
 * 
 */
public class ShiroSessionFactory implements SessionFactory{

	/** (non-Javadoc)
	 * @see org.apache.shiro.session.mgt.SessionFactory#createSession(org.apache.shiro.session.mgt.SessionContext)
	 */
	@Override
	public Session createSession(SessionContext initData) {
        SimpleSession session = new SimpleSession();
        if (initData != null && initData instanceof WebSessionContext)
        {
            WebSessionContext sessionContext = (WebSessionContext) initData;
            HttpServletRequest request = (HttpServletRequest) sessionContext.getServletRequest();
            if (request != null)
            {
//                UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
//                // 获取客户端操作系统
//                String os = userAgent.getOperatingSystem().getName();
//                // 获取客户端浏览器
//                String browser = userAgent.getBrowser().getName();
//                session.setHost(IpUtils.getIpAddr(request));
//                session.setBrowser(browser);
//                session.setOs(os);
            }
        }
        return session;
	}

}
