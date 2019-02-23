/**   */
package cn.com.qingqfeng.archer.shiro.session;

import java.util.Date;

import org.apache.shiro.session.Session;

/**   
 * <p>类名称: SessionInMemory </p> 
 * <p>描述: TODO  </p>
 * <p>创建时间 : 2019年2月23日 下午2:10:02 </p>
 * @author lijunliang
 * @version 1.0
 * 
 */
public class SessionInMemory {
    private Session session;
    private Date createTime;

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
