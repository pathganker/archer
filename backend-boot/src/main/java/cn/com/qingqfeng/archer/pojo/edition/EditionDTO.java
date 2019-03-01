/**   */
package cn.com.qingqfeng.archer.pojo.edition;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import cn.com.qingqfeng.archer.pojo.article.ArticleDTO;

/**   
 * <p>类名称: EditionDTO </p> 
 * <p>描述: TODO  </p>
 * <p>创建时间 : 2019年3月1日 下午3:50:23 </p>
 * @author lijunliang
 * @version 1.0
 * 
 */
public class EditionDTO implements Serializable{
	/** */
	private static final long serialVersionUID = 1L;
	private String id;
	private String title;
	private String userId;
	private Date createTime;
	private Date modifyTime;
	private List<ArticleDTO> articles;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public List<ArticleDTO> getArticles() {
		return articles;
	}
	public void setArticles(List<ArticleDTO> articles) {
		this.articles = articles;
	}
	
}
