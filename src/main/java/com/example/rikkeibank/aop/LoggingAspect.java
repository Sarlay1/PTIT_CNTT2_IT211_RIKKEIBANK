package com.example.rikkeibank.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    @Around(
        "execution(* com.example.rikkeibank.service.impl.*.*(..))"
    )
    public Object logExecutionTime(
            ProceedingJoinPoint joinPoint
    ) throws Throwable {

        long start = System.currentTimeMillis();

        Object result = joinPoint.proceed();

        long end = System.currentTimeMillis();

        log.info(
                "{} executed in {} ms",
                joinPoint.getSignature().toShortString(),
                (end - start)
        );

        return result;
    }
}