package com.hs.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hs.token.JWTTokenManager;
import com.hs.token.TokenManager;

@Configuration
public class PlatBaseConfig {
	
	
	@Bean(name="tokenManager")
	@ConditionalOnMissingBean(name="tokenManager")
	public TokenManager tokenManager() {
		return new JWTTokenManager();
	}

}
