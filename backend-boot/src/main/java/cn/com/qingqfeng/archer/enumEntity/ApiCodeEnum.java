/**   */
package cn.com.qingqfeng.archer.enumEntity;

/**
 * 
 * <p> 类名：  ApiCodeEnum   </p>
 * <p> 描述：	TODO  </p>
 * <p> 创建时间： 2019年2月17日 下午3:10:06   </p>
 * @author lijunliang 
 * @version 1.0
 */
public enum ApiCodeEnum {
	/** 成功 */
	SUCCESS(200),
	/** 参数没有问题没有先要的结果 */
	NO_RESULT(202),
	/** 服务异常 稍后再试 */
	SERVICE_WRONG(500),
	/** 对该接口无权限 请申请使用该接口 */
	API_AUTHORITY(403),
	/** 用户名和密码错误 请输入正确的用户名和密码 */
	USER_NAME_OR_PWD(101),
	/** Token失效 请重新登陆 */
	TOKEN_TIME_OUT(102),
	/** 缺少签名 请使用正确的签名 */
	TOKEN_LOST(103),
	/** 错误参数 请输入正确参数 */
	TOKEN_WRONG(104),
	/** 错误参数 请输入正确参数 */
	ARGS_WRONG(105),
	/** 接口访问次数超限 请明天再试。（默认一天内最多访问1000次） */
	API_ACCESS_RATE_OUT(106),
	/** 更换设备，请重新登陆 重新登录 */
	CHANGE_DEVICE(107),
	/** 注册的邮箱不正确（不是邮箱） */
	REGIST_EMAIL_WRONG(108),
	/** 邮箱已注册 */
	REGIST_EMAIL_EXIST(109),
	/** 注册的手机号码不正确（不是手机号） */
	REGIST_MOBILEPHONE_WRONG(110),
	/** 手机号已注册 */
	REGIST_MOBILEPHONE_EXIST(111),
	/** 时间戳错误 */
	TIMESTAMP_ERROR(112),
	/** 验证码错误 */
	CAPTCHA_WRONG(113),
	/** 设备id为空 */
	DEVICE_ID_NULL(114),
	/** 用户id错误 */
	USER_ID_ERROR(115);
	
	/** 错误码 */
	private Integer errorCode;

	private ApiCodeEnum(Integer code) {
		this.errorCode = code;
	}
	/**
	 * @return errorCode
	 */
	public Integer getErrorCode() {
		return errorCode;
	}
}
