<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="immo.portal.bean.HaustypBean"%>
<%@page import="immo.portal.bean.ObjektBean"%>
<%@page import="immo.portal.servlets.KaufenServlet"%>
<%@page import="immo.portal.servlets.LogoutServlet"%>
<%@page import="immo.portal.servlets.AnsichtServlet"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		
		<link rel="stylesheet" href="../css/hauptbild.css">
	<link rel="stylesheet" href="../css/ansichtbietenkaufensuchen.css">
		<link rel="stylesheet" href="../css/dropdownNavBar.css">
		<link rel="stylesheet" href="../css/footer.css">
		
		<title>sps-immobilien.de/Übersicht</title>
	</head>
	
<body>
	<header>
		<%@ include file="../jspf/navBarHauptbild.jspf"%>
	</header>
	<br>

<c:if test="${benutzer != null}">
	<table class="tabelle">
		<tr>
		<td>
			<c:forEach var="haus" items="${eigeneobjekte}">
				<table class="objekttabelle">
				<tr><td class="titel" colspan="3">${haus.titel}</td></tr>
				<tr><td rowspan="6"><img src="../kaufen_bild_servlet?id=${haus.id}" alt="" /></td>
					<td>Baujahr: </td><td>${haus.baujahr}</td></tr>
				<tr><td>Wohnfläche: </td><td>${haus.wohnflaeche} m²</td></tr>
				<tr><td>Grundstück: </td><td>${haus.grundstuecksflaeche} m²</td></tr>
				<tr><td>Standort: </td><td>${haus.standort}</td></tr>
				<tr><td>Angebotsende: </td><td>${haus.datum}</td></tr>
				<tr><td>Aktuelles Gebot: </td><td>${haus.startgebot} €</td></tr>
				<tr><td colspan="3"><hr></td></tr>
				</table>
				<br>				
			</c:forEach>
		</td>
		</tr>
	</table>
</c:if>

<%@ include file="../jspf/footer.jspf"%>
</body>
</html>