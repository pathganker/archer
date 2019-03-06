/**   */
package cn.com.qingqfeng.archer.pojo;

/**   
 * <p>类名称: PageInfo </p> 
 * <p>描述: TODO  </p>
 * <p>创建时间 : 2019年3月6日 下午1:00:38 </p>
 * @author lijunliang
 * @version 1.0
 * 
 */
public class PageInfo {
	private Integer pageCount;
	private Integer totalCount;
	public Integer getPageCount() {
		return pageCount;
	}
	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
}
