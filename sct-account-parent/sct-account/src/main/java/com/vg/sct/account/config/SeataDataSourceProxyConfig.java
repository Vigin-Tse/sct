package com.vg.sct.account.config;

import com.alibaba.druid.pool.DruidDataSource;
import io.seata.rm.datasource.DataSourceProxy;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * seata配置代理数据源
 *
 * spring-boot-seata 在配置文件（.yml）中默认开启自动配置数据源
 *
 * @author: xieweij
 * @time: 2021/7/15 11:41
 */
//@Configuration
public class SeataDataSourceProxyConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DruidDataSource druidDataSource() {
        return new DruidDataSource();
    }

    @Primary //有多个相同类型的bean时，使用@Primary来赋予bean更高的优先级。
    @Bean("dataSource")
    public DataSource dataSource(DruidDataSource druidDataSource) {
        return new DataSourceProxy(druidDataSource);
    }

    /**
     * MyBatis 还需要额外注入 org.apache.ibatis.session.SqlSessionFactory
     * @param dataSourceProxy
     * @return
     * @throws Exception
     */
//    @Bean
//    public SqlSessionFactory sqlSessionFactoryBean(DataSourceProxy dataSourceProxy) throws Exception {
//        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//        sqlSessionFactoryBean.setDataSource(dataSourceProxy);
//        return sqlSessionFactoryBean.getObject();
//    }
}
