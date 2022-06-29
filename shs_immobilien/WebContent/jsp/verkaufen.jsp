<!-- @author Thomas Paril, Thomas Schwarzmeier -->	

<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.ArrayList"%>
<%@page import="immo.portal.bean.HaustypBean"%>
<%@page import="immo.portal.servlets.LogoutServlet"%>
<%@page import="immo.portal.servlets.VerkaufServlet"%>

<!DOCTYPE html>
<html lang="de">

<head>
	<!-- Verwendete CSS Imports -->	
	<link rel="stylesheet" href="../css/hauptbild.css">
	<link rel="stylesheet" href="../css/verkaufen.css">
	<link rel="stylesheet" href="../css/dropdownNavBar.css">
	<link rel="stylesheet" href="../css/footer.css">

	<script src="../js/verkaufen.js"></script>
	
	<!-- Homepage Titel -->
	<title>sps-immobilien.de/Verkaufen</title>
</head>

<body>
	<header>
		<!-- Einbinden der Navigationsleite -->
		<%@ include file="../jspf/navBarHauptbild.jspf"%>
	</header>
		
<nav>
	<!-- Überprüfung ob ein Benutzer in der Session vorhanden ist = eingeloggt,
	falls null, Ausgabe sich bitte einzuloggen -->
	<c:if test="${benutzer == null}">
		<table class="einloggenmeldung">
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
	<!-- Überprüfung ob ein Benutzer in der Session vorhanden ist = eingeloggt,
	wenn nicht null Ausgabe der Verkaufenseite -->
	<c:if test="${benutzer != null}">
		
		<!-- Überprüfung ob die Sessionvariable bautypExistiert true ist,
		falls ja Fehlermeldung ausgeben -->
		<c:if test="${bautypExistiert == true}">
			<h1 class="fehlerbutton">Bautyp existiert bereits!!</h1>
		</c:if>

		<!-- Überprüfung ob die Sessionvariable haustypExistiert true ist,
		falls ja Fehlermeldung ausgeben -->
		<c:if test="${haustypExistiert == true}">
			<h1 class="fehlerbutton">Haustyp existiert bereits!!</h1>
		</c:if>

		<div class="hintergrund">
			<div class="verkaufsdaten">
				<!-- Form zum anlegen eines neuen Verkaufobjekts -->
				<form id="verkaufForm" class="verkaufformular" action="../VerkaufServlet" method=post accept-charset="utf-8" enctype="multipart/form-data">
					<table>
						<tr>
							<!-- Dropdownmenü für jeden Haustyp, Werte wird im Servlet bei Aufruf in der doGet gesetzt -->
							<td><label for="htyp">Haustyp</label><br>
								<select	name="haustyp" id="htyp">
									<c:forEach items="${haustyplist}" var="haustyp">
										<option id="${haustyp.typ}" value="${haustyp.typ}">${haustyp.typ}</option>
									</c:forEach>
								</select>
							</td>
							<!-- Dropdownmenü für jeden Bautyp, Werte wird im Servlet bei Aufruf in der doGet gesetzt -->
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
							<!-- Maximallänge des Textfelds 100 Zeichen synchron zur Datenbankgröße -->
							<input type="text" id="titel" name="titel" placeholder="Objekttitel" maxlength="100" required /><br><br>
							</td>
						</tr>
						
						<tr>
							<td><label for="baujahr">Baujahr</label><br>
								<!-- Es kann keine Zahl größer als 9999 eingegeben werdne, somit 4 Zeichen synchron zur Datenbankgröße -->
								<input type="number" id="baujahr" name="baujahr" placeholder="Baujahr" max="9999" required /><br><br>
							</td>
							<td><label for="standort">Objektstandort</label><br>
								<!-- Maximallänge des Textfelds 32 Zeichen synchron zur Datenbankgröße (32 Zeichen da der längste Ort in DE 32 Zeichen hat) -->
								<input type="text" id="standort" name="standort" placeholder="Ort" maxlength="32" required /><br><br>
							</td>
						</tr>
						
						<tr>
							<td><label for="wohnflaeche">Wohnfläche</label><br>
								<!-- Es kann keine Zahl größer als 9999 eingegeben werdne, somit 4 Zeichen synchron zur Datenbankgröße (max. realistische Wohnflächengröße) -->
								<input type="number" id="wohnflaeche" name="wohnflaeche" placeholder="125" max="9999" required /> m²<br><br>
							</td>
							<td><label for="grundstuecksflaeche">Grundstück</label><br>
								<!-- Es kann keine Zahl größer als 999999 eingegeben werdne, somit 6 Zeichen synchron zur Datenbankgröße (max. realistische Grundstücksgröße) -->
								<input type="number" id="grundstuecksflaeche" name="grundstuecksflaeche" placeholder="250" max="999999" required /> m²<br><br>
							</td>
						</tr>
						
						<tr>
							<td><label for="datum">Auktionsende</label><br>
								<input type="date" id="datum" name="datum" required /><br><br>
							</td>
							<td><label for="startgebot">Startgebot</label><br>
								<!-- Es kann keine Zahl größer als 999.999.999 eingegeben werdne, somit 9 Zeichen synchron zur Datenbankgröße (max. realistisches Gebot) -->
								<input type="number" id="startgebot" name="startgebot" placeholder="500000" max="999999999" required /> €<br><br>
							</td>
						</tr>

						<tr>
							<td colspan="2"><label for="beschreibung">Objektbeschreibung</label><br>
								<!-- Maximal 450 Zeichen mit 50 Zeichen Puffer in der Datenbank für Zeilenumbrüche etc. -->
								<textarea rows="5" cols="50" id="beschreibung" name="beschreibung" placeholder="Maximal 450 Zeichen" maxlength="450" required></textarea><br><br>
							</td>
						</tr>

						<tr>
							<td colspan="2"><label for="bilder">Objektbilder</label><br>
								<input class="buttonpointer" type="file" id="bilder" name="bilder" accept="image/*" required /><br><br>
							</td>
						</tr>

						<tr>
							<td>
									<button class="abschicken" type="submit" name="vformular_absenden" value="${benutzer.id}">Absenden</button>
							</td>
							<td>
								<button class="zurücksetzen" type="reset" value="Abbrechen">Reset</button>
							</td>
						</tr>

					</table>
				</form>
				
				<!-- From zum anlegen neuer Haus- oder Bautypen falls noch nicht vorhanden -->
				<form id="typAnlegenForm" class="verkaufformular" action="../VerkaufServlet" method=post>
					<table>
						<tr>
							<td><label for="htyp_edit">Ihr Haustyp ist nicht dabei?</label><br>
							<input type="text" id="htyp_edit" name="htyp_edit" placeholder="Haustyp" pattern="[0-9A-Za-zÄäÖöÜüß]{5,}" maxlength="64" title="Mind. 5 Zeichen: Leerzeichen & Sonderzeichen sind nicht erlaubt" /><br><br>
								<button class="anlegenbutton" type="submit"	name="htyp_edit_absenden" value="absenden">Absenden</button></td>
								
							<td><label for="btyp_edit">Ihr Bautyp ist nicht	dabei?</label><br>
							<input type="text" id="btyp_edit" name="btyp_edit" placeholder="Bautyp" pattern="[0-9A-Za-zÄäÖöÜüß]{5,}" maxlength="64" title="Mind. 5 Zeichen: Leerzeichen & Sonderzeichen sind nicht erlaubt"/><br><br>
								<button class="anlegenbutton" type="submit"	name="btyp_edit_absenden" value="absenden">Absenden</button></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</c:if>
</main>

<!-- Falls Sessionvariable gesetzt wird haustypExistiert & bautypExistiert am Ende zurückgesetzt -->
<%
	if (session.getAttribute("haustypExistiert") != null) {
		session.removeAttribute("haustypExistiert");
	}
	if (session.getAttribute("bautypExistiert") != null) {
		session.removeAttribute("bautypExistiert");
	}
%>

<!-- Einbindung der Fußzeile -->	
<%@ include file="../jspf/fußzeile.jspf"%>
</body>
</html>