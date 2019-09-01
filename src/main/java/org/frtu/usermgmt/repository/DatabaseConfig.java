package org.frtu.usermgmt.repository;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by frtu on 20/12/2014.
 */
@Configuration
@EnableTransactionManagement
// Create an impl out of JpaRepository for UserRepository
@EnableJpaRepositories
public class DatabaseConfig {
    @Value("${db.driver}")
    private String dbDriver;

    @Value("${db.password}")
    private String dbPassword;

    @Value("${db.url}")
    private String dbUrl;

    @Value("${db.username}")
    private String dbUsername;

    @Value("${hibernate.dialect}")
    private String hibernateDialect;

    @Value("${hibernate.show_sql}")
    private String hibernateShowSql;

    @Value("${hibernate.format_sql}")
    private String hibernateFormatSql;

    @Value("${hibernate.hbm2ddl.auto}")
    private String hibernateHbm2DdlAuto;

    @Value("${entitymanager.packagesToScan}")
    private String entitymanagerPackagesToScan;

    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        PropertiesFactoryBean.getProperties(); for JMX
//        OR
//        Resource resource = new ClassPathResource("/my.properties");
//        Properties props = PropertiesLoaderUtils.loadProperties(resource);
        HikariConfig dataSourceConfig = new HikariConfig();
        dataSourceConfig.setDriverClassName(dbDriver);
        dataSourceConfig.setJdbcUrl(dbUrl);
        dataSourceConfig.setUsername(dbUsername);
        dataSourceConfig.setPassword(dbPassword);
        return new HikariDataSource(dataSourceConfig);
    }

    @Bean
    LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource,
                                                                Environment env) {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactoryBean.setPackagesToScan(entitymanagerPackagesToScan);

        Properties jpaProperties = new Properties();

        //Configures the used database dialect. This allows Hibernate to create SQL
        //that is optimized for the used database.
        jpaProperties.put("hibernate.dialect", hibernateDialect);

        //Specifies the action that is invoked to the database when the Hibernate
        //SessionFactory is created or closed.
        jpaProperties.put("hibernate.hbm2ddl.auto", hibernateHbm2DdlAuto);

        //Configures the naming strategy that is used when Hibernate creates
        //new database objects and schema elements
//        jpaProperties.put("hibernate.ejb.naming_strategy",
//                env.getRequiredProperty("hibernate.ejb.naming_strategy")
//        );

        //If the value of this property is true, Hibernate writes all SQL
        //statements to the console.
        jpaProperties.put("hibernate.show_sql", hibernateShowSql);

        //If the value of this property is true, Hibernate will format the SQL
        //that is written to the console.
        jpaProperties.put("hibernate.format_sql", hibernateFormatSql);

        entityManagerFactoryBean.setJpaProperties(jpaProperties);

        return entityManagerFactoryBean;
    }

//    @Bean
//    public LocalSessionFactoryBean sessionFactory() {
//        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
//        sessionFactoryBean.setDataSource(dataSource());
//        sessionFactoryBean.setPackagesToScan(entitymanagerPackagesToScan);
//        Properties hibernateProperties = new Properties();
//        hibernateProperties.put("hibernate.dialect", hibernateDialect);
//        hibernateProperties.put("hibernate.show_sql", hibernateShowSql);
//        hibernateProperties.put("hibernate.hbm2ddl.auto", hibernateHbm2DdlAuto);
//        sessionFactoryBean.setHibernateProperties(hibernateProperties);
//
//        return sessionFactoryBean;
//    }

    @Bean
    JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

//    @Bean
//    public HibernateTransactionManager transactionManager() {
//        HibernateTransactionManager transactionManager =
//                new HibernateTransactionManager();
//        transactionManager.setSessionFactory(sessionFactory().getObject());
//        return transactionManager;
//    }

} // class DatabaseConfig
