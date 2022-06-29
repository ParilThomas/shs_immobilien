<!-- @author Thomas Paril -->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="immo.portal.bean.HaustypBean"%>
<%@page import="immo.portal.bean.ObjektBean"%>
<%@page import="immo.portal.servlets.VerkaufServlet"%>
<%@page import="immo.portal.servlets.BietenServlet"%>
<%@page import="immo.portal.servlets.LogoutServlet"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="de">
<head>
	<!-- Verwendete CSS Imports -->
	<link rel="stylesheet" href="../css/hauptbild.css">
	<link rel="stylesheet" href="../css/ansichtbietenkaufensuchen.css">
	<link rel="stylesheet" href="../css/dropdownNavBar.css">
	<link rel="stylesheet" href="../css/footer.css">

	<!-- Einbinden der JavaScript Datei -->
	<script src="../js/bieten.js"></script>

	<!-- Homepage Titel -->
	<title>sps-immobilien.de/Kaufen/Details</title>
</head>

<body>
	<header>
		<!-- Einbinder der Navigationsleiste -->
		<%@ include file="../jspf/navBarHauptbild.jspf"%>
	</header>
	
<main>
<div>

<!-- Seitenaufbau über eine Tabelle -->
<table class="bietentabelle">
<tr>
	<td>
		<!-- Einbinden der Buttons der einzelnen Haustypen -->
		<%@ include file="../jspf/hausauswahl.jspf"%>
	</td>
<td>

<!-- Darstellung eines einzelenen Objekts mit der From
ein Gebot für das Objekt abzugeben -->
<form id="bietenForm" action="../GebotServlet" method=post>
	<c:forEach var="bieten" items="${objekt}">
		<p class="titel">${bieten.titel}</p>
		<img src="../kaufen_bild_servlet?id=${bieten.id}" alt="Kein Bild vorhanden!"/>
		<table>
			<tr>
				<td>Haustyp</td>
				<td>${bieten.haustyp}</td>
			</tr>
			<tr>
				<td>Bautyp</td>
				<td>${bieten.bautyp}</td>
			</tr>
			<tr>
				<td>Baujahr</td>
				<td>${bieten.baujahr}</td>
			</tr>
			<tr>
				<td>Wohnfläche</td>
				<td>${bieten.wohnflaeche} m²</td>
			</tr>
			<tr>
				<td>Grundstück</td>
				<td>${bieten.grundstuecksflaeche} m²</td>
			</tr>
			<tr>
				<td>Ort</td>
				<td>${bieten.standort}</td>
			</tr>
			<tr>
				<td>Angebotsende</td>
				<td>${bieten.datum}</td>
			</tr>
			<tr>
			    <td>Beschreibung</td>
			    <td class="beschreibung">${bieten.beschreibung}</td>
			</tr>
			<tr>
				<td colspan="2" class="trennung"><hr></td>
			</tr>
			<tr>
				<td>Aktuelles Gebot</td>
				<td>${bieten.startgebot}€</td>
			</tr>
			<tr>
				<td colspan="2" class="trennung"><hr></td>
			</tr>
		</table>
		
		<!-- Überprüfen ob ein Benutzer in der Session hinterlegt ist,
		falls JA kann der Benutzer ein Gebot abgeben -->
		<c:if test="${benutzer != null}">
			<label for="gebot">Ihr Gebot (€):</label><br>
				<input type="number" id="gebot" name="gebot" placeholder="Ihr Gebot" max="999999999"/><br>
				<input type="hidden" name="benutzer" value="${benutzer.id}"/><br>
				<button class="buttonpointer" type="submit" name="gebot_absenden" value="${bieten.id}" onclick="myFunction()">Absenden</button>
		</c:if>

		<!-- Überprüfen ob ein Benutzer in der Session hinterlegt ist = eingeloggt,
		falls NEIN Ausgabe um sich einzuloggen damit ein Gebot abgegeben werden kann -->
		<c:if test="${benutzer == null}">
			Bitte loggen Sie sich ein um ein Gebot abzugeben!
			<input class="buttonpointer" type="button" value="Login" onclick="location.href = 'login.jsp'"/>
		</c:if>
				
	</c:forEach>
</form>
</td>
</tr>
</table>

</div>
</main>

<!-- Einbindung der Fußzeile -->
<%@ include file="../jspf/fußzeile.jspf"%>
</body>
</html>