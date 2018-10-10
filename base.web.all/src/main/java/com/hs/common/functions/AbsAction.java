package com.hs.common.functions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.hs.annotations.Authorization;
import com.hs.common.tool.StringUtil;
import com.hs.core.Message;
import com.hs.core.exceptions.ResultException;

public abstract class AbsAction<T> {


	private CRUDService<T> service;


	private Class<T> clazz;

	/**
	 *新增一条数据记录
	 * @param stream-> BusinessInfo
	 * @return
	 */
	@Authorization
	@RequestMapping(value="insert" ,method=RequestMethod.POST)
	public Message insert(@RequestBody String  stream){
		Message msg = null;
		T item = JSON.parseObject(stream, clazz);
		((CRUDBean)item).setId(StringUtil.sId());
		try {
			msg = service.insert(item);
		}catch(ResultException e) {
			msg=e.getResultMessage();
		}
		return  msg;
	}
	
	
	/**
	 * 根据ID查询一条记录
	 * @param param
	 * @return
	 */
	@Authorization
	@RequestMapping(value="getById" ,method=RequestMethod.GET)
	public Message getById(@RequestParam String id){
		Message msg = null;
		try {
			msg = service.getById(id);
		}catch(ResultException e) {
			msg=e.getResultMessage();
		}
		return  msg;
	}

	
	/**
	 * 根据ID删除
	 * @param param
	 * @return
	 */
	@Authorization
	@RequestMapping(value="deleteById" ,method=RequestMethod.GET)
	public Message deleteById(@RequestParam String id){
		Message msg = null;
		try {
			msg = service.deleteById(id);
		}catch(ResultException e) {
			msg=e.getResultMessage();
		}
		return  msg;
	}

	/**
	 * 修改
	 * @param param
	 * @return
	 */
	@Authorization
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="updateById" ,method=RequestMethod.POST)
	public Message updateById(@RequestBody String  stream){
		Message msg = null;
		Map map = JSON.parseObject(stream, HashMap.class);
		try {
			msg = service.updateById(map);
		}catch(ResultException e) {
			msg=e.getResultMessage();
		}
		return  msg;
	}

	public CRUDService<T> getService() {
		return service;
	}

	public void setService(CRUDService<T> service) {
		this.service = service;
	}

	public Class<T> getClazz() {
		return clazz;
	}

	public void setClazz(Class<T> clazz) {
		this.clazz = clazz;
	}



}
