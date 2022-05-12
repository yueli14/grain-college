/**
 * @Classname SeataConfig
 * @Description TODO
 * @Date 2022/5/6 18:40
 * @Created by 28327
 */

package com.wcl.servicebase.config;
import com.zaxxer.hikari.HikariDataSource;
import io.seata.rm.datasource.DataSourceProxy;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;

@Configuration
public class SeataConfig {
    @Bean
    public DataSource dataSource(DataSourceProperties properties){
        HikariDataSource dataSource = properties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
        if (StringUtils.hasText(properties.getName())){
            dataSource.setPoolName(properties.getName());
        }
        return new DataSourceProxy(dataSource);
    }
}
