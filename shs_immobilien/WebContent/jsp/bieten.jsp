<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="immo.portal.bean.HaustypBean"%>
<%@page import="immo.portal.bean.ObjektBean"%>
<%@page import="immo.portal.servlets.VerkaufServlet"%>
<%@page import="immo.portal.servlets.BietenServlet"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html class="html_hintergrund"><head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<!-- Stylesheet Test TP-->
<link rel="stylesheet" href="../css/verkaufen.css">

<title>Bietfunktion</title>
</head>
<body>
	<header>
		<p class="willkommen"></p>

		<div class="center">
			<h1>Details</h1>
		</div>

	</header>
	<main>

		<div class="verkaufsformular">
			<input class="zurueck" type="button" value="Zurück"
				onclick="location.href = 'kaufen.jsp'"></input>

			<form action="../BietenServlet" method=post>
				<c:forEach var="bieten" items="${objekt1}">
				<img src="../kaufen_bild_servlet?id=${bieten.id}"></img> 
				</c:forEach>
			<br>
			<br>	
				<label
					for="startgebot">Ihr Gebot (€):</label><br> <input
					type="number" id="startgebot" name="gebot" placeholder="Ihr Gebot"/> 


		</form>




		</div>
	</main>
</body>
</html>