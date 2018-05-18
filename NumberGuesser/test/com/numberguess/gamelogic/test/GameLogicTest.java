/**
 * 
 */
package com.numberguess.gamelogic.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.numberguesser.beans.GuessBean;
import com.numberguesser.exceptions.InvalidBoundariesException;
import com.numberguesser.exceptions.InvalidHighOrLowException;
import com.numberguesser.exceptions.InvalidIntegerException;
import com.numberguesser.gamelogic.GameLogic;

/**
 * @author mz90
 * 
 */
public class GameLogicTest {

	GuessBean guess;
	GameLogic game;

	/**
	 * Test method for
	 * {@link com.numberguesser.gamelogic.GameLogic#setInitialBoundaries(com.numberguesser.beans.GuessBean, int, int)}
	 * .
	 * 
	 * @throws InvalidBoundariesException
	 * @throws InvalidBoundaryException
	 * @throws InvalidIntegerException
	 */
	@Test
	public void testInitialLowerBoundary() throws InvalidBoundariesException,
			InvalidIntegerException {
		guess = new GuessBean();
		game = new GameLogic();

		game.setInitialLowerBoundary(guess, "56");

		// test initial lowerbound and lowerbound are set properly
		assertEquals(56, guess.getInitialLowerBound());
		assertEquals(56, guess.getLowerBound());
	}

	/**
	 * Test method for
	 * {@link com.numberguesser.gamelogic.GameLogic#setInitialBoundaries(com.numberguesser.beans.GuessBean, int, int)}
	 * .
	 * 
	 * @throws InvalidBoundariesException
	 * @throws InvalidBoundaryException
	 * @throws InvalidUpperBoundaryException
	 * @throws InvalidIntegerException
	 */
	@Test
	public void testInitialUpperBoundary() throws InvalidBoundariesException,
			InvalidIntegerException {
		guess = new GuessBean();
		game = new GameLogic();

		game.setInitialUpperBoundary(guess, "56");

		// test initial lowerbound and lowerbound are set properly
		assertEquals(56, guess.getInitialUpperBound());
		assertEquals(56, guess.getUpperBound());
	}

	@Test(expected = InvalidBoundariesException.class)
	public void testValidBoundaryRange() throws InvalidBoundariesException,
			InvalidIntegerException {

		guess = new GuessBean();
		game = new GameLogic();

		game.setInitialLowerBoundary(guess, "56");
		game.setInitialUpperBoundary(guess, "56");

		game.checkBoundaryRange(guess);
	}

	/**
	 * This program calculates a guess by taking the average of the upper and
	 * lower boundaries. For this scenario the lower boundary is 0, the
	 * upperboundary is 1000. The calculated guess is expected to be 500
	 * 
	 */
	@Test
	public void testCalculateGuess() {
		guess = new GuessBean();
		game = new GameLogic();

		// set upper and lower boundaries
		guess.setLowerBound(0);
		guess.setUpperBound(1000);

		// calculate guess
		game.calculateGuess(guess);

		// guess should be equal to 500
		assertEquals(500, guess.getCalculatedGuess());

	}

	/**
	 * When the user indicates that their number is higher than the current
	 * guess, the lower boundary is updated to now be equal to the current
	 * guess. This method tests when the user says their number is higher than
	 * 500, the lower boundary is adjusted to 500
	 * 
	 * @throws Exception
	 * 
	 */
	@Test
	public void testUpdateLowerBoundary() throws Exception {

		guess = new GuessBean();
		game = new GameLogic();

		// set upper and lower boundaries
		guess.setLowerBound(0);
		guess.setUpperBound(1000);

		// calculated guess will be 500
		game.calculateGuess(guess);

		// user says their number is higher than 500
		guess.setLowOrHigh('H');

		// updated boundaries from user input
		game.updateGuessBoundaries(guess);

		// lowerbound should be equal to 500
		assertEquals(500, guess.getLowerBound());

	}

	/**
	 * When the user indicates that their number is lower than the current
	 * guess, the upper boundary is updated to now be equal to the current
	 * guess. This method tests when the user said their number is lower than
	 * 500, the upper boundary is adjusted to 500
	 * 
	 * @throws Exception
	 * 
	 */
	@Test
	public void testUpdateUppperBoundary() throws Exception {

		guess = new GuessBean();
		game = new GameLogic();

		// set upper and lower boundaries
		guess.setLowerBound(0);
		guess.setUpperBound(1000);

		// calculated guess will be 500
		game.calculateGuess(guess);

		// user says their number is lower than 500
		guess.setLowOrHigh('L');

		// updated boundaries from user input
		game.updateGuessBoundaries(guess);

		// upper boundary should be equal to 500
		assertEquals(500, guess.getUpperBound());
	}

