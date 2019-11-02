package com.springboot.jwt.filter;

import com.springboot.jwt.JWTToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class JwtFilter extends BasicHttpAuthenticationFilter {

    // 登录标识
    private static String LOGIN_SIGN = "Authorization";

    /**
     * @param [request, response]
     * @return boolean
     * @description: //TODO 判断用户是否想要登入
     * 检测header里面是否包含Authorization字段即可
     * @author Louis
     * @date 2019/7/27 18:08
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        String authorization = req.getHeader(LOGIN_SIGN);
        log.info("authorization：{}", authorization);
        return authorization != null;
    }


    /**
     * @param request
     * @param response
     * @param mappedValue
     * @return
     * @描述: 执行登录认证
     * @方法名: isAccessAllowed
     * @创建人：T-liul4
     * @创建时间：2019年7月25日下午3:52:56
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        // TODO Auto-generated method stub
        log.info("执行isAccessAllowed()");
        if (isLoginAttempt(request, response)) {
            try {
                executeLogin(request, response);
            } catch (Exception e) {
                log.error("JwtFilter过滤验证失败!" + e.getMessage());
            }
        }
        return true;
    }

    /**
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @描述: 执行登录
     * @方法名: executeLogin
     * @创建人：T-liul4
     * @创建时间：2019年7月25日下午3:52:45
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        // TODO Auto-generated method stub
        log.info("执行executeLogin()");
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String authorization = httpServletRequest.getHeader(LOGIN_SIGN);

        JWTToken token = new JWTToken(authorization);
        // 提交给realm进行登入，如果错误他isAccessAllowed会抛出异常并被捕获
        getSubject(request, response).login(token);
        // 如果没有抛出异常则代表登入成功，返回true
        return true;
    }

    /**
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @描述: 对跨域提供支持
     * @方法名: preHandle
     * @创建人：T-liul4
     * @创建时间：2019年7月25日下午3:52:38
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers",
                httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }

}
