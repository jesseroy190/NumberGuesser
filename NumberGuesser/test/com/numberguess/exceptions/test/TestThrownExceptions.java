package com.numberguess.exceptions.test;

import org.junit.Test;

import com.numberguesser.beans.GuessBean;
import com.numberguesser.exceptions.InvalidBoundariesException;
import com.numberguesser.exceptions.InvalidHighOrLowException;
import com.numberguesser.exceptions.InvalidIntegerException;
import com.numberguesser.gamelogic.GameLogic;

public class TestThrownExceptions {

	GuessBean guess;
	GameLogic game;

	/**
	 * Test that a InvalidHighOrLowException is thrown when the user submits
	 * feedback about the guessed number that is not equal to lower or higher.
	 */
	@Test(expected = InvalidHighOrLowException.class)
	public void testInvalidHighOrLow() throws Exception {

		guess = new GuessBean();
		game = new GameLogic();

		// calculate a guess
		game.calculateGuess(guess);

		// user needs to indicate if guess is too high or too low
		// Q is an invalid highOrLowValue
		guess.setLowOrHigh('Q');

		// exception will be thrown
		game.updateGuessBoundaries(guess);
	}

	/**
	 * This test case will throw an InvalidBoundariesException when the user
	 * submits their range, but the initial lower boundary is higher than the
	 * initial upper boundary
	 */
	@Test(expected = InvalidBoundariesException.class)
	public void testLBoundGreaterThanHBound()
			throws InvalidBoundariesException, InvalidIntegerException {

		guess = new GuessBean();
		game = new GameLogic();

		game.setInitialLowerBoundary(guess, "200");
		game.setInitialUpperBoundary(guess, "100");

		game.checkBoundaryRange(guess);

	}

	/**
	 * This test case will throw an InvalidBoundariesException when the user
	 * submits their range, and the initial lower boundary is equal to the
	 * initial upper boundary
	 */

	@Test(expected = InvalidBoundariesException.class)
	public void testLBoundEqualsHBound() throws InvalidBoundariesException,
			InvalidIntegerException {

		guess = new GuessBean();
		game = new GameLogic();

		game.setInitialLowerBoundary(guess, "200");
		game.setInitialUpperBoundary(guess, "200");

		game.checkBoundaryRange(guess);

	}

	/**
	 * An InvalidIntegerException should be thrown when the user enters a value
	 * for the lower boundary that is not a valid integer
	 */

	@Test(expected = InvalidIntegerException.class)
	public void testLowerBoundInvalidInt() throws InvalidBoundariesException,
			InvalidIntegerException {

		guess = new GuessBean();
		game = new GameLogic();

		game.setInitialLowerBoundary(guess, "djflajlfsd");

	}

	/**
	 * An InvalidIntegerException should be thrown when the user enters a value
	 * for the upper boundary that is not a valid integer
	 */

	@Test(expected = InvalidIntegerException.class)
	public void testUpperBoundInvalidInt() throws InvalidBoundariesException,
			InvalidIntegerException {

		guess = new GuessBean();
		game = new GameLogic();

		game.setInitialUpperBoundary(guess, "djflajlfsd");

	}

}
