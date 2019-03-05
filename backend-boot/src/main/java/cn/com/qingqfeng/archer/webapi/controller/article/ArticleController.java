/**
 * 
 */
package cn.com.qingqfeng.archer.webapi.controller.article;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.com.qingqfeng.archer.enums.ApiCodeEnum;
import cn.com.qingqfeng.archer.pojo.Result;
import cn.com.qingqfeng.archer.pojo.article.ArticleDTO;
import cn.com.qingqfeng.archer.pojo.edition.EditionDTO;
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
	
	@RequestMapping("front/list")
	public Result getFrontArticleList(Integer curretPage, Integer currentPage, String sortName, String tagId) {
		Result result = new Result();
		result.setCode(ApiCodeEnum.SUCCESS);
		return result;
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
		rs.setCode(ApiCodeEnum.SUCCESS);
		List<EditionDTO> editions = new ArrayList<EditionDTO>();
		List<ArticleDTO> articles = new ArrayList<ArticleDTO>();
		ArticleDTO article = new ArticleDTO();
		article.setTitle("我是一个恶人，我莫得感情");
		article.setId("1234");
		article.setBackendContent("我是一个恶人，我莫得感情");
		articles.add(article);
		article = new ArticleDTO();
		article.setTitle("我是一个杀手，我莫得感情");
		article.setId("1324");
		article.setBackendContent("我是一个杀手，我莫得感情");
		articles.add(article);
		article = new ArticleDTO();
		article.setTitle("我是一个莫得感情的青团子");
		article.setId("3245");
		article.setBackendContent("我是一个莫得感情的青团子");
		articles.add(article);
		for(int i =0; i< 3;i++){
			EditionDTO edition = new EditionDTO();
			edition.setId("12345678");
			edition.setUserId(JwtUtils.getCurrentUserId());
			edition.setTitle("残酷天使的行动纲领");
			edition.setArticles(articles);
			editions.add(edition);
		}
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
		rs.setCode(ApiCodeEnum.SUCCESS);
		return rs;		
	}
}
