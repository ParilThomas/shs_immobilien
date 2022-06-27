<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="immo.portal.bean.HaustypBean"%>
<%@page import="immo.portal.bean.ObjektBean"%>
<%@page import="immo.portal.servlets.VerkaufServlet"%>
<%@page import="immo.portal.servlets.BietenServlet"%>
<%@page import="immo.portal.servlets.LogoutServlet"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="../css/hauptbild.css">
	<link rel="stylesheet" href="../css/ansichtbietenkaufensuchen.css">
	<link rel="stylesheet" href="../css/dropdownNavBar.css">
	<link rel="stylesheet" href="../css/footer.css">

	<script src="../js/bieten.js"></script>

	<title>sps-immobilien.de/Kaufen/Details</title>
</head>

<body>
	<header>
		<%@ include file="../jspf/navBarHauptbild.jspf"%>
	</header>
<main>

<div>

<table class="bietentabelle">
<tr>
	<td>
		<%@ include file="../jspf/hausauswahl.jspf"%>
	</td>
<td>
	
<form id="bietenForm" action="../GebotServlet" method=post>
	<c:forEach var="bieten" items="${objekt}">
		<p class="titel">${bieten.titel}</p>
		<img src="../kaufen_bild_servlet?id=${bieten.id}"></img>
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
		
		<c:if test="${benutzer != null}">
			<label for="startgebot">Ihr Gebot (€):</label><br>
				<input type="number" id="gebot" name="gebot" placeholder="Ihr Gebot" maxlength="9"/><br>
				<input type="hidden" name="benutzer" value="${benutzer.id}"/><br>
				<button class="buttonpointer" type="submit" name="gebot_absenden" value="${bieten.id}" onclick="myFunction()">Absenden</button>
		</c:if>

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
<%@ include file="../jspf/footer.jspf"%>
</body>
</html>