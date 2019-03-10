/**
 * 
 */
package cn.com.qingqfeng.archer.webapi.controller.article;

import java.util.Date;
import java.util.HashMap;
import java.util.List;











import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.support.DelegatingSubject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.com.qingqfeng.archer.enums.ApiCodeEnum;
import cn.com.qingqfeng.archer.pojo.Result;
import cn.com.qingqfeng.archer.pojo.article.ArticleDTO;
import cn.com.qingqfeng.archer.pojo.article.ArticleQuery;
import cn.com.qingqfeng.archer.pojo.edition.EditionDTO;
import cn.com.qingqfeng.archer.pojo.record.ArticleVisitDTO;
import cn.com.qingqfeng.archer.service.article.IArticleService;
import cn.com.qingqfeng.archer.service.edition.IEditionService;
import cn.com.qingqfeng.archer.service.record.IVisitRecordService;
import cn.com.qingqfeng.archer.utils.JwtUtils;

/**   
 * <p> 类名：  ArticleController   </p>
 * <p> 描述：	TODO  </p>
 * <p> 创建时间： 2019年2月17日 下午3:37:18   </p>
 * @author lijunliang 
 * @version 1.0   
 */

@RestController
@RequestMapping("article")
public class ArticleController {
	
	@Autowired
	private IArticleService articleService;
	
	@Autowired
	private IEditionService editionService;
	
	@Autowired
	private IVisitRecordService visitRecordService;
	/**
	 * 
	 * <p>方法名:  getFrontArticleList </p> 
	 * <p>描述:    TODO </p>
	 * <p>创建时间:  2019年3月6日下午1:40:29 </p>
	 * @version 1.0
	 * @author lijunliang
	 * @param page
	 * @param pageSize
	 * @param tag
	 * @return  
	 * Result
	 */
	@RequestMapping(value="front/list", method={RequestMethod.GET})
	public Result getFrontArticleList(Integer page, Integer pageSize, String tag, String sortName, String sortType) {
		Result result = new Result();
		ArticleQuery query = new ArticleQuery(page, pageSize, sortName, sortType);
		query.setTag(tag);
		List<ArticleDTO> articles = this.articleService.requestArticleByOptions(query);
		if(null == articles || articles.isEmpty()){
			result.setCode(ApiCodeEnum.NO_RESULT);
			return result;
		}
		result.setData(articles);
		result.setCode(ApiCodeEnum.SUCCESS);
		return result;
	}
	/**
	 * 
	 * <p>方法名:  getFrontArticleCount </p> 
	 * <p>描述:    TODO </p>
	 * <p>创建时间:  2019年3月6日下午1:44:10 </p>
	 * @version 1.0
	 * @author lijunliang
	 * @param tag
	 * @return  
	 * Result
	 */
	@RequestMapping(value="front/count",method={RequestMethod.GET})
	public Result getFrontArticleCount(String tag){
		Result result = new Result();
		ArticleQuery query = new ArticleQuery();
		query.setTag(tag);
		Long total = this.articleService.requestArticleTotal(query);
		result.setData(total);
		result.setCode(ApiCodeEnum.SUCCESS);
		return result;
	}
	/**
	 * 
	 * <p>方法名:  getToggleLike </p> 
	 * <p>描述:    TODO </p>
	 * <p>创建时间:  2019年3月7日下午1:23:50 </p>
	 * @version 1.0
	 * @author lijunliang
	 * @param id
	 * @return  
	 * Result
	 */
	@RequestMapping(value="front/togglelike")
	public Result getToggleLike(@RequestParam String id){
		Result rs = new Result();
		String userId = JwtUtils.getCurrentUserId();
		boolean isLike = this.articleService.isUserLike(userId, id);
		if(isLike){
			this.articleService.cancleUserLike(userId, id);
			isLike=false;
		}else{
			this.articleService.addUserLike(userId, id);
			isLike=true;
		}
		Long likeCount = this.articleService.requestLikeCount(id);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("likeCount", likeCount);
		data.put("isLike", isLike);
		rs.setCode(ApiCodeEnum.SUCCESS);
		rs.setData(data);
		return rs;
		
	}
	
