"use strict";

document.addEventListener("DOMContentLoaded", init);

function init() {		
	var form = document.getElementById("myForm");
	form.addEventListener("submit", checkPasswort);
}

function checkPasswort(evt) {

	var passwort1 = document.getElementById("passwort1").value;
	var passwort2 = document.getElementById("passwort2").value;

	if (passwort1 != passwort2) {
		alert("Passwort ist Falsch!! Bitte wiederholen");
		evt.preventDefault();
	}
}
