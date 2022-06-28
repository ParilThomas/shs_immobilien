<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="immo.portal.servlets.LoginServlet"%>
<%@page import="immo.portal.servlets.LogoutServlet"%>
<%@page import="immo.portal.servlets.AnsichtServlet"%>
<%@page import="immo.portal.bean.BenutzerBean"%>

<!DOCTYPE html>
<html lang="de">
<head>
	<!-- Verwendete CSS Imports -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css" />
	<link rel="stylesheet" href="../css/login.css">
	<link rel="stylesheet" href="../css/dropdownNavBar.css">
	<link rel="stylesheet" href="../css/hauptbild.css">
	<link rel="stylesheet" href="../css/footer.css">

	<script src="../js/login.js"></script>
	
	<!-- Homepage Titel -->
	<title>sps-immobilien.de/Login</title>
</head>

<body>
	<header>
		<!-- Einbindung der Navigationsleiste -->
		<%@ include file="../jspf/navBarHauptbild.jspf"%>
	</header>
	
<nav>
	<div class="hintergrund">
		<!-- Fehlermeldung falls die eingegebene E-Mail Adresse noch nicht registriert ist -->
		<c:if test="${istNichtRegistriert == true}">
			<div class="schonregistriert"> 
				<h1>!! Der Benutzer mit dieser E-Mail existiert nicht !!</h1>		
			</div>		
		</c:if>
		<!-- Fehlermeldung falls die E-Mail existiert aber das Passwort nicht zur E-Mail passt -->
		<c:if test="${falscheLoginDaten == true}">
			<div class="schonregistriert"> 
				<h1>!! Falsches Passwort !!</h1>
			</div>
		</c:if>		
	</div>
</nav>

<main>
<div class="hintergrund">

	<!-- Login From zum einloggen -->
	<form id="login-form" class="loginfenster" action="../LoginServlet" method="post" accept-charset="utf-8">
		<div class="logintextfeld">
			<input type="email" id="mail" name="email" placeholder="Ihre Email" required />
				<div class="icon">
					<i class="fas fa-user"></i>
				</div>
		</div>
		<div class="logintextfeld">
			<input type="password" id="passwort" name="passwort" placeholder="Ihr Passwort" required />
				<div class="icon">
					<i class="fas fa-lock"></i>
				</div>
		</div>
		<br>
		
		<!-- Checkbox Benutzer merken zur Cookieverarbeitung -->
		<div class="benutzermerken">
			<label for="remember">Benutzer merken</label>
			<input type="checkbox" id="remember" name="remember" checked>			
		</div>

		<button id="login_button" class="loginbutton" type="submit" name="login_absenden" value="absenden">Login</button>
		<!-- Link zur Registrierenseite falls noch nicht registriert -->
		<div class="registrieren">
				Noch nicht registriert? <a class="aregistrieren" href="../RegistrierenServlet">Hier Registrieren</a>
		</div>
	</form>
</div>
</main>

<!-- Falls Sessionvariable gesetzt wird istNichtRegistriert & falscheLoginDaten am Ende zurückgesetzt -->
<%
	if (session.getAttribute("istNichtRegistriert") != null) {
		session.removeAttribute("istNichtRegistriert");
	}
	if (session.getAttribute("falscheLoginDaten") != null) {
		session.removeAttribute("falscheLoginDaten");
	}
%>

<!-- Einbindung der Fußzeile -->
<%@ include file="../jspf/fußzeile.jspf"%>
</body>
</html>