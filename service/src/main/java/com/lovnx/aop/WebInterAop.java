package com.lovnx.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class WebInterAop {
	
	@Pointcut("execution(* com.lovnx.web..*.*(..))")  
	public void executeService(){  
	  
	}
	
	@Around("executeService()")  
    public Object doAroundAdvice(ProceedingJoinPoint proceedingJoinPoint){  
        try {
        	long t1 = System.currentTimeMillis();
            Object obj = proceedingJoinPoint.proceed();
            long t2 = System.currentTimeMillis();
            System.out.println(proceedingJoinPoint.getSignature().getName()+"方法执行了"+(t2-t1)+"ms");
            return obj;
        } catch (Throwable throwable) {  
            throwable.printStackTrace();  
            return null;
        }  
    }  

}
