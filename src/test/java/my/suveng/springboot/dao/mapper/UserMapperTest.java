package my.suveng.springboot.dao.mapper;

import my.suveng.springboot.SpringbootApplicationTests;
import my.suveng.springboot.user.entity.User;
import my.suveng.springboot.user.mapper.UserMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author 苏文广 created at 2019/2/11
 */
public class UserMapperTest extends SpringbootApplicationTests {
    @Autowired
    UserMapper userMapper;

    @Test
    public void testMpSelectById(){
        User user = userMapper.selectById(1);
        System.out.println(user);
    }
    @Test
    public void testMp(){
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }

}