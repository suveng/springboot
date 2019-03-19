package my.suveng.springboot.modules.user.service;

import my.suveng.springboot.modules.user.entity.User;

import java.util.List;

/**
 * @author suwenguang
 * suveng@163.com
 * since 2019/3/19
 * description:
 **/
public interface UserService {
    List<User> selectList();
}
