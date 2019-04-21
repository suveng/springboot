package my.suveng.server.common.aspect.costtime;


import my.suveng.server.common.aspect.costtime.usage.CostTime;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author suwenguang
 * suveng@163.com
 * since 2019/2/27
 * description: 切面类---计算执行时间,再方法前添加@costtime注解即可打印执行时间
 **/
@Aspect
@Component
public class CostTimeAspect {
    private static final Logger log = LoggerFactory.getLogger(CostTimeAspect.class);
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
        long start = System.nanoTime();
        try {
            Object res = pjp.proceed();
            long end = System.nanoTime();
            double cost = (end - start)/1000000;
            log.info("#############【{}】:执行完成,耗时：【{} 毫秒】", pjp.getSignature().toString(), cost);
            return res;
        } catch (Throwable throwable) {
            log.error("方法【{}】执行异常！",pjp.getSignature().getDeclaringTypeName());
            throw new RuntimeException("方法【"+pjp.getSignature().getDeclaringTypeName()+"】执行异常！",throwable);
        }
    }
}
