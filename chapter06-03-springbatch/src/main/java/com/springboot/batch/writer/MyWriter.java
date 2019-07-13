package com.springboot.batch.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

import com.springboot.entity.People;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyWriter implements ItemWriter<People> {

	/**
	 *
	 * @description: //TODO 写入数据
	 * @author Louis
	 * @date 2019/7/13 14:59
	 * @param [items]
	 * @return void
	 */
	@Override
	public void write(List<? extends People> items) throws Exception {
		log.info("MyWriter Starter!!!");
		// TODO Auto-generated method stub
		for (People people : items) {
			log.info("write data : " + people); 
		}
	}

}
