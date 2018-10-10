package com.hs.core.user;

import com.hs.core.user.bean.IUser;

public interface UserAdapter {
	
	/**
	 * 注册一个用户对象
	 * @param user
	 */
	public void setUser(IUser user);
	
	/**
	 * 获取一个用户对象
	 * @return
	 */
	public IUser getUser();

}
