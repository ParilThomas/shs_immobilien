<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="immo.portal.bean.BenutzerBean"%>
<%@page import="immo.portal.servlets.RegistrierenServlet"%>
<!DOCTYPE html>
<html lang="de">
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<!-- Stylesheet Test TP-->
<link rel="stylesheet" href="../css/registrieren.css">
<link rel="stylesheet" href="../css/dropdownNavBar.css">

<title>sps-immobilien.de/Registrierung</title>
<script>
	document.addEventListener("DOMContentLoaded", init);
	function init() {
		var form = document.getElementById("myForm");
		form.addEventListener("submit", checkPasswort);
	}

	function checkPasswort(evt) {

		var passwort1 = document.getElementById("passwort1").value;
		var passwort2 = document.getElementById("passwort2").value;

		if (passwort1 != passwort2) {
			alert("Passwort ist Falsch!! Bitte wiederholen");
			evt.preventDefault();
		}
	}
</script>
</head>
<body>
	<header>
		<div class="willkommen"></div>

	</header>
	<%@ include file="../jspf/allgbutton.jspf"%>
	<br>

	<main>
		<div class="hintergrund">


			<div class="textfeld">
				<form id="myForm" class="ansicht" action="../RegistrierenServlet"
					method="post">
					<table>
						<tr>
							<td><label for="vorname">Vorname:</label><br> <input
								type="text" id="vorname" name="vorname" placeholder="Vorname"
								required /><br> <br></td>
							<td><label for="nachname">Nachname:</label><br> <input
								type="text" id="nachname" name="nachname" placeholder="Nachname"
								required /><br> <br></td>
						</tr>
						<tr>
							<td><label for="anschrift">Anschrift:</label><br> <input
								type="text" id="anschrift" name="anschrift"
								placeholder="Anschrift" required /><br> <br></td>
							<td><label for="plz">Postleitzahl:</label><br> <input
								type="number" id="plz" name="plz" placeholder="Postleitzahl"
								required /><br> <br></td>


						</tr>
						<tr>
							<td><label for="wohnort">Wohnort:</label><br> <input
								type="text" id="wohnort" name="wohnort" placeholder="Wohnort"
								required /><br> <br></td>
							<td><label for="telefon">Telefon:</label><br> <input
								type="number" id="telefon" name="telefon"
								placeholder="Telefonnummer" required /><br> <br></td>

						</tr>
						<tr>
							<td colspan="2"><label for="mail">E-Mail:</label><br> <input
								type="email" id="mail" name="email" placeholder="E-Mail"
								required /><br> <br></td>
						</tr>
						<tr>
							<td colspan="2"><label for="passwort1">Passwort:</label><br>
								<input type="password" id="passwort1" name="passwort1"
								placeholder="Passwort" required /><br> <br></td>
						</tr>
						<tr>
							<td colspan="2"><label for="passwort2">Passwort
									wiederholen:</label><br> <input type="password" id="passwort2"
								name="passwort2" placeholder="Passwort wiederholen" required /><br>
								<br></td>
						</tr>

						<tr>
							<td>
								<button class="registrieren" type="submit"
									name="rformular_absenden" value="absenden">Registrieren</button>

							</td>
							<td>
								<button class="abbrechen" type="button" name="abbrechen"
									value="abbrechen" onclick="location.href = './startseite.jsp'">Abbrechen</button>
							</td>
						</tr>

					</table>
				</form>

			</div>

		</div>

	</main>
</body>
</html>