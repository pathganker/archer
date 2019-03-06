/**   */
package cn.com.qingqfeng.archer.service.comment.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.qingqfeng.archer.dao.comment.ICommentDao;
import cn.com.qingqfeng.archer.pojo.comment.CommentDO;
import cn.com.qingqfeng.archer.pojo.comment.CommentDTO;
import cn.com.qingqfeng.archer.pojo.reply.ReplyDO;
import cn.com.qingqfeng.archer.pojo.reply.ReplyDTO;
import cn.com.qingqfeng.archer.service.comment.ICommentServcie;

/**   
 * <p>类名称: CommentServiceImpl </p> 
 * <p>描述: TODO  </p>
 * <p>创建时间 : 2019年3月6日 下午5:10:40 </p>
 * @author lijunliang
 * @version 1.0
 * 
 */
@Service
public class CommentServiceImpl implements ICommentServcie{
	
	@Autowired
	private ICommentDao commentDao;

	/** (non-Javadoc)
	 * @see cn.com.qingqfeng.archer.service.comment.ICommentServcie#requestCommentByArticleId(java.lang.String)
	 */
	@Override
	public List<CommentDTO> requestCommentByArticleId(String id) {
		List<CommentDTO> comments = new ArrayList<CommentDTO>();
		List<CommentDO> cdos = this.commentDao.queryCommentByArticleId(id);
		if(null == cdos || cdos.isEmpty()){
			return comments;
		}
		for(CommentDO cdo: cdos){
			CommentDTO comment = new CommentDTO();
			BeanUtils.copyProperties(cdo, comment);
			if(null!= cdo.getReplys() && !cdo.getReplys().isEmpty()){
				List<ReplyDTO> replys = new ArrayList<ReplyDTO>();
				for(ReplyDO rdo: cdo.getReplys()){
					ReplyDTO reply = new ReplyDTO();
					BeanUtils.copyProperties(rdo, reply);
					replys.add(reply);
				}
				comment.setReplys(replys);
			}
			comments.add(comment);
		}
		return comments;
	}

	/** (non-Javadoc)
	 * @see cn.com.qingqfeng.archer.service.comment.ICommentServcie#addComment(cn.com.qingqfeng.archer.pojo.comment.CommentDTO)
	 */
	@Override
	public void addComment(CommentDTO comment) {
		CommentDO cdo = new CommentDO();
		BeanUtils.copyProperties(comment, cdo);
		cdo.setCreateTime(new Date());
		this.commentDao.addCommment(cdo);
		
	}

	/** (non-Javadoc)
	 * @see cn.com.qingqfeng.archer.service.comment.ICommentServcie#deleteCommentById(java.lang.String)
	 */
	@Override
	public void deleteCommentById(String id) {
		if(StringUtils.isBlank(id)){
			return;
		}
		this.commentDao.deleteCommment(id);
		this.commentDao.deleteReplyByCommentId(id);
		
	}

	/** (non-Javadoc)
	 * @see cn.com.qingqfeng.archer.service.comment.ICommentServcie#addReply(cn.com.qingqfeng.archer.pojo.reply.ReplyDTO)
	 */
	@Override
	public void addReply(ReplyDTO reply) {
		ReplyDO rdo = new ReplyDO();
		BeanUtils.copyProperties(reply, rdo);
		this.commentDao.addReply(rdo);
		
	}


}
