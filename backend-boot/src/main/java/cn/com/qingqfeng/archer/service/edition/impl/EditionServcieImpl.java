/**   */
package cn.com.qingqfeng.archer.service.edition.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.qingqfeng.archer.dao.edition.IEditionDao;
import cn.com.qingqfeng.archer.pojo.article.ArticleDO;
import cn.com.qingqfeng.archer.pojo.article.ArticleDTO;
import cn.com.qingqfeng.archer.pojo.edition.EditionDO;
import cn.com.qingqfeng.archer.pojo.edition.EditionDTO;
import cn.com.qingqfeng.archer.service.edition.IEditionService;

/**   
 * <p>类名称: EditionServcieImpl </p> 
 * <p>描述: TODO  </p>
 * <p>创建时间 : 2019年3月6日 下午3:47:01 </p>
 * @author lijunliang
 * @version 1.0
 * 
 */
@Service
public class EditionServcieImpl implements IEditionService{
	
	@Autowired
	private IEditionDao editionDao;

	/** (non-Javadoc)
	 * @see cn.com.qingqfeng.archer.service.edition.IEditionService#requestEditionsByUserId(java.lang.String)
	 */
	@Override
	public List<EditionDTO> requestEditionsByUserId(String userId) {
		List<EditionDTO> editions = new ArrayList<EditionDTO>();
		if(StringUtils.isBlank(userId)){
			return editions;
		}
		List<EditionDO> edos = this.editionDao.queryEditionsByUserId(userId);
		for(EditionDO edo : edos){
			EditionDTO edition = new EditionDTO();
			BeanUtils.copyProperties(edo, edition);
			if(null != edo.getArticles() && !edo.getArticles().isEmpty()){
				List<ArticleDTO> articles = new ArrayList<ArticleDTO>();
				for(ArticleDO ado : edo.getArticles()){
					ArticleDTO article = new ArticleDTO();
					BeanUtils.copyProperties(ado, article);
					articles.add(article);
				}
				edition.setArticles(articles);
			}
			editions.add(edition);
		}
		return editions;
	}

	/** (non-Javadoc)
	 * @see cn.com.qingqfeng.archer.service.edition.IEditionService#addEdition(cn.com.qingqfeng.archer.pojo.edition.EditionDTO)
	 */
	@Override
	public void addEdition(EditionDTO edition) {
		EditionDO edo = new EditionDO();
		BeanUtils.copyProperties(edition, edo);
		edo.setModifyTime(new Date());
		this.editionDao.addEdition(edo);
	}

	/** (non-Javadoc)
	 * @see cn.com.qingqfeng.archer.service.edition.IEditionService#updateEdition(cn.com.qingqfeng.archer.pojo.edition.EditionDTO)
	 */
	@Override
	public void updateEdition(EditionDTO edition) {
		EditionDO edo = new EditionDO();
		BeanUtils.copyProperties(edition, edo);
		edo.setModifyTime(new Date());
		this.editionDao.updateEdition(edo);
		
	}

	/** (non-Javadoc)
	 * @see cn.com.qingqfeng.archer.service.edition.IEditionService#deleteEdition(java.lang.String)
	 */
	@Override
	public void deleteEdition(String id) {
		if(StringUtils.isBlank(id)){
			return;
		}
		this.editionDao.deleteEdition(id);
	}
	

}
