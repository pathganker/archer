/**
 * 
 */
package cn.com.qingqfeng.archer.pojo.article;

import java.io.Serializable;
import java.util.Date;

/**   
 * <p> 类名：  articleDTO   </p>
 * <p> 描述：	TODO  </p>
 * <p> 创建时间： 2019年2月17日 下午4:57:59   </p>
 * @author lijunliang 
 * @version 1.0   
 */
public class ArticleDTO implements Serializable{

	/**
	 * @FieldsserialVersionUID
	 */
	private static final long serialVersionUID = 1879598723154109228L;
	/** 图片 */
	private String image;
	/** 主键 */
	private String id;
	/** 标题 */
	private String title;
	/** 浏览量 */
	private Integer visitCount;
	/** 评论数量 */
	private Integer commentCount;
	/** 点赞数量 */
	private Integer likeCount;
	/** 发布时间 */
	private Date publishTime;
	/** 文集 */
	private String edition;
	/** 编辑内容 */
	private String frontContent;
	/** 展示内容 */
	private String backendContent;
	/** 概览 */
	private String profile;
	/** 用户id */
	private String userId;
	/** 用户昵称 */
	private String nickname;
	/** 发布状态 */
	private Boolean publish;
	/** 创建时间 */
	private Date createTime;
	/** 修改时间 */
	private Date modifyTime;
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
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
	public Integer getVisitCount() {
		return visitCount;
	}
	public void setVisitCount(Integer visitCount) {
		this.visitCount = visitCount;
	}
	public Integer getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}
	public Integer getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}
	public Date getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getEdition() {
		return edition;
	}
	public void setEdition(String edition) {
		this.edition = edition;
	}
	public String getFrontContent() {
		return frontContent;
	}
	public void setFrontContent(String frontContent) {
		this.frontContent = frontContent;
	}
	public String getBackendContent() {
		return backendContent;
	}
	public void setBackendContent(String backendContent) {
		this.backendContent = backendContent;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Boolean getPublish() {
		return publish;
	}
	public void setPublish(Boolean publish) {
		this.publish = publish;
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
