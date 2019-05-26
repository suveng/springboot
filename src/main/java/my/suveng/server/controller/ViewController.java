package my.suveng.server.controller;

import my.suveng.server.common.costtime.usage.CostTime;
import org.joda.time.DateTime;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 苏文广 created at 2019/1/29
 */
@Controller
public class ViewController{
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

    @RequestMapping("/user/login.html")
    @CostTime
    public String login(){
        return "login";
    }


}
