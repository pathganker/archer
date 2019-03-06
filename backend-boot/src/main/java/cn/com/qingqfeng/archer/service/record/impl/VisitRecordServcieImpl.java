/**
 * 
 */
package cn.com.qingqfeng.archer.service.record.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.qingqfeng.archer.dao.record.IVisitRecordDao;
import cn.com.qingqfeng.archer.pojo.record.ArticleVisitDO;
import cn.com.qingqfeng.archer.pojo.record.ArticleVisitDTO;
import cn.com.qingqfeng.archer.service.record.IVisitRecordService;

/**   
 * <p> 类名：  VisitRecordServcieImpl   </p>
 * <p> 描述：	TODO  </p>
 * <p> 创建时间： 2019年3月7日 上午1:11:25   </p>
 * @author lijunliang 
 * @version 1.0   
 */
@Service
public class VisitRecordServcieImpl implements IVisitRecordService{
	@Autowired
	private IVisitRecordDao visitRecordDao;

	/**
	 * 
	 * <p>Title: addArticleVisitRecord</p>   
	 * <p>Description: </p>   
	 * @param record   
	 * @see cn.com.qingqfeng.archer.service.record.IVisitRecordService#addArticleVisitRecord(cn.com.qingqfeng.archer.pojo.record.ArticleVisitDO)
	 */
	@Override
	public void addArticleVisitRecord(ArticleVisitDTO record) {
		ArticleVisitDO ado = new ArticleVisitDO();
		BeanUtils.copyProperties(record, ado);
		this.visitRecordDao.addArticleVisitRecord(ado);
	}
	
}
