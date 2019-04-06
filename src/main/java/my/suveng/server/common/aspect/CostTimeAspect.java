package my.suveng.server.common.aspect;


import lombok.extern.slf4j.Slf4j;
import my.suveng.server.common.aspect.annotations.CostTime;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.springframework.stereotype.Component;

/**
 * @author suwenguang
 * suveng@163.com
 * since 2019/2/27
 * description: 切面类---计算执行时间
 **/
@Aspect
@Component
@Slf4j
public class CostTimeAspect {
    @Pointcut(value = "execution(* *..*.*.*(..)) ")
    public void anyMethod() {
    }

    /**
     * 环绕通知，计算执行时间
     *
     * @param pjp      joinPoint
     * @param costTime 自定义注解
     */
    @Around(value = "anyMethod() && @annotation(costTime)", argNames = "pjp,costTime")
    public Object aroundMethod(ProceedingJoinPoint pjp, CostTime costTime) {
        log.info("#############【{}】开始执行!",pjp.getSignature().toString());
        DateTime start = DateTime.now();
        try {
            Object res = pjp.proceed();
            DateTime end = DateTime.now();
            long cost = new Duration(start, end).getMillis();
            log.info("#############【{}】:执行完成,耗时：【{}】", pjp.getSignature().toString(), cost);
            return res;
        } catch (Throwable throwable) {
            log.error("方法【{}】执行异常！",pjp.getSignature().getDeclaringTypeName());
            throw new RuntimeException("方法【"+pjp.getSignature().getDeclaringTypeName()+"】执行异常！",throwable);
        }
    }
}
