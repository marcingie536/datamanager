package com.zaju.datamanager.controllers.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RestControllerAspect {
	
//	@Around("execution(String com.zaju.datamanager.controllers.Controller.*())")
//	public Object testAspect(ProceedingJoinPoint joinPoint) throws Throwable {
//		Object returnValue = joinPoint.proceed();
//		System.out.println("aroundHello" + joinPoint.getSignature());
//		return returnValue;
//	}

//	@Before("execution(String com.zaju.datamanager.controllers.Controller.*())")
//	public void testAspect(JoinPoint joinPoint) throws Throwable {
//		System.out.println("aroundHello " + joinPoint.getSignature().toShortString());
//	}

//	@Before("@annotation(GetMapping)")
//	public void testAspect(JoinPoint joinPoint) throws Throwable {
//		System.out.println("aroundHello " + joinPoint.getSignature().toShortString());
//	}
}
