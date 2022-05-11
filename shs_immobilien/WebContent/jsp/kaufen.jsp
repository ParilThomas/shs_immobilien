<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html class="html_hintergrund">
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../css/kaufen.css">
<title>Kaufübersicht</title>
</head>
<body>
	<header>
	<p class="willkommen"></p>
		<h1>Objektübersicht</h1>
		<form>
			<div>
				<input class="zurueck" type="button" value="Zurück" onclick="location.href = '../html/kaufen.html'">
			</div>
		</form>
	</header>
	<main>


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
<!-- 					<th>Ende der Auktion</th> -->
					<th>Akt. Preis</th>
					<th>Beschreibung</th>
					<th>Angebot abgeben</th>
					
				</tr>
			</thead>
			<tbody>
				<c:forEach var="haus" items="${kform}">
					<tr>
						<td><img src="../kaufen_bild_servlet?haustyp=${haus.haustyp}"></td> 
						<td>${haus.haustyp}</td>
						<td>${haus.bautyp}</td>
						<td>${haus.titel}</td>
						<td>${haus.baujahr}</td>
						<td>${haus.wohnflaeche}</td>
						<td>${haus.grundstuecksflaeche}</td>
						<td>${haus.standort}</td>
<%-- 						<td>${haus.datum}</td> --%>
						<td>${haus.startgebot}</td>
						<td>${haus.beschreibung}</td>
						<td><label for="startgebot">Ihr Gebot (€):</label><br> <input
							type="number" id="startgebot" name="startgebot" placeholder="Ihr Gebot"/>
							<button type="submit" name="absenden" value="absenden">Gebot absenden</button>
						</td>
  					
					</tr>
				</c:forEach>






			</tbody>

		</table>



	</main>

</body>
</html>