	/**
	 * 
	 * <p>方法名:  getFrontArticle </p> 
	 * <p>描述:    TODO </p>
	 * <p>创建时间:  2019年3月6日下午1:47:53 </p>
	 * @version 1.0
	 * @author lijunliang
	 * @param id
	 * @return  
	 * Result
	 */
	@RequestMapping(value="front/detail",method={RequestMethod.GET})
	public Result getFrontArticle(@RequestParam String id){
		Result rs = new Result();
		ArticleDTO article = this.articleService.requestArticleById(id);
		ArticleDTO count = this.articleService.requestCountDataById(id);
		if(null == article) {
			rs.setCode(ApiCodeEnum.NO_RESULT);
			return rs;
		}
		article.setCommentCount(count.getCommentCount());
		article.setVisitCount(count.getVisitCount());
		article.setLikeCount(count.getLikeCount());
		DelegatingSubject test=  (DelegatingSubject) SecurityUtils.getSubject();
		String ip = test.getHost();
		String userId = JwtUtils.getCurrentUserId();
		ArticleVisitDTO record = new ArticleVisitDTO();
		record.setArticleId(id);
		record.setHost(ip);
		record.setUserId(userId);
		record.setCreateTime(new Date());
		this.visitRecordService.addArticleVisitRecord(record);
		boolean isLike = this.articleService.isUserLike(userId, id);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("article", article);
		data.put("isLike", isLike);
		rs.setCode(ApiCodeEnum.SUCCESS);
		rs.setData(data);
		return rs;
	}
	/**
	 * 
	 * <p>方法名:  getPreNext </p> 
	 * <p>描述:    TODO </p>
	 * <p>创建时间:  2019年3月6日下午3:00:33 </p>
	 * @version 1.0
	 * @author lijunliang
	 * @param id
	 * @param page
	 * @param pageSize
	 * @param tag
	 * @return  
	 * Result
	 */
	@RequestMapping(value="front/next/{id}",method={RequestMethod.GET})
	public Result getPreNext(@PathVariable String id, Integer page, Integer pageSize, String tag, String sortName, String sortType){
		Result rs = new Result();
		ArticleQuery query = new ArticleQuery(page, pageSize, sortName, sortType);
		query.setTag(tag);
		List<ArticleDTO> articles = this.articleService.requestPreNext(id, query);
		if(null == articles || articles.isEmpty()){
			rs.setCode(ApiCodeEnum.NO_RESULT);
			return rs;
		}
		rs.setCode(ApiCodeEnum.SUCCESS);
		rs.setData(articles);
		return rs;
	}
	
	
	/**
	 * 
	 * <p>方法名:  getEditionList </p> 
	 * <p>描述:    TODO </p>
	 * <p>创建时间:  2019年3月1日下午3:44:17 </p>
	 * @version 1.0
	 * @author lijunliang
	 * @return  
	 * Result
	 */
	@RequestMapping(value="edition", method={RequestMethod.GET})
	public Result getEditionList(){
		Result rs = new Result();
		String userId = JwtUtils.getCurrentUserId();
		if(StringUtils.isBlank(userId)){
			rs.setCode(ApiCodeEnum.API_AUTHORITY);
			return rs;
		}
		List<EditionDTO> editions = this.editionService.requestEditionsByUserId(userId);
		rs.setCode(ApiCodeEnum.SUCCESS);
		rs.setData(editions);
		return rs;
	}
	/**
	 * 
	 * <p>方法名:  getBackendArticle </p> 
	 * <p>描述:    TODO </p>
	 * <p>创建时间:  2019年3月1日下午8:42:41 </p>
	 * @version 1.0
	 * @author lijunliang
	 * @param id
	 * @return  
	 * Result
	 */
	@RequestMapping(value="backend", method={RequestMethod.GET})
	public Result getBackendArticle(@RequestParam String id){
		Result rs = new Result();
		ArticleDTO article = new ArticleDTO();
		article.setBackendContent("这是一个无情的人");
		article.setTitle("这是一个无情的人");
		article.setId(id);
		rs.setData(article);
		rs.setCode(ApiCodeEnum.SUCCESS);
		return rs;
	}
	/**
	 * 
	 * <p>方法名:  addNewArticle </p> 
	 * <p>描述:    TODO </p>
	 * <p>创建时间:  2019年3月1日下午8:48:04 </p>
	 * @version 1.0
	 * @author lijunliang
	 * @param article
	 * @return  
	 * Result
	 */
	@RequestMapping(value="backend/newblog", method={RequestMethod.POST})
	public Result addNewArticle(@RequestBody ArticleDTO article){
		Result rs = new Result();
		if(null == article || StringUtils.isBlank(article.getEdition()) || StringUtils.isBlank(article.getId())){
			rs.setCode(ApiCodeEnum.ARGS_WRONG);
			return rs;
		}

		this.articleService.addArticle(article);
		rs.setCode(ApiCodeEnum.SUCCESS);
		return rs;
	}
	/**
	 * 
	 * <p>方法名:  updateArticle </p> 
	 * <p>描述:    TODO </p>
	 * <p>创建时间:  2019年3月1日下午8:48:06 </p>
	 * @version 1.0
	 * @author lijunliang
	 * @param article
	 * @return  
	 * Result
	 */
	@RequestMapping(value="backend/oldblog", method={RequestMethod.POST})
	public Result updateArticle(@RequestBody ArticleDTO article){
		Result rs = new Result();
		if(null == article || StringUtils.isBlank(article.getId())){
			rs.setCode(ApiCodeEnum.ARGS_WRONG);
			return rs;
		}
		this.articleService.updateArticle(article);
		rs.setCode(ApiCodeEnum.SUCCESS);
		return rs;
	}
	/**
	 * 
	 * <p>方法名:  deleteArticle </p> 
	 * <p>描述:    TODO </p>
	 * <p>创建时间:  2019年3月1日下午8:48:09 </p>
	 * @version 1.0
	 * @author lijunliang
	 * @param id
	 * @return  
	 * Result
	 */
	@RequestMapping(value="backend/blogless", method={RequestMethod.DELETE})
	public Result deleteArticle(@RequestParam String id){
		Result rs = new Result();
		this.articleService.deleteArticle(id);
		rs.setCode(ApiCodeEnum.SUCCESS);
		return rs;		
	}
	
