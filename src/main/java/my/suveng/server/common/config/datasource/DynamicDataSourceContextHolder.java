package my.suveng.server.common.config.datasource;

import lombok.extern.slf4j.Slf4j;

/**
 * description:
 * @author suwenguang@52tt.com
 * @date 2019/5/27
 * @version 1.0.0
 **/
@Slf4j
public class DynamicDataSourceContextHolder {

    /**
     * 说明: 当前采用的数据源
     */
    private static final ThreadLocal<DataSourceKey> CURRENT_DATESOURCE = new ThreadLocal<>();

    /**
     * 清除当前数据源
     */
    public static void clear() {
        log.info("当前数据源:{},即将被清空",get());

        CURRENT_DATESOURCE.remove();
    }

    /**
     * 获取当前使用的数据源
     *
     * @return 当前使用数据源的ID
     */
    public static DataSourceKey get() {
        return CURRENT_DATESOURCE.get();
    }

    /**
     * 设置当前使用的数据源
     *
     * @param value 需要设置的数据源ID
     */
    public static void set(DataSourceKey value) {
log.info("当前数据源:{},需要设置的数据源:{}",get(),value);

        CURRENT_DATESOURCE.set(value);
    }

    ///**
    // * 设置从从库读取数据
    // * 采用简单生成随机数的方式切换不同的从库
    // */
    //public static void setSlave() {
    //    if (RandomUtils.nextInt(0, 2) > 0) {
    //        DynamicDataSourceContextHolder.set(DataSourceKey.DB_SLAVE2);
    //    } else {
    //        DynamicDataSourceContextHolder.set(DataSourceKey.DB_SLAVE1);
    //    }
    //}
}
