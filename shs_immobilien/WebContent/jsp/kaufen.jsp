<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.ArrayList"%>
<%@page import="immo.portal.bean.HaustypBean"%>
<%@page import="immo.portal.bean.ObjektBean"%>
<%@page import="immo.portal.servlets.KaufenServlet"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<%@ include file="../jspf/allgbutton.jspf"%>
	<br>
	
	
	
	<c:if test="${haustypSelektiert == false}">
	<form action="../KaufenServlet" method=post>	
	<div class="center">
	<c:if test="${GebotZuNiedrig == true}"><h1 class="gebotfehlermeldung">Ihr Gebot konnte nicht akzeptiert werden, da es unter dem aktuellen Höchstpreis liegt!</h1></c:if>
	</div>
	<div class="center">
	<c:if test="${GebotIstOk == true}"><h1 class="gebotakzeptiert">Herzlichen Glückwunsch Ihr Gebot wurde akzeptiert!</h1></c:if>
	</div>
	

					<c:forEach items="${haustyplist}" var="haustyp">
						<button type="submit" class="button" name="${haustyp.typ}"><span>${haustyp.typ}</span></button><br>
					</c:forEach>
		</form>
	</c:if>
	
	
	
	<c:if test="${haustypSelektiert == true}">
	<table class="tabelle">
	<tr>
	<td>
		<form action="../KaufenServlet" method=post>	
			<c:forEach items="${haustyplist}" var="haustyp">
				<button type="submit" class="button" name="${haustyp.typ}"><span>${haustyp.typ}</span></button><br>
			</c:forEach>
		</form>
	</td>
	<td>
		<form action="../BietenServlet" method=post>
				<c:forEach var="haus" items="${objekte}">
				<table class="objekttabelle">
				<tr><td colspan="3">${haus.titel}</td></tr>
				<tr><td rowspan="7"><img src="../kaufen_bild_servlet?id=${haus.id}"></img></td><td>Baujahr: </td><td>${haus.baujahr}</td></tr>
				<tr>			<td>Wohnfläche: </td><td>${haus.wohnflaeche} m²</td></tr>
				<tr>			<td>Grundstück: </td><td>${haus.grundstuecksflaeche} m²</td></tr>
				<tr>			<td>Standort: </td><td>${haus.standort}</td></tr>
				<tr>			<td>Angebotsende: </td><td>${haus.datum}</td></tr>
				<tr>			<td>Aktuelles Gebot: </td><td>${haus.startgebot} €</td></tr>
				<tr>			<td colspan="2" class="detailbutton"><Button type="submit" name="detailid" value="${haus.id}">Details ansehen</Button></td></tr>
				</table>
				<br>				
				</c:forEach>

	</form>
	</td>
	</tr>
	</table>

	</c:if>

	</body>
</html>

