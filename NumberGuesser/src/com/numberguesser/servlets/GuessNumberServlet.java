package com.numberguesser.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.numberguesser.beans.GuessBean;
import com.numberguesser.exceptions.InvalidBoundariesException;
import com.numberguesser.gamelogic.GameLogic;

/**
 * Servlet implementation class GuessNumberServlet
 */
public class GuessNumberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GuessNumberServlet() {
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
	protected void doPost(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {

		HttpSession session = request.getSession(false);

		GuessBean guess = new GuessBean();
		GameLogic game = new GameLogic();

		try {
			// set the initial boundaries the user specified
			game.setInitialLowerBoundary(guess,
					request.getParameter("lowerBound"));
			game.setInitialUpperBoundary(guess,
					request.getParameter("upperBound"));

			// check that the range entered is valid
			game.checkBoundaryRange(guess);

			// calculate the first guess
			game.calculateGuess(guess);

			// set the guessbean in session
			session.setAttribute("guessBean", guess);

			// forward to the first guess jsp
			RequestDispatcher rd = request
					.getRequestDispatcher("/jsp/firstguess.jsp");
			rd.forward(request, response);

		} catch (InvalidBoundariesException boundaryException) {
			request.setAttribute("errorMessage", boundaryException.getMessage());
			request.getRequestDispatcher("/jsp/numberguesserhome.jsp").forward(
					request, response);
		} catch (Exception exception) {
			request.setAttribute("errorMessage", exception.getMessage());
			request.getRequestDispatcher("/jsp/numberguesserhome.jsp").forward(
					request, response);

		} // end try/catch
	}// end doPost method
}// end GuessNumberServlet
