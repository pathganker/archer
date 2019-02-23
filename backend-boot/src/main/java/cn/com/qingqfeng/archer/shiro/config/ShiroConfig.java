/**   */
package cn.com.qingqfeng.archer.shiro.config;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;

import cn.com.qingqfeng.archer.service.user.impl.UserServiceImpl;
import cn.com.qingqfeng.archer.shiro.realm.UserRealm;
import cn.com.qingqfeng.archer.shiro.session.SessionDao;
import cn.com.qingqfeng.archer.shiro.session.ShiroSessionFactory;

/**   
 * <p>类名称: ShiroConfig </p> 
 * <p>描述: TODO  </p>
 * <p>创建时间 : 2019年2月23日 下午2:29:14 </p>
 * @author lijunliang
 * @version 1.0
 * 
 */
@Configuration
public class ShiroConfig {
	@Autowired
	private RedisConnectionFactory factory;
	
    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
    	RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
    	template.setConnectionFactory(factory);
    	Jackson2JsonRedisSerializer<String> keySerializer = new Jackson2JsonRedisSerializer<String>(String.class);
    	Jackson2JsonRedisSerializer <SimpleSession> valueSerializer = new Jackson2JsonRedisSerializer<SimpleSession>(SimpleSession.class);
        template.setKeySerializer(keySerializer);
    	template.setValueSerializer(valueSerializer);
    	template.setHashKeySerializer(keySerializer);
    	template.setValueSerializer(valueSerializer);
        template.afterPropertiesSet();
        return template;
    }
    @Bean
	public SessionDao sessionDao(){
		SessionDao sessionDao = new SessionDao();
		Jackson2JsonRedisSerializer <SimpleSession> valueSerializer = new Jackson2JsonRedisSerializer<SimpleSession>(SimpleSession.class);
		sessionDao.setValueSerializer(valueSerializer);
		sessionDao.setRedisManager(redisTemplate());
		return sessionDao;
	}
	//cookie
    @Bean
	public SimpleCookie sessionIdCookie(){
		SimpleCookie sessionIdCookie = new SimpleCookie();
		sessionIdCookie.setName("sid");
		sessionIdCookie.setHttpOnly(true);
		sessionIdCookie.setMaxAge(180000);
		return sessionIdCookie;
	}
	@Bean
	public DefaultWebSessionManager sessionManager(){
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		sessionManager.setSessionDAO(sessionDao());
		sessionManager.setGlobalSessionTimeout(3600*1000*24);
		sessionManager.setDeleteInvalidSessions(true);
		sessionManager.setSessionValidationSchedulerEnabled(true);
		sessionManager.setSessionIdCookieEnabled(true);
		sessionManager.setSessionIdCookie(sessionIdCookie());
	//	sessionManager.setSessionFactory(sessionFactory());
		return sessionManager;	
	}
	
	//用户服务
	@Bean
	public UserServiceImpl userService(){
		return new UserServiceImpl();
	}
	
	@Bean
	public UserRealm userRealm(){
		UserRealm userRealm = new UserRealm();
		userRealm.setUserService(userService());
//		userRealm.setCredentialsMatcher(credentialsMatcher());
//		userRealm.setCachingEnabled(true);
//		userRealm.setCacheManager(cacheManager());
//		userRealm.setAuthenticationCachingEnabled(true);
//		userRealm.setAuthenticationCacheName("authenticationCache");
		return userRealm;
	}
	
	@Bean
	public ShiroSessionFactory sessionFactory(){
		return new ShiroSessionFactory();
	}
	@Bean
	public SecurityManager securityManager(){
		DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
		securityManager.setRealm(userRealm());
		securityManager.setSessionManager(sessionManager());
		//securityManager.setCacheManager(cacheManager());
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
