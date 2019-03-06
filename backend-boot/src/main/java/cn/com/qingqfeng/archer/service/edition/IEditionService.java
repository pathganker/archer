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
	
	List<EditionDTO> requestEditionsByUserId(String userId);
	
	void addEdition(EditionDTO edition);
	
	void updateEdition(EditionDTO edition);
	
	void deleteEdition(String id);
}
