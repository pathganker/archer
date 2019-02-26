/**
 * 
 */
package cn.com.qingqfeng.archer.shiro.token;

import java.util.Map;

import org.apache.shiro.authc.AuthenticationToken;

/**   
 * <p> 类名：  ArcherToken   </p>
 * <p> 描述：	TODO  </p>
 * <p> 创建时间： 2019年2月26日 上午12:35:21   </p>
 * @author lijunliang 
 * @version 1.0   
 */
public class HmacToken implements AuthenticationToken{
	/**
	 * @FieldsserialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
    private String clientKey;// 客户标识（可以是用户名、app id等等）
    private String digest;// 消息摘要
    private long timestamp;// 时间戳
    private Map<String, String[]> parameters;// 访问参数
    private String host;// 客户端IP
    private String os;
    private String browser;
    
    public HmacToken(String clientKey, String digest, long timestamp, String host, String os, String browser,
    		Map<String, String[]> parameters) {
    	this.clientKey = clientKey;
    	this.digest = digest;
    	this.timestamp = timestamp;
    	this.host = host;
    	this.os = os;
    	this.setBrowser(browser);
    	this.parameters = parameters;
    }

	/**   
	 * <p>Title: getPrincipal</p>   
	 * <p>Description: </p>   
	 * @return   
	 * @see org.apache.shiro.authc.AuthenticationToken#getPrincipal()   
	 */
	@Override
	public Object getPrincipal() {
		return this.clientKey;
	}

	/**   
	 * <p>Title: getCredentials</p>   
	 * <p>Description: </p>   
	 * @return   
	 * @see org.apache.shiro.authc.AuthenticationToken#getCredentials()   
	 */
	@Override
	public Object getCredentials() {
		return this.digest;
	}


	public String getClientKey() {
		return clientKey;
	}

	public void setClientKey(String clientKey) {
		this.clientKey = clientKey;
	}

	public String getDigest() {
		return digest;
	}

	public void setDigest(String digest) {
		this.digest = digest;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public Map<String, String[]> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, String[]> parameters) {
		this.parameters = parameters;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}
}
