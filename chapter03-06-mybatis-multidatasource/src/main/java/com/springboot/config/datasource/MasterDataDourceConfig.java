package com.springboot.config.datasource;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
@MapperScan(basePackages = MasterDataDourceConfig.PACKAGE, sqlSessionFactoryRef = "masterSqlSessionFactory")
public class MasterDataDourceConfig {

	static final String PACKAGE = "com.springboot.dao.master";
	static final String MAPPER_LOCATION = "classpath:mapper/master/*.xml";

	// 将对象放入Spring容器中
	@Bean(name = "masterDataSource")
	// 表示这个数据源是默认数据源
	@Primary
	// 读取application.properties中的配置参数映射成为一个对象
	// prefix表示参数的前缀
	@ConfigurationProperties(prefix = "spring.datasource.master")
	public DataSource getDataSource() {
		return new DruidDataSource();
	}

	@Bean(name = "masterSqlSessionFactory")
	// 表示这个数据源是默认数据源
	@Primary
	// @Qualifier表示查找Spring容器中名字为masterSqlSessionFactory的对象
	public SqlSessionFactory masterSqlSessionFactory(@Qualifier("masterDataSource") DataSource datasource)
			throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(datasource);
		bean.setMapperLocations(
				// 设置mybatis的xml所在位置
				new PathMatchingResourcePatternResolver().getResources(MasterDataDourceConfig.MAPPER_LOCATION));
		return bean.getObject();
	}

	@Bean("masterSqlSessionTemplate")
	// 表示这个数据源是默认数据源
	@Primary
	public SqlSessionTemplate mastersqlsessiontemplate(
			@Qualifier("masterSqlSessionFactory") SqlSessionFactory sessionfactory) {
		return new SqlSessionTemplate(sessionfactory);
	}
}
