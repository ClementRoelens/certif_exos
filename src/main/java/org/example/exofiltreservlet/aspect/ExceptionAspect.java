package org.example.exofiltreservlet.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

@Component
@Aspect
public class ExceptionAspect {

    @Pointcut("@annotation(org.example.exofiltreservlet.annotation.ExceptionAnnotation)")
    public void exceptionPointcut() {}

    @AfterThrowing(value = "exceptionPointcut()", throwing = "e")
    public void afterThrowing(Exception e) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/log.txt",true))){
            writer.newLine();
            writer.write("ExceptionAspect");
            writer.newLine();
            writer.write(e.getClass().getSimpleName() + " déclenchée à " + System.currentTimeMillis());
            writer.newLine();
            writer.write(e.getMessage());
            writer.newLine();
            for (StackTraceElement stackTraceElement : e.getStackTrace()) {
                writer.write(stackTraceElement.toString());
                writer.newLine();
            }
            writer.newLine();
        } catch (IOException ex){
            System.out.println("Pas possible d'écrire le fichier de log");
            System.out.println(ex.getMessage());
        }
    }
}
