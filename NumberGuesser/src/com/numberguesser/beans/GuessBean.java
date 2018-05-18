package com.numberguesser.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jesse Roy
 * 
 * 
 */
public class GuessBean {

	private int initialLowerBound;
	private int initialUpperBound;
	private int lowerBound;
	private int upperBound;
	private char lowOrHigh;
	private int calculatedGuess;
	private int numOfGuesses = 1;
	private List<Integer> previousGuesses = new ArrayList<Integer>();
	
	/**
	 * @return the initialLowerBound
	 */
	public int getInitialLowerBound() {
	
		return initialLowerBound;
	}

	/**
	 * @param initialLowerBound
	 *            the initialLowerBound to set
	 */
	public void setInitialLowerBound(final int initialLowerBound) {
		this.initialLowerBound = initialLowerBound;
	}

	/**
	 * @return the initialUpperBound
	 */
	public int getInitialUpperBound() {
		return initialUpperBound;
	}

	/**
	 * @param initialUpperBound
	 *            the initialUpperBound to set
	 */
	public void setInitialUpperBound(final int initialUpperBound) {
		this.initialUpperBound = initialUpperBound;
	}

	/**
	 * @return the lowOrHigh
	 */
	public char getLowOrHigh() {
		return lowOrHigh;
	}

	/**
	 * @param lowOrHigh
	 *            the lowOrHigh to set
	 */
	public void setLowOrHigh(final char lowOrHigh) { 
		this.lowOrHigh = lowOrHigh;
	}

	/**
	 * @return the lowerBound
	 */
	public int getLowerBound() {
		return lowerBound;
	}

	/**
	 * @param lowerBound
	 *            the lowerBound to set
	 */
	public void setLowerBound(final int lowerBound) {
		this.lowerBound = lowerBound;
	}

	/**
	 * @return the upperBound
	 */
	public int getUpperBound() {
		return upperBound;
	}

	/**
	 * @param upperBound
	 *            the upperBound to set
	 */
	public void setUpperBound(final int upperBound) {
		this.upperBound = upperBound;
	}

	/**
	 * @return the calculatedGuess
	 */
	public int getCalculatedGuess() {
		return calculatedGuess;
	}

	/**
	 * Calculate the guess by getting the average of the upper and lower bound
	 * 
	 */
	public void setCalculatedGuess(final int calculatedGuess) {
		this.calculatedGuess = calculatedGuess;
	}

	public int getNumOfGuesses() {
		return numOfGuesses;
	}

	public void setNumOfGuesses() {
		this.numOfGuesses = this.getNumOfGuesses() +1;
	}

	public List<Integer> getPreviousGuesses() {
		return previousGuesses;
	}

	public void setPreviousGuesses(final List<Integer> previousGuesses) {
		this.previousGuesses = previousGuesses;
	}
	
	public void addGuessToCollection (final int lastGuess) {
		this.previousGuesses.add(lastGuess);
	}

}
