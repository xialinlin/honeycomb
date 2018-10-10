package com.hs.token;

public class TokenEntity {
	
	/**
	 * jwt的头部信息
	 */
	private String header;
	
	
	/**
	 * jwt主体信息
	 */
	private String payload;
	
	/**
	 * 签名信息
	 */
	private String secret;
	
	
	public String getJWT() {
		return header+"."+payload+"."+secret;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}
	

}
