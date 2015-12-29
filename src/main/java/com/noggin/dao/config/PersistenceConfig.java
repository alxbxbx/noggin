package com.noggin.dao.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.ejb.HibernatePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.MultipartConfigElement;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories({"com.noggin.dao.repositories"})
@EnableTransactionManagement
@ComponentScan({"com.noggin"})
@PropertySource({ "classpath:application.properties" })
public class PersistenceConfig {
	
	@Autowired
	Environment env;
	
	  @Bean
	  public DataSource dataSource() {
		  BasicDataSource dataSource = new BasicDataSource();
	      dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
	      dataSource.setUrl(env.getProperty("jdbc.url"));
	      dataSource.setUsername(env.getProperty("jdbc.user"));
	      dataSource.setPassword(env.getProperty("jdbc.pass"));
	      return dataSource;
	  }
	  
	  @Bean
	    public HibernatePersistence persistenceProvider() {
	        return new HibernatePersistence();
	    }

	  @Bean
	  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

	    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	    vendorAdapter.setGenerateDdl(true);
	    vendorAdapter.setDatabasePlatform("org.hibernate.dialect.MySQL5Dialect");

	    LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
	    factory.setPackagesToScan(new String[] { "com.noggin.models" });
	    factory.setDataSource(dataSource());
	    factory.setPersistenceProvider(persistenceProvider());
	    factory.setJpaVendorAdapter(vendorAdapter);
	    
	    
	    
	    final Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        hibernateProperties.setProperty("hibernate.show_sql", "true");
        
	    factory.setJpaProperties(hibernateProperties);
	    return factory;
	  }

	  @Bean
	  public PlatformTransactionManager transactionManager() {

	    JpaTransactionManager txManager = new JpaTransactionManager();
	    txManager.setEntityManagerFactory(entityManagerFactory().getObject());
	    return txManager;
	  }
	  @Bean
	  public CommonsMultipartResolver multipartResolver(){
	      CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
	      commonsMultipartResolver.setDefaultEncoding("utf-8");
	      commonsMultipartResolver.setMaxUploadSize(50000000);
	      return commonsMultipartResolver;
	  }
	  @Bean
	  public MultipartConfigElement multipartConfigElement(){
	      MultipartConfigFactory multipartConfigFactory = new MultipartConfigFactory();
	      multipartConfigFactory.setMaxFileSize("20MB");
	      multipartConfigFactory.setMaxRequestSize("50MB");
	      return multipartConfigFactory.createMultipartConfig();
	  }

}
