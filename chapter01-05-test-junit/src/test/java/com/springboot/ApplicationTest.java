package com.springboot;

import com.springboot.entity.People;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Louis
 * @title: ApplicationTest
 * @projectName springboot-study
 * @description: TODO
 * @date 2019/5/26 14:31
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {

    @Autowired
    People people;

    /**
     * @param []
     * @return void
     * @description: //TODO //初始化执行
     * @author Louis
     * @date 2019/5/26 14:59
     */
    @Before
    public void init() {
        System.out.println("准备工作开始测试-----------------");
    }

    @Test
    public void test() {
        System.out.println(people.toString());
        //简单验证结果集是否正确
        Assert.assertSame("数量有误", 10, people.getAge());
        Assert.assertEquals("数据有误", "louis", people.getName());
    }

    @Test
    public void test1() {
        //验证结果集，提示
        Assert.assertTrue("true", people.getAge() == 12);
        Assert.assertFalse("false", people.getAge() == 10);
    }

    @After
    public void after() {
        System.out.println("测试结束关闭资源-----------------");
    }
}
