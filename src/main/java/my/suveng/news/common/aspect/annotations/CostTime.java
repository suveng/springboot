package my.suveng.news.common.aspect.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author suwenguang
 * suveng@163.com
 * since 2019/2/27
 * description: 自定义注解，打上注解可计算方法执行时间。
 **/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CostTime {
    /**
     * 方法名
     *
     * @return 方法名
     */
    String name() default "";
}
