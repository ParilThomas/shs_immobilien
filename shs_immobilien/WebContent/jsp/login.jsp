<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="immo.portal.servlets.LoginServlet"%>
<%@page import="immo.portal.servlets.LogoutServlet"%>
<%@page import="immo.portal.servlets.AnsichtServlet"%>
<%@page import="immo.portal.bean.BenutzerBean"%>
<!DOCTYPE html>
<html lang="de">
<head>
<meta charset="UTF-8">

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css"/>
<link rel="stylesheet" href="../css/login.css">
<link rel="stylesheet" href="../css/dropdownNavBar.css">


<title>sps-immobilien.de/Login</title>
</head>
<body>
    <div class="willkommen"></div>
    	<%@ include file="../jspf/allgbutton.jspf"%>
    	<br>
    

<div class="hintergrund">

	<c:if test="${istNichtRegistriert == true}">
		<h1>Der Benutzer ist nicht registriert. Weiter zur <a class="aregistrieren" href="./registrieren.jsp">Registrierung</a>?</h1>
	</c:if>
	
	<c:if test="${falscheLoginDaten == true}">
		<h1>Falsche Login Daten</h1>
	</c:if>
    
    <form class="ansicht" action="../LoginServlet" method="post" accept-charset="utf-8">
    
      <div class="textfeld">
        <input type="email" id="mail" name="email" placeholder="Ihre Email" required/>
        <div class="icon"><i class="fas fa-user"></i></div>
      </div>
      <div class="textfeld">
        <input type="password" id="passwort" name="passwort" placeholder="Ihr Passwort" required/>
        <div class="icon"><i class="fas fa-lock"></i></div>
      </div>
      <br>
        <button class="loginbutton" type="submit" name="login_absenden" value="absenden">Login</button>
        
       <div class="registrieren">
       Noch nicht registriert? <a class="aregistrieren" href="./registrieren.jsp">Hier Registrieren</a>
      </div>
    </form>

</div>
    
</body>
</html>