<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="immo.portal.bean.RegistrierenBean"%>
<%@page import="immo.portal.servlets.RegistrierenServlet"%>
<!DOCTYPE html>
<html class="html_hintergrund">
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<!-- Stylesheet Test TP-->
<link rel="stylesheet" href="../css/registrieren.css">
<title>sps-immobilien.de/Registrierung</title>
</head>
<body>
<header>
		<p class="willkommen"></p>
		
<!-- 		<div class="center"> -->
<!-- 			<h1>Registrierung</h1> -->
<!-- 		</div> -->
	</header>
	<%@ include file="../jspf/allgbutton.jspf"%>
	<br>
	<main>
	
		<form action="../RegistrierenServlet" method="post"
			accept-charset="utf-8">
			<div class="registrieren">
				<p>
					<label for="vorname">Vorname:</label><br> <input type="text"
						id="vorname" name="vorname" placeholder="Ihr Vorname" required />
				</p>

				<p>
					<label for="nachname">Nachname:</label><br> <input type="text"
						id="nachname" name="nachname" placeholder="Ihr Nachname" required />
				</p>


				<p>
					<label for="anschrift">Anschrift</label><br> <input
						type="text" id="anschrift" name="anschrift"
						placeholder="Ihre Anschrift" required />
				</p>

				<p>
					<label for="plz">Postleitzahl</label><br> <input
						type="number" id="plz" name="plz"
						placeholder="Ihre Postleitzahl" required />
				</p>

				<p>
					<label for="wohnort">Wohnort</label><br> <input
						type="text" id="wohnort" name="wohnort"
						placeholder="Ihr Wohnort" required />
				</p>
				

				<p>
					<label for="telefon">Telefon</label><br> <input type="number"
						id="telefon" name="telefon" placeholder="Ihre Telefonnummer"
						required />
				</p>

				<p>
					<label for="mail">E-Mail</label><br> <input type="email"
						id="mail" name="email" placeholder="Ihre E-Mail" required />
				</p>
				<hr>
				<p>
					<label for="passwort">Passwort</label><br> <input type="password"
						id="passwort" name="passwort1" placeholder="Ihr Passwort" required />
				</p>
					<p>
					<label for="passwort">Passwort wiederholen</label><br> <input type="password"
						id="passwort" name="passwort2" placeholder="Ihr Passwort wiederholen" required />
				</p>
				<br>
				<hr>
				<p>
				<button type="submit" name="rformular_absenden" value="absenden">Registrieren</button>
				<input class="abbrechen" type="button" value="Abbrechen" onclick="location.href = '../html/startseite.html'">
				</p>
				
				
		</div>	</form>	
	</main>
</body>
</html>