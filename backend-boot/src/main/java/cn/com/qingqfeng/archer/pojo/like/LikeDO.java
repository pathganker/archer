/**   */
package cn.com.qingqfeng.archer.pojo.like;

import java.util.Date;

/**   
 * <p>类名称: LikeDO </p> 
 * <p>描述: TODO  </p>
 * <p>创建时间 : 2019年3月7日 下午12:48:25 </p>
 * @author lijunliang
 * @version 1.0
 * 
 */
public class LikeDO {
	/** 用户id */
	private String userId;
	/** 文章id */
	private String articleId;
	/** 创建时间 */
	private Date createTime;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
}