	/**
	 * The program has already guessed 56 and 58, and just guessed 57. There are
	 * no possible guesses left but the user selected the 'Higher' button. This
	 * means the user is cheating
	 * 
	 * Test method for
	 * {@link com.numberguesser.gamelogic.GameLogic#detectCheating(com.numberguesser.beans.GuessBean)}
	 * .
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDetectCheating() throws Exception {
		guess = new GuessBean();
		game = new GameLogic();

		// set upper and lower boundaries to 56 and 58
		guess.setLowerBound(56);
		guess.setUpperBound(58);

		// calculated guess will be 57
		// (56+58)/2
		game.calculateGuess(guess);

		// user indicates that their number is higher
		guess.setLowOrHigh('H');
		game.updateGuessBoundaries(guess);

		// user is cheating since there are no possible guesses left
		assertEquals(true, game.detectCheating(guess));

	}

	/**
	 * The program requirements indicated that the numbers must be an int. When
	 * dividing integers in java the results is rounded down. If the user's
	 * number is the upper boundary value, the program has to use the
	 * detectCheating method to determine if there is one guess left that is
	 * either the upper or lower boundary. For this test case the user's number
	 * is equal to the initial lower boundary. The upper boundary must be set to
	 * the initial lower boundary and calculate one last guess.
	 */
	@Test
	public void testUsersNumIsInitialLowerBoundary() throws Exception {

		guess = new GuessBean();
		game = new GameLogic();

		// set the inital boundaries
		guess.setInitialLowerBound(0);
		guess.setInitialUpperBound(999);
		guess.setLowerBound(0);

		// set upperboundary to 2
		guess.setUpperBound(2);

		// calculate a guess which should be 1
		// (0+2)/2
		game.calculateGuess(guess);

		// user indicates that their number is lower than 1
		guess.setLowOrHigh('L');

		// update the boundaries
		game.updateGuessBoundaries(guess);

		// detect if user is cheating or if the inital lower boundary is a
		// possible guess
		game.detectCheating(guess);
		game.calculateGuess(guess);

		// upper boundary should be equal to the initial lower boundary
		assertEquals(0, guess.getUpperBound());

		// calculated guess should be equal to the initial lower boundary
		assertEquals(0, guess.getCalculatedGuess());
	}

	/**
	 * The program requirements indicated that the numbers must be an int. When
	 * dividing integers in java the results is rounded down. If the user's
	 * number is the upper boundary value, the program has to use the
	 * detectCheating method to determine if there is one guess left that is
	 * either the upper or lower boundary. In this scenario the lower boundary
	 * must be set to the initial upper boundary and calculate one last guess.
	 */
	@Test
	public void testUsersNumIsInitialUpperBoundary() throws Exception {

		guess = new GuessBean();
		game = new GameLogic();

		// set initial boundaries
		guess.setInitialLowerBound(0);
		guess.setInitialUpperBound(999);
		guess.setUpperBound(999);

		// lowerBoundary is 997
		guess.setLowerBound(997);

		// calculate a guess which will be 998
		// (997+999)/2
		game.calculateGuess(guess);

		// user indicated that their guess is higher than 998
		guess.setLowOrHigh('H');

		// updated the boundaries for next guess
		game.updateGuessBoundaries(guess);

		// user is not cheating because the inital upper boundary is the last
		// valid guess
		game.detectCheating(guess);

		// calculate last guess
		game.calculateGuess(guess);

		// lower boundary should be 999
		assertEquals(999, guess.getLowerBound());

		// calculated guess should be 999
		assertEquals(999, guess.getCalculatedGuess());
	}

	/**
	 * Test that a InvalidHighOrLowException is thrown when the user submits
	 * feedback about the guessed number that is not equal to lower or higher.
	 */
	@Test(expected = InvalidHighOrLowException.class)
	public void testInvalidHighOrLow() throws Exception {

		guess = new GuessBean();
		game = new GameLogic();

		// game.setInitialBoundaries(guess, 0, 1000);

		// calculate a guess
		game.calculateGuess(guess);

		// user needs to indicate if guess is too high or too low
		// Q is an invalid highOrLowValue
		guess.setLowOrHigh('Q');

		// exception will be thrown
		game.updateGuessBoundaries(guess);
	}

	/**
	 * The detectCheating method uses a switch statement with case 0, 1, and
	 * default. The default case is where the difference between the upper
	 * boundary and lower boundary is not 0 or 1. In this scenario the user is
	 * not cheating so the detectCheating method should return false.
	 */
	@Test
	public void testUserNotCheatingDefault() {

		guess = new GuessBean();
		game = new GameLogic();

		// set upper and lower boundaries farther than one number away from each
		// other
		guess.setLowerBound(50);
		guess.setUpperBound(560);

		// calculate a guess
		game.calculateGuess(guess);

		// expected that the user is not cheating
		assertEquals(false, game.detectCheating(guess));
	}

	/**
	 * Test that the detectCheating method correctly identifies that the user is
	 * cheating when the upper and lower boundary are equal to the same number
	 */
	@Test
	public void testUserCheatingAtLowerBoundary() {
		guess = new GuessBean();
		game = new GameLogic();

		// set upper and lower boundary to the same int
		guess.setLowerBound(50);
		guess.setUpperBound(50);

		// calculate a guess
		game.calculateGuess(guess);

		// expected that the user is cheating
		assertEquals(true, game.detectCheating(guess));

	}

	/**
	 * @throws InvalidIntegerException
	 * 
	 */
	@Test
	public void testUpdatePreviousGuesses() throws InvalidIntegerException {
		guess = new GuessBean();
		game = new GameLogic();

		game.setInitialLowerBoundary(guess, "0");
		game.setInitialUpperBoundary(guess, "1000");

		game.calculateGuess(guess);

		game.updatedCollectionOfGuesses(guess);

		int guess1 = guess.getPreviousGuesses().get(0);

		// the first guess should be equal to 500
		assertEquals(500, guess1);

	}

	/**
	 * @throws InvalidIntegerException
	 * 
	 */
	@Test
	public void testUserCheating() throws InvalidIntegerException {
		guess = new GuessBean();
		game = new GameLogic();

		game.setInitialLowerBoundary(guess, "0");
		game.setInitialUpperBoundary(guess, "999");

		guess.setLowerBound(998);

		game.calculateGuess(guess);
		guess.setLowOrHigh('L');

		assertEquals(true, game.detectCheating(guess));

	}

}
