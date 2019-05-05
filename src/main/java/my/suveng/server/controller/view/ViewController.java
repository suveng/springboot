package my.suveng.server.controller.view;

import my.suveng.server.common.aspect.costtime.usage.CostTime;
import my.suveng.server.common.base.BaseController;
import org.joda.time.DateTime;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 苏文广 created at 2019/1/29
 */
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
    @CostTime
    public String test(Model model) {
        int i = 1 / 0;
        model.addAttribute("time", new DateTime().toDate());
        return "test";
    }
}
