/**   */
package cn.com.qingqfeng.archer.shiro.realm;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.DatatypeConverter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import cn.com.qingqfeng.archer.shiro.token.JwtPlayload;
import cn.com.qingqfeng.archer.shiro.token.JwtToken;

/**   
 * <p>类名称: JwtRealm </p> 
 * <p>描述: TODO  </p>
 * <p>创建时间 : 2019年2月26日 上午10:45:56 </p>
 * @author lijunliang
 * @version 1.0
 * 
 */
public class JwtRealm extends AuthorizingRealm{
	private final static String SECRET_KEY = "*(-=4eklfasdfarerf0417fdasf";
	
    public Class<?> getAuthenticationTokenClass() {
        return JwtToken.class;//此Realm只支持JwtToken
    }
	/** (non-Javadoc)
	 * @see org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache.shiro.subject.PrincipalCollection)
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
        JwtPlayload jwtPlayload = (JwtPlayload) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 解析角色并设置
        Set<String> roles = new HashSet<String>(Arrays.asList(StringUtils.split(jwtPlayload.getRoles(), ",")));
        info.setRoles(roles);
        // 解析权限并设置
        Set<String> permissions = new HashSet<String>(Arrays.asList(StringUtils.split(jwtPlayload.getPerms(), ",")));
        info.setStringPermissions(permissions);
        return info;

	}

	/** (non-Javadoc)
	 * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
        JwtToken jwtToken = (JwtToken) token;
        String jwt = (String) jwtToken.getPrincipal();
        JwtPlayload jwtPlayload;
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                    .parseClaimsJws(jwt)
                    .getBody();
            jwtPlayload = new JwtPlayload();
            jwtPlayload.setId(claims.getId());
            jwtPlayload.setUserId(claims.getSubject());// 用户名
            jwtPlayload.setIssuer(claims.getIssuer());// 签发者
            jwtPlayload.setIssuedAt(claims.getIssuedAt());// 签发时间
            jwtPlayload.setAudience(claims.getAudience());// 接收方
            jwtPlayload.setRoles(claims.get("roles", String.class));// 访问主张-角色
            jwtPlayload.setPerms(claims.get("perms", String.class));// 访问主张-权限
        } catch (ExpiredJwtException e) {
            throw new AuthenticationException("JWT 令牌过期:" + e.getMessage());
        } catch (UnsupportedJwtException e) {
            throw new AuthenticationException("JWT 令牌无效:" + e.getMessage());
        } catch (MalformedJwtException e) {
            throw new AuthenticationException("JWT 令牌格式错误:" + e.getMessage());
        } catch (SignatureException e) {
            throw new AuthenticationException("JWT 令牌签名无效:" + e.getMessage());
        } catch (IllegalArgumentException e) {
            throw new AuthenticationException("JWT 令牌参数异常:" + e.getMessage());
        } catch (Exception e) {
            throw new AuthenticationException("JWT 令牌错误:" + e.getMessage());
        }
        // 如果要使token只能使用一次，此处可以过滤并缓存jwtPlayload.getId()
        // 可以做签发方验证
        // 可以做接收方验证
        return new SimpleAuthenticationInfo(jwtPlayload, Boolean.TRUE, getName());
	}

}
