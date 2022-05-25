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
<meta charset="UTF-8">
<link rel="stylesheet" href="../css/kaufen.css">
<link rel="stylesheet" href="../css/dropdownNavBar.css">
<title>Insert title here</title>
</head>
<body>
	<header>
	<p class="willkommen"></p>
	</header>
	<%@ include file="../jspf/allgbutton.jspf"%>
	<br>

	<c:if test="${email != null}">
	<table class="tabelle">
	<tr>
	<td>
				<c:forEach var="haus" items="${eigeneobjekte}">
				<table class="objekttabelle">
				<tr><td colspan="3">${haus.titel}</td></tr>
				<tr><td rowspan="6"><img src="../kaufen_bild_servlet?id=${haus.id}" alt="" /></td><td>Baujahr: </td><td>${haus.baujahr}</td></tr>
				<tr>			<td>Wohnfläche: </td><td>${haus.wohnflaeche} m²</td></tr>
				<tr>			<td>Grundstück: </td><td>${haus.grundstuecksflaeche} m²</td></tr>
				<tr>			<td>Standort: </td><td>${haus.standort}</td></tr>
				<tr>			<td>Angebotsende: </td><td>${haus.datum}</td></tr>
				<tr>			<td>Aktuelles Gebot: </td><td>${haus.startgebot} €</td></tr>
				</table>
				<br>				
				</c:forEach>
	</td>
	</tr>
	</table>

	</c:if>

</body>
</html>