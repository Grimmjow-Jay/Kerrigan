package com.jay.kerrigan.common.model;

import org.springframework.http.HttpStatus;

public class ResponseModel<T> {

	private boolean success;
	private String message;
	private int code;

	private T data;

	public ResponseModel() {
	}

	public ResponseModel(boolean success, String message, int code, T data) {
		this.success = success;
		this.message = message;
		this.code = code;
		this.data = data;
	}

	public ResponseModel(boolean success, String message, HttpStatus httpStatus, T data) {
		this.success = success;
		this.message = message;
		this.code = httpStatus.value();
		this.data = data;
	}

	public ResponseModel(boolean success, String message, T data) {
		this(success, message, HttpStatus.OK, data);
	}

	public static <T> ResponseModel<T> success(T data) {
		return new ResponseModel<T>(true, "success", HttpStatus.OK, data);
	}

	public static <T> ResponseModel<T> error() {
		return error("error");
	}

	public static <T> ResponseModel<T> error(String message) {
		return new ResponseModel<T>(true, message, HttpStatus.OK, null);
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ResponseModel [success=" + success + ", message=" + message + ", code=" + code + ", data=" + data + "]";
	}

}