package my.suveng.springboot.config;

import org.hibernate.dialect.MySQL5InnoDBDialect;

/**
 * @author suwenguang
 * email suveng@163.com
 * since 2019/3/24
 * description:
 **/
public class MySQL5DialectUTF8 extends MySQL5InnoDBDialect {

    @Override
    public String getTableTypeString() {
        return " ENGINE=InnoDB DEFAULT CHARSET=utf8";
    }
}
