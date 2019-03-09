/**   */
package cn.com.qingqfeng.archer.shiro.config;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.AnonymousFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.com.qingqfeng.archer.shiro.filter.JwtFilter;
import cn.com.qingqfeng.archer.shiro.filter.JwtRoleFilter;
import cn.com.qingqfeng.archer.shiro.realm.JwtRealm;
import cn.com.qingqfeng.archer.shiro.session.StatelessSessionManager;
import cn.com.qingqfeng.archer.shiro.session.StatelessSubjectFactory;

/**   
 * <p>类名称: StateLessShiroConfig </p> 
 * <p>描述: TODO  </p>
 * <p>创建时间 : 2019年2月25日 下午8:49:43 </p>
 * @author lijunliang
 * @version 1.0
 * 
 */
@Configuration
public class StatelessShiroConfig {
	
	private DefaultSessionStorageEvaluator sessionStorageEvaluator(){
		DefaultSessionStorageEvaluator sessionStorageEvaluator = new DefaultSessionStorageEvaluator();
		sessionStorageEvaluator.setSessionStorageEnabled(false);
		return sessionStorageEvaluator;
	}
	//subject dao
	private DefaultSubjectDAO subjectDao(){
    	DefaultSubjectDAO subjectDao = new DefaultSubjectDAO();
    	subjectDao.setSessionStorageEvaluator(sessionStorageEvaluator());
    	return subjectDao;
    }
    //subject factroy
	private StatelessSubjectFactory subjectFactory() {
    	return new StatelessSubjectFactory();
    }
    //session manager
	private StatelessSessionManager sessionManager(){
		StatelessSessionManager sessionManager = new StatelessSessionManager();
		sessionManager.setSessionValidationSchedulerEnabled(false);
		return sessionManager;	
	}
	
	private JwtRealm jwtRealm(){
		return new JwtRealm();
	}
	
//	private HmacRealm hmacRealm(){
//		HmacRealm hmacRealm = new HmacRealm();
//		hmacRealm.setCryptogramService(new CryptogramService());
//		hmacRealm.setUserService(userService);
//		return hmacRealm;
//	}
	private SecurityManager securityManager(){
		DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
//		List<Realm> realms = new ArrayList<Realm>();
//		realms.add(hmacRealm());
//		realms.add(jwtRealm());
//		securityManager.setRealms(realms);
		securityManager.setRealm(jwtRealm());
		securityManager.setSubjectDAO(subjectDao());
		securityManager.setSubjectFactory(subjectFactory());
		securityManager.setSessionManager(sessionManager());
		return securityManager;
	}
	
//	private MethodInvokingFactoryBean methodInvokingFactoryBean(){
//		MethodInvokingFactoryBean methodInvokingFactoryBean = new MethodInvokingFactoryBean();
//		methodInvokingFactoryBean.setStaticMethod("org.apache.shiro.SecurityUtils.setSecurityManager");
//		methodInvokingFactoryBean.setArguments(securityManager());
//		return methodInvokingFactoryBean;
//	}
	//filter
	private JwtRoleFilter jwtRoleFilter(){
		return new JwtRoleFilter();
	}
	private JwtFilter jwtFilter(){
		return new JwtFilter();
	}
	private AnonymousFilter anon(){
		return new AnonymousFilter(); 
	}
	@Bean
	public ShiroFilterFactoryBean shiroFilter() {
		ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
		shiroFilter.setSecurityManager(securityManager());
		Map<String, Filter> filterMap = new LinkedHashMap<String, Filter>();
		filterMap.put("jwt", jwtFilter());
		filterMap.put("role", jwtRoleFilter());
		filterMap.put("anon", anon());
		shiroFilter.setFilters(filterMap);
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
		filterChainDefinitionMap.put("/auth/captcha/**", "anon");
		filterChainDefinitionMap.put("/auth/jwttoken", "anon");
		filterChainDefinitionMap.put("/users/**", "jwt,role[admin]");
		filterChainDefinitionMap.put("/article/edition", "jwt,role[admin]");
		filterChainDefinitionMap.put("/article/backend/**", "jwt,role[admin]");
		filterChainDefinitionMap.put("/login/check/**", "jwt,role[admin]");
		filterChainDefinitionMap.put("/**", "jwt,role[ordinary]");
		shiroFilter.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilter;
	}
}
