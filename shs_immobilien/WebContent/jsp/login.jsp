<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="immo.portal.servlets.LoginServlet"%>
<%@page import="immo.portal.servlets.LogoutServlet"%>
<%@page import="immo.portal.servlets.AnsichtServlet"%>
<%@page import="immo.portal.bean.BenutzerBean"%>

<!DOCTYPE html>
<html lang="de">
<head>
<meta charset="UTF-8">

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css" />
<link rel="stylesheet" href="../css/login.css">
<link rel="stylesheet" href="../css/dropdownNavBar.css">
<link rel="stylesheet" href="../css/hauptbild.css">

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
			<h1>
			!! Der Benutzer ist nicht registriert !!
<!-- 			<a href="./registrieren.jsp">Registrierung</a>? -->
				</h1>
			
			</div>
			
		</c:if>

		<c:if test="${falscheLoginDaten == true}">
			<div class="schonregistriert"> 
			<h1>!! Falsche Login Daten !!</h1>
		</div>
		</c:if>
		
</div>
</nav>
<main>
<div class="hintergrund">
		<form id="login-form" class="ansicht" action="../LoginServlet" method="post"
			accept-charset="utf-8">

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
				<input type="checkbox" id="remember" name="remember" checked>			
				<label for="remember">Benutzer merken</label>
			</div>

			<button id="login_button" class="loginbutton" type="submit" name="login_absenden"
				value="absenden">Login</button>

			<div class="registrieren">
				Noch nicht registriert? <a class="aregistrieren"
					href="./registrieren.jsp">Hier Registrieren</a>
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
<footer>
			<i class="fa fa-building-o">&nbsp;SPS-Immobilien |</i> <i
				class="fa fa-map-marker">&nbsp; Baumweg 10 - 85296 Rohrbach |</i> <i
				class="fa fa-phone">&nbsp; +49-8442-4563-0 |</i> <i
				class="fa fa-envelope-o">&nbsp; contact@sps.com |</i> <i
				class="fa fa-fax">&nbsp; +49-8442-4563-4 </i>
		</footer>

</body>

</html>