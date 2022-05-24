<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="immo.portal.servlets.LoginServlet"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<!-- Stylesheet Test TP-->
<link rel="stylesheet" href="../css/verkaufen.css">
<title>sps-immobilien.de/Login</title>
</head>
<body>
    <p class="willkommen"></p>
    	<%@ include file="../jspf/allgbutton.jspf"%>
    	<br>
    
    <header>
        <div class="center">
            <h1>Login</h1>
        </div>
    </header>
    
	<c:if test="${istRegistriert == true}">
<!-- 	Hier sollte man dann auf die Bietfunktion zugreifen können  -->
	<h1 class="registriert">Wilkommen jetzt können Sie ein Gebot abgeben!!!</h1>

	</c:if>
	<c:if test="${istNichtRegistriert == true}">
	<h1 class="nichtregistriert">Eingaben Falsch!!! Bitte Eingaben überprüfen, falls Sie noch nicht Registriert sind bitte :</h1>
	<br>
	<a href="registrieren.jsp">Hier Registrieren</a>
	</c:if>
    <main>
		
        
        <form action="../LoginServlet" method="post"
            accept-charset="utf-8" enctype="multipart/form-data">

            <div class="verkaufsformular">

                <p>
                    <label for="mail">E-Mail:</label><br> <input type="email"
                        id="mail" name="email" placeholder="Ihre E-Mail" required />
                

                
                    <label for="passwort">Passwort:</label><br> <input type="password"
                        id="passwort" name="passwort" placeholder="Ihr Passwort" required />
                

               
                    <button type="submit" name="login_absenden" value="login">Anmelden</button>
                    <input class="abbrechen" type="button" value="Registrieren"
                        onclick="location.href = 'registrieren.jsp'">
                </p>


            </div>
        </form>
    </main>
    
    </body>
</html>