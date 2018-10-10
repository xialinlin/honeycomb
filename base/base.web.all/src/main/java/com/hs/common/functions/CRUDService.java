package com.hs.common.functions;

import java.util.Map;

import com.hs.core.Message;


/**
 * 包含基础操作的服务接口</br>
 * 新增、删除、修改、根据ID查询4个接口的服务
 * @author Administrator
 *
 * @param <T>
 */
@SuppressWarnings("rawtypes")
public interface CRUDService<T> {
	
	
	Message insert(T item);
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	Message deleteById(String id);

	/**
	 * 
	 * @param map
	 * @return
	 */
	
	Message updateById(Map map);

	/**
	 * 
	 * @param id
	 * @return
	 */
	Message getById(String id);

}
