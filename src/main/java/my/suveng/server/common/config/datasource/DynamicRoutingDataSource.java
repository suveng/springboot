package my.suveng.server.common.config.datasource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * description:
 * @author suwenguang@52tt.com
 * @date 2019/5/27
 * @version 1.0.0
 **/
@Slf4j
public  class DynamicRoutingDataSource extends AbstractRoutingDataSource {


    @Override
    protected Object determineCurrentLookupKey() {
        log.info("当前数据源是:[{}]", DynamicDataSourceContextHolder.get());
        return DynamicDataSourceContextHolder.get();
    }
}
