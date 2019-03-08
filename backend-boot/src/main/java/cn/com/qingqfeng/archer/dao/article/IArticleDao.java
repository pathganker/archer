/**   */
package cn.com.qingqfeng.archer.dao.article;

import java.util.List;

import cn.com.qingqfeng.archer.pojo.article.ArticleDO;
import cn.com.qingqfeng.archer.pojo.article.ArticleQuery;

/**   
 * <p>类名称: IArticleDao </p> 
 * <p>描述: TODO  </p>
 * <p>创建时间 : 2019年3月5日 下午5:54:40 </p>
 * @author lijunliang
 * @version 1.0
 * 
 */
public interface IArticleDao {
	/**
	 * 
	 * <p>方法名:  queryArticleByOptions </p> 
	 * <p>描述:    TODO </p>
	 * <p>创建时间:  2019年3月5日下午8:05:21 </p>
	 * @version 1.0
	 * @author lijunliang
	 * @param query
	 * @return  
	 * List<ArticleDO>
	 */
	List<ArticleDO> queryArticleByOptions(ArticleQuery query);
	/**
	 * 
	 * <p>方法名:  queryArticleById </p> 
	 * <p>描述:    TODO </p>
	 * <p>创建时间:  2019年3月5日下午8:05:23 </p>
	 * @version 1.0
	 * @author lijunliang
	 * @param id
	 * @return  
	 * ArticleDO
	 */
	ArticleDO queryArticleById(String id);
	/**
	 * 
	 * <p>方法名:  queryCountDataById </p> 
	 * <p>描述:    TODO </p>
	 * <p>创建时间:  2019年3月6日上午11:18:13 </p>
	 * @version 1.0
	 * @author lijunliang
	 * @param id
	 * @return  
	 * ArticleDO
	 */
	ArticleDO queryCountDataById(String id);
	/**
	 * 
	 * <p>方法名:  queryArticelTotal </p> 
	 * <p>描述:    TODO </p>
	 * <p>创建时间:  2019年3月6日上午11:41:34 </p>
	 * @version 1.0
	 * @author lijunliang
	 * @param query
	 * @return  
	 * Long
	 */
	Long queryArticelTotal(ArticleQuery query);
	/**
	 * 
	 * <p>方法名:  querySortNum </p> 
	 * <p>描述:    TODO </p>
	 * <p>创建时间:  2019年3月6日下午2:05:29 </p>
	 * @version 1.0
	 * @author lijunliang
	 * @param query
	 * @return  
	 * Integer
	 */
	Integer querySortNum(ArticleQuery query);
	/**
	 * 
	 * <p>方法名:  addArticle </p> 
	 * <p>描述:    TODO </p>
	 * <p>创建时间:  2019年3月6日下午3:14:55 </p>
	 * @version 1.0
	 * @author lijunliang
	 * @param article  
	 * void
	 */
	void addArticle(ArticleDO article);
	/**
	 * 
	 * <p>方法名:  updateArticle </p> 
	 * <p>描述:    TODO </p>
	 * <p>创建时间:  2019年3月6日下午3:14:57 </p>
	 * @version 1.0
	 * @author lijunliang
	 * @param article  
	 * void
	 */
	void updateArticle(ArticleDO article);
	/**
	 * 
	 * <p>方法名:  deleteArticle </p> 
	 * <p>描述:    TODO </p>
	 * <p>创建时间:  2019年3月6日下午3:15:00 </p>
	 * @version 1.0
	 * @author lijunliang
	 * @param id  
	 * void
	 */
	void deleteArticle(String id);
	/**
	 * 
	 * <p>方法名:  deleteArticleByEditionId </p> 
	 * <p>描述:    TODO </p>
	 * <p>创建时间:  2019年3月8日下午6:00:27 </p>
	 * @version 1.0
	 * @author lijunliang
	 * @param id  
	 * void
	 */
	void deleteArticleByEditionId(String id);
	
}
