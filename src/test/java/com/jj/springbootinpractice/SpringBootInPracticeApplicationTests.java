package com.jj.springbootinpractice;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest

class SpringBootInPracticeApplicationTests {

	@Autowired
	private DataSource dataSource;


	@Test
	void contextLoads() {
	}

	@Test
	public void givenDatasourceAvailableWhenAccesssDetailsTheExpectDetails() throws SQLException {
		assertThat(dataSource.getClass().getName()).isEqualTo("com.zaxxer.hikari.HikariDataSource");
		assertThat(dataSource.getConnection().getMetaData().getDatabaseProductName()).isEqualTo("H2");
	}

}
