package com.hs.core.advice;

import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;

import com.hs.consts.Constants;
import com.hs.core.adapater.BeanAdapter;
import com.hs.core.user.UserAdapter;
import com.hs.core.user.bean.UserBean;
import com.hs.support.mybatis.tenant.FilterInfo;
import com.hs.support.mybatis.tenant.SQLFilterAdapter;



public class AbsAdviseDefine {

	protected final Logger log = LoggerFactory.getLogger(this.getClass()); 

	public void doBefore(JoinPoint joinPoint){
		
		log.debug("执行调用前操作。");

		SQLFilterAdapter.setFilterInfo(new FilterInfo());

		UserBean user = (UserBean) RequestContextHolder.getRequestAttributes().getAttribute(Constants.USER_REQUEST_ID, 0);
		BeanAdapter beanAdapter;

		if(user!=null) {
			SQLFilterAdapter.getFilterInfo().setTenantId(user.getTenantId());
			SQLFilterAdapter.setIsSuperAdmin(user.getIsSuperAdmin());
			if(joinPoint.getTarget() instanceof UserAdapter) ((UserAdapter)joinPoint.getTarget()).setUser(user);

			if(joinPoint.getSignature().getName().startsWith("insert") && joinPoint.getArgs().length==1 && joinPoint.getArgs()[0] instanceof  BeanAdapter){

				beanAdapter = (BeanAdapter)joinPoint.getArgs()[0] ;
				beanAdapter.setCreateDate(new Date());
				beanAdapter.setCreaterId(user.getUserId());
				beanAdapter.setUpdateDate(beanAdapter.getCreateDate());
				beanAdapter.setUpdaterId(beanAdapter.getCreaterId());

				if(StringUtils.isEmpty(beanAdapter.getTenantId()))
					beanAdapter.setTenantId(user.getTenantId());

			}

		}

	}

	public void doAfter(JoinPoint joinPoint){
	}

	public void afterReturn(JoinPoint joinPoint,Object returnVal){
	}

	public void afterThrowing(JoinPoint joinPoint,Throwable error){
		log.error("程序发生异常。");
	}

	public <T>T around(ProceedingJoinPoint pjp) throws Throwable{
		log.debug("开始调用服务");
		long start = System.currentTimeMillis();
		Object data = null;
		try {
			data = pjp.proceed();
		} catch (Throwable e) {
			log.error("处理发生错误,执行服务耗时：{}",System.currentTimeMillis()-start,e);
			throw e;
		}
		log.debug("服务调用结束，执行服务耗时：{}",System.currentTimeMillis()-start);
		return (T) data;
	}

}
