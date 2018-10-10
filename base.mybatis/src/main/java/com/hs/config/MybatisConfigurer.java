package com.hs.config;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;

@Configuration
public class MybatisConfigurer {

	

	@Value("${test.test1}")  
	private String mapperScanner;  

//	@Bean
//	@Order(10)
	@Bean     
	@Primary 
	@ConditionalOnMissingBean(name="mapperScannerConfigurer")
	public MapperScannerConfigurer mapperScannerConfigurer() {
		System.out.println(mapperScanner);
		MapperScannerConfigurer ms = new MapperScannerConfigurer();
		ms.setBasePackage("com.hs.plat.*.mapper");
//		ms.setBasePackage(this.mapperScanner);
		return ms;
	}

}
