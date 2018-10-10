package com.hs.core.adapater;

import java.util.Date;

/**
 * 当判断到位insert方法时，将自动添加一些特有的字段信息。
 * @author RW
 *
 */
public interface BeanAdapter {

	public String getTenantId() ;   

	public void setTenantId(String tenantId) ;

	//
	public String getCreaterId();

	public void setCreaterId(String createrId) ;

	//
	public Date getCreateDate() ;

	public void setCreateDate(Date createDate);

	//
	public String getUpdaterId() ;

	public void setUpdaterId(String updaterId) ;

	//
	public Date getUpdateDate();

	public void setUpdateDate(Date updateDate);


}
