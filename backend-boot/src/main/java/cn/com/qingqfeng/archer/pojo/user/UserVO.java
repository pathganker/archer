/**   */
package cn.com.qingqfeng.archer.pojo.user;

import java.io.Serializable;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

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
	/** 头像地址*/
	private String avatar;
	/** 邮件 */
	private String email;
	/** 喜欢 */
	private List<String> likes;
	/** 头像文件 */
	private MultipartFile file;
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
	public String getavatar() {
		return avatar;
	}
	public void setavatar(String avatar) {
		this.avatar = avatar;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<String> getLikes() {
		return likes;
	}
	public void setLikes(List<String> likes) {
		this.likes = likes;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
	
}
