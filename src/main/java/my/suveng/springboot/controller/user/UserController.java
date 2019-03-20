package my.suveng.springboot.controller.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import my.suveng.springboot.common.page.Pagination;
import my.suveng.springboot.common.response.Result;
import my.suveng.springboot.common.response.ResultBuilder;
import my.suveng.springboot.common.response.ResultEnums;
import my.suveng.springboot.modules.user.entity.User;
import my.suveng.springboot.modules.user.service.UserService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
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
@Api(tags = {"用户模块"})
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/getList")
    @ApiOperation(value = "获取用户列表", httpMethod = "POST")
    @ApiResponses({@ApiResponse(code = 200, message = "统一返回对象", response = Result.class)})
    @CrossOrigin(origins = "*")
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
    @CrossOrigin(origins = "*", allowCredentials = "true")
    public Result add(User user) {
        // 校验参数
        if (!ObjectUtils.allNotNull(user)) {
            return ResultBuilder.buildSimpleIllegalArgumentError();
        }
        userService.save(user);
        return ResultBuilder.buildSimpleSuccessResult();
    }
}
