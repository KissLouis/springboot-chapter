**SpringBoot整合Shiro**  

#### Shiro的三个核心组件：
    * Subject：主体。代表当前正在执行操作的用户，每个Subject实例绑定到SercurityManger上
    * SecurityManger：安全管理器。全部的subject进行安全管理，它是shiro的核心
    * Subject：用户数据和Shiro数据交互的桥梁。比如需要用户身份认证、权限认证。都是需要通过Realm来读取数据。

#### Shiro身份验证基本流程

#####1.Shiro登录基本流程
    // 1.创建当前登录操作用户
    Subject subject = SecurityUtils.getSubject();
    // 2.创建token令牌，token中有用户提交的认证信息即账号和密码
    UsernamePasswordToken token = new UsernamePasswordToken(user.getNickname(), user.getPswd());
    // 3.调用 Subject.login 进行登录，如果失败将得到相应的 AuthenticationException 异常，根据异常提示用户错误信息；否则登录成功。
    subject.login(token);
    
#####2.Shiro自定义realm    

    //创建自定义的 Realm 类，继承org.apache.shiro.realm.AuthorizingRealm 类，实现 doGetAuthorizationInfo() 和 doGetAuthenticationInfo() 方法
    public class ShiroRealm extends AuthorizingRealm {

        @Override
        protected AuthorizationInfo doGetAuthorizationInfo(
                PrincipalCollection principals) {
                return null;
        }

        @Override
        protected AuthenticationInfo doGetAuthenticationInfo(
                AuthenticationToken authcToken) throws AuthenticationException {
                return null;
        }
    }