package com.enation.eop.processor.core;

public class EopException extends RuntimeException {
	public EopException(){
		super();
	}
	
	public EopException(String message){
		super(message);
	}
}
