<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML><%@page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<html>
<head>
<title>cheater</title>
<link rel="stylesheet" href="/numberguesser/css/numberguesserstyles.css" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
	<h1>Jesse's Number Guesser</h1>
	<form action="/numberguesser/loadprogramservlet" method="post">
		<fieldset>
			<span class="mediumText blueText bold">We detected that you're cheating! Based on your feedback, there are no valid guesses left in the range of ${sessionScope.guessBean.initialLowerBound } and ${sessionScope.guessBean.initialUpperBound }.</span>
			<br>
			<br>
			<span class="mediumText blueText bold">Last Guess:</span>
			<span class="bold">${sessionScope.guessBean.calculatedGuess }</span>
			<br>
			<br>
			<span class="mediumText blueText bold">All Numbers Guessed:</span>
			<br>
			<div class=bold>
			<c:forEach var="guess" items="${sessionScope.guessBean.previousGuesses }">
				<c:out value="${ guess}"/>
				<br>
			</c:forEach>
			</div>
			
			<br>
			If you want to play again and follow the rules this time, click 'Play Again'
			<br>
			<input type="submit" value="Play Again">
		</fieldset>

	</form>

</body>
</html>