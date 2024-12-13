package org.example.exoaopbases.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class LoggingAspect {

    @Pointcut("@annotation(org.example.exoaopbases.annotation.LogAnnotation)")
    public void logPointcut() {}

    @Pointcut("@annotation(org.example.exoaopbases.annotation.ReturningLogAnnotation)")
    public void logReturningLogAnnotation() {}

    @Before("logPointcut()")
    public void logBefore(JoinPoint joinPoint){
        System.out.println("LoggingAspect : une méthode va se déclencher : " + joinPoint.getSignature().getName());
        Object[] args = joinPoint.getArgs();
        if (args.length > 0){
            System.out.println("Les arguments sont : " + Arrays.toString(args));
        }
    }

    @AfterReturning(value = "logReturningLogAnnotation()", returning = "result")
    public void logAfter(Object result){
        System.out.println("LoggingAspect : une méthode va retourner : ");
        System.out.println(result);
    }
}
