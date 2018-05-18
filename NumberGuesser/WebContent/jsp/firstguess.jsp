<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML><%@ page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<html>
<head>
<title>guesspage</title>
<link rel="stylesheet" href="/numberguesser/css/numberguesserstyles.css" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<h1>Jesse's Number Guesser</h1>
			<h3>I will guess your number between ${sessionScope.guessBean.initialLowerBound } and ${sessionScope.guessBean.initialUpperBound }</h3>
	<form action="/numberguesser/nextguessservlet" method="post">
		<fieldset>
			<span class="largeText blueText bold">Is your number ${sessionScope.guessBean.calculatedGuess }?</span>
			<br>
			<br>
			<div class="centerAlign">
				<div class="leftAlignText">
					&nbsp;&nbsp;&nbsp;&nbsp;Click 'YES' if it is your number,
					<br>
					&nbsp;&nbsp;&nbsp;&nbsp;Click 'Higher' if your number is higher than my guess,
					<br>
					&nbsp;&nbsp;&nbsp;&nbsp;Click 'Lower' if your number is lower than my guess
				</div>
			<br>
			<br>
			<input type="submit" name="yesButton" value="YES!" class="yesButton" >
			<input type="submit" name="higherButton" value="Higher">
			<input type="submit" name="lowerButton"value="Lower">
			</div>
		</fieldset>
	</form>
</body>
</html>