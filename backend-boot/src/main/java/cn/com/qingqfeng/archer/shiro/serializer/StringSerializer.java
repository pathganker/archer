/**   */
package cn.com.qingqfeng.archer.shiro.serializer;

import java.io.UnsupportedEncodingException;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

/**   
 * <p>类名称: StringSerializer </p> 
 * <p>描述: TODO  </p>
 * <p>创建时间 : 2019年2月25日 上午11:19:51 </p>
 * @author lijunliang
 * @version 1.0
 * 
 */
public class StringSerializer implements RedisSerializer<String>{
	
    private static final String DEFAULT_CHARSET = "UTF-8";

    /**
     * Refer to https://docs.oracle.com/javase/8/docs/technotes/guides/intl/encoding.doc.html
     * UTF-8, UTF-16, UTF-32, ISO-8859-1, GBK, Big5, etc
     */
    private String charset = DEFAULT_CHARSET;

	/** (non-Javadoc)
	 * @see org.springframework.data.redis.serializer.RedisSerializer#serialize(java.lang.Object)
	 */
	@Override
	public byte[] serialize(String s) throws SerializationException {
        try {
            return (s == null ? null : s.getBytes(charset));
        } catch (UnsupportedEncodingException e) {
            throw new SerializationException("serialize error, string=" + s, e);
        }
	}

	/** (non-Javadoc)
	 * @see org.springframework.data.redis.serializer.RedisSerializer#deserialize(byte[])
	 */
	@Override
	public String deserialize(byte[] bytes) throws SerializationException {
        try {
            return (bytes == null ? null : new String(bytes, charset));
        } catch (UnsupportedEncodingException e) {
            throw new SerializationException("deserialize error", e);
        }
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

}
