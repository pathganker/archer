/**   */
package cn.com.qingqfeng.archer.enums;


/**   
 * <p>类名称: ArticleSortNameEnum </p> 
 * <p>描述: TODO  </p>
 * <p>创建时间 : 2019年3月7日 上午11:13:34 </p>
 * @author lijunliang
 * @version 1.0
 * 
 */
public enum ArticleSortNameEnum {
	
	PUBLISTHTIME("publishTime","publish_time"),
	CRATETIME("createTime","create_time"),
	VISITCOUNT("visitCount","visit_count"),
	COMMENTCOUNT("commentCount","comme_count"),
	LIKECOUNT("likeCount","like_count");
	private String view;
	private String code;
	
	private ArticleSortNameEnum(String view, String code){
		this.view = view;
		this.code = code;
	}

	public String getView() {
		return view;
	}

	public String getCode() {
		return code;
	}
	
	public static ArticleSortNameEnum requestEnumByView(String view){
		if(null == view){
			return null;
		}
		for(ArticleSortNameEnum type: ArticleSortNameEnum.values()){
			if(type.getView().equals(view)){
				return type;
			}
		}
		return null;
	}
}
