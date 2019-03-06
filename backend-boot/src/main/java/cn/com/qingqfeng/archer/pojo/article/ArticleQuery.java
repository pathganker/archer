/**   */
package cn.com.qingqfeng.archer.pojo.article;


import cn.com.qingqfeng.archer.enums.SortType;
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

	/**
	 * @param page
	 * @param pageSize
	 * @param sortName
	 * @param sortType
	 */
	public ArticleQuery(Integer page, Integer pageSize, String sortName,
			SortType sortType) {
		super(page, pageSize, sortName, sortType);
	}
	public ArticleQuery(){
		super();
	}
	public ArticleQuery(Integer page, Integer pageSize){
		super(page, pageSize);
	}
	/** */
	private static final long serialVersionUID = 1L;
	
	private String tag;
	private String id;
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

}
