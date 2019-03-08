/**   */
package cn.com.qingqfeng.archer.service.article;

import java.util.List;

import cn.com.qingqfeng.archer.pojo.article.ArticleDTO;
import cn.com.qingqfeng.archer.pojo.article.ArticleQuery;

/**   
 * <p>类名称: IArticleService </p> 
 * <p>描述: TODO  </p>
 * <p>创建时间 : 2019年3月5日 下午5:56:03 </p>
 * @author lijunliang
 * @version 1.0
 * 
 */
public interface IArticleService {
	/**
	 * 
	 * <p>方法名:  requestArticleByOptions </p> 
	 * <p>描述:    TODO </p>
	 * <p>创建时间:  2019年3月5日下午6:27:48 </p>
	 * @version 1.0
	 * @author lijunliang
	 * @param tagId
	 * @param page
	 * @param pageSize
	 * @return  
	 * List<ArticleDTO>
	 */
	List<ArticleDTO> requestArticleByOptions(ArticleQuery query);
	/**
	 * 
	 * <p>方法名:  requestArticleTotal </p> 
	 * <p>描述:    TODO </p>
	 * <p>创建时间:  2019年3月6日下午1:02:44 </p>
	 * @version 1.0
	 * @author lijunliang
	 * @param query
	 * @return  
	 * Long
	 */
	Long requestArticleTotal(ArticleQuery query);
	/**
	 * 
	 * <p>方法名:  requestArticleById </p> 
	 * <p>描述:    TODO </p>
	 * <p>创建时间:  2019年3月5日下午6:27:51 </p>
	 * @version 1.0
	 * @author lijunliang
	 * @param id
	 * @return  
	 * ArticleDTO
	 */
	ArticleDTO requestArticleById(String id);
	/**
	 * 
	 * <p>方法名:  requestPreNext </p> 
	 * <p>描述:    TODO </p>
	 * <p>创建时间:  2019年3月6日下午2:01:31 </p>
	 * @version 1.0
	 * @author lijunliang
	 * @param id
	 * @param query
	 * @return  
	 * List<ArticleDTO>
	 */
	List<ArticleDTO> requestPreNext(String id, ArticleQuery query);
	/**
	 * 
	 * <p>方法名:  requestCountDataById </p> 
	 * <p>描述:    TODO </p>
	 * <p>创建时间:  2019年3月7日上午9:52:36 </p>
	 * @version 1.0
	 * @author lijunliang
	 * @param id
	 * @return  
	 * ArticleDTO
	 */
	ArticleDTO requestCountDataById(String id);
	/**
	 * 
	 * <p>方法名:  addArticle </p> 
	 * <p>描述:    TODO </p>
	 * <p>创建时间:  2019年3月7日上午10:22:41 </p>
	 * @version 1.0
	 * @author lijunliang
	 * @param article  
	 * void
	 */
	void addArticle(ArticleDTO article);
	/**
	 * 
	 * <p>方法名:  updateArticle </p> 
	 * <p>描述:    TODO </p>
	 * <p>创建时间:  2019年3月7日上午10:22:44 </p>
	 * @version 1.0
	 * @author lijunliang
	 * @param article  
	 * void
	 */
	void updateArticle(ArticleDTO article);
	/**
	 * 
	 * <p>方法名:  deleteArticle </p> 
	 * <p>描述:    TODO </p>
	 * <p>创建时间:  2019年3月7日上午10:22:47 </p>
	 * @version 1.0
	 * @author lijunliang
	 * @param id  
	 * void
	 */
	void deleteArticle(String id);
	/**
	 * 
	 * <p>方法名:  isUserLike </p> 
	 * <p>描述:    TODO </p>
	 * <p>创建时间:  2019年3月7日下午12:01:16 </p>
	 * @version 1.0
	 * @author lijunliang
	 * @param userId
	 * @param articleId
	 * @return  
	 * Boolean
	 */
	Boolean isUserLike(String userId, String articleId);
	/**
	 * 
	 * <p>方法名:  addUserLike </p> 
	 * <p>描述:    TODO </p>
	 * <p>创建时间:  2019年3月7日下午1:18:35 </p>
	 * @version 1.0
	 * @author lijunliang
	 * @param userId
	 * @param articleId  
	 * void
	 */
	void addUserLike(String userId, String articleId);
	/**
	 * 
	 * <p>方法名:  cancleUserLike </p> 
	 * <p>描述:    TODO </p>
	 * <p>创建时间:  2019年3月7日下午1:18:37 </p>
	 * @version 1.0
	 * @author lijunliang
	 * @param userId
	 * @param articleId  
	 * void
	 */
	void cancleUserLike(String userId, String articleId);
	/**
	 * 
	 * <p>方法名:  requestLikeCount </p> 
	 * <p>描述:    TODO </p>
	 * <p>创建时间:  2019年3月7日下午1:31:56 </p>
	 * @version 1.0
	 * @author lijunliang
	 * @param articleId
	 * @return  
	 * Long
	 */
	Long requestLikeCount(String articleId);
	
}
