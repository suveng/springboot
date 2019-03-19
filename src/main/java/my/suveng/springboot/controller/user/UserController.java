package my.suveng.springboot.controller.user;

import lombok.extern.slf4j.Slf4j;
import my.suveng.springboot.common.enums.ResCodeEnums;
import my.suveng.springboot.common.response.Result;
import my.suveng.springboot.common.response.ResultBuilder;
import my.suveng.springboot.modules.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author suwenguang
 * suveng@163.com
 * since 2019/3/19
 * description:
 **/
@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/getList")
    public Result getList(){
        userService.selectList();
        return ResultBuilder.buildSimpleSuccessResult();
    }
}
