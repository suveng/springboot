package my.suveng.server.common.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author suwenguang
 *         suveng@163.com
 * since 2019/5/15
 * description: 日志分业务模块打印到不同文件
 **/
public class LogbackFactory {
    /**
     * 系统logger
     */
    public static Logger SYSTEM_LOGGER = LoggerFactory.getLogger("systemLogger");

    /**
     * 业务模块-user模块 logger
     */
    public static Logger USER_LOGGER =LoggerFactory.getLogger("userLogger");

}
