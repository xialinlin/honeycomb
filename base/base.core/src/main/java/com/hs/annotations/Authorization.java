package com.hs.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) 
@Retention(RetentionPolicy.RUNTIME) 
public @interface Authorization { 
	
	
	 /**
	  * 执行等级。0位最低等级
	  * @return
	  */
	 public int level() default 0;
	
}

