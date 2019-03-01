/**
 * 
 */
package cn.com.qingqfeng.archer.pojo.article;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
	
	private List<ImageDTO> images;
	private String id;
	private String title;
	private Integer visitCount;
	private Integer commentCount;
	private Integer likeCount;
	private Date publishTime;
	private String edition;
	private String frontContent;
	private String backendContent;
	public List<ImageDTO> getImages() {
		return images;
	}
	public void setImages(List<ImageDTO> images) {
		this.images = images;
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
	
	

}
