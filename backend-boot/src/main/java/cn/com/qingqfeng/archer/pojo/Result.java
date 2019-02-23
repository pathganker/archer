/**
 * 
 */
package cn.com.qingqfeng.archer.pojo;

import java.io.Serializable;

import cn.com.qingqfeng.archer.enums.ApiCodeEnum;



/**
 * 
 * <p> 类名：  Result   </p>
 * <p> 描述：	TODO  </p>
 * <p> 创建时间： 2019年2月17日 下午3:07:32   </p>
 * @author lijunliang 
 * @version 1.0
 */
public class Result implements Serializable{
	/**
	 * @FieldsserialVersionUID
	 */
	private static final long serialVersionUID = 7883070263735616459L;
	
	/** 返回码 */
	private Integer code;
	/** 描述 */
	private String message;
	/** 数据 */
	private Object data;
	
	public Result(){
		
	}
	public Result(ApiCodeEnum code, Object data){
		this.code=code.getErrorCode();
		this.data=data;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(ApiCodeEnum code) {
		this.code = code.getErrorCode();
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
