package com.numberguesser.exceptions;

/**
 * @author Jesse Roy
 * 
 */
public class InvalidIntegerException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public InvalidIntegerException(String message, Throwable e) {
		super(message, e);
	}

	/**
	 * @param string
	 */
	public InvalidIntegerException(String message) {
		super(message);
	}
}
