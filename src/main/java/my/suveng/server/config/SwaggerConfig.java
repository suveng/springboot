package my.suveng.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author suwenguang
 * @email suveng@163.com
 * since 2019/2/22
 * description: swagger 配置类
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("my.suveng.springboot.controller"))
                .paths(PathSelectors.any())
                .build().useDefaultResponseMessages(false);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("springboot学习项目的RESTful APIs")
                .description("springboot学习项目后台api接口文档")
                .contact(new Contact("suveng", "https://suveng.github.io/blog/about.me.html", "suveng@163.com"))
                .license("MIT")
                .licenseUrl("https://opensource.org/licenses/MIT")
                .version("1.0")
                .build();
    }
}
