package com.hs.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import com.hs.consts.SecurityConst;

@Configuration
@ConditionalOnMissingBean(name={"secretInfoConfig"})
public class SecretConfig {


	//	@Value("${jwt.secret}")
	//	private String jwtSecret;
	//
	//
	//	@Bean
	//	public void createJwt() {
	//		System.out.println(jwtSecret);
	//		SecurityConst.JWT_SECRET=jwtSecret;
	//	}

	
	@Value("${jwt.secret:}")
	public void setJwtSecret(String jwtSecret) {
		System.out.println(jwtSecret);
		if(!StringUtils.isEmpty(jwtSecret))
			SecurityConst.JWT_SECRET = jwtSecret;
	}

}
