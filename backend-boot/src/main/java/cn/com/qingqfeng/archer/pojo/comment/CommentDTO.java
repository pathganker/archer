/**   */
package cn.com.qingqfeng.archer.pojo.comment;

import java.util.Date;
import java.util.List;

import cn.com.qingqfeng.archer.pojo.reply.ReplyDTO;

/**   
 * <p>类名称: CommentDTO </p> 
 * <p>描述: 评论实体  </p>
 * <p>创建时间 : 2019年3月5日 下午4:59:39 </p>
 * @author lijunliang
 * @version 1.0
 * 
 */
public class CommentDTO {
	/** 自增主键 */
	private Integer id;
	/** 业务主键 **/
	private String uid;
	/** 内容 */
	private String content;
	/** 关联文章 */
	private String articleId;
	/** 创建时间 */
	private Date createTime;
	/** 关联用户 */
	private String userId;
	/** 用户昵称 */
	private String nickname;
	
	private List<ReplyDTO> replys;
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
	public String getArticleId() {
		return articleId;
	}
	public void setArticleId(String articleId) {
		this.articleId = articleId;
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
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public List<ReplyDTO> getReplys() {
		return replys;
	}
	public void setReplys(List<ReplyDTO> replys) {
		this.replys = replys;
	}
}
