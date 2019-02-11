package my.suveng.springboot.dao.mapper;

import my.suveng.springboot.SpringbootApplicationTests;
import my.suveng.springboot.model.po.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 苏文广 created at 2019/2/11
 */
public class UserMapperTest extends SpringbootApplicationTests {
    @Autowired
    UserMapper userMapper;

    @Test
    public void testMp(){
        User user = userMapper.selectById(1);
        System.out.println(user);
    }

}