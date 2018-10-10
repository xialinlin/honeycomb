package com.hs.core;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Message implements Serializable{


	private Object data;

	private long count;

	private long size;

	private long page;

	private long pages;

	/**
	 * 0:失败
	 * 1：正常
	 */
	private int status;

	private String title;

	private String text;

	private String tokenId;

	private String code;

	private Object otherData;

	/**
	 * 调用是否成功
	 * true:成功
	 * false：失败
	 */
	private boolean success;
	
	public boolean getSuccess() {
		return success;
	}


	public void setSuccess(boolean success) {
		this.success = success;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}



	public String getTokenId() {
		return tokenId;
	}



	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}



	public Message() {
		this.status = 1;
		this.success=true;
	}



	public Message( String text , Object data) {
		super();
		this.data = data;
		this.text = text;
		this.status=1;
		this.success=true;
	}





	public Message(String code , String text) {
		super();
		this.text = text;
		this.code = code;
		this.status = 1;
		this.success=true;
	}


	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}



	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}


	public Object getOtherData() {
		return otherData;
	}


	public void setOtherData(Object otherData) {
		this.otherData = otherData;
	}


	public long getSize() {
		return size;
	}


	public void setSize(long size) {
		this.size = size;
	}


	public long getPage() {
		return page;
	}


	public void setPage(long page) {
		this.page = page;
	}


	public long getPages() {
		return pages;
	}


	public void setPages(long pages) {
		this.pages = pages;
	}


}
