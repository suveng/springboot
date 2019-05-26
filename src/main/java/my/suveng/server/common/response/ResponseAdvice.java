package my.suveng.server.common.response;


import my.suveng.server.common.exception.annotation.IgnoreResponseAdvice;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author suwenguang
 * suveng@163.com
 * since 2019/2/27
 * description: spring统一返回处理类
 **/
@RestControllerAdvice
@SuppressWarnings("all")
public class ResponseAdvice implements ResponseBodyAdvice<Object> {



    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        //检查类上是否有注解
        if (methodParameter.getDeclaringClass().isAnnotationPresent(IgnoreResponseAdvice.class)) {
            return false;
        }
        //检查方法上是否有注解
        if (methodParameter.getMethod().isAnnotationPresent(IgnoreResponseAdvice.class)) {
            return false;
        }
        return true;
    }

    @Override
    public Object beforeBodyWrite(@Nullable Object o,
                                  MethodParameter methodParameter,
                                  MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> aClass,
                                  ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {
        Response res = new Response(0, "默认包装返回", null);
        if (o == null) {
            return res;
        } else if (o instanceof Response) {
            return (Response)o;
        } else {
            res.setData(o);
            return res;
        }
    }
}
