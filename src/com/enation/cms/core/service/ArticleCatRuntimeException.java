package com.enation.cms.core.service;

/**
 * 文章分类异常
 * @author kingapex
 *
 */
public class ArticleCatRuntimeException extends RuntimeException {
	public ArticleCatRuntimeException(int code) {
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
