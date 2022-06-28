<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="immo.portal.bean.HaustypBean"%>
<%@page import="immo.portal.bean.ObjektBean"%>
<%@page import="immo.portal.servlets.KaufenServlet"%>
<%@page import="immo.portal.servlets.LogoutServlet"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="de">
<head>
	<!-- Verwendete CSS Imports -->
	<link rel="stylesheet" href="../css/hauptbild.css">
	<link rel="stylesheet" href="../css/ansichtbietenkaufensuchen.css">
	<link rel="stylesheet" href="../css/dropdownNavBar.css">
	<link rel="stylesheet" href="../css/footer.css">

	<!-- Homepage Titel -->
	<title>sps-immobilien.de/Suchen/Ergebnisse</title>
</head>

<body>
	<header>
		<!-- Einbindung der Navigationsleiste -->
		<%@ include file="../jspf/navBarHauptbild.jspf"%>
	</header>
	<br>
	
	<!-- Form für die Ausgabe der gesuchten Objekte -->
	<form action="../BietenServlet" method=post>
		<c:forEach var="haus" items="${objekte}">
			<table class="objekttabelle">
				<tr>
					<td class="titel" colspan="3">${haus.titel}</td>
				</tr>
				
				<tr>
					<td rowspan="7"><img src="../kaufen_bild_servlet?id=${haus.id}" alt="Kein Bild vorhanden!" /></td>
					<td>Baujahr:</td>
					<td>${haus.baujahr}</td>
				</tr>
				
				<tr>
					<td>Wohnfläche:</td>
					<td>${haus.wohnflaeche}m²</td>
				</tr>
				
				<tr>
					<td>Grundstück:</td>
					<td>${haus.grundstuecksflaeche}m²</td>
				</tr>
				
				<tr>
					<td>Standort:</td>
					<td>${haus.standort}</td>
				</tr>
				
				<tr>
					<td>Angebotsende:</td>
					<td>${haus.datum}</td>
				</tr>
				
				<tr>
					<td>Aktuelles Gebot:</td>
					<td>${haus.startgebot}€</td>
				</tr>
				
				<tr>
					<!-- Öffnet das Objekt mit den Detailinformationen und Bietfunktion -->
					<td colspan="2" class="detailbutton"><button class="buttonpointer" type="submit" name="detailid" value="${haus.id}">Details ansehen</button></td>
				</tr>
				
				<tr><td colspan="3" class="trennung"><hr></td></tr>
			</table>
			<br>
		</c:forEach>
	</form>

<!-- Einbindung der Fußzeile -->
<%@ include file="../jspf/fußzeile.jspf"%>
</body>
</html>

