<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML><%@page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<html>
<head>
<title>winner</title>
<link rel="stylesheet" href="/numberguesser/css/numberguesserstyles.css" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
	<h1>Jesse's Number Guesser</h1>
	<form action="/numberguesser/loadprogramservlet" method="post">
		<fieldset>
			<span class="largeText blueText bold">I guessed your number of ${sessionScope.guessBean.calculatedGuess} in ${sessionScope.guessBean.numOfGuesses} tries!</span>
			<br>
			<br>
			<span class="mediumText blueText bold">Numbers Guessed:</span>
			<br>
			<div class="bold">
			<c:forEach var="guess" items="${sessionScope.guessBean.previousGuesses }">
				<c:out value="${ guess}"/>
				<br>
			</c:forEach>
			</div>
			<input type="submit" value="Play Again">
		</fieldset>
	</form>

</body>
</html>