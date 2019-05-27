package my.suveng.server.modules.user.mapper.mysql;

import my.suveng.server.modules.user.entity.User;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author suveng
 * @since 2019-05-05
 */
public interface UserMapper {

    List<User> selectList();

}
