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
public class ArcherToken implements AuthenticationToken{
	/**
	 * @FieldsserialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
    private String tid;// 客户标识（可以是用户名、app id等等）
    private String digest;// 消息摘要
    private long timestamp;// 时间戳
    private Map<String, String> parameters;// 访问参数
    private String host;// 客户端IP
    private String os;
    
    public ArcherToken(String tid, String digest, long timestamp, String host, String os,
    		Map<String, String> parameters) {
    	this.tid = tid;
    	this.digest = digest;
    	this.timestamp = timestamp;
    	this.host = host;
    	this.os = os;
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
		return this.tid;
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

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
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

	public Map<String, String> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, String> parameters) {
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
}
