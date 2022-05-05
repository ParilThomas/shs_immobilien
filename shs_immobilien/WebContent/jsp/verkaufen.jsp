<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Verkaufen</title>
</head>
<body>
	<header>
		<h1>Verkauf:</h1>
	</header>
	<main>
		<h2>Ihre Verkaufseingaben:</h2>
		<br>
		<b>Haustyp:</b>${vform.haustyp} <br>
		<b>Bautyp: </b>${vform.bautyp} <br>
		<b>Titel: </b>${vform.titel} <br>
		<b>Baujahr: </b>${vform.baujahr} <br>
		<b>Wohnfläche:</b>${vform.wohnflaeche} <br>
		<b>Grundstücksfläche: </b>${vform.grundstuecksflaeche} <br>
		<b>Standort: </b>${vform.standort} <br>
		<b>Startgebot: </b>${vform.startgebot} <br>
		<b>Beschreibung: </b>${vform.beschreibung} <br>
		<b>Dateiname: </b>${vform.dateiname} <br>

	</main>




</body>
</html>