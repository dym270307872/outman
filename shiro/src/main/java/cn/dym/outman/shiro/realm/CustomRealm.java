package cn.dym.outman.shiro.realm;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @author zhouguanglin
 * @date 2018/2/26 14:05
 */
public class CustomRealm extends AuthorizingRealm {
    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String userName = (String) principalCollection.getPrimaryPrincipal();
        List<String> permissionList=new ArrayList<String>();
        permissionList.add("user:add");
        permissionList.add("user:delete");
        if (userName.equals("zhou")) {
            permissionList.add("user:query");
        }
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        info.addStringPermissions(permissionList);
        info.addRole("admin");
        return info;
    }
    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String userName = (String) authenticationToken.getPrincipal();
        if ("".equals(userName)) {
            return  null;
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userName,"123456",this.getName());
        return info;
    }
}
