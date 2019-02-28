/**   */
package cn.com.qingqfeng.archer.pojo.user;

import java.io.Serializable;

/**   
 * <p>类名称: UserVO </p> 
 * <p>描述: TODO  </p>
 * <p>创建时间 : 2019年2月28日 下午7:26:56 </p>
 * @author lijunliang
 * @version 1.0
 * 
 */
public class UserVO implements Serializable{

	/** */
	private static final long serialVersionUID = 1L;
	
	/** 员工ID */
	private String id;
	/** 登录名*/
	private String username;
	/** 用户名*/
	private String nickname;
	/** 联系电话 */
	private String telephone;
	/** 部门 */
	private String depart;
	/** 主页 */
	private String defaultUrl;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getDepart() {
		return depart;
	}
	public void setDepart(String depart) {
		this.depart = depart;
	}
	public String getDefaultUrl() {
		return defaultUrl;
	}
	public void setDefaultUrl(String defaultUrl) {
		this.defaultUrl = defaultUrl;
	}
	
	
}
