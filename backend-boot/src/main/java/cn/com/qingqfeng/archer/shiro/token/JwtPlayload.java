/**   */
package cn.com.qingqfeng.archer.shiro.token;

import java.io.Serializable;
import java.util.Date;

/**   
 * <p>类名称: jwtPlayload </p> 
 * <p>描述: TODO  </p>
 * <p>创建时间 : 2019年2月26日 上午10:54:21 </p>
 * @author lijunliang
 * @version 1.0
 * 
 */
public class JwtPlayload implements Serializable{
	/** */
	private static final long serialVersionUID = 7849740744916214464L;
	private String id;
	private String userId;
	private String Issuer;
	private Date IssuedAt;
	private String audience;
	private String roles;
	private String perms;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getIssuer() {
		return Issuer;
	}
	public void setIssuer(String issuer) {
		Issuer = issuer;
	}
	public Date getIssuedAt() {
		return IssuedAt;
	}
	public void setIssuedAt(Date issuedAt) {
		IssuedAt = issuedAt;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	public String getPerms() {
		return perms;
	}
	public void setPerms(String perms) {
		this.perms = perms;
	}
	public String getAudience() {
		return audience;
	}
	public void setAudience(String audience) {
		this.audience = audience;
	}
}
