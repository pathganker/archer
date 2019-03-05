/**   */
package cn.com.qingqfeng.archer.pojo.tag;

/**   
 * <p>类名称: TagDO </p> 
 * <p>描述: TODO  </p>
 * <p>创建时间 : 2019年3月5日 下午6:23:53 </p>
 * @author lijunliang
 * @version 1.0
 * 
 */
public class TagDO {
	/** 主键 */
	private String tagId;
	/** 关联用户 */
	private String userId;
	/** 标签名称 */
	private String tagName;
	public String getTagId() {
		return tagId;
	}
	public void setTagId(String tagId) {
		this.tagId = tagId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
}
