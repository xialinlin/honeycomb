package com.hs.core.user.bean;

public class UserBean implements IUser{
	
	
	private String userId;
	
	
	private String tenantId;
	
	
	private int isSuperAdmin;
	
	
	private String userName;
	

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public int getIsSuperAdmin() {
		return isSuperAdmin;
	}

	public void setIsSuperAdmin(int isSuperAdmin) {
		this.isSuperAdmin = isSuperAdmin;
	}
	
	

}
