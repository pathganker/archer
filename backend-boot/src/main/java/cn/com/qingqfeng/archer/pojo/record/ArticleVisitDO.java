/**
 * 
 */
package cn.com.qingqfeng.archer.pojo.record;

import java.util.Date;

/**   
 * <p> 类名：  ArticleVisitDO   </p>
 * <p> 描述：	TODO  </p>
 * <p> 创建时间： 2019年3月7日 上午12:45:29   </p>
 * @author lijunliang 
 * @version 1.0   
 */
public class ArticleVisitDO {
	/** host */
	private String host;
	/** 文章id */
	private String articleId;
	/** 创建时间 */
	private Date createTime;
	/** 用户id */
	private String userId;
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
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
	
}
