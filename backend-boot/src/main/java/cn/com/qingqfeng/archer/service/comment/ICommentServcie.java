/**   */
package cn.com.qingqfeng.archer.service.comment;

import java.util.List;

import cn.com.qingqfeng.archer.pojo.comment.CommentDTO;
import cn.com.qingqfeng.archer.pojo.reply.ReplyDTO;

/**   
 * <p>类名称: ICommentServcie </p> 
 * <p>描述: TODO  </p>
 * <p>创建时间 : 2019年3月6日 下午5:02:04 </p>
 * @author lijunliang
 * @version 1.0
 * 
 */
public interface ICommentServcie {
	/**
	 * 
	 * <p>方法名:  requestCommentByArticleId </p> 
	 * <p>描述:    TODO </p>
	 * <p>创建时间:  2019年3月6日下午5:10:00 </p>
	 * @version 1.0
	 * @author lijunliang
	 * @param id
	 * @return  
	 * List<CommentDTO>
	 */
	List<CommentDTO> requestCommentByArticleId(String id);
	/**
	 * 
	 * <p>方法名:  addComment </p> 
	 * <p>描述:    TODO </p>
	 * <p>创建时间:  2019年3月6日下午5:10:02 </p>
	 * @version 1.0
	 * @author lijunliang
	 * @param comment  
	 * void
	 */
	void addComment(CommentDTO comment);
	/**
	 * 
	 * <p>方法名:  updateComment </p> 
	 * <p>描述:    TODO </p>
	 * <p>创建时间:  2019年3月6日下午5:10:04 </p>
	 * @version 1.0
	 * @author lijunliang
	 * @param comment  
	 * void
	 */
	void deleteCommentById(String id);
	/**
	 * 
	 * <p>方法名:  addReply </p> 
	 * <p>描述:    TODO </p>
	 * <p>创建时间:  2019年3月6日下午5:54:28 </p>
	 * @version 1.0
	 * @author lijunliang
	 * @param reply  
	 * void
	 */
	void addReply(ReplyDTO reply);
}
