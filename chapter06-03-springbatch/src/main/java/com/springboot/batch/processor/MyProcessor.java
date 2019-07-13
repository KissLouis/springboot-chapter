package com.springboot.batch.processor;

import com.springboot.entity.People;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

@Slf4j
public class MyProcessor implements ItemProcessor<People, People> {

    /**
     *
     * @description: //TODO 处理数据
     * @author Louis
     * @date 2019/7/13 14:59
     * @param [people]
     * @return com.springboot.entity.People
     */
    @Override
    public People process(People people) throws Exception {
        log.info("MyProcessor Starter!!!");
        // TODO Auto-generated method stub
        log.info("processor data : " + people.toString()); // 模拟 假装处理数据,这里处理就是打印一下
        return people;
    }

}
