package my.suveng.springboot.modules.user.service.impl;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import my.suveng.springboot.modules.user.dao.UserRepository;
import my.suveng.springboot.modules.user.entity.User;
import my.suveng.springboot.modules.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Override
    public void selectList() {
        User entity = new User();
        entity.setEmail("12");
        entity.setNickName("12");
        entity.setPassWord("12");
        entity.setRegTime("sd");
        entity.setUserName("d");
        log.info("user:【{}】", JSON.toJSONString(entity));
        userRepository.save(entity);

    }
}
