package com.springboot.util.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author Louis
 * @title: MyPasswordEncoder
 * @projectName springboot-chapter
 * @description: TODO 自定义的密码加密方法，实现了PasswordEncoder接口
 * @date 2019/6/27 21:17
 */
@Component
public class MyPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        //加密方法可以根据自己的需要修改
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return encode(charSequence).equals(s);
    }

}
