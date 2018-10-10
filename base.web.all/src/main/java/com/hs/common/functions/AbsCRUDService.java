package com.hs.common.functions;

import java.util.Map;

import com.hs.core.Message;
import com.hs.core.adapater.AbsMapper;
import com.hs.core.exceptions.ResultException;
import com.hs.core.user.AbsUserAdapter;

import jodd.bean.BeanCopy;


@SuppressWarnings({ "unchecked", "rawtypes" })
public abstract class AbsCRUDService<T> extends AbsUserAdapter implements CRUDService<T> {



	private AbsMapper<T> mapper;


	/**
	 * 通用新增方法
	 */
	@Override
	public Message insert(T item)throws ResultException {
		Message msg = new Message();
		try {
			mapper.insert(item);
			msg.setText("新增成功。");
		}catch(Exception e) {
			throw new ResultException(e,"数据新增失败，请重试，或者联系管理员！");
		}
		return msg;
	}


	@Override
	public Message deleteById(String id) {
		Message msg = new Message();
		try {
			mapper.deleteByPrimaryKey(id);
			msg.setText("删除成功");
		}catch(Exception e) {
			throw new ResultException(e,"删除发生异常，请联系管理员");
		}
		return msg;
	}

	@Override
	public Message updateById(Map map) {
		T item =null;
		Message msg = new Message();
		try {
			item =  mapper.selectByPrimaryKey(map.get("id").toString());
			BeanCopy.beans(map, item).copy();
			mapper.updateByPrimaryKey(item);
			msg.setText("修改成功。");
			msg.setData(item);
		}catch(Exception e) {
			throw new ResultException(e,"修改发生异常,请联系管理员");
		}
		return msg;
	}

	@Override
	public Message getById(String id) {
		Message msg = new Message();
		T item = null;
		try {
			item = mapper.selectByPrimaryKey(id);
			msg.setData(item);
			msg.setText("查询成功");
		}catch(Exception e) {
			throw new ResultException(e,"查询发生异常，请联系管理员");
		}
		return msg;
	}



	public void setAbsMapper(AbsMapper mapper) {
		this.mapper = mapper;

	}

}
