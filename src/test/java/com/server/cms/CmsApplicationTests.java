package com.server.cms;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
//@ActiveProfiles("test")
@SpringBootTest
@EnableJpaAuditing(auditorAwareRef = "getCurrentAuditor")
class CmsApplicationTests {

	@Test
	void contextLoads() {
	}

//	@ComponentScan(basePackages = "com.server.cms")
//	@EnableJpaRepositories(basePackages = "com.server.cms")
//	@EntityScan(basePackages = "com.server.cms.domain")
//	@SpringBootApplication
//	@PropertySource("classpath:application.yml")
//	static class TestConfiguration {}

}
