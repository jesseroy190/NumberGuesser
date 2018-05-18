<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML><%@page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<html>
<head>
<link rel="stylesheet" href="/numberguesser/css/numberguesserstyles.css"
	type="text/css">
<title>numberguesserhome</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
	<h1>Jesse's Number Guesser</h1>
	<form action="/numberguesser/guessnumberservlet" method="post">
		<fieldset>
			<h3>Enter a range of numbers for me to guess from (ex. 0-100)</h3>
	 	    <%-- Display error message when it is not set to null in request --%>
			<div class="error">
				<c:choose>
        	    	<c:when test="${requestScope.errorMessage == null}">                 
                 	</c:when>
                 	<c:otherwise>
       		  			*${requestScope.errorMessage }
						<br>
                   		<br>
                   	</c:otherwise>     
               	</c:choose>
            </div>
			<input type="text" id="lowerBound" name="lowerBound"placeholder="lower bound" /> - 
			<input type="text" id="upperBound" name="upperBound" placeholder="upper bound" /> <br>
			<h3>Think of a number in your selected range, then click 'START'</h3>
			<input type="submit" value="Start">
		</fieldset>
	</form>
</body>
</html>