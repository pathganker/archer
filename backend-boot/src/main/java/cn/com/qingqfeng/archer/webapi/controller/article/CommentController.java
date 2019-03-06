/**   */
package cn.com.qingqfeng.archer.webapi.controller.article;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.qingqfeng.archer.enums.ApiCodeEnum;
import cn.com.qingqfeng.archer.pojo.Result;
import cn.com.qingqfeng.archer.pojo.comment.CommentDTO;
import cn.com.qingqfeng.archer.pojo.reply.ReplyDTO;
import cn.com.qingqfeng.archer.service.comment.ICommentServcie;

/**   
 * <p>类名称: CommentController </p> 
 * <p>描述: TODO  </p>
 * <p>创建时间 : 2019年3月6日 下午4:52:57 </p>
 * @author lijunliang
 * @version 1.0
 * 
 */
@RestController
@RequestMapping("comment")
public class CommentController {
	
	@Autowired
	private ICommentServcie commentService;
	
	/**
	 * 
	 * <p>方法名:  getFrontCommentList </p> 
	 * <p>描述:    TODO </p>
	 * <p>创建时间:  2019年3月6日下午6:33:36 </p>
	 * @version 1.0
	 * @author lijunliang
	 * @param id
	 * @return  
	 * Result
	 */
	@RequestMapping(value="",method={RequestMethod.GET})
	public Result getFrontCommentList(String id){
		Result rs = new Result();
		List<CommentDTO> comment = this.commentService.requestCommentByArticleId(id);
		if(null == comment || comment.isEmpty()){
			rs.setCode(ApiCodeEnum.NO_RESULT);
			return rs;
		}
		rs.setCode(ApiCodeEnum.SUCCESS);
		rs.setData(comment);
		return rs;
	}
	/**
	 * 
	 * <p>方法名:  addNewComment </p> 
	 * <p>描述:    TODO </p>
	 * <p>创建时间:  2019年3月6日下午6:33:39 </p>
	 * @version 1.0
	 * @author lijunliang
	 * @param comment
	 * @return  
	 * Result
	 */
	@RequestMapping(value="new",method={RequestMethod.GET})
	public Result addNewComment(@RequestBody CommentDTO comment){
		Result rs = new Result();
		this.commentService.addComment(comment);
		rs.setCode(ApiCodeEnum.SUCCESS);
		return rs;
	}
	/**
	 * 
	 * <p>方法名:  addReply </p> 
	 * <p>描述:    TODO </p>
	 * <p>创建时间:  2019年3月6日下午6:33:41 </p>
	 * @version 1.0
	 * @author lijunliang
	 * @param reply
	 * @return  
	 * Result
	 */
	@RequestMapping(value="reply",method={RequestMethod.POST})
	public Result addReply(@RequestBody ReplyDTO reply){
		Result rs = new Result();
		this.commentService.addReply(reply);
		rs.setCode(ApiCodeEnum.SUCCESS);
		return rs;
	}
	/**
	 * 
	 * <p>方法名:  delCommnet </p> 
	 * <p>描述:    TODO </p>
	 * <p>创建时间:  2019年3月6日下午6:33:44 </p>
	 * @version 1.0
	 * @author lijunliang
	 * @param id
	 * @return  
	 * Result
	 */
	@RequestMapping(value="less",method={RequestMethod.DELETE})
	public Result delCommnet(String id){
		Result rs = new Result();
		this.commentService.deleteCommentById(id);
		rs.setCode(ApiCodeEnum.SUCCESS);
		return rs;
	}
}
