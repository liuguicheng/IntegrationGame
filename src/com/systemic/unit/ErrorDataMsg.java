package com.systemic.unit;

/**
 * json ʵ�� 
 * @author lgc
 *
 */
public class ErrorDataMsg {
	private String message;//��ʾ��Ϣ
	private int code;// 0�ɹ�1ʧ��


	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}


	

}
