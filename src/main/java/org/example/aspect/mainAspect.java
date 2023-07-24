package org.example.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class mainAspect {

    @Pointcut("execution(* org.example.App.main(..))")
    private void mainPointcut() {

    }

    @Around("mainPointcut()")
    public void timeLog(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.nanoTime();

        Object result = joinPoint.proceed();

        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;

        System.out.println("Method " + joinPoint.getSignature().toShortString() + " took " + executionTime + " nanoseconds.");
    }
}
