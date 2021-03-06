/**   */
package cn.com.qingqfeng.archer.pojo;

import java.io.Serializable;

import cn.com.qingqfeng.archer.enums.SortEnum;


/**   
 * <p>类名称: Query </p> 
 * <p>描述: 分页查询条件  </p>
 * <p>创建时间 : 2019年3月5日 下午6:34:12 </p>
 * @author lijunliang
 * @version 1.0
 * 
 */
public class Query implements Serializable{
	/** */
	private static final long serialVersionUID = 1L;
	public Integer page = 1;
	public Integer pageSize = 10;
	public String sortType=SortEnum.DESC.getSort();
	
	public Query(Integer page, Integer pageSize){
		this.page=page;
		this.pageSize=pageSize;
	}
	public Query(){
		
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public String getSortType() {
		return sortType;
	}
}
