package com.local.test;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class Application {
	
	private static Logger logger = Logger.getLogger(Application.class);
	 
//    DataSource配置
//    @Bean
//    @ConfigurationProperties(prefix="spring.datasource")
//    public DataSource dataSource() {
//        return new org.apache.tomcat.jdbc.pool.DataSource();
//    }
//    
//    //提供SqlSeesion
//    @Bean
//    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
// 
//        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//        sqlSessionFactoryBean.setDataSource(dataSource());
// 
//        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
// 
//        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/mybatis/*.xml"));
// 
//        return sqlSessionFactoryBean.getObject();
//    }
// 
//    @Bean
//    public PlatformTransactionManager transactionManager() {
//        return new DataSourceTransactionManager(dataSource());
//    }

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);

	}

}
