/** @author Thomas Schwarzmeier */

/**
Alle Variablen müssen deklariert werden Fehler werden in der Konsole ausgegeben,
da JS diese sonst übergehen würde
*/
"use strict";

/**
Eventhandler
*/
document.addEventListener("DOMContentLoaded", init);

function init() {

	document.getElementById("mail").value = getCookie("sps-username");
	var loginform = document.getElementById("login-form");

	loginform.addEventListener("submit", checkLogin);

	/**
	Überprüft ob die Checkbox "remember" aktiv ist, falls ja
	setze den Cookie sps-username mit dem Wert der e-mail
	
	ansonsten setze einen leeren String
	*/
}
function checkLogin() {

	if (document.getElementById("remember").checked) {
		setCookie("sps-username", document.getElementById("mail").value);
	} else {
		setCookie("sps-username", "");
	}
}


// Cookie Funktion eingefügt von w3schools
// https://www.w3schools.com/js/js_cookies.asp

/**
cname = Name des Cookies
cvalue = Wert des Cookies
exdays = Anzahl der Tage, bis das Cookie ablaufen soll

Funktion setzt ein Cookie, indem der cname, cvalue und exdays addiert(zusammengefügt) werden
*/
function setCookie(cname, cvalue, exdays = 7) {
	const d = new Date();
	d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
	let expires = "expires=" + d.toUTCString();
	document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}

/**
cname = Cookiename als Parameter
*/
function getCookie(cname) {
	/**
	Variable name nach der gesucht werden soll
	*/
	let name = cname + "=";
	/**
Decodieren des Strings, um Cookies mit Sonderzeichen zu behandeln
Array ca wird durchlaufen und liest jeden Wert aus c = ca[i]
*/
	let ca = document.cookie.split(';');
	for (let i = 0; i < ca.length; i++) {
		let c = ca[i];
		while (c.charAt(0) == ' ') {
			c = c.substring(1);
		}
		/**
		Wird ein Cookie gefunden, gib den Wert des Cookies zurück
		*/
		if (c.indexOf(name) == 0) {
			return c.substring(name.length, c.length);
		}
	}
	/**
	 Wird kein Cookie gefunden gib "" zurück
	 */
	return "";
}