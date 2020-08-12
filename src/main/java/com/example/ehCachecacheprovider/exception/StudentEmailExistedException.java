package com.example.ehCachecacheprovider.exception;

public class StudentEmailExistedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8316911005597573324L;

	public StudentEmailExistedException() {
		// TODO Auto-generated constructor stub
	}

	public StudentEmailExistedException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public StudentEmailExistedException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public StudentEmailExistedException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public StudentEmailExistedException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
