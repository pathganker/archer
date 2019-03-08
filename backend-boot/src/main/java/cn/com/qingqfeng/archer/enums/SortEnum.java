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
public enum SortEnum {
	ASC("ASC"),
	DESC("DESC");
	
	private String sort;
	private SortEnum(String sort){
		this.sort = sort;
	}
	public String getSort() {
		return sort;
	}
	public static SortEnum requestTypeBySort(String sort){
		if(null == sort){
			return null;
		}
		for (SortEnum type : SortEnum.values()){
			if(type.sort.equals(sort)){
				return type;
			}
		}
		return null;
	}
}
