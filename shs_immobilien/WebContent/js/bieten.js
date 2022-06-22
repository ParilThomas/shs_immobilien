"use strict";

document.addEventListener("DOMContentLoaded", init);

function init() {		
	var form = document.getElementById("bietenForm");
	form.addEventListener("submit", checkGebot);
}



function checkGebot(evt) {

	var gebot = document.getElementById("gebot").value;
	
	if (gebot > 100000000) {
		alert("Das maximal zulässige Gebot liegt bei 100.000.000€!");
		evt.preventDefault();
	}
}