/**
 * 
 */
package cn.com.qingqfeng.archer.webapi.controller.article;

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
import cn.com.qingqfeng.archer.pojo.edition.EditionDTO;
import cn.com.qingqfeng.archer.service.article.IArticleService;
import cn.com.qingqfeng.archer.service.edition.IEditionService;

/**   
 * <p> 类名：  CategoryController   </p>
 * <p> 描述：	TODO  </p>
 * <p> 创建时间： 2019年3月17日 下午1:49:00   </p>
 * @author lijunliang 
 * @version 1.0   
 */
@RestController
@RequestMapping("category")
public class CategoryController {
	
	@Autowired
	private IArticleService articleService;
	@Autowired
	private IEditionService editionService;
	/**
	 * 
	 * <p> 方法名 ：getCategory  </p> 
	 * <p> 描述 ：TODO </p> 
	 * <p> 创建时间  ： 2019年3月17日 下午2:20:46 </p>  
	 * @return           
	 * @author lijunliang 
	 * @version 1.0
	 */
	@RequestMapping(value="front",method=RequestMethod.GET)
	public Result getCategory() {
		Result rs = new Result();
		String userId = "bdbdd26f-5df8-4b4a-9b07-69a8b22ac327";
		List<EditionDTO> editions = this.editionService.requestEditionFront(userId);
		rs.setCode(ApiCodeEnum.SUCCESS);
		rs.setData(editions);
		return rs;
	}
	/**
	 * 
	 * <p> 方法名 ：getArticleList  </p> 
	 * <p> 描述 ：TODO </p> 
	 * <p> 创建时间  ： 2019年3月17日 下午2:20:50 </p>  
	 * @param id
	 * @return           
	 * @author lijunliang 
	 * @version 1.0
	 */
	@RequestMapping(value="edition",method=RequestMethod.GET)
	public Result getArticleList(String id) {
		Result rs = new Result();
		Map<String, Object> data = new HashMap<>();
		EditionDTO edition = this.editionService.requestEditionById(id);
		List<ArticleDTO> articles = this.articleService.requestArticleByEdition(id);
		if(null !=edition) {
			data.put("name", edition.getTitle());
		}
		data.put("articles", articles);
		rs.setCode(ApiCodeEnum.SUCCESS);
		rs.setData(data);
		return rs;
	}
}
