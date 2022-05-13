<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html class="html_hintergrund">

<head>
<meta charset="UTF-8">

<!-- Stylesheet Test TP-->
<link rel="stylesheet" href="../css/kaufen.css">

<title>sps-immobilien.de/verkaufen</title>

</head>

<body>
	<header>
		<p class="willkommen"></p>
	</header>
	

			<form action="../KaufenServlet" method=post accept-charset="utf-8"
			enctype="multipart/form-data">

				<h1>Wählen Sie Ihren gewünschten Haustyp!</h1>
				<input class="zurueck" type="button" value="Zurück" onclick="location.href = '../html/homepage.html'"><br>

					<c:forEach items="${haustyplist}" var="haustyp">
						<a href=""><button type="submit" class="button" value="${haustyp.typ}">${haustyp.typ}</button></a>
					</c:forEach>
			</form>

	</body>
</html>

