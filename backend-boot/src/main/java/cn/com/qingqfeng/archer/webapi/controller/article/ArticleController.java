/**
 * 
 */
package cn.com.qingqfeng.archer.webapi.controller.article;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.com.qingqfeng.archer.enums.ApiCodeEnum;
import cn.com.qingqfeng.archer.pojo.Result;
import cn.com.qingqfeng.archer.pojo.article.ArticleDTO;
import cn.com.qingqfeng.archer.pojo.article.ImageDTO;

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
	
	@RequestMapping("getFrontArticleList")
	public Result requestFrontArticleList(Integer curretPage, Integer currentPage, String sortName, String tagId) {
		Result result = new Result();
		result.setCode(ApiCodeEnum.SUCCESS);
		// test data
		List<ArticleDTO> test = new ArrayList<>();
		ArticleDTO article_1 = new ArticleDTO();
		article_1.setId(UUID.randomUUID().toString());
		List<ImageDTO> images = new ArrayList<>();
		ImageDTO image_1 = new ImageDTO();
		image_1.setUrl("/image/body_bg_page.jpg");
		images.add(image_1);
		article_1.setImages(images);
		article_1.setLikeCount(100000);
		article_1.setVisitCount(1000000);
		article_1.setCommentCount(9999999);
		article_1.setTitle("我是一个莫得感情的青团子");
		article_1.setPublishTime(new Date());
		
		ArticleDTO article_2 = new ArticleDTO();
		article_2.setId(UUID.randomUUID().toString());
		article_2.setImages(images);
		article_2.setLikeCount(100000);
		article_2.setVisitCount(1000000);
		article_2.setCommentCount(9999999);
		article_2.setTitle("我是一个杀手，我莫得感情");
		article_2.setPublishTime(new Date());
		
		ArticleDTO article_3 = new ArticleDTO();
		article_3.setId(UUID.randomUUID().toString());
		article_3.setImages(images);
		article_3.setLikeCount(100000);
		article_3.setVisitCount(1000000);
		article_3.setCommentCount(9999999);
		article_3.setTitle("我是一个恶人，我莫得感情");
		article_3.setPublishTime(new Date());
		for (int i =0 ;i<10;i++){
			ArticleDTO article = new ArticleDTO();
			article.setId(UUID.randomUUID().toString());
			article.setImages(images);
			article.setLikeCount(100000);
			article.setVisitCount(1000000);
			article.setCommentCount(9999999);
			article.setTitle("我是一个恶人，我莫得感情");
			article.setPublishTime(new Date());
			test.add(article);
		}
		test.add(article_1);
		test.add(article_2);
		test.add(article_3);
		result.setData(test);
		return result;
	}
}
