<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="html_hintergrund">
	<head>
		<link rel="stylesheet" href="../css/kontakt_formular_jsp.css">
	</head>
	<body>
		<header>
		<p class="willkommen"></p>
		</header>
	
		<nav>
		<form>
			<div>
				<input class="zurueck" type="button" value="Zurück"  onclick="location.href = '../html/homepage.html'">
			</div>
		</form>
	</nav>		
		<%-- Auslesen der Formularfelder --%>
		<%
			request.setCharacterEncoding("UTF-8");
			final String nachname = request.getParameter("nachname");
			final String vorname = request.getParameter("vorname");
			final String telefon = request.getParameter("telefon");
			final String email = request.getParameter("email");
			final String anliegen = request.getParameter("anliegen");
		%>
		
		<%-- Ausgabe der Formulardaten --%>
		<h3>Ihre Formulareingaben</h3>
		<br><b>Nachname: </b><%= nachname %>
		<br><b>Vorname: </b><%= vorname %>
		<br><b>Telefon: </b><%= telefon %>
		<br><b>Telefon: </b><%= email %>
		<br><b>Anliegen: </b><br><%= anliegen %>
	</body>
</html>