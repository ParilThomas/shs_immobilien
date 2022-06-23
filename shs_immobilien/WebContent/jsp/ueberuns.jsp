<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="immo.portal.servlets.LogoutServlet"%>

<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="../css/hauptbild.css">
	<link rel="stylesheet" href="../css/ueberuns.css">
	<link rel="stylesheet" href="../css/dropdownNavBar.css">
	<link rel="stylesheet" href="../css/footer.css">

	<title>sps-immobilien.de/Über-Uns</title>
</head>

<body>
	<header>
		<%@ include file="../jspf/navBarHauptbild.jspf"%>
	</header>

<main>
	<br>
	<h2>Ihr Ansprechpartner rund um Immoblilien</h2>
		<p class="infotext">
			Wir unterstützen Sie rund um das Thema Immoblie. Kontaktieren Sie uns
			über E-Mail,<br> rufen Sie uns an oder registrieren Sie sich
			kostenlos um ihre Immobilie zu inserieren<br> - wir sind gerne
			für Sie da. Ihr SPS-Team freut sich auf Sie!
		</p>
		<br>
	<hr class="trenner">
		<br>
		<br>
	<table class="table">
		<thead>
			<tr>
				<th class="profilthomass"></th>
				<th class="th2"></th>
				<th class="profilthomasp"></th>
				<th class="th2"></th>
				<th class="profilsimons"></th>
			</tr>
		</thead>
		
		<tbody>
			<tr>
				<th>Thomas Schwarzmeier</th>
				<th class="th2"></th>
				<th>Thomas Paril</th>
				<th class="th2"></th>
				<th>Simon Schrödl</th>
			</tr>
			
			<tr>
				<td>Immobilienfachwirt<br></td>
				<td class="th2"></td>
				<td>Immobiliengutachter<br></td>
				<td class="th2"></td>
				<td>Immobilienmarkler<br></td>
			</tr>
			
			<tr>
				<td><br>Telefon:<br> 08442-4563-1</td>
				<td class="th2"></td>
				<td><br>Telefon:<br> 08442-4563-2</td>
				<td class="th2"></td>
				<td><br>Telefon:<br> 08442-4563-3</td>
			</tr>
			
			<tr>
				<td>Mobil:<br> 0175-15455717</td>
				<td class="th2"></td>
				<td>Mobil:<br> 0157-16487975</td>
				<td class="th2"></td>
				<td>Mobil:<br> 0160-21173579</td>
			</tr>
			
			<tr>
				<td>E-Mail:<br> t.schwarzm@sps.com</td>
				<td class="th2"></td>
				<td>E-Mail:<br> t.paril@sps.com</td>
				<td class="th2"></td>
				<td>E-Mail:<br> s.schrödl@sps.com</td>
			</tr>
		</tbody>
	</table>
</main>
	
<%@ include file="../jspf/footer.jspf"%>
</body>
</html>