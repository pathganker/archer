/**   */
package cn.com.qingqfeng.archer.dao.edition;

import java.util.List;

import cn.com.qingqfeng.archer.pojo.edition.EditionDO;

/**   
 * <p>类名称: IEditionDao </p> 
 * <p>描述: TODO  </p>
 * <p>创建时间 : 2019年3月5日 下午5:55:18 </p>
 * @author lijunliang
 * @version 1.0
 * 
 */
public interface IEditionDao {
	/**
	 * 
	 * <p>方法名:  queryEditionsByUserId </p> 
	 * <p>描述:    TODO </p>
	 * <p>创建时间:  2019年3月6日下午4:18:55 </p>
	 * @version 1.0
	 * @author lijunliang
	 * @param userId
	 * @return  
	 * List<EditionDO>
	 */
	List<EditionDO> queryEditionsByUserId(String userId);
	/**
	 * 
	 * <p>方法名:  addEdition </p> 
	 * <p>描述:    TODO </p>
	 * <p>创建时间:  2019年3月6日下午4:18:58 </p>
	 * @version 1.0
	 * @author lijunliang
	 * @param edition  
	 * void
	 */
	void addEdition(EditionDO edition);
	/**
	 * 
	 * <p>方法名:  updateEdition </p> 
	 * <p>描述:    TODO </p>
	 * <p>创建时间:  2019年3月6日下午4:19:00 </p>
	 * @version 1.0
	 * @author lijunliang
	 * @param edition  
	 * void
	 */
	void updateEdition(EditionDO edition);
	/**
	 * 
	 * <p>方法名:  deleteEdition </p> 
	 * <p>描述:    TODO </p>
	 * <p>创建时间:  2019年3月6日下午4:24:50 </p>
	 * @version 1.0
	 * @author lijunliang
	 * @param id  
	 * void
	 */
	void deleteEdition(String id);
}
