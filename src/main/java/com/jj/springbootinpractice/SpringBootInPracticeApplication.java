package com.jj.springbootinpractice;

import com.jj.springbootinpractice.config.AppProperties;
import com.jj.springbootinpractice.services.AppService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Properties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class SpringBootInPracticeApplication {

	private static final Logger LOG = LoggerFactory.getLogger(SpringBootInPracticeApplication.class);

	public static void main(String[] args) {
		Properties properties = new Properties();
		properties.setProperty("spring.config.on-not-found", "ignore");

		SpringApplication application = new SpringApplication(SpringBootInPracticeApplication.class);
		application.setDefaultProperties(properties);
	    ConfigurableApplicationContext applicationContext = application.run(args);

		AppService appService = applicationContext.getBean(AppService.class);
		LOG.info(appService.getAppProperties().toString());
	}
}
