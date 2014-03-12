package com.enation.javashop.core.service.impl;

public class ArticleRuntimeException extends RuntimeException {

	public ArticleRuntimeException(int code) {
		error_code = code;
	}

	private int error_code;

	public int getError_code() {
		return error_code;
	}

	public void setError_code(int error_code) {
		this.error_code = error_code;
	}

	
}
