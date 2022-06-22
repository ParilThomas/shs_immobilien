"use strict";

document.addEventListener("DOMContentLoaded", init);

function init() {		
	var form = document.getElementById("myForm");
	form.addEventListener("submit", checkPasswort);
	form.addEventListener("submit", checkPLZ);
	form.addEventListener("submit", checkTelefon);
}

function checkPasswort(evt) {

	var passwort1 = document.getElementById("passwort1").value;
	var passwort2 = document.getElementById("passwort2").value;

	if (passwort1 != passwort2) {
		alert("Passwort ist Falsch!! Bitte wiederholen");
		evt.preventDefault();
	}
}

function checkPLZ(evt) {

	var plz = document.getElementById("plz").value;

	if (plz < 0) {
		alert("Ungültige Postleitzahl!");
		evt.preventDefault();
	}
}

function checkTelefon(evt) {

	var telefon = document.getElementById("telefon").value;

	if (telefon < 0) {
		alert("Ungültige Telefonnummer!");
		evt.preventDefault();
	}
}