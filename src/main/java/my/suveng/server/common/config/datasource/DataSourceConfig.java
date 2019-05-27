package my.suveng.server.common.config.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * description:
 * @author suwenguang@52tt.com
 * @date 2019/5/27
 * @version 1.0.0
 **/
@Configuration
@Slf4j
public class DataSourceConfig {
    @Autowired
    private DruidDataSource clickHouseDataSource;
    @Autowired
    private DruidDataSource dataSource;
    /**
     * 说明: 设置动态加载数据源
     * @author suwenguang
     * @date 2019/5/27
     * @return javax.sql.DataSource <- 返回类型
     */
    @Bean
    public DataSource dynamicDataSource() {
        //创建数据源路由
        DynamicRoutingDataSource dynamicRoutingDataSource = new DynamicRoutingDataSource();
        //设置默认数据源为MySQL
        dynamicRoutingDataSource.setDefaultTargetDataSource(dataSource);
        //设置目标数据源列表
        Map<Object, Object> dataSourceMap = new HashMap<>(2);
        dataSourceMap.put(DataSourceKey.mysql, dataSource);
        dataSourceMap.put(DataSourceKey.ck, clickHouseDataSource);
        dynamicRoutingDataSource.setTargetDataSources(dataSourceMap);
        log.info("########多数据源就绪########");
        return dynamicRoutingDataSource;
    }
}
