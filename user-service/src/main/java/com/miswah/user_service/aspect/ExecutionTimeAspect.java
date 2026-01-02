package com.miswah.user_service.aspect;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Aspect
@Component
public class ExecutionTimeAspect {

    @Pointcut("execution(* com.miswah.user_service.controller.*.*(..))")
    public void controllerMethods(){}


    @Around("controllerMethods()")
    public Object mesureExecutionTime(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.nanoTime();

        try{
            return pjp.proceed();
        } finally {
            long end = System.nanoTime();
            long elaspedTime = end - start;
            long elaspedMs = TimeUnit.NANOSECONDS.toMillis(elaspedTime);
            String signature = pjp.getSignature().getName();

            log.info("Controller method {} executed in {} ms", signature, elaspedMs);
        }
    }


}
