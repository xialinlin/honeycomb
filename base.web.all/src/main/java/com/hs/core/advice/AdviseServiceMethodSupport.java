package com.hs.core.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Configuration;


/**
 * 
 * 服务切面接口，默认服务名：adviseServiceMethod
 * 如果需要覆盖该方法，重新定义@Configuration("adviseServiceMethod")
 * @author welkin
 *
 */
@Aspect
@Configuration
@ConditionalOnMissingBean(name="adviseServiceMethod")
public class AdviseServiceMethodSupport extends AbsAdviseDefine {

	protected final Logger log = LoggerFactory.getLogger(this.getClass()); 

	@Pointcut("execution(* com.hs.plat.*.service.impl.*.*(..))|| "
			+ "execution(* com.hs.common.functions.AbsCRUDService.*(..)) ")
	public void pointCut(){}

	@Before("pointCut()")
	public void doBefore(JoinPoint joinPoint){
		super.doBefore(joinPoint);
	}

	@After("pointCut()")
	public void doAfter(JoinPoint joinPoint){
		log.debug("AOP After Advice...");
	}

	@AfterReturning(pointcut="pointCut()",returning="returnVal")
	public void afterReturn(JoinPoint joinPoint,Object returnVal){
		log.debug("AOP AfterReturning Advice:" + returnVal);
	}

	@AfterThrowing(pointcut="pointCut()",throwing="error")
	public void afterThrowing(JoinPoint joinPoint,Throwable error){
		log.debug("AOP AfterThrowing Advice..." + error);
		log.debug("AfterThrowing...");
	}

	@Around("pointCut()")
	public <T>T around(ProceedingJoinPoint pjp) throws Throwable{
		System.out.println(1111);
		return super.around(pjp);
	}

}
