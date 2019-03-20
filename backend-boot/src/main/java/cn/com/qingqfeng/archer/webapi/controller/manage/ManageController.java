/**   */
package cn.com.qingqfeng.archer.webapi.controller.manage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.qingqfeng.archer.enums.ApiCodeEnum;
import cn.com.qingqfeng.archer.pojo.Result;
import cn.com.qingqfeng.archer.pojo.article.ArticleDTO;
import cn.com.qingqfeng.archer.pojo.article.ArticleQuery;
import cn.com.qingqfeng.archer.service.article.IArticleService;

/**   
 * <p>类名称: ManageController </p> 
 * <p>描述: TODO  </p>
 * <p>创建时间 : 2019年3月20日 上午10:47:49 </p>
 * @author lijunliang
 * @version 1.0
 * 
 */
@RestController
@RequestMapping("manage")
public class ManageController {
	
	@Autowired
	private IArticleService articleService;
	/**
	 * 
	 * <p>方法名:  getFrontArticleList </p> 
	 * <p>描述:    分页获取需要管理的文章 </p>
	 * <p>创建时间:  2019年3月20日上午11:11:05 </p>
	 * @version 1.0
	 * @author lijunliang
	 * @param page
	 * @param pageSize
	 * @param tag
	 * @param sortName
	 * @param sortType
	 * @return  
	 * Result
	 */
	@RequestMapping(value="article/list", method={RequestMethod.GET})
	public Result getFrontArticleList(Integer page, Integer pageSize, String tag, String sortName, String sortType) {
		Result result = new Result();
		ArticleQuery query = new ArticleQuery(page, pageSize, sortName, sortType);
		query.setTag(tag);
		List<ArticleDTO> articles = this.articleService.requestArticleManage(query);
		if(null == articles || articles.isEmpty()){
			result.setCode(ApiCodeEnum.NO_RESULT);
			return result;
		}
		Long total = this.articleService.requestArticleManageTotal(query);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("articles", articles);
		data.put("total", total);
		result.setCode(ApiCodeEnum.SUCCESS);
		result.setData(data);
		return result;
	}

}
