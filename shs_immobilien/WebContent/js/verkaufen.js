"use strict";

document.addEventListener("DOMContentLoaded", init);

function init() {		
	var form = document.getElementById("verkaufForm");
	form.addEventListener("submit", checkBaujahr);
	form.addEventListener("submit", checkWohnflaeche);
	form.addEventListener("submit", checkGrundflaeche);
	form.addEventListener("submit", checkStartgebot);
}

function checkBaujahr(evt) {
	var aktuellesJahr = new Date();
	var jahr = aktuellesJahr.getFullYear();
	var baujahr = document.getElementById("baujahr").value;

	if (baujahr > jahr) {
		alert("Das Baujahr liegt in der Zukunft!");
		evt.preventDefault();
	}
}

function checkDatum(evt){
	
	var eingDatum = new Date(document.getElementById("datum"))
	
	var heute = new Date();
	var dd = today.getDate();
	var mm = today.getMonth() + 1;
	var yyyy = today.getFullYear();
    
	heute = yyyy + '-' + mm + '-' + dd;
	
	if(eingDatum < today){
		alert("Bitte ein Datum wählen, das nicht in der Vergangenheit liegt!");
		evt.preventDefault();	
	}
}

function checkWohnflaeche(evt) {

	var wohnflaeche = document.getElementById("wohnflaeche").value;

	if (wohnflaeche < 0) {
		alert("Die Wohnfläche kann nicht kleiner als 0 sein!");
		evt.preventDefault();
	}
}

function checkGrundflaeche(evt) {

	var grundstuecksflaeche = document.getElementById("grundstuecksflaeche").value;

	if (grundstuecksflaeche < 0) {
		alert("Die Grundstücksfläche kann nicht kleiner als 0 sein!");
		evt.preventDefault();
	}
}

function checkStartgebot(evt) {

	var startgebot = document.getElementById("startgebot").value;

	if (startgebot > 100000000) {
		alert("Das maximal zulässige Gebot liegt bei 100.000.000€!");
		evt.preventDefault();
	}
}


