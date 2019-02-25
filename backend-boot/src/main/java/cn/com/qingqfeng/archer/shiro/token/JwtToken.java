/**
 * 
 */
package cn.com.qingqfeng.archer.shiro.token;

import org.apache.shiro.authc.AuthenticationToken;

/**   
 * <p> 类名：  JwtToken   </p>
 * <p> 描述：	TODO  </p>
 * <p> 创建时间： 2019年2月26日 上午12:53:39   </p>
 * @author lijunliang 
 * @version 1.0   
 */
public class JwtToken implements AuthenticationToken{
    
    /**
	 * @FieldsserialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private String jwt;// json web token
    private String host;// 客户端IP
    
    public JwtToken(String jwt,String host){
        this.jwt = jwt;
        this.host = host;
    }

	/**   
	 * <p>Title: getPrincipal</p>   
	 * <p>Description: </p>   
	 * @return   
	 * @see org.apache.shiro.authc.AuthenticationToken#getPrincipal()   
	 */
	@Override
	public Object getPrincipal() {
		 return this.jwt;
	}

	/**   
	 * <p>Title: getCredentials</p>   
	 * <p>Description: </p>   
	 * @return   
	 * @see org.apache.shiro.authc.AuthenticationToken#getCredentials()   
	 */
	@Override
	public Object getCredentials() {
		return Boolean.TRUE;
	}

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}
	
}
