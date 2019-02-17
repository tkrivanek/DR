package com.vvg.krivanek.warehouserental;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;

import com.mysql.cj.jdbc.MysqlDataSource;

import lombok.Data;
//import oracle.jdbc.pool.OracleDataSource;

@Data
@Configuration
public class JdbcConfig {

 	@Bean(name = "whDS")
	public DataSource dataSource() throws SQLException {
		try {
			JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
			dataSourceLookup.setResourceRef(false);
			return dataSourceLookup.getDataSource("");
		} catch (Exception e) {
			java.util.Properties prop = new java.util.Properties();
			MysqlDataSource dataSource = new MysqlDataSource();
			dataSource.setUser("ante");
			dataSource.setPassword("123456vvg");
			dataSource.setURL("jdbc:mysql://192.168.150.122:3306/warehouse_db");
			dataSource.setServerTimezone("Europe/Zagreb");
			return dataSource;
		}
 	}
}