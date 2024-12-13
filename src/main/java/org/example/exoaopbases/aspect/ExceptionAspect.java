package org.example.exoaopbases.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ExceptionAspect {
    @Pointcut("@annotation(org.example.exoaopbases.annotation.ExceptionAnnotation)")
    public void exceptionPointCut() {}

    @AfterThrowing(value = "exceptionPointCut()", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Exception e) {
        System.out.println("ExceptionAspect\nUne exception a été levée dans " + joinPoint.getSignature().getName());
        System.out.println(e.getMessage());
    }
}
