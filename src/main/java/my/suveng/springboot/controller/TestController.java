package my.suveng.springboot.controller;

import my.suveng.springboot.dao.mapper.UserMapper;
import my.suveng.springboot.model.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Wrapper;

/**
 * @author 苏文广 created at 2019/1/29
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/test")
    public String test() {
        return "测试成功了，ok";
    }

    @RequestMapping("/test2")
    public String test2() {
        return "测试成功了，ok1";
    }

    @RequestMapping("/mp")
    public String mp() {
      return   userMapper.selectById(1).toString();
    }
}
