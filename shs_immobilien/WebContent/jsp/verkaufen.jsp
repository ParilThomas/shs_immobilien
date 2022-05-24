<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.ArrayList"%>
<%@page import="immo.portal.bean.HaustypBean"%>
<%@page import="immo.portal.servlets.VerkaufServlet"%>
<!DOCTYPE html>
<html class="html_hintergrund">

<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<!-- Stylesheet Test TP-->
<link rel="stylesheet" href="../css/verkaufen.css">
<link rel="stylesheet" href="../css/dropdownNavBar.css">

<title>sps-immobilien.de/Verkaufen</title>

<script>
	function check(browser) {
		document.getElementById("answer").value = browser;
	}
</script>
</head>

<body>
	<header>
		<p class="willkommen"></p>

		
	</header>
	<%@ include file="../jspf/allgbutton.jspf"%>
	<br>
<c:if test="${email != null}">	
	<div class="center">
	<!-- Bautyp check ob schon vorhanden -->
	<c:if test="${bautypExistiert == true}"><h1 class="fehlerbutton">Bautyp existiert bereits!</h1></c:if>
	</div>
	<div class="center">
	<!-- Haustyp check ob schon vorhanden -->
	<c:if test="${haustypExistiert == true}"><h1 class="fehlerbutton">Haustyp existiert bereits!</h1></c:if>
	</div>
	
	<div class="center">
			<h1>Anzeige Aufgeben</h1>
		</div>
		

	<div class="verkaufsformular">
		<br>
		
		<form action="../VerkaufServlet" method=post accept-charset="utf-8"	enctype="multipart/form-data">
			<p>
			<!-- 
			In haustpy ist die Haustyp_Bean; haustyplist Arraylist aus Methode alleHaustypen() gespeichert 
			value wird in DB gespeichert
			-->
				<label for="typ">Haustyp</label><br>
				<select name="haustyp" id="typ">
					<c:forEach items="${haustyplist}" var="haustyp">
						<option name="${haustyp.typ}" value="${haustyp.typ}">${haustyp.typ}</option>
					</c:forEach>
				</select><br><br>
				
			<!--
			In bautyp ist die Bautyp_Bean; bautyplist Arraylist aus Methode alleBautypen() gespeichert
			value wird in DB gespeichert
			-->
				<label for="bautyp">Bautyp:</label><br> 
				<select name="bautyp" id="typ">
					<c:forEach items="${bautyplist}" var="bautyp">
						<option name="${bautyp.typ}" value="${bautyp.typ}">${bautyp.typ}</option>
					</c:forEach>
				</select><br><br>
				
				<label for="titel">Titel:</label><br>
				<input type="text" id="titel" name="titel" placeholder="Objekttitel" required /><br><br>
			
				<label for="baujahr">Baujahr:</label><br>
				<input type="number" id="baujahr" name="baujahr" placeholder="Baujahr" required /><br><br>
			
				<label for="wohnflaeche">Wohnfläche in m²:</label><br>
				<input type="number" id="wohnflaeche" name="wohnflaeche" placeholder="125" required /><br><br>

				<label for="grundstuecksflaeche">Grundstückfläche in m²:</label><br>
				<input type="number" id="grundstuecksflaeche" name="grundstuecksflaeche" placeholder="250" required /><br><br>

				<label for="standort">Objektstandort:</label><br> 
				<input type="text" id="standort" name="standort" placeholder="Ort"	required /><br><br>

				<label for="datum">Ende der Auktion:</label><br> 
				<input type="date" id="datum" name="datum" placeholder="01.01.2023"	required /><br><br>

				<label for="startgebot">Startgebot in Euro:</label><br>
				<input type="number" id="startgebot" name="startgebot" placeholder="500000" required /><br><br>

				<label for="beschreibung">Objektbeschreibung</label><br>
				<textarea rows="5" cols="50" maxlength="500" name="beschreibung" required></textarea><br><br>

				<label for="bilder">Objektbilder:</label> <input type="file" id="bilder" name="bilder" accept="image/*" required /><br><br>

				<button type="submit" name="vformular_absenden" value="absenden">Absenden</button>
				<input class="abbrechen" type="button" value="Abbrechen" onclick="location.href = '../html/homepage.html'">
			</p>
		</form>

		<hr class="trennung">
		<form action="../VerkaufServlet" method=post accept-charset="utf-8"
			enctype="multipart/form-data">
			<p>
				<label for="htyp_edit">Ihr Haustyp ist nicht dabei?</label><br>
				<input type="text" id="htyp_edit" name="htyp_edit"
					placeholder="Haustyp hinzufügen" />
				<button type="submit" name="htyp_edit_absenden" value="absenden">Absenden</button>
			</p>
		</form>

		<form action="../VerkaufServlet" method=post accept-charset="utf-8"
			enctype="multipart/form-data">
			<p>
				<label for="btyp_edit">Ihr Bautyp ist nicht dabei?</label><br>
				<input type="text" id="btyp_edit" name="btyp_edit"
					placeholder="Bautyp hinzufügen" />
				<button type="submit" name="btyp_edit_absenden" value="absenden">Absenden</button>
			</p>
		</form>
	</div>
</c:if>
<c:if test="${email == null}">
Bitte loggen Sie sich ein um ein Gebot abzugeben!
<input class="abbrechen" type="button" value="Login" onclick="location.href = 'login.jsp'"/>
</c:if>
</body>