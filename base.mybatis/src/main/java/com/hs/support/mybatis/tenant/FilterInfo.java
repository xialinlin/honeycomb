package com.hs.support.mybatis.tenant;


/**
 * 表过滤
 * @author K0410004
 *
 */
public class FilterInfo {
	
	
	private String tenantId;
	
	/**
	 * 是否启用
	 */
	private boolean isFilter;
	
	/**
	 * 别名
	 */
	private String alias;
	
	private int isSuperAdmin;
	
	
	public FilterInfo() {
		this.isFilter = true;
		this.isSuperAdmin = 0;
	}
	
	
	public int getIsSuperAdmin() {
		return isSuperAdmin;
	}



	public void setIsSuperAdmin(int isSuperAdmin) {
		this.isSuperAdmin = isSuperAdmin;
	}



	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public boolean isFilter() {
		return isFilter;
	}

	public void setFilter(boolean isFilter) {
		this.isFilter = isFilter;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}
	

}