	/**
	 * 
	 * <p>方法名:  addEdition </p> 
	 * <p>描述:    TODO </p>
	 * <p>创建时间:  2019年3月8日下午5:37:07 </p>
	 * @version 1.0
	 * @author lijunliang
	 * @param edition
	 * @return  
	 * Result
	 */
	@RequestMapping(value="backend/newedition",method={RequestMethod.POST})
	public Result addEdition(@RequestBody EditionDTO edition){
		Result rs = new Result();
		if(null == edition || StringUtils.isBlank(edition.getId())){
			rs.setCode(ApiCodeEnum.ARGS_WRONG);
			return rs;			
		}
		String userId = JwtUtils.getCurrentUserId();
		edition.setUserId(userId);
		this.editionService.addEdition(edition);
		rs.setCode(ApiCodeEnum.SUCCESS);
		return rs;
	}
	/**
	 * 
	 * <p>方法名:  updateEdition </p> 
	 * <p>描述:    TODO </p>
	 * <p>创建时间:  2019年3月8日下午5:44:09 </p>
	 * @version 1.0
	 * @author lijunliang
	 * @param edition
	 * @return  
	 * Result
	 */
	@RequestMapping(value="backend/oldedition",method={RequestMethod.POST})
	public Result updateEdition(@RequestBody EditionDTO edition){
		Result rs = new Result();
		if(null == edition || StringUtils.isBlank(edition.getId())){
			rs.setCode(ApiCodeEnum.ARGS_WRONG);
			return rs;			
		}
		String userId = JwtUtils.getCurrentUserId();
		edition.setUserId(userId);
		this.editionService.updateEdition(edition);
		rs.setCode(ApiCodeEnum.SUCCESS);
		return rs;
	}
	/**
	 * 
	 * <p>方法名:  deleteEditionOnly </p> 
	 * <p>描述:    TODO </p>
	 * <p>创建时间:  2019年3月8日下午5:49:55 </p>
	 * @version 1.0
	 * @author lijunliang
	 * @param id
	 * @return  
	 * Result
	 */
	@RequestMapping(value="backend/edition/less",method={RequestMethod.DELETE})
	public Result deleteEditionOnly(String id){
		Result rs = new Result();
		this.editionService.deleteEdition(id);
		return rs;
	}
	
	/**
	 * 
	 * <p>方法名:  deleteEditionWhole </p> 
	 * <p>描述:    TODO </p>
	 * <p>创建时间:  2019年3月8日下午5:50:02 </p>
	 * @version 1.0
	 * @author lijunliang
	 * @param id
	 * @return  
	 * Result
	 */
	@RequestMapping(value="backend/edition/lesswhole",method={RequestMethod.DELETE})
	public Result deleteEditionWhole(String id){
		Result rs = new Result();
		this.editionService.deleteEditionWhole(id);
		return rs;
	}
}
