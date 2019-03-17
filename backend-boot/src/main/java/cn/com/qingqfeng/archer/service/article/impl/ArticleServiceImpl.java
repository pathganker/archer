/**   */
package cn.com.qingqfeng.archer.service.article.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.qingqfeng.archer.dao.article.IArticleDao;
import cn.com.qingqfeng.archer.dao.like.ILikeDao;
import cn.com.qingqfeng.archer.pojo.article.ArticleDO;
import cn.com.qingqfeng.archer.pojo.article.ArticleDTO;
import cn.com.qingqfeng.archer.pojo.article.ArticleQuery;
import cn.com.qingqfeng.archer.pojo.like.LikeDO;
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
	
	@Autowired
	private ILikeDao likeDao;
	
	/** (non-Javadoc)
	 * @see cn.com.qingqfeng.archer.service.article.IArticleService#requestArticleByOptions(java.lang.String, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<ArticleDTO> requestArticleByOptions(ArticleQuery query) {
		if(query.getPage()<0){
			return null;
		}
		query.setPage((query.getPage()> 0?query.getPage()-1:query.getPage())*query.getPageSize());
		List<ArticleDTO> ars = new ArrayList<ArticleDTO>();
		List<ArticleDO> dos = this.articleDao.queryArticleByOptions(query);
		if(null == dos || dos.isEmpty()){
			return ars;
		}
		for(ArticleDO ado : dos){
			ArticleDTO article = new ArticleDTO();
			BeanUtils.copyProperties(ado, article);
			if(null == article.getCommentCount()){
				article.setCommentCount(0);
			}
			if(null == article.getVisitCount()){
				article.setVisitCount(0);
			}
			if(null == article.getLikeCount()){
				article.setLikeCount(0);
			}
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
	/**
	 * (non-Javadoc)
	 * @see cn.com.qingqfeng.archer.service.article.IArticleService#requestArticleTotal(cn.com.qingqfeng.archer.pojo.article.ArticleQuery)
	 */
	@Override
	public Long requestArticleTotal(ArticleQuery query) {
		return  this.articleDao.queryArticelTotal(query);
	}

	/** (non-Javadoc)
	 * @see cn.com.qingqfeng.archer.service.article.IArticleService#requestPreNext(java.lang.String, cn.com.qingqfeng.archer.pojo.article.ArticleQuery)
	 */
	@Override
	public List<ArticleDTO> requestPreNext(String id, ArticleQuery query) {
		List<ArticleDTO> articles = new ArrayList<ArticleDTO>();
		if(StringUtils.isBlank(id)){
			return articles;
		}
		query.setId(id);
		Integer sortNum = this.articleDao.querySortNum(query);
		if(sortNum>1){
			query.setPage(sortNum-2);
			query.setPageSize(3);
		}else {
			query.setPage(sortNum-1);
			query.setPageSize(2);
		}
		List<ArticleDO> ados = this.articleDao.queryArticleByOptions(query);
		if(null == ados || ados.isEmpty()){
			return articles;
		}
		int i=0,index=0;
		for(ArticleDO ado : ados){
			i++;
			if(id.equals(ado.getId())){
				index=i;
				continue;
			}
			ArticleDTO article = new ArticleDTO();
			BeanUtils.copyProperties(ado, article);
			articles.add(article);
		}
		if(articles.size()<2) {
			if(index==1) {
				articles.add(0, new ArticleDTO());
			}else {
				articles.add(new ArticleDTO());
			}
		}
		return articles;
	}

	/** (non-Javadoc)
	 * @see cn.com.qingqfeng.archer.service.article.IArticleService#addArticle(cn.com.qingqfeng.archer.pojo.article.ArticleDTO)
	 */
	@Override
	public void addArticle(ArticleDTO article) {
		ArticleDO ado = new ArticleDO();
		BeanUtils.copyProperties(article, ado);
		this.articleDao.addArticle(ado);
	}

	/** (non-Javadoc)
	 * @see cn.com.qingqfeng.archer.service.article.IArticleService#updateArticle(cn.com.qingqfeng.archer.pojo.article.ArticleDTO)
	 */
	@Override
	public void updateArticle(ArticleDTO article) {
		if(null == article || StringUtils.isBlank(article.getId())){
			return;
		}
		ArticleDO ado = new ArticleDO();
		BeanUtils.copyProperties(article, ado);
		this.articleDao.updateArticle(ado);
	}

	/**
	 * (non-Javadoc)
	 * @see cn.com.qingqfeng.archer.service.article.IArticleService#deleteArticle(java.lang.String)
	 */
	@Override
	public void deleteArticle(String id) {
		this.articleDao.deleteArticle(id);
	}

	/** (non-Javadoc)
	 * @see cn.com.qingqfeng.archer.service.article.IArticleService#requestCountDataById(java.lang.String)
	 */
	@Override
	public ArticleDTO requestCountDataById(String id) {
		ArticleDTO count = new ArticleDTO();
		ArticleDO data = this.articleDao.queryCountDataById(id);
		if(null == data){
			count.setVisitCount(0);
			count.setCommentCount(0);
			count.setLikeCount(0);
			return count;
		}
		if(null!=data.getCommentCount()){
			count.setCommentCount(data.getCommentCount());
		}else{
			count.setCommentCount(0);
		}
		if(null != data.getLikeCount()){
			count.setLikeCount(data.getLikeCount());
		}else{
			count.setLikeCount(0);
		}
		if(null != data.getVisitCount()){
			count.setVisitCount(data.getVisitCount());
		}else{
			count.setVisitCount(0);
		}
		return count;
	}

	/** (non-Javadoc)
	 * @see cn.com.qingqfeng.archer.service.article.IArticleService#isUserLike(java.lang.String, java.lang.String)
	 */
	@Override
	public Boolean isUserLike(String userId, String articleId) {
		LikeDO like = this.likeDao.queryLike(userId, articleId);
		if(null == like){
			return false;
		}
		return true;
	}

	/** (non-Javadoc)
	 * @see cn.com.qingqfeng.archer.service.article.IArticleService#addUserLike(java.lang.String, java.lang.String)
	 */
	@Override
	public void addUserLike(String userId, String articleId) {
		LikeDO like = new LikeDO();
		like.setUserId(userId);
		like.setArticleId(articleId);
		like.setCreateTime(new Date());
		this.likeDao.addLike(like);
		
	}

	/** (non-Javadoc)
	 * @see cn.com.qingqfeng.archer.service.article.IArticleService#cancleUserLike(java.lang.String, java.lang.String)
	 */
	@Override
	public void cancleUserLike(String userId, String articleId) {
		this.likeDao.deleteLike(userId, articleId);
	}

	/**
	 * (non-Javadoc)
	 * @see cn.com.qingqfeng.archer.service.article.IArticleService#requestLikeCount(java.lang.String)
	 */
	@Override
	public Long requestLikeCount(String articleId) {
		return this.likeDao.queryLikeCount(articleId);
	}

	/**   
	 * <p>Title: requestArticleByEdition</p>   
	 * <p>Description: </p>   
	 * @param id
	 * @return   
	 * @see cn.com.qingqfeng.archer.service.article.IArticleService#requestArticleByEdition(java.lang.String)   
	 */
	@Override
	public List<ArticleDTO> requestArticleByEdition(String id) {
		List<ArticleDTO> articles = new ArrayList<>();
		List<ArticleDO> ados = this.articleDao.queryArticleByEdition(id);
		if(null == ados || ados.isEmpty()) {
			return articles;
		}
		for(ArticleDO ado : ados) {
			ArticleDTO article = new ArticleDTO();
			BeanUtils.copyProperties(ado, article);
			articles.add(article);
		}
		return articles;
	}

}
