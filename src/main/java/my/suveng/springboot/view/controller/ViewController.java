package my.suveng.springboot.view.controller;

import io.swagger.annotations.Api;
import my.suveng.springboot.common.base.BaseController;
import org.joda.time.DateTime;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 苏文广 created at 2019/1/29
 */

@Controller
@RequestMapping("/view")
public class ViewController extends BaseController {
    @RequestMapping("/test")
    public String test(Model model){
         model.addAttribute("time",new DateTime().toDate());
        return "test";
    }
}
