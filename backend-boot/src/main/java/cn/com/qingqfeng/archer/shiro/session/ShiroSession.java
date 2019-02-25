/**
 * 
 */
package cn.com.qingqfeng.archer.shiro.session;

/**   
 * <p> 类名：  ShiroSession   </p>
 * <p> 描述：	TODO  </p>
 * <p> 创建时间： 2019年2月24日 上午12:20:40   </p>
 * @author lijunliang 
 * @version 1.0   
 */
public class ShiroSession extends SimpleSession{

	/**
	 * @FieldsserialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private String browser;
	private String os;
	public String getBrowser() {
		return browser;
	}
	public void setBrowser(String browser) {
		this.browser = browser;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	
}
