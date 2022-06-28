<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="immo.portal.bean.HaustypBean"%>
<%@page import="immo.portal.bean.ObjektBean"%>
<%@page import="immo.portal.servlets.KaufenServlet"%>
<%@page import="immo.portal.servlets.LogoutServlet"%>
<%@page import="immo.portal.servlets.AnsichtServlet"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="de">
	<head>
		<!-- Verwendete CSS Imports -->
		<link rel="stylesheet" href="../css/hauptbild.css">
		<link rel="stylesheet" href="../css/ansichtbietenkaufensuchen.css">
		<link rel="stylesheet" href="../css/dropdownNavBar.css">
		<link rel="stylesheet" href="../css/footer.css">
		
		<!-- Homepage Titel -->
		<title>sps-immobilien.de/Profil</title>
	</head>
	
<body>
	<header>
		<!-- Einfügen der Navigationsleiste -->
		<%@ include file="../jspf/navBarHauptbild.jspf"%>
	</header>
	<br>
	
<!-- Ist in der Session ein Benutzer gesetzt = eingeloggt, dann gibt alle eigenen Objekte des Benutzers aus -->
<c:if test="${benutzer != null}">
	<table>
		<tr>
		<td>
			<!-- Gibt jedes Element "eigeneobjekte" in einer Tabelle aus
			mit den dazugehörigen Daten -->
			<c:forEach var="haus" items="${eigeneobjekte}">
				<table class="objektansicht">
				<tr><td class="titel" colspan="3">${haus.titel}</td></tr>
				<!-- Verbindet 6 Zeilen miteinerander -->
				<tr><td rowspan="6"><img src="../kaufen_bild_servlet?id=${haus.id}" alt="Kein Bild vorhanden!" /></td>
					<td>Baujahr: </td><td>${haus.baujahr}</td></tr>
				<tr><td>Wohnfläche: </td><td>${haus.wohnflaeche} m²</td></tr>
				<tr><td>Grundstück: </td><td>${haus.grundstuecksflaeche} m²</td></tr>
				<tr><td>Standort: </td><td>${haus.standort}</td></tr>
				<tr><td>Angebotsende: </td><td>${haus.datum}</td></tr>
				<tr><td>Aktuelles Gebot: </td><td>${haus.startgebot} €</td></tr>
				<!-- Verbindert 3 Spalten miteinander -->
				<tr><td colspan="3"><hr></td></tr>
				</table>
				<br>				
			</c:forEach>
		</td>
		</tr>
	</table>
</c:if>

<!-- Einbindung der Fußzeile -->
<%@ include file="../jspf/fußzeile.jspf"%>
</body>
</html>