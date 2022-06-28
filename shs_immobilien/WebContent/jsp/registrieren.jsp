<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="immo.portal.bean.BenutzerBean"%>
<%@page import="immo.portal.servlets.RegistrierenServlet"%>

<!DOCTYPE html>
<html lang="de">
<head>
	<!-- Verwendete CSS Imports -->
	<link rel="stylesheet" href="../css/hauptbild.css">
	<link rel="stylesheet" href="../css/registrieren.css">
	<link rel="stylesheet" href="../css/dropdownNavBar.css">
	<link rel="stylesheet" href="../css/footer.css">

	<script src="../js/registrieren.js"></script>
	
	<!-- Homepage Titel -->
	<title>sps-immobilien.de/Registrierung</title>
</head>

<body>
	<header>
		<!-- Einbindung der Navigationsleiste -->
		<%@ include file="../jspf/navBarHauptbild.jspf"%>
	</header>
	<br>

<main>
	<!-- Fehlermeldung falls die eingegebene E-Mail Adresse schon registriert ist -->
	<c:if test="${emailExistiert == true}">
		<h1 class="fehlerbutton">E-Mail existiert bereits!</h1>
	</c:if>
	
	<div class="hintergrund">
		<div class="textfeld">
		<!-- Form um einen neuen Benutzer anzulegen -->
		<form id="registForm" class="registrierenfenster" action="../RegistrierenServlet"	method="post">
			<table>			
			<tr>
				<td><label for="vorname">Vorname</label><br>
					<!-- Maximale Länge des Vornamens 32 Zeichen  -->
					<input type="text" id="vorname" name="vorname" placeholder="Vorname" maxlength="32" required /><br><br>
				</td>
							
				<td><label for="nachname">Nachname</label><br>
					<!-- Maximale Länge des Nachnamens 32 Zeichen (Längster Nachname in DE 26 Zeichen) -->
					<input type="text" id="nachname" name="nachname" placeholder="Nachname" maxlength="32" required /><br><br>
				</td>
			</tr>
					
			<tr>
				<td><label for="anschrift">Anschrift</label><br>
					<!-- Maximale Länge der Anschrift 64 Zeichen (Längste Adresse in DE 55 Zeichen ohne Nummer) -->
					<input type="text" id="anschrift" name="anschrift" placeholder="Anschrift" maxlength="64" required /><br><br>
				</td>
								
				<td><label for="plz">Postleitzahl</label><br>
					<!-- Pattern es können nur Ziffern von 0-9 eingegeben werden, es MÜSSEN 5 Ziffern eingegeben werden) -->
					<input type="text" id="plz" name="plz" placeholder="Postleitzahl" pattern="[0-9]{5}" title="PLZ darf aus maximal 5 Ziffern bestehen" required /><br><br>
				</td>
			</tr>
						
			<tr>
				<td><label for="wohnort">Wohnort</label><br>
					<!-- Maximale Länge des Wohnorts 32 Zeichen (Längster Wohnort in DE 32 Zeichen) -->
					<input type="text" id="wohnort" name="wohnort" placeholder="Wohnort" maxlength="32" required /><br><br>
				</td>
								
				<td><label for="telefon">Telefon</label><br>
					<!-- Pattern es können nur Ziffern von 0-9 eingegeben werden, es MÜSSEN MINDESTENS 3 Ziffern eingegeben werden 
					Maximal aber 16 Ziffern (Längste Telefonnumer in DE 13 Ziffern) -->
					<input type="text" id="telefon" name="telefon" placeholder="Telefonnummer" pattern="[0-9]{3,}" title="Telefonnummer muss aus mind. 3 Ziffern bestehen" maxlength="16" required /><br><br>
				</td>
			</tr>
						
			<tr>
				<td colspan="2"><label for="mail">E-Mail</label><br>
					<!-- Maximale Länge der E-Mail 64 Zeichen -->
					<input type="email" id="mail" name="email" placeholder="E-Mail" maxlength="64" required /><br><br>
				</td>
			</tr>
					
			<tr>
				<td colspan="2"><label for="passwort">Passwort</label><br>
					<!-- Maximale Länge des Passworts 32 Zeichen (Bei evtl. Verschlüsselung mind. 20 Zeichen) -->	
					<input type="password" id="passwort" name="passwort" placeholder="Passwort" maxlength="32" required /><br><br>
				</td>
			</tr>
					
			<tr>
				<td colspan="2"><label for="passwortwdh">Passwort wiederholen</label><br>
					<!-- Maximale Länge des Passworts 32 Zeichen (Bei evtl. Verschlüsselung mind. 20 Zeichen) -->	
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

<!-- Falls Sessionvariable gesetzt wird emailExistiert am Ende zurückgesetzt -->
<%
	if (session.getAttribute("emailExistiert") != null) {
		session.removeAttribute("emailExistiert");
	}
%>

<!-- Einbindung der Fußzeile -->	
<%@ include file="../jspf/fußzeile.jspf"%>
</body>
</html>