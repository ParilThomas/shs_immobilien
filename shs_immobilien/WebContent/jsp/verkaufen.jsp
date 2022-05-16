<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>
<%@page import="immo.portal.bean.HaustypBean"%>
<%@page import="immo.portal.servlets.VerkaufServlet"%>
<!DOCTYPE html>
<html class="html_hintergrund">

<head>
<meta charset="UTF-8">

<!-- Stylesheet Test TP-->
<link rel="stylesheet" href="../css/verkaufen.css">

<title>sps-immobilien.de/verkaufen</title>

<script>
	function check(browser) {
		document.getElementById("answer").value = browser;
	}
</script>
</head>

<body>
	<header>
		<p class="willkommen"></p>

		<div class="center">
			<h1>Verkaufsformular</h1>
		</div>

	</header>
	<div class="center">
	<!-- Bautyp check ob schon vorhanden -->
	<c:if test="${bautypExistiert == true}"><h1 class="fehlerbutton">Bautyp existiert bereits!</h1></c:if>
	</div>
	<div class="center">
	<!-- Bautyp check ob schon vorhanden -->
	<c:if test="${haustypExistiert == true}"><h1 class="fehlerbutton">Haustyp existiert bereits!</h1></c:if>
	</div>
	<div class="verkaufsformular">
		<br>
		<form action="../VerkaufServlet" method=post accept-charset="utf-8"
			enctype="multipart/form-data">

			<p>
			<!-- In haustpy ist die Haustyp_Bean; haustyplist Arraylist aus Methode alleHaustypen() gespeichert -->
				<label for="typ">Haustyp</label><br>
				<select name="haustyp" id="typ">
					<c:forEach items="${haustyplist}" var="haustyp">
						<option name="${haustyp.typ}" value="${haustyp.id}">${haustyp.typ}</option>
					</c:forEach>

				</select><br><br>
				
			<!-- In bautyp ist die Bautyp_Bean; bautyplist Arraylist aus Methode alleBautypen() gespeichert -->
				<label for="bautyp">Bautyp:</label><br> 
				<select name="bautyp" id="typ">
					<c:forEach items="${bautyplist}" var="bautyp">
						<option name="${bautyp.typ}" value="${bautyp.id}">${bautyp.typ}</option>
					</c:forEach>
				</select><br><br>
				
				<label for="titel">Titel:</label><br>
				<input type="text" id="titel" name="titel" placeholder="Objekttitel" required /><br><br>
			
				<label for="baujahr">Baujahr:</label><br>
				<input type="number" id="baujahr" name="baujahr" placeholder="Baujahr" required /><br><br>
			
				<label for="wohnflaeche">Wohnfl�che in m�:</label><br>
				<input type="number" id="wohnflaeche" name="wohnflaeche" placeholder="125" required /><br><br>

				<label for="grundstuecksflaeche">Grundst�ckfl�che in m�:</label><br>
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
				<input class="abbrechen" type="button" value="Abbrechen"
					onclick="location.href = '../html/homepage.html'">
			</p>
		</form>

		<hr class="trennung">
		<form action="../VerkaufServlet" method=post accept-charset="utf-8"
			enctype="multipart/form-data">
			<p>
				<label for="htyp_edit">Ihr Haustyp ist nicht dabei?</label><br>
				<input type="text" id="htyp_edit" name="htyp_edit"
					placeholder="Haustyp hinzuf�gen" />
				<button type="submit" name="htyp_edit_absenden" value="absenden">Absenden</button>
			</p>
		</form>

		<form action="../VerkaufServlet" method=post accept-charset="utf-8"
			enctype="multipart/form-data">
			<p>
				<label for="btyp_edit">Ihr Bautyp ist nicht dabei?</label><br>
				<input type="text" id="btyp_edit" name="btyp_edit"
					placeholder="Bautyp hinzuf�gen" />
				<button type="submit" name="btyp_edit_absenden" value="absenden">Absenden</button>
			</p>
		</form>

	</div>
</body>