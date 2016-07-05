package com.app.aspect;

import javax.validation.constraints.Null;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAspect {
	
	private static Logger logger = Logger.getLogger(LogAspect.class);
	
	@Pointcut("execution(* com.app.service.impl.*Service*.*(..))")
	public void pointCut() {
	}

//	@After("pointCut()")
//	public void after(JoinPoint joinPoint) {
//		String clazz = joinPoint.getTarget().getClass().getName();
//		String methodName = joinPoint.getSignature().getName();
//		System.out.println("after aspect class:"+clazz+",method:"+ methodName+" executed");
//	}
//
//	@Before("pointCut()")
//	public void before(JoinPoint joinPoint) {
//		//如果需要这里可以取出参数进行处理
//		//Object[] args = joinPoint.getArgs();
//		System.out.println("before aspect executing");
//	}

//	@AfterReturning(pointcut = "pointCut()", returning = "returnVal")
//	public void afterReturning(JoinPoint joinPoint, Object returnVal) {
//		System.out.println("afterReturning executed, return result is "
//				+ returnVal);
//	}

	@Around("pointCut()")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		Object result = null;
		String clazz = pjp.getTarget().getClass().getName();
		String methodName = pjp.getSignature().getName();
		logger.info(clazz + "." + methodName + " start");
		System.out.println(clazz + "." + methodName + " start");
		try {
			result = pjp.proceed();
		} catch (Throwable ex) {
			System.out.println("error in " + clazz + "." + methodName);
			throw ex;
		}
		System.out.println(clazz + "." + methodName + " end");
		return result;
	}
//
//	@AfterThrowing(pointcut = "pointCut()", throwing = "error")
//	public void afterThrowing(JoinPoint jp, Throwable error) {
//		System.out.println("error:" + error);
//	}

}
