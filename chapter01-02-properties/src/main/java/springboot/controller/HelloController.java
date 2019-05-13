package springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springboot.entity.Properties1;
import springboot.entity.Properties2;

@RestController
public class HelloController {


    @Autowired
    Properties1 properties1;

    @Autowired
    Properties2 properties2;


    @Autowired
    Environment environment;

    /**
     * 第一种方式：使用@ConfigurationProperties方式
     */
    @RequestMapping("/getProperties1")
    public String getProperties1() {
        return properties1.toString();
    }

    /**
     * 第二种方式：使用@Value注解方式（该方式仅适用于String类型）
     */
    @RequestMapping("/getProperties2")
    public String getProperties2() {
        return properties2.toString();
    }

    /**
     * 第三种方式：使用Environment读取
     */
    @RequestMapping("/getProperties3")
    public String getProperties3() {
        return "name=" + environment.getProperty("com.louis.name") + ",username=" + environment.getProperty("com.louis.user[username]");
    }

}
