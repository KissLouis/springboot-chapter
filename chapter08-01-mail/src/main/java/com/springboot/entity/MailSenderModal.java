package com.springboot.entity;

import java.io.Serializable;

/**
 * 
 * 邮件发送结果回传数据
 * 
 * @author Louis
 *
 */
public class MailSenderModal implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 0为发送成功，其他为失败
	 */
	private int code = 0;
	
	/**
	 * 异常消息
	 */
	private String msg = "success";
	
	public MailSenderModal() {
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	
	@Override
	public String toString() {
		return new StringBuffer()
				.append(code)
				.append("-")
				.append(msg)
				.toString();
	}
}
