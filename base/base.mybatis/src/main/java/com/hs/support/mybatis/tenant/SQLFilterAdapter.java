package com.hs.support.mybatis.tenant;

import org.springframework.util.StringUtils;

public class SQLFilterAdapter implements SQLFilter {


	private static ThreadLocal<FilterInfo> threadLocal = new ThreadLocal<FilterInfo>();


	public static FilterInfo getFilterInfo() {
		return threadLocal.get();
	}
	
	public static void setFilterInfo(FilterInfo info) {
		threadLocal.set(info);
	}

	public static void setAlias(String s) {
		getFilterInfo().setAlias(s);
	}

	public  static void setFilter(boolean isFilter) {
		getFilterInfo().setFilter(isFilter);
	}

	public static void setTenantId(String tenantId) {
		getFilterInfo().setTenantId(tenantId);
	}

//	public static int getIsSuperAdmin() {
//		return getFilterInfo().getIsSuperAdmin();
//	}

	public static void setIsSuperAdmin(int isSuperAdmin) {
		getFilterInfo().setIsSuperAdmin(isSuperAdmin);
	}


	@Override
	public String getSql() {
		if(StringUtils.isEmpty(getFilterInfo().getAlias())) {
			return "TENANT_ID='"+getFilterInfo().getTenantId()+"'";
		}else {
			return getFilterInfo().getAlias()+".TENANT_ID='"+getFilterInfo().getTenantId()+"'";
		}
	}

	@Override
	public boolean isFilter() {
		return getFilterInfo().isFilter();
	}

	@Override
	public boolean isSuperAdmin() {
		return 1 == getFilterInfo().getIsSuperAdmin() ? true :false;
	}
	
	



}
