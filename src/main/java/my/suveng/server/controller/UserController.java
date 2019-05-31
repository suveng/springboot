package my.suveng.server.controller;

import my.suveng.server.common.response.Response;
import my.suveng.server.common.response.ResponseBuilder;
import my.suveng.server.common.response.ResponseEnums;
import my.suveng.server.modules.user.entity.User;
import my.suveng.server.modules.user.mapper.mysql.UserMapper;
import my.suveng.server.modules.user.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * description:
 * @author suwenguang@52tt.com
 * @date 2019/5/27
 * @version 1.0.0
 **/
@RestController
@RequestMapping("/user")
public class UserController {
    //@Autowired
    //private UserCkService userCkService;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserServiceImpl userService;
    //@RequestMapping("/get")
    //public Response get(){
    //    DynamicDataSourceContextHolder.set(DataSourceKey.ck);
    //    int i = userCkService.selectTotal(null);
    //    List<UserCk> userCks = userCkService.selectList();
    //    return ResponseBuilder.build(ResponseEnums.SIMPLE_SUCCESS,userCks);
    //}
    @RequestMapping("/getUser")
    public Response getUser(){
        //DynamicDataSourceContextHolder.set(DataSourceKey.mysql);
        List<User> users = userMapper.selectList();
        return  ResponseBuilder.build(ResponseEnums.SIMPLE_SUCCESS,users);
    }
}
