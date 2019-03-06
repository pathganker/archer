/**   */
package cn.com.qingqfeng.archer.dao.comment;

import java.util.List;

import cn.com.qingqfeng.archer.pojo.comment.CommentDO;
import cn.com.qingqfeng.archer.pojo.reply.ReplyDO;

/**   
 * <p>类名称: ICommentDao </p> 
 * <p>描述: TODO  </p>
 * <p>创建时间 : 2019年3月5日 下午5:55:07 </p>
 * @author lijunliang
 * @version 1.0
 * 
 */
public interface ICommentDao {
	/**
	 * 
	 * <p>方法名:  queryCommentByArticleId </p> 
	 * <p>描述:    TODO </p>
	 * <p>创建时间:  2019年3月6日下午5:12:45 </p>
	 * @version 1.0
	 * @author lijunliang
	 * @param id
	 * @return  
	 * List<CommentDO>
	 */
	List<CommentDO> queryCommentByArticleId(String id);
	/**
	 * 
	 * <p>方法名:  addCommment </p> 
	 * <p>描述:    TODO </p>
	 * <p>创建时间:  2019年3月6日下午5:12:43 </p>
	 * @version 1.0
	 * @author lijunliang
	 * @param comment  
	 * void
	 */
	void addCommment(CommentDO comment);
	/**
	 * 
	 * <p>方法名:  deleteCommment </p> 
	 * <p>描述:    TODO </p>
	 * <p>创建时间:  2019年3月6日下午5:12:48 </p>
	 * @version 1.0
	 * @author lijunliang
	 * @param id  
	 * void
	 */
	void addReply(ReplyDO replay);
	/**
	 * 
	 * <p>方法名:  deleteCommment </p> 
	 * <p>描述:    TODO </p>
	 * <p>创建时间:  2019年3月6日下午5:53:28 </p>
	 * @version 1.0
	 * @author lijunliang
	 * @param id  
	 * void
	 */
	void deleteCommment(String id);
	/**
	 * 
	 * <p>方法名:  deleteReplyByCommentId </p> 
	 * <p>描述:    TODO </p>
	 * <p>创建时间:  2019年3月6日下午5:53:30 </p>
	 * @version 1.0
	 * @author lijunliang
	 * @param id  
	 * void
	 */
	void deleteReplyByCommentId(String id);
}
