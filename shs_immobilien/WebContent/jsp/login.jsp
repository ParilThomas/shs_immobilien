<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="immo.portal.servlets.LoginServlet"%>
<%@page import="immo.portal.servlets.LogoutServlet"%>
<%@page import="immo.portal.servlets.AnsichtServlet"%>
<%@page import="immo.portal.bean.BenutzerBean"%>

<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css" />
	<link rel="stylesheet" href="../css/login.css">
	<link rel="stylesheet" href="../css/dropdownNavBar.css">
	<link rel="stylesheet" href="../css/hauptbild.css">
	<link rel="stylesheet" href="../css/footer.css">

	<script src="../js/login.js"></script>

	<title>sps-immobilien.de/Login</title>
</head>

<body>
	<header>
		<%@ include file="../jspf/navBarHauptbild.jspf"%>
	</header>
	
<nav>
	<div class="hintergrund">
		<c:if test="${istNichtRegistriert == true}">
			<div class="schonregistriert"> 
				<h1>!! Der Benutzer mit dieser E-Mail existiert nicht !!</h1>		
			</div>		
		</c:if>

		<c:if test="${falscheLoginDaten == true}">
			<div class="schonregistriert"> 
				<h1>!! Falsches Passwort !!</h1>
			</div>
		</c:if>		
	</div>
</nav>

<main>
<div class="hintergrund">
	<form id="login-form" class="ansicht" action="../LoginServlet" method="post" accept-charset="utf-8">
		<div class="textfeld">
			<input type="email" id="mail" name="email" placeholder="Ihre Email" required />
				<div class="icon">
					<i class="fas fa-user"></i>
				</div>
		</div>
		<div class="textfeld">
			<input type="password" id="passwort" name="passwort" placeholder="Ihr Passwort" required />
				<div class="icon">
					<i class="fas fa-lock"></i>
				</div>
		</div>
		<br>
			
		<div class="remember">
			<label for="remember">Benutzer merken</label>
			<input type="checkbox" id="remember" name="remember" checked>			
		</div>

		<button id="login_button" class="loginbutton" type="submit" name="login_absenden" value="absenden">Login</button>

		<div class="registrieren">
				Noch nicht registriert? <a class="aregistrieren" href="../RegistrierenServlet">Hier Registrieren</a>
		</div>
	</form>
</div>
</main>

<%
	if (session.getAttribute("istNichtRegistriert") != null) {
		session.removeAttribute("istNichtRegistriert");
	}
	if (session.getAttribute("falscheLoginDaten") != null) {
		session.removeAttribute("falscheLoginDaten");
	}
%>
	
<%@ include file="../jspf/footer.jspf"%>
</body>
</html>