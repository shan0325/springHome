package com.spring.web.exception;

public class BoardPasswordNotMatchException extends RuntimeException {

	private String pwd;

	public BoardPasswordNotMatchException(String pwd) {
		super();
		this.pwd = pwd;
	}

	public String getPwd() {
		return pwd;
	}
	
}
