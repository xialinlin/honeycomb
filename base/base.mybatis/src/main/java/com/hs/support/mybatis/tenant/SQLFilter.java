package com.hs.support.mybatis.tenant;

public interface SQLFilter {
	
	/**
	 * 获取组织后的SQL语句
	 * @return
	 */
	public String getSql();
	
	public boolean isFilter();

	public boolean isSuperAdmin();
}
