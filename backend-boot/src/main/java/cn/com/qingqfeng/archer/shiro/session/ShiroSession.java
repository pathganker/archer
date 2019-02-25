/**
 * 
 */
package cn.com.qingqfeng.archer.shiro.session;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


import org.apache.shiro.session.mgt.SimpleSession;


/**   
 * <p> 类名：  ShiroSession   </p>
 * <p> 描述：	TODO  </p>
 * <p> 创建时间： 2019年2月24日 上午12:20:40   </p>
 * @author lijunliang 
 * @version 1.0   
 */
public class ShiroSession extends SimpleSession{

	/**
	 * @FieldsserialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	private String sid;
	private String browser;
	private String os;
	@SuppressWarnings("unused")
	private String host;
	
    @SuppressWarnings("unused")
	private Date startTimestamp;
    @SuppressWarnings("unused")
	private Date stopTimestamp;
    @SuppressWarnings("unused")
	private Date lastAccessTime;
    @SuppressWarnings("unused")
	private long timeout;
    @SuppressWarnings("unused")
	private boolean expired;
 
	private Map<Object, Object> attributes;

	public void setStartTimestamp(Date startTimestamp) {
		this.startTimestamp = startTimestamp;
	}
	public void setStopTimestamp(Date stopTimestamp) {
		this.stopTimestamp = stopTimestamp;
	}
	public void setLastAccessTime(Date lastAccessTime) {
		this.lastAccessTime = lastAccessTime;
	}
	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}
	public void setExpired(boolean expired) {
		this.expired = expired;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public void setAttributes(Map<Object, Object> attributes) {
		this.attributes = attributes;
	}
	@Override
	public Serializable getId() {
		return sid;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getBrowser() {
		return browser;
	}
	public void setBrowser(String browser) {
		this.browser = browser;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public String getHost() {
		return super.getHost();
	}
	@Override
	public void setAttribute(Object key, Object value) {
		super.setAttribute(key, value);
	}
	@Override
	public Object removeAttribute(Object key) {
		return super.removeAttribute(key);
	}
    public long getTimeout() {
        return super.getTimeout();
    }
    public boolean isExpired() {
        return super.isExpired();
    }
    public Date getLastAccessTime() {
        return super.getLastAccessTime();
    }
    public Date getStopTimestamp() {
        return super.getStopTimestamp();
    }
    public Date getStartTimestamp() {
        return super.getStartTimestamp();
    }
	public Map<Object, Object> getAttributes(){
        attributes = super.getAttributes();
        if (attributes == null) {
            attributes = new HashMap<Object, Object>();
            setAttributes(attributes);
        }
        return attributes;
	}
	

}
