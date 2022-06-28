/**
Alle Variablen müssen deklariert werden Fehler werden in der Konsole ausgegeben,
da JS diese sonst übergehen würde
*/
"use strict";

/**
Event wird bereits gefeuert sobad der Paser den HTML Code eingelesen hat
und der komplette DOM Baum geladen ist

Ruft die Funktion init auf
*/
document.addEventListener("DOMContentLoaded", init);


function init() {		
	/**
	Speichern der Form
	*/
	var form = document.getElementById("bietenForm");
	/**
	Funktion "checkGebot" wird aufgerufen sobald der submit Button gedrückt wurde
	*/
	form.addEventListener("submit", checkGebot);
}


function checkGebot(evt) {
	/**
	Speichert den Value des Felds mit der ID "gebot"
	*/
	var gebot = document.getElementById("gebot").value;
	/**
	Ist das gebot zu groß wirf einen Alert
	*/
	if (gebot > 100000000) {
		alert("Das maximal zulässige Gebot liegt bei 100.000.000€!");
		/**
		Übernimmt im DOM die Aufgabe des Aufrufs "return false"
		verhindert dadurch die ursprüngliche Aktion des Browsers
		*/	
		evt.preventDefault();
	}
}