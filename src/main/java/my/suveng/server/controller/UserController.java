package my.suveng.server.controller;


import my.suveng.server.common.response.Result;
import my.suveng.server.common.response.ResultBuilder;
import my.suveng.server.common.response.ResultEnums;
import my.suveng.server.modules.user.entity.User;
import my.suveng.server.modules.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author suveng
 * @since 2019-05-05
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @RequestMapping("/add")
    @ResponseBody
    public Result addUser(){
        User t = new User();
        t.setUserId(UUID.randomUUID().toString().toLowerCase().replace("-", ""));
        t.setName("sugg");
        t.setIdNum("sdfsd");
        t.setDeposit((double) 123);
        t.setBalance((double) 213);
        t.setStatus(0);
        userService.insert(t);
        return ResultBuilder.build(ResultEnums.SIMPLE_SUCCESS,t);
    }
}
