package com.zaju.datamanager.controllers.aspects;

import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zaju.datamanager.domain.OperationHistory;
import com.zaju.datamanager.service.OperationHistoryService;

@Aspect
@Component
public class LoginControllerAspect {
	
	@Autowired
	private OperationHistoryService operationHistoryService;

	@Around("execution(* com.zaju.datamanager.controllers.LoginController.*(..))")
	public Object saveUserOperationHistory(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		OperationHistory operationHistory = new OperationHistory();
		
		Object returnValue;
		
		String params = getArgsAsString(proceedingJoinPoint.getArgs());
		
		operationHistory.setParams(params);
		operationHistory.setMethodSignatue(proceedingJoinPoint.getSignature().toLongString());
		operationHistory.setExecutionDate(new Date());
				
		try {
			returnValue = proceedingJoinPoint.proceed();
			operationHistory.setSuccess(true);
		} catch (Exception e) {
			
			operationHistory.setSuccess(false);
			operationHistory.setExceptionName(e.getClass().getName());
			operationHistory.setExceptionMessage(e.getMessage());
			
			throw e;
		} finally {
			operationHistoryService.save(operationHistory);
		}
		
		return returnValue;
	}
	
	private String getArgsAsString(Object[] args) {
		String params = "";
		
		for (Object arg : args) {
			params = params.concat((String) arg);
			params = params.concat(",");
		}
		
		return params;
	}
	
}