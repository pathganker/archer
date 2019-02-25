/**   */
package cn.com.qingqfeng.archer.shiro.config;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.com.qingqfeng.archer.service.user.impl.UserServiceImpl;
import cn.com.qingqfeng.archer.shiro.realm.UserRealm;
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
	
	@Bean
	public DefaultSessionStorageEvaluator sessionStorageEvaluator(){
		DefaultSessionStorageEvaluator sessionStorageEvaluator = new DefaultSessionStorageEvaluator();
		sessionStorageEvaluator.setSessionStorageEnabled(false);
		return sessionStorageEvaluator;
	}
	
    @Bean
    public DefaultSubjectDAO subjectDao(){
    	DefaultSubjectDAO subjectDao = new DefaultSubjectDAO();
    	subjectDao.setSessionStorageEvaluator(sessionStorageEvaluator());
    	return subjectDao;
    }
    @Bean
    public StatelessSubjectFactory subjectFactory() {
    	return new StatelessSubjectFactory();
    }
    
	@Bean
	public StatelessSessionManager sessionManager(){
		StatelessSessionManager sessionManager = new StatelessSessionManager();
		sessionManager.setSessionValidationSchedulerEnabled(false);
		return sessionManager;	
	}
	@Bean
	public UserServiceImpl userService(){
		return new UserServiceImpl();
	}
	@Bean
	public HashedCredentialsMatcher credentialsMatcher(){
		HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
		credentialsMatcher.setHashAlgorithmName("md5");
		credentialsMatcher.setHashIterations(2);
		credentialsMatcher.setStoredCredentialsHexEncoded(true);
		return credentialsMatcher;
	}
	@Bean
	public UserRealm userRealm(){
		UserRealm userRealm = new UserRealm();
		userRealm.setUserService(userService());
		userRealm.setCredentialsMatcher(credentialsMatcher());
//		userRealm.setCachingEnabled(true);
//		userRealm.setCacheManager(cacheManager());
//		userRealm.setAuthenticationCachingEnabled(true);
//		userRealm.setAuthenticationCacheName("authenticationCache");
		return userRealm;
	}
	
	@Bean
	public SecurityManager securityManager(){
		DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
		securityManager.setRealm(userRealm());
		securityManager.setSubjectDAO(subjectDao());
		securityManager.setSubjectFactory(subjectFactory());
		return securityManager;
	}
	
	@Bean
	public ShiroFilterFactoryBean shiroFilter() {
		ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
		shiroFilter.setSecurityManager(securityManager());
//		Map<String, Filter> filterMap = new LinkedHashMap<String, Filter>();
//		filterMap.put("kickout", kickoutFilter());
//		filterMap.put("url", urlFilter());
//		filterMap.put("anon", anon());
//		shiroFilter.setFilters(filterMap);
//		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
//		filterChainDefinitionMap.put("/login/**", "anon");
//		filterChainDefinitionMap.put("/**", "kickout,url");
//		shiroFilter.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilter;
	}
}
