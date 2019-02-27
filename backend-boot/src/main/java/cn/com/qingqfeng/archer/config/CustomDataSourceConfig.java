/**
 * 
 */
package cn.com.qingqfeng.archer.config;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * @ClassName: CustomDataSourceConfig
 * @Description: 数据源配置
 * @author: lijunliang
 * @date: 2019年1月31日 下午1:19:35
 * 
 */
@Configuration
@MapperScans(value = { @MapperScan(basePackages = "cn.com.qingqfeng.archer.dao", sqlSessionTemplateRef = "defSqlSessionTemplate") })
public class CustomDataSourceConfig {
	// 数据源配置
	@Bean
	@ConfigurationProperties(prefix = "spring.datasource.def")
	public Properties defSourceProperties() {
		return new Properties();
	}

	// 实例化数据源
	@Bean
	public DataSource defSource() {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.configFromPropety(defSourceProperties());
		return dataSource;
	}

	// sessionFactory
	@Bean
	public SqlSessionFactoryBean defSessionFactory() throws IOException {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(defSource());
		factoryBean
				.setMapperLocations(new PathMatchingResourcePatternResolver()
						.getResources("classpath:mapper/**/*.xml"));
		return factoryBean;
	}

	// 事务
	@Bean
	public PlatformTransactionManager defManager() {
		return new DataSourceTransactionManager(defSource());
	}

	// sql模板
	@Bean
	public SqlSessionTemplate defSqlSessionTemplate() throws IOException,
			Exception {
		return new SqlSessionTemplate(defSessionFactory().getObject());
	}

}
