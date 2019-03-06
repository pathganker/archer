/**   */
package cn.com.qingqfeng.archer.enums;

/**   
 * <p>类名称: sortType </p> 
 * <p>描述: TODO  </p>
 * <p>创建时间 : 2019年3月6日 上午11:52:20 </p>
 * @author lijunliang
 * @version 1.0
 * 
 */
public enum SortType {
	ASC("ASC"),
	DESC("DESC");
	
	private String sort;
	private SortType(String sort){
		this.sort = sort;
	}
	public String getSort() {
		return sort;
	}
}
