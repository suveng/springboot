package my.suveng.news.controller.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import my.suveng.news.modules.user.entity.User;
import my.suveng.news.modules.user.service.UserService;
import my.suveng.news.common.page.Pagination;
import my.suveng.news.common.response.Result;
import my.suveng.news.common.response.ResultBuilder;
import my.suveng.news.common.response.ResultEnums;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * @author suwenguang
 * suveng@163.com
 * since 2019/3/19
 * description:
 **/
@RestController
@Slf4j
@RequestMapping("/user")
@Api(tags = {"用户模块"})
@CrossOrigin(origins = "*", allowCredentials = "true")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/getList")
    @ApiOperation(value = "获取用户列表", httpMethod = "POST")
    @ApiResponses({@ApiResponse(code = 200, message = "统一返回对象", response = Result.class)})
    public Result getList(User user, Pagination pagination) {
        // 校验
        if (!ObjectUtils.allNotNull(user, pagination, pagination.getPageNum(), pagination.getPageSize())) {
            return ResultBuilder.buildUnknownResult();
        }
        // 分页查询逻辑
        Page<User> data = userService.selectList(user, pagination.getPageNum(), pagination.getPageSize());
        // 结果封装返回
        if (!ObjectUtils.allNotNull(data)) {
            return new Result(ResultEnums.ILLEGAL_RESULT_ERROR, data);
        }
        return new Result(ResultEnums.SIMPLE_SUCCESS, data);
    }


    @RequestMapping("/add")
    @ApiOperation(value = "添加用户", httpMethod = "POST")
    @ApiResponses({@ApiResponse(code = 200, message = "统一返回对象", response = Result.class)})
    public Result add(@RequestBody User user) {
        // 校验参数
        if (!ObjectUtils.allNotNull(user)) {
            return ResultBuilder.buildSimpleIllegalArgumentError();
        }
        userService.save(user);
        return ResultBuilder.buildSimpleSuccessResult();
    }

    @RequestMapping("/addTestData")
    public Result addTestData() {
        int i = 0;
        while (i < 200) {
            User user = new User();
            user.setEmail(RandomStringUtils.randomAscii(5));
            user.setPassword(RandomStringUtils.randomAscii(5));
            user.setUsername(RandomStringUtils.randomAscii(5));
            this.add(user);
            i++;
        }
        return ResultBuilder.buildSimpleSuccessResult();
    }
    @RequestMapping("/removeAll")
    public Result removeAll() {
        userService.removeAll();
        return ResultBuilder.buildSimpleSuccessResult();
    }
}
