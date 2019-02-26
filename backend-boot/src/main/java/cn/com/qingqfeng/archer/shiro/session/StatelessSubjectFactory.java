/**
 * 
 */
package cn.com.qingqfeng.archer.shiro.session;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;

import cn.com.qingqfeng.archer.shiro.token.HmacToken;
import cn.com.qingqfeng.archer.shiro.token.JwtToken;

/**   
 * <p> 类名：  StatelessFactory   </p>
 * <p> 描述：	TODO  </p>
 * <p> 创建时间： 2019年2月26日 上午12:30:53   </p>
 * @author lijunliang 
 * @version 1.0   
 */
public class StatelessSubjectFactory extends DefaultWebSubjectFactory{
	@Override
    public Subject createSubject(SubjectContext context) { 
        AuthenticationToken token = context.getAuthenticationToken();
        if((token instanceof HmacToken) || (token) instanceof JwtToken){
            // 当token为HmacToken时， 不创建 session 
            context.setSessionCreationEnabled(false);
        }
        return super.createSubject(context); 
    }
}
