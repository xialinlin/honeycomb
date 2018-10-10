package com.hs.core.user.bean;

public interface IUser {

	/**
	 * 获取用户ID
	 * @return
	 */
	String getUserId();
	
	void setUserId(String userId);
	
	String getUserName();
	
	void setUserName(String userName);

	/**
	 * 获取SAAS平台的租户ID
	 * @return
	 */
	String getTenantId();

	void setTenantId(String tId);

	/**
	 * 获取用户等级
	 * @return
	 */
	int getIsSuperAdmin();
	
	void setIsSuperAdmin(int i);

}
