package my.suveng.server.controller.view;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import my.suveng.server.common.aspect.annotations.CostTime;
import my.suveng.server.common.base.BaseController;
import my.suveng.server.common.response.Result;
import org.joda.time.DateTime;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 苏文广 created at 2019/1/29
 */
@Api(tags = {"视图控制模块"})
@Controller
@RequestMapping("/view")
public class ViewController extends BaseController {
    /**
     * 测试 视图
     *
     * @param model model
     *
     * @return test.ftl
     */

    @GetMapping("/test")
    @ApiOperation(value = "测试视图", httpMethod = "GET")
    @ApiResponses({@ApiResponse(code = 200, message = "统一返回对象", response = Result.class)})
    @CostTime
    public String test(Model model) {
        int i = 1 / 0;
        model.addAttribute("time", new DateTime().toDate());
        return "test";
    }
}
