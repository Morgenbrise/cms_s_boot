package com.server.cms.config.database;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.jdbc.JdbcConnectionDetails;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EntityScan("com.server.cms.domain")
@EnableJpaRepositories(basePackages = {"com.server.cms.repository", "com.server.cms.query"}, entityManagerFactoryRef = "oracleEntityManagerFactory", transactionManagerRef = "oracleTransactionManager")
public class OracleJpaDatabaseConfig {

    @Value(value = "${database.oracle.databasePlatform}")
    private String databasePlatform;
    @Value(value = "${database.oracle.ddlAuto}")
    private String ddlAuto;
    @Value(value = "${database.oracle.driverClassName}")
    private String driverClassName;
    @Value(value = "${database.oracle.url}")
    private String url;
    @Value(value = "${database.oracle.username}")
    private String username;
    @Value(value = "${database.oracle.password}")
    private String password;

    @Autowired
    private JpaProperties jpaProperties;

    private final Database database = Database.MYSQL;

//    @Primary
//    @Bean("oracleDataSourceProperties")
//    public DataSource oracleDataSourceProperties() {
//        return DataSourceBuilder
//                .create()
//                .driverClassName(driverClassName)
//                .url(url)
//                .username(username)
//                .password(password)
//                .type(HikariDataSource.class)
//                .build();
//    }

    @Bean
    @ConfigurationProperties("spring.datasource.oracle")
    public DataSourceProperties oracleDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource oracleDataSource() {
        return oracleDataSourceProperties()
                .initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean oracleEntityManagerFactory() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabasePlatform(databasePlatform);
        vendorAdapter.setDatabase(database);
        vendorAdapter.setGenerateDdl(false);

        HibernateProperties hibernateProperties = new HibernateProperties();
        hibernateProperties.setDdlAuto(ddlAuto);

        Map<String, String> properties = new HashMap<>();
        properties.put("spring.jpa.properties.hibernate.naming.physical-strategy", "org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy");
        properties.put("spring.jpa.hibernate.ddl-auto", ddlAuto);

        jpaProperties.setOpenInView(false);
        jpaProperties.setProperties(properties);

        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(oracleDataSource());
        emf.setPackagesToScan(new String[] {"com.server.cms.domain"});
        emf.setPersistenceUnitName("oracleEntityManager");
        emf.setJpaPropertyMap(jpaProperties.getProperties());
        emf.setJpaPropertyMap(jpaProperties.getProperties());

        emf.setJpaVendorAdapter(vendorAdapter);
        return emf;
    }

    @Primary
    @Bean
    public PlatformTransactionManager oracleTransactionManager( ) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(oracleEntityManagerFactory().getObject());
        return transactionManager;
    }

}
