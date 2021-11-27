package org.formation.monitor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.java.Log;

@Aspect
@Log
@Component
public class PerformanceMonitor {


	
	@Pointcut("execution(* org.formation.service.*.*(..))")
	private void anyServiceMethod() {};
	
	@Around("anyServiceMethod()")
	public Object monitor(ProceedingJoinPoint pjp) throws Throwable { 

		long start = System.nanoTime(); 
		try { 
				return pjp.proceed(); 
		} finally { 
				long time = System.nanoTime() - start; 
				log.info(pjp.toShortString() + " took " + time + " ns");
		} 
	} 

}
