package com.springboot.batch;

import com.springboot.batch.processor.MyProcessor;
import com.springboot.batch.writer.MyWriter;
import com.springboot.db.mapper.PeopleRowMapper;
import com.springboot.entity.People;
import com.springboot.listener.JobListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @类描述：
 * @项目名称：chapter08-03-springbatch @包名： com.springboot.batch
 * @类名称：DataBatchConfiguration
 * @创建人：Louis
 * @创建时间：2019年7月11日下午4:36:02
 */
@Configuration
// 提供用于构建批处理作业的基本配置
@EnableBatchProcessing
@Slf4j
public class DataBatchConfiguration {

    @Resource
    private JobBuilderFactory jobBuilderFactory; // 用于构建JOB

    @Resource
    private StepBuilderFactory stepBuilderFactory; // 用于构建Step

    @Resource
    private JobListener jobListener; // 简单的JOB listener

    @Resource
    private DataSource dataSource;

    /**
     * @return
     * @描述: 创建Job
     * @方法名: dataHandleJob
     * @返回类型 Job
     * @创建人 T-liul4
     * @创建时间 2019年7月11日下午5:05:42
     */
    @Bean
    public Job dataHandleJob() {
        return jobBuilderFactory.get("dataHandleJob").incrementer(new RunIdIncrementer()).start(handleDataStep()). //
                // start是JOB执行的第一个step
                // next(xxxStep()).
                // next(xxxStep()).
                // ...
                        listener(jobListener). // 设置了一个简单JobListener
                build();
    }

    /**
     * @return
     * @描述: 创建Step
     * @方法名: handleDataStep
     * @返回类型 Step
     * @创建人 T-liul4
     * @创建时间 2019年7月11日下午5:05:54
     */
    @Bean
    public Step handleDataStep() {
        log.info("Step Starter!!!");
        // <输入,输出> 。chunk通俗的讲类似于SQL的commit;//这里表示处理
        // (processor)3条后写入(writer)一次。
        return stepBuilderFactory.get("getData").<People, People>chunk(3).
                reader(myReader()). // 指定ItemReader
                processor(new MyProcessor()). // 指定ItemProcessor
                writer(new MyWriter()). // 指定ItemWriter
                build();
    }

    /**
     * @param []
     * @return org.springframework.batch.item.database.JdbcPagingItemReader<com.springboot.entity.People>
     * @description: //TODO 读取数据
     * @author Louis
     * @date 2019/7/13 14:56
     */
    @Bean
    @StepScope
    public JdbcPagingItemReader<People> myReader() {
        log.info("getDataReader Starter!!!");
        JdbcPagingItemReader<People> reader = new JdbcPagingItemReader<>();
        reader.setDataSource(this.dataSource); // 设置数据源
        reader.setFetchSize(2); // 设置一次最大读取条数
        reader.setRowMapper(new PeopleRowMapper()); // 把数据库中的每条数据映射到Person对中
        MySqlPagingQueryProvider queryProvider = new MySqlPagingQueryProvider();
        queryProvider.setSelectClause("peopleId,peopleName,password,salt,status,modifyTime,createTime"); // 设置查询的列
        queryProvider.setFromClause("from people"); // 设置要查询的表
        Map<String, Order> sortKeys = new HashMap<String, Order>();// 定义一个集合用于存放排序列
        sortKeys.put("peopleId", Order.ASCENDING);// 按照升序排序
        queryProvider.setSortKeys(sortKeys);
        reader.setQueryProvider(queryProvider);// 设置排序列
        return reader;
    }

}
