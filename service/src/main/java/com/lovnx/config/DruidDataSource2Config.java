package com.lovnx.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@EnableConfigurationProperties(DataSource2Properties.class)
public class DruidDataSource2Config {

    private static Logger logger = LoggerFactory.getLogger(DruidDataSource2Config.class);

    @Autowired
    private DataSource2Properties dataSource2Properties;

    @Bean("wsmysql")
    @Qualifier("wsmysql")
    @Primary
    public DataSource druidDataSource2(){
        DruidDataSource datasource = new DruidDataSource();

        datasource.setUrl(dataSource2Properties.getUrl());
        datasource.setUsername(dataSource2Properties.getUsername());
        datasource.setPassword(dataSource2Properties.getPassword());
        datasource.setDriverClassName(dataSource2Properties.getDriverClassName());
        datasource.setInitialSize(dataSource2Properties.getInitialSize());
        datasource.setMinIdle(dataSource2Properties.getMinIdle());
        datasource.setMaxActive(dataSource2Properties.getMaxActive());
        datasource.setMaxWait(dataSource2Properties.getMaxWait());
        datasource.setTimeBetweenEvictionRunsMillis(dataSource2Properties.getTimeBetweenEvictionRunsMillis());
        datasource.setMinEvictableIdleTimeMillis(dataSource2Properties.getMinEvictableIdleTimeMillis());
        datasource.setValidationQuery(dataSource2Properties.getValidationQuery());
        datasource.setTestWhileIdle(dataSource2Properties.isTestWhileIdle());
        datasource.setTestOnBorrow(dataSource2Properties.isTestOnBorrow());
        datasource.setTestOnReturn(dataSource2Properties.isTestOnReturn());
        datasource.setPoolPreparedStatements(dataSource2Properties.isPoolPreparedStatements());
        try {
            datasource.setFilters(dataSource2Properties.getFilters());
        } catch (SQLException e) {
            logger.error("Druid configuration initialization filter error.", e);
        }
        return datasource;
    }
}
