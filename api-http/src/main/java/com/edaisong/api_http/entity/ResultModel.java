package com.edaisong.api_http.entity;

public class ResultModel  <T> {
	private int Status;
    private String Message;
    private T Result;
    /**
     * 状态
     * @return
     */
	public int getStatus() {
		return Status;
	}
	/**
	 * 状态
	 * @param status
	 */
	public void setStatus(int status) {
		Status = status;
	}
	/**
	 * 状态
	 * @return
	 */
	public String getMessage() {
		return Message;
	}
	/**
	 * 状态
	 * @param message
	 */
	public void setMessage(String message) {
		Message = message;
	}
	/**
	 * 
	 * @return
	 */
	public T getResult() {
		return Result;
	}
	/**
	 * 
	 * @param result
	 */
	public void setResult(T result) {
		Result = result;
	}

}
