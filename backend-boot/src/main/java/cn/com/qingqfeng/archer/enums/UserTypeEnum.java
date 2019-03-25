/**   */
package cn.com.qingqfeng.archer.enums;

/**   
 * <p>类名称: UserTypeEnum </p> 
 * <p>描述: TODO  </p>
 * <p>创建时间 : 2019年3月25日 上午11:53:06 </p>
 * @author lijunliang
 * @version 1.0
 * 
 */
public enum UserTypeEnum {
	ADMIN(0),
	WEIBO(1),
	WEIXIN(2),
	QQ(3),
	OTHER(4);
	
	private Integer code;
	
	private UserTypeEnum(Integer code){
		this.code=code;
	}
	public Integer getCode(){
		return this.code;
	}
}
