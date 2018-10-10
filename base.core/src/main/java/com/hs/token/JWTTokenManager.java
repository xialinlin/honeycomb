package com.hs.token;

import com.alibaba.fastjson.JSON;
import com.hs.common.tool.AESUtil;
import com.hs.common.tool.SHAUtil;
import com.hs.consts.SecurityConst;

import jodd.util.Base64;
import jodd.util.StringUtil;


/**
 * JWT token实现类
 * @author welkin
 *
 * @param <T>
 */
public class JWTTokenManager<T> implements TokenManager<T> {


	private String header;

	public JWTTokenManager() {
		this.header = "{'type': 'JWT','alg': 'MD5'}";
	}

	@Override
	/**
	 * 创建一个TokenEntity对象
	 * @param T
	 */
	public TokenEntity createToken(T t) {

		TokenEntity entity = new TokenEntity();
		entity.setHeader(Base64.encodeToString(header));
		entity.setPayload(Base64.encodeToString(AESUtil.encrypt(JSON.toJSONString(t))));
		entity.setSecret(SHAUtil.encode(SecurityConst.JWT_SECRET, entity.getHeader()+SecurityConst.JWT_SALT+entity.getPayload(), SecurityConst.JWT_SHA));

		return entity;
	}

	@Override
	public boolean checkToken(TokenEntity model) {
		return false;
	}

	@Override
	public boolean checkToken(String token) {
		boolean flag = false;
		if(StringUtil.isNotEmpty(token)) {
			String[] s = token.split("\\.");

			if(s.length==3 && SHAUtil.encode(SecurityConst.JWT_SECRET, s[0]+SecurityConst.JWT_SALT+s[1], SecurityConst.JWT_SHA).equals(s[2])  ) {
				flag =true;
			}
		}
		return flag;
	}

	@Override
	public TokenEntity getToken(String authentication) {

		return null;
	}


	public <T>T getUser(String auth ,Class<T> cls){
		Object obj = null;
		if(StringUtil.isNotEmpty(auth)) {
			String[] s = auth.split("\\.");
			obj = JSON.parseObject(AESUtil.decrypt(Base64.decodeToString(s[1]) ), cls);
		}
		return (T) obj;
	}

	@Override
	public void deleteToken(T t) {
	}






}
