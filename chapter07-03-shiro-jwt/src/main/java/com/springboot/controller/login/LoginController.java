package com.springboot.controller.login;

import com.springboot.entity.SysUser;
import com.springboot.service.SysUserService;
import com.springboot.util.result.Result;
import com.springboot.util.result.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @类描述： 登陆等处理的 LoginController
 * @项目名称：chapter07-03-shiro @包名： com.springboot.controller.login
 * @类名称：LoginController
 * @创建人：Louis
 * @创建时间：2019年7月23日上午10:46:41
 */
@RestController
@Slf4j
public class LoginController {

    @Autowired
    private SysUserService sysUserService;

    @GetMapping("/notLogin")
    public Result login() {
        return new Result(ResultCode.UNAUTHENTICATED, "默认未登录跳转请求");
    }

    @GetMapping(value = "/notRole")
    public Result notRole() {
        return new Result(ResultCode.UNAUTHORISE);
    }

    @GetMapping(value = "/logout")
    public Result logout() {
        Subject subject = SecurityUtils.getSubject();
        // 注销
        subject.logout();
        return new Result(ResultCode.LOGOUT_SUCCESS);
    }

    @PostMapping("/login")
    public Result login(String username, String password, HttpServletRequest request, HttpServletResponse response) {
        // Shiro认证通过后会将user信息放到subject内，生成token并返回
        String token = sysUserService.login(username, password);
        if (token != null) {
            return new Result(ResultCode.LOGIN_SUCCESS, token);
        }
        return new Result(ResultCode.LOGIN_FAIL);
    }

    @PostMapping("/register")
    public Result doRegister(SysUser user) {
        // 此处省略校验逻辑
        sysUserService.insert(user);
        return new Result(ResultCode.SUCCESS);
    }

}
