package my.suveng.server.common.config.datasource;

import java.util.function.Supplier;

/**
 * description:
 * @author suwenguang@52tt.com
 * @date 2019/5/27
 * @version 1.0.0
 **/
public enum DataSourceKey {
    mysql("mysqlDataSource"),
    ck("clickHouseDataSource"),
    ;


    DataSourceKey(String name) {
        this.name = name;
    }

    private String name;

}

