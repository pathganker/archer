/**   */
package cn.com.qingqfeng.archer.pojo.article;


import cn.com.qingqfeng.archer.enums.ArticleSortNameEnum;
import cn.com.qingqfeng.archer.enums.SortEnum;
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

	public ArticleQuery(){
		super();
	}
	public ArticleQuery(Integer page, Integer pageSize){
		super(page, pageSize);
	}
	public ArticleQuery(Integer page, Integer pageSize, String sortName, String sortType){
		this.page = page;
		this.pageSize = pageSize;
		ArticleSortNameEnum name = ArticleSortNameEnum.requestEnumByView(sortName);
		if(null != name){
			this.sortName = name.getCode();
		}
		SortEnum sort = SortEnum.requestTypeBySort(sortType);
		if(null != sort){
			this.sortType=sort.getSort();
		}
	}
	/** */
	private static final long serialVersionUID = 1L;
	
	private String tag;
	private String id;
	private String sortName;
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSortName() {
		return sortName;
	}
	public void setSortName(ArticleSortNameEnum name){
		if(null == name){
			return;
		}
		this.sortName = name.getCode();
	}

}
