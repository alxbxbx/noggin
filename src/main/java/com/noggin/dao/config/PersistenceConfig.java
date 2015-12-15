package com.noggin.dao.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.ejb.HibernatePersistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories({"com.noggin.dao.repositories"})
@EnableTransactionManagement
@ComponentScan({"com.noggin"})
public class PersistenceConfig {
	
	  @Bean
	  public DataSource dataSource() {
		  BasicDataSource dataSource = new BasicDataSource();
	      dataSource.setDriverClassName("com.mysql.jdbc.Driver");
	      dataSource.setUrl("jdbc:mysql://localhost/noggin");
	      dataSource.setUsername("root");
	      dataSource.setPassword("");
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
	  

}
