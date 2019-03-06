/**
 * 
 */
package cn.com.qingqfeng.archer.dao.record;

import cn.com.qingqfeng.archer.pojo.record.ArticleVisitDO;

/**   
 * <p> 类名：  IArticleVisitDao   </p>
 * <p> 描述：	TODO  </p>
 * <p> 创建时间： 2019年3月7日 上午1:07:12   </p>
 * @author lijunliang 
 * @version 1.0   
 */
public interface IVisitRecordDao {
	/**
	 * 
	 * <p> 方法名 ：addArticleVisitRecord  </p> 
	 * <p> 描述 ：TODO </p> 
	 * <p> 创建时间  ： 2019年3月7日 上午1:08:16 </p>  
	 * @param record           
	 * @author lijunliang 
	 * @version 1.0
	 */
	void addArticleVisitRecord(ArticleVisitDO record);
}
