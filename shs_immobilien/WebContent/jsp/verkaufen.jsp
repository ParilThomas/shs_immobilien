<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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
	
	<div class="center"><h1>Verkaufsformular</h1>
	</div>
	
	</header>
	<div class="verkaufsformular">
		<br>
		<form action ="list" method="post" accept-charset="utf-8"   enctype="multipart/form-data">
		
		<p>
		<select name="haustyp_bean">
			<c:forEach items="${listHaustyp_Bean}" var="haustyp_bean">
				<option value="${haustyp_bean.id}">${haustyp_bean.id}</option>
			</c:forEach>
			
		</select>
		</p>
		
		<p>
		<label for="bautyp">Bautyp:</label><br> <select name="bautyp"
				id="bautyp" size="4">
				<option value="Massivbau Fertighaus">Massivbau Fertighaus</option>
				<option value="Holz Fertighaus">Holz Fertighaus</option>
				<option value="Massivbauweise">Massivbauweise</option>
				<option value="Ytongbauweise">Ytongbauweise</option>
			</select>
		</p>
		
		<p>
			<label for="titel">Titel:</label><br> <input type="text"
					id="titel" name="titel" placeholder="Objekttitel" required />
		</p>
	
		<p>
			<label for="baujahr">Baujahr:</label><br> <input type="number"
					id="baujahr" name="baujahr" placeholder="Baujahr" required />
		</p>
	
		<p>
			<label for="wohnflaeche">Wohnfläche in m²:</label><br> <input
					type="number" id="wohnflaeche" name="wohnflaeche" placeholder="125"	required />
		</p>
	
		<p>
			<label for="grundstuecksflaeche">Grundstückfläche in m²:</label><br>
			<input type="number" id="grundstuecksflaeche"
					name="grundstuecksflaeche" placeholder="250" required />
		</p>
	
		<p>
			<label for="standort">Objektstandort:</label><br> <input
					type="text" id="standort" name="standort"
					placeholder="Ort" required />
		</p>
	
	
		<p>
			<label for="datum">Ende der Auktion:</label><br> <input
					type="date" id="datum" name="datum" placeholder="01.01.2023" required />
		<p>
			<label for="startgebot">Startgebot in Euro:</label><br> <input
					type="number" id="startgebot" name="startgebot" placeholder="500000" required />
		</p>
	
		<p>
			<label for="beschreibung">Objektbeschreibung</label>
			<textarea rows="5" cols="50" maxlength="500" name="beschreibung" required></textarea>
		</p>
	
		<p>
			<label for="bilder">Objektbilder:</label>
				<input type="file" id="bilder" name="bilder" accept="image/*" required />
		</p>
	
		<p>
			<button type="submit" name="vformular_absenden" value="absenden">Absenden</button>
			<input class="abbrechen" type="button" value="Abbrechen" onclick="location.href = '../html/homepage.html'">
		</p>
		

	
<hr class="trennung"> 

		<p>
			<label for="htyp_edit">Ihr Haustyp ist nicht dabei?</label><br> <input type="text"
					id="htyp_edit" name="htyp_edit" placeholder="Haustyp hinzufügen"/>
			<button type="submit" name="htyp_edit_absenden" value="absenden">Absenden</button>
		</p>
		
	</form>
	

		<p>
			<label for="btyp_edit">Ihr Bautyp ist nicht dabei?</label><br> <input type="text"
					id="btyp_edit" name="btyp_edit" placeholder="Bautyp hinzufügen"/>
			<button type="submit" name="btyp_edit_absenden" value="absenden">Absenden</button>
		</p>
	</form>
	
	</div>
</body>