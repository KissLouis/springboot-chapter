package com.springboot.controller.sms;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Louis
 * @title: SmsCodeController
 * @projectName springboot-chapter
 * @description: TODO
 * @date 2019/7/5 19:57
 */
@RestController
@Slf4j
public class SmsCodeController {

    @RequestMapping("/sms/code")
    public void sms(String mobile, HttpSession session) {
        int code = (int) Math.ceil(Math.random() * 9000 + 1000);

        Map<String, Object> map = new HashMap<>(16);
        map.put("mobile", mobile);
        map.put("code", code);

        session.setAttribute("smsCode", map);

        log.info("{}：为 {} 设置短信验证码：{}", session.getId(), mobile, code);
    }

}
