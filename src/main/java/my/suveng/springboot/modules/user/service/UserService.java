package my.suveng.springboot.modules.user.service;

import my.suveng.springboot.modules.user.entity.User;
import org.springframework.data.domain.Page;

/**
 * @author suwenguang
 * suveng@163.com
 * since 2019/3/19
 * description:
 **/
public interface UserService {
    /**
     * 分页查询user list
     * @return list
     */
    Page<User> selectList(User user, int page, int size);

    /**
     * 保存一个user
     * @param user user
     */
    void save(User user);
}
