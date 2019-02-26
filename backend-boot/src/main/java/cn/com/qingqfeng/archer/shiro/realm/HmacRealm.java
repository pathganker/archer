/**   */
package cn.com.qingqfeng.archer.shiro.realm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import cn.com.qingqfeng.archer.service.user.IUserService;
import cn.com.qingqfeng.archer.shiro.service.CryptogramService;
import cn.com.qingqfeng.archer.shiro.token.HmacToken;
import cn.com.qingqfeng.archer.shiro.token.JwtPlayload;

/**   
 * <p>类名称: HamcRealm </p> 
 * <p>描述: TODO  </p>
 * <p>创建时间 : 2019年2月26日 上午10:03:23 </p>
 * @author lijunliang
 * @version 1.0
 * 
 */
public class HmacRealm extends AuthorizingRealm{
	private  IUserService userService;//账号服务(持久化服务)
    private  CryptogramService cryptogramService;//密码服务
    
    public HmacRealm(){
    	
    }
    public HmacRealm(IUserService userService,CryptogramService userPwdService){
        this.setUserService(userService);
        this.setCryptogramService(userPwdService);
    }
    
    public Class<?> getAuthenticationTokenClass() {
        return HmacToken.class;//此Realm只支持HmacToken
    }
    
    /**
     *  认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) 
                                                          throws AuthenticationException {
        HmacToken hmacToken = (HmacToken)token;
        List<String> keys = new ArrayList<String>();
        for (String key:hmacToken.getParameters().keySet()){
            if (!"digest".equals(key))
                keys.add(key);
        }
        Collections.sort(keys);//对请求参数进行排序参数->自然顺序
        StringBuffer baseString = new StringBuffer();
        for (String key : keys) {
            baseString.append(hmacToken.getParameters().get(key));
        }
        //认证端生成摘要
        String serverDigest = cryptogramService.hmacDigest(baseString.toString());
        //客户端请求的摘要和服务端生成的摘要不同
        if(!serverDigest.equals(hmacToken.getDigest())){
            throw new AuthenticationException("数字摘要验证失败！！！");
        }
        Long visitTimeStamp = hmacToken.getTimestamp();
        Long nowTimeStamp = System.currentTimeMillis();
        Long jge = nowTimeStamp - visitTimeStamp;
        if (jge > 600000) {// 十分钟之前的时间戳，这是有效期可以双方约定由参数传过来
            throw new AuthenticationException("数字摘要失效！！！");
        }
        // 此处可以添加查询数据库检查账号是否存在、是否被锁定、是否被禁用等等逻辑
        return new SimpleAuthenticationInfo(hmacToken.getClientKey(),Boolean.TRUE,getName());
    }
    
    /** 
     * 授权 
     */  
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    	JwtPlayload playload = (JwtPlayload) principals.getPrimaryPrincipal();
//        String clientKey = (String)principals.getPrimaryPrincipal();
        String clientKey = playload.getUserId();
        SimpleAuthorizationInfo info =  new SimpleAuthorizationInfo();
        // 根据客户标识（可以是用户名、app id等等） 查询并设置角色
        Set<String> roles = userService.requestRoleIdByName(clientKey);
        info.setRoles(roles);
      // 根据客户标识（可以是用户名、app id等等） 查询并设置权限
        Set<String> permissions = userService.requestPermiUrlsByName(clientKey);
        info.setStringPermissions(permissions);
        return info;  
    }

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public CryptogramService getCryptogramService() {
		return cryptogramService;
	}

	public void setCryptogramService(CryptogramService cryptogramService) {
		this.cryptogramService = cryptogramService;
	}
}
