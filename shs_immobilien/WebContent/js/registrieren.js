"use strict";

document.addEventListener("DOMContentLoaded", init);

function init() {		
	var form = document.getElementById("registForm");
	form.addEventListener("submit", checkPasswort);
}

function checkPasswort(evt) {

	var passwort1 = document.getElementById("passwort").value;
	var passwortwdh = document.getElementById("passwortwdh").value;

	if (passwort1 != passwortwdh) {
		alert("Passwort ist Falsch!! Bitte wiederholen");
		evt.preventDefault();
	}
}
