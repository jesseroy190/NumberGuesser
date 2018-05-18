package com.numberguesser.exceptions;

/**
 * @author Jesse Roy
 * 
 *         This exception is thrown when the lowerBoundary is higher than or
 *         equal to the upper boundary
 * 
 */
public class InvalidBoundariesException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public InvalidBoundariesException(String message, Throwable e) {
		super(message, e);
	}

	/**
	 * @param string
	 */
	public InvalidBoundariesException(String message) {
		super(message);
	}
}
