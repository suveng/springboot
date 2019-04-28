package my.suveng.server.controller.user;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import my.suveng.server.common.page.Pagination;
import my.suveng.server.common.poi.export.POIIncrementExport;
import my.suveng.server.common.response.Result;
import my.suveng.server.common.response.ResultBuilder;
import my.suveng.server.common.response.ResultEnums;
import my.suveng.server.modules.user.entity.User;
import my.suveng.server.modules.user.service.UserService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;

import static my.suveng.server.common.poi.DataUtil.createTableStyle;

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

    @RequestMapping("/poi/export")
    public void poiExport(HttpServletResponse response){
        //使用公司的封装的poi，导出。
        String fileName = "demo";
        POIIncrementExport poiIncrementExport = new POIIncrementExport(User.class,
                Arrays.asList("id", "username", "password"));
        poiIncrementExport.createTableBody(userService.selectList(new User(), 0, 20).getContent());
        poiIncrementExport.writeAndFlush(response, fileName);
    }

    @RequestMapping("/easy/export")
    public void export(HttpServletResponse response) throws IOException, InterruptedException {
        //使用easy excel的导出
        ServletOutputStream outputStream = response.getOutputStream();
        String fileName = "用户列表";
        //列表数据
        List<User> list = userService.selectList(new User(), 0, 20).getContent();
        //获取easy writer 的数据
        ExcelWriter writer = EasyExcelFactory.getWriter(outputStream);
        //写第二个sheet sheet  模型上打有表头的注解，合并单元格
        Sheet sheet = new Sheet(2, 3, User.class, "用户列表", null);
        sheet.setTableStyle(createTableStyle());
        writer.write(list, sheet);
        //设置返回头
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName + ".xlsx", "UTF-8"));
        //设置返回头
        response.setHeader("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        //写入workbook,并关闭流
        writer.finish();
        //关闭流
        outputStream.flush();

    }
}
