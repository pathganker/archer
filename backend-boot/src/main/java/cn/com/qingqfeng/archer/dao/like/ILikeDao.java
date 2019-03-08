/**   */
package cn.com.qingqfeng.archer.dao.like;

import java.util.List;

import cn.com.qingqfeng.archer.pojo.like.LikeDO;

/**   
 * <p>类名称: ILikeDao </p> 
 * <p>描述: TODO  </p>
 * <p>创建时间 : 2019年3月7日 下午12:52:47 </p>
 * @author lijunliang
 * @version 1.0
 * 
 */
public interface ILikeDao {
	/**
	 * 
	 * <p>方法名:  addLike </p> 
	 * <p>描述:    TODO </p>
	 * <p>创建时间:  2019年3月7日下午12:58:25 </p>
	 * @version 1.0
	 * @author lijunliang
	 * @param like  
	 * void
	 */
	void addLike(LikeDO like);
	/**
	 * 
	 * <p>方法名:  cancelLike </p> 
	 * <p>描述:    TODO </p>
	 * <p>创建时间:  2019年3月7日下午12:58:27 </p>
	 * @version 1.0
	 * @author lijunliang
	 * @param userId
	 * @param articleId  
	 * void
	 */
	void deleteLike(String userId, String articleId);
	/**
	 * 
	 * <p>方法名:  queryLike </p> 
	 * <p>描述:    TODO </p>
	 * <p>创建时间:  2019年3月7日下午12:58:31 </p>
	 * @version 1.0
	 * @author lijunliang
	 * @param userId
	 * @param articleId
	 * @return  
	 * LikeDO
	 */
	LikeDO queryLike(String userId, String articleId);
	/**
	 * 
	 * <p>方法名:  queryLikeCount </p> 
	 * <p>描述:    TODO </p>
	 * <p>创建时间:  2019年3月7日下午1:31:34 </p>
	 * @version 1.0
	 * @author lijunliang
	 * @param articleId
	 * @return  
	 * Long
	 */
	Long queryLikeCount(String articleId);
	/**
	 * 
	 * <p>方法名:  queryLikeByUserId </p> 
	 * <p>描述:    TODO </p>
	 * <p>创建时间:  2019年3月7日下午1:40:36 </p>
	 * @version 1.0
	 * @author lijunliang
	 * @param id
	 * @return  
	 * List<String>
	 */
	List<String> queryLikeByUserId(String id);
}
