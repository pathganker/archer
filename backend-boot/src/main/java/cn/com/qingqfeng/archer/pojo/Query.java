/**   */
package cn.com.qingqfeng.archer.pojo;

import java.io.Serializable;

/**   
 * <p>类名称: Query </p> 
 * <p>描述: TODO  </p>
 * <p>创建时间 : 2019年3月5日 下午6:34:12 </p>
 * @author lijunliang
 * @version 1.0
 * 
 */
public class Query implements Serializable{
	/** */
	private static final long serialVersionUID = 1L;
	public Integer page =0;
	public Integer pageSize = 10;
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
