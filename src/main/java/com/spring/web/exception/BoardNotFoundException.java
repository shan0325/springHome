package com.spring.web.exception;

public class BoardNotFoundException extends RuntimeException {

	private Long brdid;
	

	public BoardNotFoundException(Long brdid) {
		super();
		this.brdid = brdid;
	}

	public Long getBrdid() {
		return brdid;
	}
	
	
}
