package com.minitech.boot.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TestAspect {

	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 
	 * @description 切点
	 * @author sm
	 */

	// 单个连接点
	@Pointcut("execution (* com.minitech.boot.service.CityService.addCity(..))")
	public void executionPc() {

	}

	// 多个连接点
	@Pointcut("execution (* com.minitech.boot.service.CityService..*(..))")
	public void executionAll() {

	}

	// 包内所有连接点
	@Pointcut("within (com.minitech.boot.service.*)")
	public void withinPc() {

	}

	// 单个实例的连接点且限定方法
	@Pointcut("target (com.minitech.boot.service.CityService) && execution( * addCity(..))")
	public void targetPc() {

	}

	@Around("targetPc()")
	public Object targetAdvice(ProceedingJoinPoint jp) throws Throwable {
		Object result = "";
		try {
			result = jp.proceed();
		} catch (Throwable e) {
			result = e.getMessage();
			throw e;
		} finally {
			logger.info("切点target，环绕通知");
		}
		return result;

	}

	@Before("executionPc()")
	public void executionAdvice(JoinPoint jp) throws Throwable {
		logger.info("切点execution,之前通知");

	}

	@After("executionAll()")
	public void executionAllAdvice(JoinPoint jp) throws Throwable {

		logger.info("切点executionAll，之后通知");

	}

	@AfterReturning("withinPc() &&  args(i)")
	public void withinAdvice(int i) throws Throwable {
		logger.info("切点within，成功之后通知,i="+i);

	}

}
