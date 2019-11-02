package com.springboot.shiro.realm;

import com.springboot.dao.SysUserDao;
import com.springboot.jwt.JWTToken;
import com.springboot.jwt.util.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * @类描述： 自定义 realm 类
 * @项目名称：chapter07-03-shiro-jwt @包名： com.springboot.shiro.realm
 * @类名称：CustomRealm
 * @创建人：Louis
 * @创建时间：2019年7月25日下午4:21:56
 */
@Slf4j
@Component
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private SysUserDao sysUserDao;

    @Override
    public boolean supports(AuthenticationToken token) {
        boolean flag = token instanceof JWTToken;
        log.info("supports：{}", flag);
        return true;
    }

    /**
     * @param token
     * @return
     * @throws AuthenticationException
     * @描述: 获取身份验证信息
     * @方法名: doGetAuthenticationInfo
     * @创建人：T-liul4
     * @创建时间：2019年7月25日下午4:22:26
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        // TODO Auto-generated method stub
        log.info("身份认证方法：doGetAuthenticationInfo：{}",auth.getPrincipal());

        String token = (String) auth.getCredentials();
        // 解密获得userName，用于和数据库进行对比
        String userName = null;
        try {
            userName = JWTUtil.getUsername(token);
        } catch (Exception e) {
            throw new AuthenticationException("heard的token拼写错误或者值为空");
        }
        if (userName == null) {
            log.error("token无效");
            throw new AuthenticationException("token无效");
        }

        // 根据用户名从数据库获取密码
        String password = sysUserDao.getPassword(userName);
        if (password == null) {
            throw new AccountException("用户名不正确");
        } else if (!JWTUtil.verify(token, userName, password)) {
            // 存到数据库的是密文，将输入的明文通过加密方式加密进行对比，一样则成功，否则失败
            throw new AccountException("密码不正确");
        }

        return new SimpleAuthenticationInfo(token, token, "my_realm");
    }

    /**
     * @param principals
     * @return
     * @描述: 获取授权信息
     * 只有当需要检测用户权限的时候才会调用此方法
     * @方法名: doGetAuthorizationInfo
     * @创建人：T-liul4
     * @创建时间：2019年7月25日下午4:22:35
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // TODO Auto-generated method stub
        log.info("权限认证方法：doGetAuthorizationInfo");
        String userName = JWTUtil.getUsername(principals.toString());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        // 设置角色
        Set<String> roles = sysUserDao.getRoles(userName);
        info.setRoles(roles);

        // 设置权限
        Set<String> stringSet = new HashSet<>();
        stringSet.add("user:show");
        info.setStringPermissions(stringSet);

        return info;
    }

}
