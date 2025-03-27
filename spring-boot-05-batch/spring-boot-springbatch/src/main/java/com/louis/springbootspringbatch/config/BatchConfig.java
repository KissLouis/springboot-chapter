package com.louis.springbootspringbatch.config;

import com.louis.springbootspringbatch.model.Trade;
import com.louis.springbootspringbatch.repository.TradeRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BatchConfig {

    // 从CSV文件读取数据
    @Bean
    public ItemReader<Trade> reader() {
        FlatFileItemReader<Trade> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("trades.csv"));
        reader.setLinesToSkip(1);

        DefaultLineMapper<Trade> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames("id", "symbol", "price", "quantity");

        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(new BeanWrapperFieldSetMapper<Trade>() {{
            setTargetType(Trade.class);
        }});

        reader.setLineMapper(lineMapper);
        return reader;
    }

    // 处理数据（转换symbol为大写）
    @Bean
    public ItemProcessor<Trade, Trade> processor() {
        return trade -> {
            trade.setSymbol(trade.getSymbol().toUpperCase());
            return trade;
        };
    }

    // 写入MySQL数据库
    @Bean
    public ItemWriter<Trade> writer(TradeRepository repository) {
        return items -> repository.saveAll(items);
    }

    // 定义处理步骤
    @Bean
    public Step processStep(JobRepository jobRepository,
                            PlatformTransactionManager transactionManager,
                            ItemReader<Trade> reader,
                            ItemProcessor<Trade, Trade> processor,
                            ItemWriter<Trade> writer) {
        return new StepBuilder("tradeProcessStep", jobRepository)
                .<Trade, Trade>chunk(10, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    // 定义批处理任务
    @Bean
    public Job importTradeJob(JobRepository jobRepository, Step processStep) {
        return new JobBuilder("importTradeJob", jobRepository)
                .start(processStep)
                .build();
    }
}