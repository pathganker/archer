/**   */
package cn.com.qingqfeng.archer.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.com.qingqfeng.archer.pojo.article.ArticleDTO;
import cn.com.qingqfeng.archer.pojo.article.ArticleQuery;
import cn.com.qingqfeng.archer.service.article.IArticleService;

/**   
 * <p>类名称: ArticleServiceTest </p> 
 * <p>描述: TODO  </p>
 * <p>创建时间 : 2019年3月6日 上午10:59:27 </p>
 * @author lijunliang
 * @version 1.0
 * 
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleServiceTest {
	@Autowired
	private IArticleService articleService;
	
	@Test
	public void queryTest(){
		ArticleQuery query = new ArticleQuery(0,10,null,null);
		List<ArticleDTO> articles = this.articleService.requestArticleByOptions(query);
		ArticleDTO article = this.articleService.requestArticleById("bdbdd26f-5df8-4b4a-9b07-69a8b22ac417");
		Long total = this.articleService.requestArticleTotal(query);
		List<ArticleDTO> test = this.articleService.requestPreNext("bdbdd26f-5df8-4b4a-9b07-69a8b22ac417", query);
		System.out.println(articles);
		System.out.println(article);
		System.out.println(total);
		System.out.println(test);
	}
	
	
	@Test 
	public void deleteTest(){
		this.articleService.deleteArticle("bdbdd26f-5df8-4b4a-9b07-69a8b22ac417");
	}
}
