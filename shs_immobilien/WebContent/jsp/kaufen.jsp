<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../css/kaufen.css">
<title>Kaufübersicht</title>
</head>
<body>
	<header>
		<h1>Kaufübersicht</h1>
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
					<th>Wohnfläche</th>
					<th>Grundstücksfläche</th>
					<th>Standort</th>
					<th>Startgebot</th>
					<th>Beschreibung</th>
					
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
						<td>${haus.startgebot}</td>
						<td>${haus.beschreibung}</td>
						
  					
					</tr>
				</c:forEach>






			</tbody>

		</table>



	</main>

</body>
</html>