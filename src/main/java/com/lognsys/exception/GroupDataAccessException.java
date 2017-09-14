package com.lognsys.exception;

@SuppressWarnings("serial")
public class GroupDataAccessException extends RuntimeException {

	public GroupDataAccessException() {
		super();
	}

	public GroupDataAccessException(String message, Throwable cause) {
		super(message, cause);
	}

	public GroupDataAccessException(String message) {
		super(message);
	}

	public GroupDataAccessException(Throwable cause) {
		super(cause);
	}

}
