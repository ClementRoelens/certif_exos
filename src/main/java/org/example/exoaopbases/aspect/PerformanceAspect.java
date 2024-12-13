package org.example.exoaopbases.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class PerformanceAspect {
    @Pointcut("@annotation(org.example.exoaopbases.annotation.PerformanceAnnotation)")
    public void performancePointcut() {
    }


    @Around("performancePointcut()")
    public Object monitor(ProceedingJoinPoint proceedingJoinPoint)  {
        long begin = 0,end;
        String methodName = proceedingJoinPoint.getSignature().getName();
        try {
            begin = System.currentTimeMillis();
            return proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            return new Throwable(throwable);
        } finally {
            end = System.currentTimeMillis();
            System.out.println("PerformanceAspect\nTemps d'exécution de " + methodName + " : " + (end - begin) + "ms");
        }
    }
}
