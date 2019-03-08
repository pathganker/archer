/**   */
package cn.com.qingqfeng.archer.pojo;

import java.util.List;

/**   
 * <p>类名称: PageInfo </p> 
 * <p>描述: TODO  </p>
 * <p>创建时间 : 2019年3月6日 下午1:00:38 </p>
 * @author lijunliang
 * @version 1.0
 * @param <T>
 * 
 */
public class PageInfo<T> {
	private Long pageCount;
	private Long totalCount;
	private Integer page;
	private Integer pageSize;
	private List<T> object;
	
	public Long getPageCount() {
		return pageCount;
	}
	public void setPageCount(Long pageCount) {
		this.pageCount = pageCount;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public List<T> getObject() {
		return object;
	}
	public void setObject(List<T> object) {
		this.object = object;
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
}
