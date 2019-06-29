package com.springboot.controller.sys;


import com.springboot.entity.sys.SysUser;
import com.springboot.service.sys.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author Louis
 * @since 2019-06-29
 */
@Controller
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @GetMapping("/user")
    public String user(@AuthenticationPrincipal Principal principal, Model model) {
        model.addAttribute("username", principal.getName());
        return "user/user";
    }

    @PostMapping("/register")
    public String doRegister(SysUser user, Model model){
        // 此处省略校验逻辑
        sysUserService.insert(user);
        return "redirect:register?success=true";
    }


}
