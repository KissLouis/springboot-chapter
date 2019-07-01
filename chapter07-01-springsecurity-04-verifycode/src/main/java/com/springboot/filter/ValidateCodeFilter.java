package com.springboot.filter;

import com.springboot.constants.MyConstants;
import com.springboot.entity.image.ImageCode;
import com.springboot.exception.ValidateCodeException;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @author Louis
 * @title: ValidateCodeFilter
 * @projectName springboot-chapter
 * @description: TODO
 * @date 2019/6/30 17:51
 */
@Slf4j
@Component("validateCodeFilter")
public class ValidateCodeFilter extends OncePerRequestFilter implements Filter {

    /**
     * 验证码校验失败处理器
     */
    @Getter
    @Setter
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private SessionStrategy sessionStrategy;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // 必须是登录的post请求才能进行验证，其他的直接放行
        if (StringUtils.equals("/login", request.getRequestURI())
                && StringUtils.equalsIgnoreCase(request.getMethod(), "POST")) {
            try {
                // 1. 进行验证码的校验
                validate(new ServletWebRequest(request));
            } catch (ValidateCodeException e) {
                // 2. 捕获步骤1中校验出现异常，交给失败处理类进行进行处理
                authenticationFailureHandler.onAuthenticationFailure(request, response, e);
                return;
            }
        }
        // 3. 校验通过，放行
        filterChain.doFilter(request, response);
    }

    //检验方法
    private void validate(ServletWebRequest request) throws ServletRequestBindingException  {
        // 1. 获取请求中的验证码
        String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), "imageCode");
        // 2. 校验空值情况
        if (StringUtils.isEmpty(codeInRequest)) {
            throw new ValidateCodeException("验证码不能为空");
        }
        // 3. 获取服务器session池中的验证码
        ImageCode codeInSession = (ImageCode) sessionStrategy.getAttribute(request, MyConstants.SESSION_KEY);
        if (Objects.isNull(codeInSession)) {
            throw new ValidateCodeException("验证码不存在");
        }
        // 4. 校验服务器session池中的验证码是否过期
        if (codeInSession.isExpried()) {
            sessionStrategy.removeAttribute(request, MyConstants.SESSION_KEY);
            throw new ValidateCodeException("验证码已过期");
        }
        // 5. 请求验证码校验
        if (!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
            throw new ValidateCodeException("验证码不匹配");
        }
        // 6. 移除已完成校验的验证码
        sessionStrategy.removeAttribute(request, MyConstants.SESSION_KEY);
    }
}
