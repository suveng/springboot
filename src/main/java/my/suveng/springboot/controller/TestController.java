package my.suveng.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 苏文广 created at 2019/1/29
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @RequestMapping("/test")
    public String test(){
        return "测试成功了，ok";
    }
    @RequestMapping("/test2")
    public String test2(){
        return "测试成功了，ok";
    }
}
