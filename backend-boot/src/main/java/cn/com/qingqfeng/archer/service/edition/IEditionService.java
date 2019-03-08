/**   */
package cn.com.qingqfeng.archer.service.edition;

import java.util.List;

import cn.com.qingqfeng.archer.pojo.edition.EditionDTO;

/**   
 * <p>类名称: IEditionService </p> 
 * <p>描述: TODO  </p>
 * <p>创建时间 : 2019年3月6日 下午3:10:35 </p>
 * @author lijunliang
 * @version 1.0
 * 
 */
public interface IEditionService {
	/**
	 * 
	 * <p>方法名:  requestEditionsByUserId </p> 
	 * <p>描述:    TODO </p>
	 * <p>创建时间:  2019年3月8日下午6:02:07 </p>
	 * @version 1.0
	 * @author lijunliang
	 * @param userId
	 * @return  
	 * List<EditionDTO>
	 */
	List<EditionDTO> requestEditionsByUserId(String userId);
	/**
	 * 
	 * <p>方法名:  addEdition </p> 
	 * <p>描述:    TODO </p>
	 * <p>创建时间:  2019年3月8日下午6:02:10 </p>
	 * @version 1.0
	 * @author lijunliang
	 * @param edition  
	 * void
	 */
	void addEdition(EditionDTO edition);
	/**
	 * 
	 * <p>方法名:  updateEdition </p> 
	 * <p>描述:    TODO </p>
	 * <p>创建时间:  2019年3月8日下午6:02:12 </p>
	 * @version 1.0
	 * @author lijunliang
	 * @param edition  
	 * void
	 */
	void updateEdition(EditionDTO edition);
	/**
	 * 
	 * <p>方法名:  deleteEdition </p> 
	 * <p>描述:    只删除文集 </p>
	 * <p>创建时间:  2019年3月8日下午6:02:15 </p>
	 * @version 1.0
	 * @author lijunliang
	 * @param id  
	 * void
	 */
	void deleteEdition(String id);
	/**
	 * 
	 * <p>方法名:  deleteEditionWhole </p> 
	 * <p>描述:    删除文集和文章 </p>
	 * <p>创建时间:  2019年3月8日下午6:02:36 </p>
	 * @version 1.0
	 * @author lijunliang
	 * @param id  
	 * void
	 */
	void deleteEditionWhole(String id);
}
