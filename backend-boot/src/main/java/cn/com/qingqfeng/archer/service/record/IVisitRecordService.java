/**
 * 
 */
package cn.com.qingqfeng.archer.service.record;

import cn.com.qingqfeng.archer.pojo.record.ArticleVisitDTO;

/**   
 * <p> 类名：  IVisitRecordService   </p>
 * <p> 描述：	TODO  </p>
 * <p> 创建时间： 2019年3月7日 上午1:09:44   </p>
 * @author lijunliang 
 * @version 1.0   
 */
public interface IVisitRecordService {
	/**
	 * 
	 * <p> 方法名 ：addArticleVisitRecord  </p> 
	 * <p> 描述 ：TODO </p> 
	 * <p> 创建时间  ： 2019年3月7日 上午1:10:52 </p>  
	 * @param record           
	 * @author lijunliang 
	 * @version 1.0
	 */
	void addArticleVisitRecord(ArticleVisitDTO record);
}
