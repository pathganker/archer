/**   */
package cn.com.qingqfeng.archer.pojo.reply;

import java.util.Date;

/**   
 * <p>类名称: ReplyDO </p> 
 * <p>描述: TODO  </p>
 * <p>创建时间 : 2019年3月6日 下午5:13:47 </p>
 * @author lijunliang
 * @version 1.0
 * 
 */
public class ReplyDO {
	/** 自增主键 */
	private Integer id;
	/** 内容 */
	private String content;
	/** 关联评论 */
	private String commentId;
	/** 创建时间 */
	private Date createTime;
	/** 关联用户 */
	private String userId;
	/** 用户昵称 */
	private String nickname;
	/** 目标id */
	private String targetId;
	/** 目标昵称 */
	private String targetName;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCommentId() {
		return commentId;
	}
	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getTargetId() {
		return targetId;
	}
	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}
	public String getTargetName() {
		return targetName;
	}
	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}
}
