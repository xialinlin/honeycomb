package com.hs.interceptors;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hs.annotations.Authorization;
import com.hs.consts.Constants;
import com.hs.core.user.bean.UserBean;
import com.hs.token.TokenManager;

/**
 * 专门用于前后端分离使用的拦截器
 * @author RW
 *
 */
@SuppressWarnings("rawtypes")
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

	
	@Autowired
	private TokenManager tokenManager;



	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		//如果不是映射到方法直接通过
		if (!(handler instanceof HandlerMethod)) {
			return true;
		}
		
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method method = handlerMethod.getMethod();
	
		//从header中得到token
		String authorization = request.getHeader(Constants.AUTHORIZATION);
	
		//  验证token
		//  TokenEntity model = tokenManager.getToken(authorization);
		if (tokenManager.checkToken(authorization)) {
			//如果token验证成功，将token对应的用户id存在request中，便于之后注入
			request.setAttribute(Constants.USER_REQUEST_ID,tokenManager.getUser(authorization,UserBean.class));
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
