package my.suveng.server.common.exceptionhandler;

import my.suveng.server.common.response.Result;
import my.suveng.server.common.response.ResultBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author suwenguang
 * suveng@163.com
 * since 2019/2/27
 * description: spring统一异常处理类
 **/
@ControllerAdvice
public class ExceptionAdvice {


    @ExceptionHandler({Exception.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Result handleIndexOutOfBoundsException(Exception e) {
        e.printStackTrace();
        return ResultBuilder.buildUnknownResult();
    }
}
