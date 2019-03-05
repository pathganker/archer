/**   */
package cn.com.qingqfeng.archer.pojo.edition;

import java.util.Date;

/**   
 * <p>类名称: EditionDO </p> 
 * <p>描述: TODO  </p>
 * <p>创建时间 : 2019年3月5日 下午5:42:40 </p>
 * @author lijunliang
 * @version 1.0
 * 
 */
public class EditionDO {
	/** 主键 */
	private String id;
	/** 标题 */
	private String title;
	/** 关联用户 */
	private String userId;
	/** 创建时间 */
	private Date createTime;
	/** 修改时间 */
	private Date modifyTime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
}
