package com.hs.core.adapater;

import java.util.List;


public abstract interface AbsMapper<T> {

	int countByExample(Object example);

	int deleteByExample(Object example);

	int deleteByPrimaryKey(String id);

	int insert(T record);

	int insertSelective(T record);

	<T>List<T> selectByExample(Object example);

	T selectByPrimaryKey(String id);

//	int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);
//
//	int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

	int updateByPrimaryKeySelective(T record);

	int updateByPrimaryKey(T record);

}
