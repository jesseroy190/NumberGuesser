package com.numberguesser.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.numberguesser.beans.GuessBean;
import com.numberguesser.gamelogic.GameLogic;
import com.numberguesser.interfaces.UserInputValuesInterface;

/**
 * Servlet implementation class NextGuessServlet
 */
public class NextGuessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NextGuessServlet() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		GuessBean guess = (GuessBean) session.getAttribute("guessBean");
		GameLogic game = new GameLogic();

		// update the list of guesses to display to user
		game.updatedCollectionOfGuesses(guess);

		RequestDispatcher rd;

		// user says the calculated guess is their number
		if (request.getParameter("yesButton") != null) {

			// forward to the winner.jsp page
			rd = request.getRequestDispatcher("/jsp/winner.jsp");
			rd.forward(request, response);

		} else if (request.getParameter("higherButton") != null) {

			// user says their number is higher than the calculated guess
			// send input to guess bean
			guess.setLowOrHigh(UserInputValuesInterface.HIGH);

		} else if (request.getParameter("lowerButton") != null) {

			// user says their number is lower than the calculated guess
			// send input to guess bean
			guess.setLowOrHigh(UserInputValuesInterface.LOW);

		}// end if statement

		// update the boundaries based on user feedback
		try {
			game.updateGuessBoundaries(guess);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// detect if the user is cheating
		if (game.detectCheating(guess) == false) {

			// user isn't cheating so calculate a new guess
			game.calculateGuess(guess);
			session.setAttribute("guessBean", guess);

			// forward to the guesspage.jsp
			rd = request.getRequestDispatcher("/jsp/guesspage.jsp");
			rd.forward(request, response);
		} else {

			// user cheated, forward to cheater page
			rd = request.getRequestDispatcher("/jsp/cheater.jsp");
			rd.forward(request, response);

		}// end if statement

	}// end doPost method

}// end NextGuessServlet
