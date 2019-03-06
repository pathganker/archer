/**   */
package cn.com.qingqfeng.archer.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.com.qingqfeng.archer.pojo.comment.CommentDTO;
import cn.com.qingqfeng.archer.service.comment.ICommentServcie;

/**   
 * <p>类名称: CommentService </p> 
 * <p>描述: TODO  </p>
 * <p>创建时间 : 2019年3月6日 下午6:45:15 </p>
 * @author lijunliang
 * @version 1.0
 * 
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentService {
	@Autowired
	private ICommentServcie commentService;
	
	@Test
	public void queryTest(){
		List<CommentDTO> comments = this.commentService.requestCommentByArticleId("bdbdd26f-5df8-4b4a-9b07-69a8b22ac417");
		System.out.println(comments);
	}
}
