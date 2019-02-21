/**
 * 
 */
package cn.com.qingqfeng.archer.pojo.article;

import java.io.Serializable;

/**   
 * <p> 类名：  ImageDTO   </p>
 * <p> 描述：	TODO  </p>
 * <p> 创建时间： 2019年2月17日 下午4:59:17   </p>
 * @author lijunliang 
 * @version 1.0   
 */
public class ImageDTO implements Serializable{

	/**
	 * @FieldsserialVersionUID
	 */
	private static final long serialVersionUID = -5757504372338476493L;
	
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
