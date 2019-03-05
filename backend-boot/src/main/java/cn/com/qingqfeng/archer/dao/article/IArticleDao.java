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
}
