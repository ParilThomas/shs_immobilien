<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="immo.portal.bean.BenutzerBean"%>
<%@page import="immo.portal.servlets.RegistrierenServlet"%>

<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="../css/hauptbild.css">
	<link rel="stylesheet" href="../css/registrieren.css">
	<link rel="stylesheet" href="../css/dropdownNavBar.css">
	<link rel="stylesheet" href="../css/footer.css">

	<script src="../js/registrieren.js"></script>

	<title>sps-immobilien.de/Registrierung</title>
</head>

<body>
	<header>
		<%@ include file="../jspf/navBarHauptbild.jspf"%>
	</header>
	<br>

<main>
	<c:if test="${emailExistiert == true}">
		<h1 class="fehlerbutton">E-Mail existiert bereits!</h1>
	</c:if>
	
	<div class="hintergrund">
		<div class="textfeld">
		<form id="registForm" class="ansicht" action="../RegistrierenServlet"	method="post">
			<table>			
			<tr>
				<td><label for="vorname">Vorname</label><br>
					<input type="text" id="vorname" name="vorname" placeholder="Vorname" maxlength="32"	required /><br><br>
				</td>
							
				<td><label for="nachname">Nachname</label><br>
					<input type="text" id="nachname" name="nachname" placeholder="Nachname" maxlength="32" required /><br><br>
				</td>
			</tr>
					
			<tr>
				<td><label for="anschrift">Anschrift</label><br>
					<input type="text" id="anschrift" name="anschrift" placeholder="Anschrift" maxlength="64" required /><br><br>
				</td>
								
				<td><label for="plz">Postleitzahl</label><br>
					<input type="text" id="plz" name="plz" placeholder="Postleitzahl" pattern="[0-9]{5}" required /><br><br>
				</td>
			</tr>
						
			<tr>
				<td><label for="wohnort">Wohnort</label><br>
					<input type="text" id="wohnort" name="wohnort" placeholder="Wohnort" maxlength="32" required /><br><br>
				</td>
								
				<td><label for="telefon">Telefon</label><br>
					<input type="text" id="telefon" name="telefon" placeholder="Telefonnummer" pattern="[0-9]{20}" required /><br><br>
				</td>
			</tr>
						
			<tr>
				<td colspan="2"><label for="mail">E-Mail</label><br>
					<input type="email" id="mail" name="email" placeholder="E-Mail" maxlength="64" required /><br><br>
				</td>
			</tr>
					
			<tr>
				<td colspan="2"><label for="passwort1">Passwort</label><br>
					<input type="password" id="passwort" name="passwort" placeholder="Passwort" maxlength="32" required /><br><br>
				</td>
			</tr>
					
			<tr>
				<td colspan="2"><label for="passwortwdh">Passwort wiederholen</label><br>
					<input type="password" id="passwortwdh" name="passwortwdh" placeholder="Passwort wiederholen" maxlength="32" required /><br><br>
				</td>
			</tr>

			<tr>
				<td><button class="registrieren" type="submit" name="rformular_absenden" value="absenden">Registrieren</button></td>
				<td><button class="abbrechen" type="button" name="abbrechen" value="abbrechen" onclick="location.href = './startseite.jsp'">Abbrechen</button></td>
			</tr>
			</table>
		</form>
		</div>
	</div>
</main>
	
<%@ include file="../jspf/footer.jspf"%>
</body>
</html>