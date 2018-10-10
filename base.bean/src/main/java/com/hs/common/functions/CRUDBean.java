package com.hs.common.functions;

import com.hs.core.adapater.BeanAdapter;

/**
 * 用于执行基础操作的bean。
 * 主要包含id,
 * @author Administrator
 *
 */
public interface CRUDBean extends BeanAdapter{
	
	
	public void setId(String id);
	
	public String getId();

}
