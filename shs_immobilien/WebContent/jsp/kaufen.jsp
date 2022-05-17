<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.ArrayList"%>
<%@page import="immo.portal.bean.HaustypBean"%>
<%@page import="immo.portal.bean.ObjektBean"%>
<%@page import="immo.portal.servlets.KaufenServlet"%>
<!DOCTYPE html>
<html class="html_hintergrund">

<head>
<meta charset="UTF-8">

<!-- Stylesheet Test TP-->
<link rel="stylesheet" href="../css/kaufen.css">

<title>sps-immobilien.de/verkaufen</title>

</head>
<body>
	<header>
		<p class="willkommen"></p>
	</header>
	
	<input class="zurueck" type="button" value="Zurück" onclick="location.href = '../html/homepage.html'"></input>
	<c:if test="${haustypSelektiert == false}">
		<form action="../KaufenServlet" method=post>

				<h1>Wählen Sie Ihren gewünschten Haustyp!</h1>
					<c:forEach items="${haustyplist}" var="haustyp">
						<button type="submit" class="button" name="${haustyp.typ}">${haustyp.typ}</button>
					</c:forEach>
		</form>
	
	</c:if>
	<c:if test="${haustypSelektiert == true}">
		<table class="tabelle">
			<thead>
				<tr>
					<th>Bild</th>
					<th>Haustyp</th>
					<th>Bautyp</th>
					<th>Titel</th>
					<th>Baujahr</th>
					<th>Wohn m²</th>
					<th>Grund m²</th>
					<th>Ort</th>
					<th>Ende der Auktion</th>
					<th>Akt. Preis</th>
					<th>Beschreibung</th>
					<th>Angebot abgeben</th>	
				</tr>
			</thead>
			<tbody>
				<c:forEach var="haus" items="${objekte}">
					<tr>
						<td><img src="../kaufen_bild_servlet?id=${haus.id}"></img></td> 
						<td>${haus.haustyp}</td>
						<td>${haus.bautyp}</td>
						<td>${haus.titel}</td>
						<td>${haus.baujahr}</td>
						<td>${haus.wohnflaeche}</td>
						<td>${haus.grundstuecksflaeche}</td>
						<td>${haus.standort}</td>
 						<td>${haus.datum}</td>
						<td>${haus.startgebot}</td>
						<td>${haus.beschreibung}</td>
						<td>
<!-- 						<label for="startgebot">Ihr Gebot (€):</label><br> <input -->
<!-- 							type="number" id="startgebot" name="gebot" placeholder="Ihr Gebot"/> -->
							<form action="../BietenServlet" method=post>
							<button type="submit" name="hausid=${haus.id}" value="${haus.id}">Details</button>
							</form>
						</td>
  					
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
	

	</body>
</html>

