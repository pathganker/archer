/**   */
package cn.com.qingqfeng.archer.service.article.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.qingqfeng.archer.dao.article.IArticleDao;
import cn.com.qingqfeng.archer.pojo.article.ArticleDO;
import cn.com.qingqfeng.archer.pojo.article.ArticleDTO;
import cn.com.qingqfeng.archer.pojo.article.ArticleQuery;
import cn.com.qingqfeng.archer.service.article.IArticleService;

/**   
 * <p>类名称: ArticleServiceImpl </p> 
 * <p>描述: TODO  </p>
 * <p>创建时间 : 2019年3月5日 下午6:28:24 </p>
 * @author lijunliang
 * @version 1.0
 * 
 */
@Service
public class ArticleServiceImpl implements IArticleService{
	
	@Autowired
	private IArticleDao articleDao;

	/** (non-Javadoc)
	 * @see cn.com.qingqfeng.archer.service.article.IArticleService#requestArticleByOptions(java.lang.String, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<ArticleDTO> requestArticleByOptions(ArticleQuery query) {
		List<ArticleDTO> ars = new ArrayList<ArticleDTO>();
		List<ArticleDO> dos = this.articleDao.queryArticleByOptions(query);
		if(null == dos || dos.isEmpty()){
			return ars;
		}
		for(ArticleDO ado : dos){
			ArticleDTO article = new ArticleDTO();
			BeanUtils.copyProperties(ado, article);
			ars.add(article);
		}
		return ars;
	}

	/** (non-Javadoc)
	 * @see cn.com.qingqfeng.archer.service.article.IArticleService#requestArticleById(java.lang.String)
	 */
	@Override
	public ArticleDTO requestArticleById(String id) {
		ArticleDTO article = new ArticleDTO();
		ArticleDO ar = this.articleDao.queryArticleById(id);
		if(null != ar){
			BeanUtils.copyProperties(ar, article);
		}
		return article;
	}

}
