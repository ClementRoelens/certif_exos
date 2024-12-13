package org.example.exofiltreservlet.aspect;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
@Aspect
public class JournalizationAspect {

    @Pointcut("@annotation(org.example.exofiltreservlet.annotation.JournalizationAnnotation)")
    public void journalizationPointCut() {}

    @Before("journalizationPointCut()")
    public void journalize() {
        ServletRequestAttributes servletRequestAttributes = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
        if (servletRequestAttributes != null) {
            HttpServletRequest request = servletRequestAttributes.getRequest();
            System.out.println("URI demandée : " + request.getRequestURI());
            System.out.println("Méthode utilisée : " + request.getMethod());
            System.out.println("Timestamp de la requête : " +System.currentTimeMillis());
        }
    }
}
