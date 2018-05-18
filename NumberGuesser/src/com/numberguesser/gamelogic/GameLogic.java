package com.numberguesser.gamelogic;

import java.util.Collections;

import com.numberguesser.beans.GuessBean;
import com.numberguesser.exceptions.InvalidBoundariesException;
import com.numberguesser.exceptions.InvalidHighOrLowException;
import com.numberguesser.exceptions.InvalidIntegerException;

/**
 * @author Jesse Roy
 * 
 *         The GameLogic class houses all of the methods that are used to carry
 *         out the basic functions of the game such as updating the initial
 *         boundaries, checking that the user entered a valid range, detect if
 *         the user is cheating, and calculating guesses
 * 
 */
public class GameLogic {

	/**
	 * Set the initial lower boundary, and lower boundary to the users input. If
	 * the user's input is not a valid integer a InvalidIntegerException is
	 * thrown
	 */
	public void setInitialLowerBoundary(GuessBean guess,
			String initialLowerBoundary) throws InvalidIntegerException {
		try {
			guess.setInitialLowerBound(Integer.parseInt(initialLowerBoundary));
			guess.setLowerBound(Integer.parseInt(initialLowerBoundary));
		} catch (Exception e) {
			throw new InvalidIntegerException(
					"Lower Boundary must be a valid Integer!", e);
		}// ending try/catch bracket
	}// end setInitialLowerBoundary method

	/**
	 * Set the initial upper boundary, and upper boundary to the users input. If
	 * the user's input is not a valid integer a InvalidIntegerException is
	 * thrown
	 */
	public void setInitialUpperBoundary(GuessBean guess,
			String initialUpperBoundary) throws InvalidIntegerException {
		try {

			guess.setInitialUpperBound(Integer.parseInt(initialUpperBoundary));
			guess.setUpperBound(Integer.parseInt(initialUpperBoundary));

		} catch (Exception e) {

			throw new InvalidIntegerException(
					"Upper Boundary must be a valid Integer!", e);

		}// ending try/catch bracket
	}// end setInitialUpperBoundary

	/**
	 * This method is used to check that the lower boundary is smaller than the
	 * upper boundary they entered. If the lower boundary is greater than or
	 * equal to the upper boundary, an invalid boundaries exception will be
	 * thrown
	 * */
	public void checkBoundaryRange(GuessBean guess)
			throws InvalidBoundariesException {

		// check that lowerbound is greater than or equal to upper
		if (guess.getLowerBound() > guess.getUpperBound()

		|| guess.getLowerBound() == guess.getUpperBound()) {

			throw new InvalidBoundariesException(
					"Lower boundary must be smaller than the upper boundary!");

		}// ending if bracket
	}// end checkBoundaryRange

	/**
	 * Calculate the guess by getting the average of the current lower and upper
	 * boundaries
	 */
	public void calculateGuess(GuessBean guess) {

		// get the average of upper and lower bound
		guess.setCalculatedGuess((guess.getLowerBound() + guess.getUpperBound()) / 2);

	}// end calculateGuess

	/**
	 * Add the recently calculated guess to this list of all previous guesses
	 * that's store in the GuessBean
	 */
	public void updatedCollectionOfGuesses(GuessBean guess) {

		guess.getPreviousGuesses().add(guess.getCalculatedGuess());

		Collections.sort(guess.getPreviousGuesses());

	}// end updatedCollectionOfGuesses

	/**
	 * The boundaries are updated with input from the user. If the user says the
	 * number guessed is lower than their number, the upper bound is set to the
	 * most current calculated guess. If the user says the number guess is
	 * higher than their number, the lower bound is set to the most current
	 * calculated guess. If the user does not specify lower or higher, and
	 * InvalidHighOrLowException is thrown
	 */
	public void updateGuessBoundaries(GuessBean guess) throws Exception {
		switch (guess.getLowOrHigh()) {

		// user indicates their number is lower than the guess num
		case 'L':

			// update the upper boundary to last guess
			guess.setUpperBound(guess.getCalculatedGuess());
			guess.setNumOfGuesses();
			break;

		// user indicates their number his higher than the guess num
		case 'H':

			// update lower boundary to last guess
			guess.setLowerBound(guess.getCalculatedGuess());
			guess.setNumOfGuesses();
			break;

		default:

			// only 'H' or 'L' can be passed to this method
			throw new InvalidHighOrLowException(
					"Must enter 'H' for higher or 'L' for lower");

		}// end of switch
	}// end updateGuessBoundaries

	/**
	 * This method is used to determine if the user is cheating. The scenarios
	 * where a user is cheating are listed below.
	 * 
	 * 1. The upper and lower boundary are equal, there are no more possible
	 * guesses 2. The upper boundary and the calculated guess are 1 number apart
	 * (Ex 998 and 999) and the user says their number is lower.
	 * 
	 * Since the program is dividing by Integers, the upper and lower boundaries
	 * will never be guessed by taking the average. When the number guessed is
	 * one number below the initial upper boundary, or one number above the
	 * lower boundary. The boundaries are updated so these intial upper and
	 * lower boundaries can be guessed
	 * 
	 * Example: initial lower boundary =0 initial upper boundary =999 last guess
	 * = 998 but user says their number is higher. The average of 998 and 999 is
	 * 98.5, which is rounded down since we're using integers. The lower
	 * boundary is adjust to 999 so the average and next guess will be 999.
	 * 
	 */
	public Boolean detectCheating(GuessBean guess) {

		boolean userIsCheating = false;

		// determine if user is cheating
		int diffUpperAndLowerBound = (guess.getUpperBound() - guess
				.getLowerBound());

		switch (diffUpperAndLowerBound) {

		case 0:
			// the upperbound and lowerbound are equal so there are no
			// possible
			// guesses left
			userIsCheating = true;
			break;
		case 1:

			// check if guess is one number below the initial upperbound.
			// program is dividing integers so it will always round down and
			// the inital upperbound will never be guessed
			if (guess.getInitialUpperBound() - guess.getCalculatedGuess() == 1) {

				// if the user says their number is lower the user is cheating
				if (guess.getLowOrHigh() == 'L') {
					userIsCheating = true;
				} else {
					// set lower bound to the initial upperbound to see if
					// that's the user's guess
					guess.setLowerBound(guess.getInitialUpperBound());
				}

			} else if (guess.getCalculatedGuess()
					- guess.getInitialLowerBound() == 1) {

				// if the lowerbound is the user's guess we need to set the
				// upperbound to the inital lowerbound value
				guess.setUpperBound(guess.getInitialLowerBound());

			} else {
				// there are no possible guesses left if the upperbound -
				// lowerbound equals 0.

				userIsCheating = true;
			}
			break;

		default:

			// user isn't cheating so break out of case statement
			userIsCheating = false;
			break;

		}// end of switch

		return userIsCheating;

	}// end detectCheating

}// end GameLogic class
