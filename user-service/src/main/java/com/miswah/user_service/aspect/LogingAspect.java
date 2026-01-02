package com.miswah.user_service.aspect;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LogingAspect {


    @Pointcut("execution(* com.miswah.user_service.service.*.*(..))")
    public void serviceMethod(){}


    @Before("serviceMethod()")
    public void logBefore(JoinPoint joinpoint){
        log.info("Called service method : {} with arguments {}", joinpoint.getSignature().getName(), joinpoint.getArgs());
    }

    @AfterReturning(pointcut = "serviceMethod()", returning= "result")
    public void afterReturning(JoinPoint joinpoint, Object result){
        log.info("Service Method : {} returned : {}", joinpoint.getSignature().getName(), result);
    }
}
