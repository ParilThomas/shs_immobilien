/** @author Simon Schrödl */

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
	var form = document.getElementById("registForm");
	/**
	Funktion "checkPasswort" wird aufgerufen sobald der submit Button gedrückt wurde
	*/
	form.addEventListener("submit", checkPasswort);
}

function checkPasswort(evt) {
	/**
	Speichert die beiden Values der eingegebenen Passwörter
	*/
	var passwort1 = document.getElementById("passwort").value;
	var passwortwdh = document.getElementById("passwortwdh").value;

	/**
	Sind die Passwörter nicht gleich wirf einen Alert
	*/
	if (passwort1 != passwortwdh) {
		alert("Passwort ist Falsch!! Bitte wiederholen");
		/**
		Übernimmt im DOM die Aufgabe des Aufrufs "return false"
		verhindert dadurch die ursprüngliche Aktion des Browsers
		*/	
		evt.preventDefault();
	}
}
