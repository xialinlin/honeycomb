package com.hs.core.user;

import java.util.Map;

import org.springframework.util.StringUtils;

import com.hs.core.user.bean.IUser;

/**
 * 提供给给service层的abs类
 * @author RW
 *
 */
public abstract class AbsUserAdapter implements UserAdapter {
	
	private ThreadLocal<IUser> user = new ThreadLocal<IUser>();
	
	/**
	 * 注册一个用户对象
	 * @param user
	 */
	public void setUser(IUser user) {
		this.user.set(user);
	}
	
	/**
	 * 获取一个用户对象
	 * @return
	 */
	public IUser getUser() {
		return this.user.get();
	}
	
	/**
	 * 获取分页信参数.从以下两个参数获取
	 * page:
	 * szie:
	 * @param map
	 * @return 返回类型一个数组。第一个：page 第二个：size
	 */
	protected int[] parsePage (Map param) {
		
		int size=10;
		int page=1;
		
		
		if(!StringUtils.isEmpty(param.get("size"))) {
			size = Integer.parseInt(param.get("size").toString());
		}
		if(!StringUtils.isEmpty(param.get("page"))) {
			page = Integer.parseInt(param.get("page").toString());
		}
		
		int pages[] = {page,size};
		return pages;
	}

}
