package my.suveng.server.common.config.datasource;

/**
 * description:
 * @author suwenguang@52tt.com
 * @date 2019/5/27
 * @version 1.0.0
 **/
public enum DataSourceKey {

    /**
     * mysql数据源
     **/
    mysql("mysqlDataSource"),

    /**
     * clickhouse 数据源
     **/
    ck("clickHouseDataSource"),
    ;


    DataSourceKey(String name) {
        this.name = name;
    }


    /**
     * 数据源名称
     **/
    private String name;

}

