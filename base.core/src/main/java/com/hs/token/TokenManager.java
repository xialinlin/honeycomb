package com.hs.token;


/**
 * 
 * @author K0410004
 *
 * @param <T> java泛型
 */
public interface TokenManager<T> {

	/**
	 * 创建一个token关联上指定用户
	 * @param userId 指定用户的id
	 * @return 生成的token
	 */
	public TokenEntity createToken(T t);

	/**
	 * 检查token是否有效
	 * @param model token
	 * @return 是否有效
	 */
	public boolean checkToken(TokenEntity model);
	
	/**
	 * 检查token是否有效
	 * @param model token
	 * @return 是否有效
	 */
	public boolean checkToken(String token);

	/**
	 * 从字符串中解析token
	 * @param authentication 加密后的字符串
	 * @return
	 */
	public TokenEntity getToken(String authentication);

	/**
	 * 清除token
	 * @param userId 登录用户的id
	 */
	public void deleteToken(T t);
	
	
	public <T>T getUser(String auth ,Class<T> cls);


}
