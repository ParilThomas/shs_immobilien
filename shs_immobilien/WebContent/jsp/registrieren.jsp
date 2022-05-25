<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="immo.portal.bean.RegistrierenBean"%>
<%@page import="immo.portal.servlets.RegistrierenServlet"%>
<!DOCTYPE html>
<html class="html_hintergrund">
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<!-- Stylesheet Test TP-->
<link rel="stylesheet" href="../css/registrieren.css">
<link rel="stylesheet" href="../css/dropdownNavBar.css">

<title>sps-immobilien.de/Registrierung</title>
</head>
<body>
<header>
		<p class="willkommen"></p>
		
	</header>
	<%@ include file="../jspf/allgbutton.jspf"%>
	<br>
	<main>
		
		<div class="registrierenfenster">
			<div class="center">
			  <div class="title">Registrierung</div>
		        </div>
		        
		        <div class="textfeld">
			<form action="../RegistrierenServlet" method="post">
			
					<label for="vorname">Vorname:</label><br>  
					<input type="text"id="vorname" name="vorname" placeholder="Ihr Vorname" required /><br><br>  
				
					<label for="nachname">Nachname:</label><br>  
					<input type="text"id="nachname" name="nachname" placeholder="Ihr Nachname" required /><br><br>  
				
					<label for="anschrift">Anschrift:</label><br>  
					<input type="text" id="anschrift" name="anschrift" placeholder="Ihre Anschrift" required /><br><br>  
				
					<label for="plz">Postleitzahl:</label><br>
					<input type="number" id="plz" name="plz" placeholder="Ihre Postleitzahl" required /><br><br>  
				
					<label for="wohnort">Wohnort:</label><br>  
					<input type="text" id="wohnort" name="wohnort" placeholder="Ihr Wohnort" required /><br><br>  
				
					<label for="telefon">Telefon:</label><br> 
					<input type="number" id="telefon" name="telefon" placeholder="Ihre Telefonnummer" required /><br><br>  
				
					<label for="mail">E-Mail:</label><br> 
					<input type="email" id="mail" name="email" placeholder="Ihre E-Mail" required /><br><br>  
				
					<label for="passwort">Passwort:</label><br>
					<input type="password" id="passwort" name="passwort1" placeholder="Ihr Passwort" required /><br><br>  
				
					
					<label for="passwort">Passwort wiederholen:</label><br>  
					<input type="password" id="passwort" name="passwort2" placeholder="Ihr Passwort wiederholen" required /><br><br>  
				
					
				<button class="registrieren" type="submit" name="rformular_absenden" value="absenden">Registrieren</button>
				<button class="abbrechen" type="button" name="abbrechen" value="abbrechen" onclick="location.href = '../html/hauptseite.html'">Abbrechen</button>
				</form>
				
				</div>
				
		</div>
	</main>
</body>
</html>