package org.example.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class TimeAspect {


    @Pointcut("execution(* org.example.App.say())")
    private void pointcut() {

    }



    @Before("pointcut()")
    public void before(){
        System.out.println("Hello");
    }

    @Around("pointcut()")
    public void timeuse(ProceedingJoinPoint joinPoint) throws Throwable {

        long startTime = System.nanoTime();

        Object result = joinPoint.proceed();

        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;

        System.out.println("Method " + joinPoint.getSignature().toShortString() + " took " + executionTime + " nanoseconds.");

    }
}