package com.lognsys.exception;

@SuppressWarnings("serial")
public class UserDataAccessException extends RuntimeException {

	public UserDataAccessException() {
		super();
	}

	public UserDataAccessException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserDataAccessException(String message) {
		super(message);
	}

	public UserDataAccessException(Throwable cause) {
		super(cause);
	}

}
