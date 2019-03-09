/**   */
package cn.com.qingqfeng.archer.utils;

import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

import javax.xml.bind.DatatypeConverter;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;

import cn.com.qingqfeng.archer.shiro.token.JwtPlayload;

/**   
 * <p>类名称: JwtUtils </p> 
 * <p>描述: TODO  </p>
 * <p>创建时间 : 2019年2月27日 下午1:20:15 </p>
 * @author lijunliang
 * @version 1.0
 * 
 */
public class JwtUtils {
	
	private final static String SECRET_KEY = "*(-=4eklfasdfarerf0417fdasf";
	
    public static String issueJwt(String id, String subject, String issuer, Long period,
    		String roles, String permissions, String host, SignatureAlgorithm algorithm) {
		long currentTimeMillis = System.currentTimeMillis();// 当前时间戳
		byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);// 秘钥
		JwtBuilder jwt  =  Jwts.builder();
		if(StringUtils.isNotBlank(id)) 
			jwt.setId(id);
		jwt.setSubject(subject);// 用户名主题
		if(StringUtils.isNotBlank(issuer)) 
			jwt.setIssuer(issuer);//签发者
		jwt.setIssuedAt(new Date(currentTimeMillis));//签发时间
		if(null != period){
			Date expiration = new Date(currentTimeMillis+period);
			jwt.setExpiration(expiration);//有效时间
		}
		if(StringUtils.isNotBlank(roles)) 
			jwt.claim("roles", roles);//角色
		if(StringUtils.isNotBlank(permissions)) 
			jwt.claim("perms", permissions);//权限
		if(StringUtils.isNotBlank(host)) {
			jwt.claim("host", host);
		}
		jwt.compressWith(CompressionCodecs.DEFLATE);//压缩，可选GZIP
		jwt.signWith(algorithm, secretKeyBytes);//加密设置
		return jwt.compact();
	}
    
    public static String getCurrentUserId(){
    	try{
    		JwtPlayload jwtPlayload =(JwtPlayload) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
    		if(null != jwtPlayload){
    			return jwtPlayload.getId();
    		}
    	}catch(Exception e){
    		return null;
    	}
    	return null;
    }
}
