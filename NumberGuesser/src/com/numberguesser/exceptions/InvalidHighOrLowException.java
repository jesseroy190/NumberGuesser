package com.numberguesser.exceptions;

/**
 * @author Jesse Roy
 * 
 */
public class InvalidHighOrLowException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public InvalidHighOrLowException(String message, Throwable e) {
		super(message, e);
	}

	/**
	 * @param string
	 */
	public InvalidHighOrLowException(String message) {
		super(message);
	}
}
