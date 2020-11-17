package com.leopord.hmall.config.assist;

import com.leopord.hmall.entity.User;
import com.leopord.hmall.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @description:
 * @author: Leopord
 * @time: 2020/11/17 15:38
 */

@Component
public class MyRealm extends AuthorizingRealm {

    @Resource
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String name = token.getPrincipal().toString();
        User user = userService.getUserByName(name);
        if (user == null) {
            throw new UnknownAccountException();
        }
        String password = user.getPassword();
        String salt = user.getSalt();
        System.out.println(""user.getPassword());
        System.out.println(new SimpleHash("md5", token.getCredentials().toString(), ByteSource.Util.bytes(salt),1));
        return new SimpleAuthenticationInfo(name, password, ByteSource.Util.bytes(salt), getName());
    }
}
