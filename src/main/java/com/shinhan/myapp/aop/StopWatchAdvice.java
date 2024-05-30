package com.shinhan.myapp.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;


@Component
//@Aspect
public class StopWatchAdvice{

	@Pointcut("within(com.shinhan.myapp.controller.BoardController)")
	public void pointcutTarget() {}
	
	
	@Around("pointcutTarget()")
	public Object aa(ProceedingJoinPoint jp) throws Throwable {
		String methodname = jp.getSignature().getName();
		
		
		System.out.println("******" + methodname + "*******");
		StopWatch watch = new StopWatch("aa");
		watch.start();
		
		 
		// 주업무
		Object object = jp.proceed();
		
		// 보조업무
		System.out.println("*****" + methodname +  "*******");
		watch.stop();
		System.out.println("걸린 시간:" + watch.getTotalTimeMillis());
		System.out.println(watch.prettyPrint());
		return object;
	}
}
