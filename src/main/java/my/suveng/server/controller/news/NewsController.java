package my.suveng.server.controller.news;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author suwenguang
 * email suveng@163.com
 * since 2019/4/6
 * description:新闻模块
 **/
@RestController
@Slf4j
@RequestMapping("/news")
@Api(tags = {"新闻模块"})
@CrossOrigin(origins = "*", allowCredentials = "true")
public class NewsController {

}
