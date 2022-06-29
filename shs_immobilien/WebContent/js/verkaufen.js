/** @author Thomas Paril */

/**
Alle Variablen müssen deklariert werden Fehler werden in der Konsole ausgegeben,
da JS diese sonst übergehen würde
*/
"use strict";

/**
Event wird bereits gefeuert sobad der Paser den HTML Code eingelesen hat
und der komplette DOM Baum geladen ist

Ruft die Funktion init auf
Ruft die Funktion reset auf
*/
document.addEventListener("DOMContentLoaded", init);
document.addEventListener("DOMContentLoaded", reset)

function init() {	
	/**
	Speichern der Form
	*/		
	var form = document.getElementById("verkaufForm");
	/**
	Funktion "checkBaujahr" wird aufgerufen sobald der submit Button gedrückt wurde
	Funktion "checkWohnflaeche" wird aufgerufen sobald der submit Button gedrückt wurde
	Funktion "checkGrundflaeche" wird aufgerufen sobald der submit Button gedrückt wurde
	Funktion "checkStartgebot" wird aufgerufen sobald der submit Button gedrückt wurde
	Funktion "checkDatum" wird aufgerufen sobald der submit Button gedrückt wurde
	*/
	form.addEventListener("submit", checkBaujahr);
	form.addEventListener("submit", checkWohnflaeche);
	form.addEventListener("submit", checkGrundflaeche);
	form.addEventListener("submit", checkStartgebot);
	form.addEventListener("submit", checkDatum);
	/**
	Funktion "checkReset" wird aufgerufen sobald der reset Button gedrückt wurde
	*/
	form.addEventListener("reset", checkReset);
}


/**
Setzt die Felder zurück
*/
function checkReset(evt){
	/**
	Sicherheitsfrage ob man die Felder wirklich zurücksetzen möchte
	*/
	var wirklichReset = confirm("Wollen Sie wirklich ihre Eingabe zurücksetzen?");
	/**
	Wenn man nicht zurücksetzen möchte
	*/
	if(!wirklichReset){
		/**
		Übernimmt im DOM die Aufgabe des Aufrufs "return false"
		verhindert dadurch die ursprüngliche Aktion des Browsers
		*/	
		evt.preventDefault();
	}
	
}


/**
Überprüft ob das Baujahr in der Zukunft liegt
*/
function checkBaujahr(evt) {
	var aktuellesJahr = new Date();
	var jahr = aktuellesJahr.getFullYear();
	var baujahr = document.getElementById("baujahr").value;

	/**
	Ist das eingegebene Baujahr größer als das aktuelle jahr wirf einen Alert
	*/
	if (baujahr > jahr) {
		alert("Das Baujahr liegt in der Zukunft!");
		/**
		Übernimmt im DOM die Aufgabe des Aufrufs "return false"
		verhindert dadurch die ursprüngliche Aktion des Browsers
		*/	
		evt.preventDefault();
	}
}

/**
Überprüft ob das Datum in der Vergangenheit liegt
*/
function checkDatum(evt){
	
	var eingDatum = document.getElementById("datum").value;
	
	var heute = new Date();
	var dd = heute.getDate();
	var mm = heute.getMonth() + 1;
	var yyyy = heute.getFullYear();
    
    /**
	Fügt beim Tag und Monat eine vorangestellte 0 hinzu wenn der Tag oder Monat kleier als 10 ist
	*/
    if (dd < 10){
		dd = "0" + dd;
	}

	if (mm < 10){
		mm = "0" + mm;
	}
    
    /**
	Setzt das heutige Systemdatum zusammen
	*/
	heute = yyyy + '-' + mm + '-' + dd;
	/**
	Überprüft ob das eingegebene Datum kleiner ist als das heutige Datum falls ja wirf einen Alert
	*/
	if(eingDatum < heute){
		alert("Bitte ein Datum wählen, das nicht in der Vergangenheit liegt!");
		/**
		Übernimmt im DOM die Aufgabe des Aufrufs "return false"
		verhindert dadurch die ursprüngliche Aktion des Browsers
		*/	
		evt.preventDefault();	
	}
}


/**
Überprüft ob die Wohnfläche kleiner ist als 0 und wirft einen Alert
*/
function checkWohnflaeche(evt) {

	var wohnflaeche = document.getElementById("wohnflaeche").value;

	if (wohnflaeche < 0) {
		alert("Die Wohnfläche kann nicht kleiner als 0 sein!");
		/**
		Übernimmt im DOM die Aufgabe des Aufrufs "return false"
		verhindert dadurch die ursprüngliche Aktion des Browsers
		*/	
		evt.preventDefault();
	}
}


/**
Überprüft ob die Grundfläche kleiner ist als 0 und wirft einen Alert
*/
function checkGrundflaeche(evt) {

	var grundstuecksflaeche = document.getElementById("grundstuecksflaeche").value;

	if (grundstuecksflaeche < 0) {
		alert("Die Grundstücksfläche kann nicht kleiner als 0 sein!");
		/**
		Übernimmt im DOM die Aufgabe des Aufrufs "return false"
		verhindert dadurch die ursprüngliche Aktion des Browsers
		*/	
		evt.preventDefault();
	}
}


/**
Überprüft ob das Startgebot größer ist als 100.000.000€ und wirft einen Alert
*/
function checkStartgebot(evt) {

	var startgebot = document.getElementById("startgebot").value;

	if (startgebot > 100000000) {
		alert("Das maximal zulässige Gebot liegt bei 100.000.000€!");
		/**
		Übernimmt im DOM die Aufgabe des Aufrufs "return false"
		verhindert dadurch die ursprüngliche Aktion des Browsers
		*/	
		evt.preventDefault();
	}
}


