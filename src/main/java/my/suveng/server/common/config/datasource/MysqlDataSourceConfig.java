package my.suveng.server.common.config.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * description:
 * @author suwenguang@52tt.com
 * @date 2019/5/27
 * @version 1.0.0
 **/
@Slf4j
@Configuration
@MapperScan(basePackages = {"my.suveng.server.modules.user.mapper.mysql"},
        sqlSessionFactoryRef = "sqlSessionFactory"
)
public class MysqlDataSourceConfig {
    /** 配置文件名, 放在 classpath */
    private String MYBATIS_CONFIG_FILE = "mybatis-config.xml";

    @Bean(name = "dataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.mysql")
    public DataSource dataSource() {
        log.info("dataSource 数据源加载");
        return new DruidDataSource();
    }

    @Bean(name = "sqlSessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) throws Exception {
        log.info("sqlSessionFactory 加载");
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setConfigLocation(new ClassPathResource(MYBATIS_CONFIG_FILE));
        sessionFactory.setDataSource(dataSource);
        return sessionFactory.getObject();
    }


}

