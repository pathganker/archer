/**   */
package cn.com.qingqfeng.archer.pojo.article;

import cn.com.qingqfeng.archer.pojo.Query;

/**   
 * <p>类名称: ArticleQuery </p> 
 * <p>描述: TODO  </p>
 * <p>创建时间 : 2019年3月5日 下午6:37:12 </p>
 * @author lijunliang
 * @version 1.0
 * 
 */
public class ArticleQuery extends Query{

	/** */
	private static final long serialVersionUID = 1L;
	
	private String sortName;
	private String tagId;
	public String getSortName() {
		return sortName;
	}
	public void setSortName(String sortName) {
		this.sortName = sortName;
	}
	public String getTagId() {
		return tagId;
	}
	public void setTagId(String tagId) {
		this.tagId = tagId;
	}
}
