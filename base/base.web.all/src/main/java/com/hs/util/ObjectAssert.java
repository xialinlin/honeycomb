package com.hs.util;

import java.util.Collection;

import org.springframework.util.CollectionUtils;

import com.hs.core.exceptions.ResultException;

public class ObjectAssert {
	
	/**
	 * 不能为空
	 * @param obj
	 * @param text
	 */
	public static void notNull(Object obj,String text)throws  ResultException{
		if(obj==null) {
			throw new ResultException(text);
		}
	}
	
	public static void arrayNotNull(Collection<?> obj,String text)throws  ResultException{
		if(CollectionUtils.isEmpty(obj)) {
			throw new ResultException(text);
		}
	}

}
