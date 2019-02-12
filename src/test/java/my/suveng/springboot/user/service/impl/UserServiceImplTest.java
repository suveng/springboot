package my.suveng.springboot.user.service.impl;

import com.baomidou.mybatisplus.extension.service.additional.query.impl.QueryChainWrapper;
import my.suveng.springboot.SpringbootApplicationTests;
import my.suveng.springboot.user.entity.User;
import my.suveng.springboot.user.service.IUserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author 苏文广 created at 2019/2/12
 */
public class UserServiceImplTest extends SpringbootApplicationTests {
    @Autowired
    IUserService userService;
    @Test
    public void testUserService(){
        List<User> list = userService.list();
        System.out.println(list);
        int count = userService.count();
        User byId = userService.getById(1);
    }

}