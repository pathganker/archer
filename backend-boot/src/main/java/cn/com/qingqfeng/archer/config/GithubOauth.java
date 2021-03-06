/**   */
package cn.com.qingqfeng.archer.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**   
 * <p>类名称: GithubOauth </p> 
 * <p>描述: TODO  </p>
 * <p>创建时间 : 2019年3月27日 下午3:59:03 </p>
 * @author lijunliang
 * @version 1.0
 * 
 */
@Component
@ConfigurationProperties(prefix = "oauth2.github")
public class GithubOauth {
	private String clientId;
	private String clientSecret;
	private String redirectUri;
	private String authorizationUri;
	private String tokenUri;
	private String userInfoUri;
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getClientSecret() {
		return clientSecret;
	}
	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}
	public String getRedirectUri() {
		return redirectUri;
	}
	public void setRedirectUri(String redirectUri) {
		this.redirectUri = redirectUri;
	}
	public String getAuthorizationUri() {
		return authorizationUri;
	}
	public void setAuthorizationUri(String authorizationUri) {
		this.authorizationUri = authorizationUri;
	}
	public String getTokenUri() {
		return tokenUri;
	}
	public void setTokenUri(String tokenUri) {
		this.tokenUri = tokenUri;
	}
	public String getUserInfoUri() {
		return userInfoUri;
	}
	public void setUserInfoUri(String userInfoUri) {
		this.userInfoUri = userInfoUri;
	}
}
