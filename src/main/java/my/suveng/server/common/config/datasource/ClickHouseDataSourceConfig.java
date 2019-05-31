//package my.suveng.server.common.config.datasource;
//
//import com.alibaba.druid.pool.DruidDataSource;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.annotation.MapperScan;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * description:
// * @author suwenguang@52tt.com
// * @date 2019/5/27
// * @version 1.0.0
// **/
////@Configuration
////@MapperScan(basePackages = {"my.suveng.server.modules.user.mapper.ck"},
////        sqlSessionFactoryRef = "clickHouseSqlSessionFactory"
////)
//public class ClickHouseDataSourceConfig {
//    private static final Logger log = LoggerFactory.getLogger(ClickHouseDataSourceConfig.class);
//    @Bean(name = "clickHouseDataSource")
//    @ConfigurationProperties(prefix = "spring.clickhouse")
//    public DruidDataSource clickHouseDataSource() {
//        log.info("########:clickhouse datasource就绪:########");
//        return new DruidDataSource();
//    }
//    @Bean(name = "clickHouseSqlSessionFactory")
//    public SqlSessionFactory clickHouseSqlSessionFactory(@Qualifier("clickHouseDataSource") DruidDataSource clickHouseDataSource) throws Exception {
//        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
//        sessionFactory.setDataSource(clickHouseDataSource);
//        return sessionFactory.getObject();
//    }
//}
