<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.ArrayList"%>
<%@page import="immo.portal.bean.HaustypBean"%>
<%@page import="immo.portal.servlets.LogoutServlet"%>
<%@page import="immo.portal.servlets.VerkaufServlet"%>

<!DOCTYPE html>
<html>

<head>
	<link rel="stylesheet" href="../css/hauptbild.css">
	<link rel="stylesheet" href="../css/verkaufen.css">
	<link rel="stylesheet" href="../css/dropdownNavBar.css">
	<link rel="stylesheet" href="../css/footer.css">

	<script src="../js/verkaufen.js"></script>

	<title>sps-immobilien.de/Verkaufen</title>
</head>

<body>
	<header>
		<%@ include file="../jspf/navBarHauptbild.jspf"%>
	</header>
		
<nav>
	<c:if test="${benutzer == null}">
		<table class="meldung">
			<tr>
				<td>Bitte loggen Sie sich ein um ein Gebot abzugeben!</td>
			</tr>
			
			<tr>
				<td>
					<a class="login" href="../LoginServlet">Login</a>
				</td>
			</tr>
		</table>
	</c:if>
</nav>
	
<main>
	<c:if test="${benutzer != null}">

		<c:if test="${bautypExistiert == true}">
			<h1 class="fehlerbutton">Bautyp existiert bereits!</h1>
		</c:if>


		<c:if test="${haustypExistiert == true}">
			<h1 class="fehlerbutton">Haustyp existiert bereits!</h1>
		</c:if>

		<div class="hintergrund">
			<div class="textfeld">
				<form id="verkaufForm" class="ansicht" action="../VerkaufServlet" method=post accept-charset="utf-8" enctype="multipart/form-data">
					<table>
						<tr>
							<td><label for="htyp">Haustyp</label><br>
								<select	name="haustyp" id="htyp">
									<c:forEach items="${haustyplist}" var="haustyp">
										<option id="${haustyp.typ}" value="${haustyp.typ}">${haustyp.typ}</option>
									</c:forEach>
								</select>
							</td>
							
							<td><label for="btyp">Bautyp</label><br>
								<select	name="bautyp" id="btyp">
									<c:forEach items="${bautyplist}" var="bautyp">
										<option id="${bautyp.typ}" value="${bautyp.typ}">${bautyp.typ}</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						
						<tr>
							<td colspan="2"><label for="titel">Titel</label><br>
							<input type="text" id="titel" name="titel" placeholder="Objekttitel" maxlength="100" required /><br><br>
							</td>
						</tr>
						
						<tr>
							<td><label for="baujahr">Baujahr</label><br>
								<input type="number" id="baujahr" name="baujahr" placeholder="Baujahr" max="9999" required /><br><br>
							</td>
							<td><label for="standort">Objektstandort</label><br>
								<input type="text" id="standort" name="standort" placeholder="Ort" maxlength="32" required /><br><br>
							</td>
						</tr>
						
						<tr>
							<td><label for="wohnflaeche">Wohnfläche</label><br>
								<input type="number" id="wohnflaeche" name="wohnflaeche" placeholder="125" max="9999" required /> m²<br><br>
							</td>
							<td><label for="grundstuecksflaeche">Grundstück</label><br>
								<input type="number" id="grundstuecksflaeche" name="grundstuecksflaeche" placeholder="250" max="999999" required /> m²<br><br>
							</td>
						</tr>
						
						<tr>
							<td><label for="datum2">Auktionsende</label><br>
								<input type="date" id="datum" name="datum" required /><br><br>
							</td>
							<td><label for="startgebot">Startgebot</label><br>
								<input type="number" id="startgebot" name="startgebot" placeholder="500000" max="999999999" required /> €<br><br>
							</td>
						</tr>

						<tr>
							<td colspan="2"><label for="beschreibung">Objektbeschreibung</label><br>
								<textarea rows="5" cols="50" maxlength="500" id="beschreibung" name="beschreibung" maxlength="500" required></textarea><br><br>
							</td>
						</tr>

						<tr>
							<td colspan="2"><label for="bilder">Objektbilder</label><br>
								<input type="file" id="bilder" name="bilder" accept="image/*" required /><br><br>
							</td>
						</tr>

						<tr>
							<td>
									<button class="abschicken" type="submit" name="vformular_absenden" value="${benutzer.id}">Absenden</button>
							</td>
							<td>
								<button class="abbrechen" type="submit" value="Abbrechen">Reset</button>
							</td>
						</tr>

					</table>
				</form>
				
				<form class="ansicht" action="../VerkaufServlet" method=post>
					<table>
						<tr>
							<td><label for="htyp_edit">Ihr Haustyp ist nicht dabei?</label><br> <input type="text" id="htyp_edit" name="htyp_edit" placeholder="Haustyp" maxlength="64"/><br><br>
								<button class="anlegenbutton" type="submit"	name="htyp_edit_absenden" value="absenden">Absenden</button></td>
							<td><label for="btyp_edit">Ihr Bautyp ist nicht	dabei?</label><br> <input type="text" id="btyp_edit" name="btyp_edit" placeholder="Bautyp" maxlength="64"/><br><br>
								<button class="anlegenbutton" type="submit"	name="btyp_edit_absenden" value="absenden">Absenden</button></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</c:if>
</main>

<%
	if (session.getAttribute("haustypExistiert") != null) {
		session.removeAttribute("haustypExistiert");
	}
	if (session.getAttribute("bautypExistiert") != null) {
		session.removeAttribute("bautypExistiert");
	}
%>
	
<%@ include file="../jspf/footer.jspf"%>
</body>
</html>