package com.hs.interceptors;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hs.annotations.Authorization;
import com.hs.consts.Constants;
import com.hs.core.user.bean.IUser;

/**
 * 专门用于session使用的拦截器。获取用户信息
 * @author RW
 *
 */
@SuppressWarnings("rawtypes")
@Component
public class SessionAuthInterceptor extends HandlerInterceptorAdapter {


	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		IUser user = (IUser) request.getSession().getAttribute(Constants.SESSION_USER_KEY);
		
		//如果不是映射到方法直接通过
		if (!(handler instanceof HandlerMethod)) {
			return true;
		}
		
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method method = handlerMethod.getMethod();

		if (user!=null) {
			request.setAttribute(Constants.USER_REQUEST_ID,user);
			return true;
		}
	
		//如果验证token失败，并且方法注明了Authorization，返回401错误
		if (method.getAnnotation(Authorization.class) != null) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return false;
		}
		
		return true;
	}
}
