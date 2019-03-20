package my.suveng.springboot.modules.user.service.impl;

import lombok.extern.slf4j.Slf4j;
import my.suveng.springboot.modules.user.dao.UserRepository;
import my.suveng.springboot.modules.user.entity.User;
import my.suveng.springboot.modules.user.service.UserService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * @author suwenguang
 * suveng@163.com
 * since 2019/3/19
 * description:
 **/
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    /**
     * 分页查询user list
     * @return list
     */
    @Override
    public Page<User> selectList(User user, int page, int size) {
        // 校验
        if (!ObjectUtils.allNotNull(user,page,size)) {
            return null;
        }
        // 排序
        Sort orders = new Sort(Sort.Direction.DESC,"id");
        // 分页查询
        return userRepository.findAll(PageRequest.of(page, size, orders));
    }

    /**
     * 保存一个user
     * @param user user
     */
    @Override
    public void save(User user) {
        userRepository.save(user);
    }
}
