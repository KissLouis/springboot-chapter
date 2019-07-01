package com.springboot.config.security;

import com.springboot.filter.ValidateCodeFilter;
import com.springboot.util.security.MyPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

/**
 * @类描述：
 * @项目名称：chapter07-01-springsecurity-01-login @包名：
 * com.springboot.config.security
 * @类名称：WebSecurityConfig
 * @创建人：T-liul4
 * @创建时间：2019年6月26日上午9:41:33
 */
@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService myCustomUserService;

    @Autowired
    private MyPasswordEncoder myPasswordEncoder;

    @Autowired
    private ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();

    /**
     * @param http
     * @throws Exception
     * @描述: 匹配 "/" 路径，不需要权限即可访问.
     * 匹配 "/user" 及其以下所有路径，都需要 "USER" 权限.
     * 登录地址为"/login"，登录成功默认跳转到页面 "/user".
     * 退出登录的地址为 "/logout"，退出成功后跳转到页面"/login".
     * 默认启用CSRF
     * @方法名: configure
     * @创建人：T-liul4
     * @创建时间：2019年6月26日上午9:41:39
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {



        // 关闭CSRF
        http.cors().and().csrf().disable();

        http
                // 将自定义的验证码过滤器放置在 UsernamePasswordAuthenticationFilter 之前
                .addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                //使用form表单post方式进行登录
                .formLogin()//登录页面为自定义的登录页面
                .loginPage("/login")
                //设置登录成功跳转页面，error=true控制页面错误信息的展示
                .defaultSuccessUrl("/user").failureUrl("/login?error=true")
                //允许不登陆就可以访问的方法，多个用逗号分隔
                .and().authorizeRequests().antMatchers("/static/**", "/webjars/**", "/public/**", "/login",
                "/register", "/code/image",
                "/favicon.ico").permitAll()
                //其他的需要授权后访问
                .anyRequest().authenticated();


        //session管理,失效后跳转
        http.sessionManagement().invalidSessionUrl("/login");
        //单用户登录，如果有一个登录了，同一个用户在其他地方登录将前一个剔除下线
        //http.sessionManagement().maximumSessions(1).expiredSessionStrategy(expiredSessionStrategy());
        //单用户登录，如果有一个登录了，同一个用户在其他地方不能登录
        //http.sessionManagement().maximumSessions(1).maxSessionsPreventsLogin(true);
        //退出时清空cookies
        http.logout().logoutUrl("/logout").logoutSuccessUrl("/login").deleteCookies("JESSIONID");
        //解决中文乱码问题
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        //
        http.addFilterBefore(filter, CsrfFilter.class);
    }


    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider bean = new DaoAuthenticationProvider();
        //返回错误信息提示，而不是Bad Credential
        bean.setHideUserNotFoundExceptions(true);
        //覆盖UserDetailsService类
        bean.setUserDetailsService(myCustomUserService);
        //覆盖默认的密码验证类
        bean.setPasswordEncoder(myPasswordEncoder);
        return bean;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(this.daoAuthenticationProvider());
        auth.userDetailsService(myCustomUserService).passwordEncoder(new BCryptPasswordEncoder());
    }

}
