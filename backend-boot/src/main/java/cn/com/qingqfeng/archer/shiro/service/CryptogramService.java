/**   */
package cn.com.qingqfeng.archer.shiro.service;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.shiro.authc.credential.PasswordService;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Service;

import cn.com.qingqfeng.archer.pojo.user.UserDTO;

/**   
 * <p>类名称: PasswordService </p> 
 * <p>描述: TODO  </p>
 * <p>创建时间 : 2019年2月25日 下午4:19:41 </p>
 * @author lijunliang
 * @version 1.0
 * 
 */
@Service
public class CryptogramService implements PasswordService{
	
	
    private String algorithmName = "md5";  
    private final int hashIterations = 2; 
    private static final String SECRET_KEY = "*(-=4eklfasdfarerf41585fdasf";
	/** (non-Javadoc)
	 * @see org.apache.shiro.authc.credential.PasswordService#encryptPassword(java.lang.Object)
	 */
	@Override
	public String encryptPassword(Object plaintextPassword)
			throws IllegalArgumentException {
		UserDTO user= (UserDTO) plaintextPassword;
		String password = new SimpleHash(algorithmName, user.getPassword(),
				ByteSource.Util.bytes(user.getCredentialsSalt()),
				hashIterations).toHex();
		return password;
	}

	/** (non-Javadoc)
	 * @see org.apache.shiro.authc.credential.PasswordService#passwordsMatch(java.lang.Object, java.lang.String)
	 */
	@Override
	public boolean passwordsMatch(Object submittedPlaintext, String encrypted) {
		UserDTO user = (UserDTO) submittedPlaintext;
		String submitPassword = new SimpleHash(algorithmName, user.getPassword(),
				ByteSource.Util.bytes(user.getCredentialsSalt()),
				hashIterations).toHex();
		return submitPassword.equals(encrypted);
	}
	/**
	 * 
	 * <p>方法名:  hamcDigest </p> 
	 * <p>描述:    TODO </p>
	 * <p>创建时间:  2019年2月26日上午10:19:26 </p>
	 * @version 1.0
	 * @author lijunliang
	 * @param data
	 * @return  
	 * String
	 */
	public String hmacDigest(String data){
		String checksum = null;
		try{
			Mac mac = Mac.getInstance("HmacMD5");
			byte[] secretBytes = SECRET_KEY.getBytes("UTF-8");
			byte[] dataBytes = data.getBytes("UTF-8");
			SecretKey secret = new SecretKeySpec(secretBytes, "HmacMD5");
			mac.init(secret);
			byte[] doFinal = mac.doFinal(dataBytes);
			checksum = Hex.encodeToString(doFinal);
		}catch(Exception e){
			//TODO
			e.printStackTrace();
		}
		return checksum;
	}

}
