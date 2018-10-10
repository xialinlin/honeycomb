package com.hs.core.exceptions;

import com.hs.core.Message;

public class ResultException extends RuntimeException {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8740015256368922042L;
	
	
	private Message resultMessage;
	
	
	public ResultException(String text) {
		super();
		Message msg = new Message();
		msg.setSuccess(false);
		msg.setText(text);
		this.resultMessage = msg;
	}
	
	public ResultException(Exception e , String text) {
		super(e);
		Message msg = new Message();
		msg.setText(text);
		msg.setSuccess(false);
		this.resultMessage = msg;
	}
	
	public ResultException(Exception e) {
		super(e);
		Message msg = new Message();
		msg.setSuccess(false);
		msg.setText("发生异常，请重试或联系管理人员。");
		this.resultMessage = msg;
	}
	
	
	
	public ResultException(Message message ,String msg) {
		super(msg);
		this.resultMessage = message;
	}
	
	
	public ResultException(Message message) {
		this.resultMessage = message;
	}


	public Message getResultMessage() {
		return resultMessage;
	}


	public void setResultMessage(Message resultMessage) {
		this.resultMessage = resultMessage;
	}


	
	


}
