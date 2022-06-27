package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

public class RegistrierenData {
private DataSource dataSource;
	
	/**
 	* Konstruktor speichert die dataSource
 	*/
	public RegistrierenData(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	
	/**
	 * Methoden Beschreibung
	 * 
	 *@Sichtbarkeit public - In anderen Klassen verwendbar
	 *@Methodentyp nonstatic - Instanzmethode für ein bestimtmes Objekt
	 *
	 *@Methodenname verkaufFormularAbschicken
	 *@Parameter vorname	- erwartet beim Aufruf einen String
	 *@Parameter nachname 	- erwartet beim Aufruf einen String
	 *@Parameter anschrift	- erwartet beim Aufruf einen String
	 *@Parameter rplz 		- erwartet beim Aufruf einen String
	 *@Parameter wohnort 	- erwartet beim Aufruf einen String
	 *@Parameter rtelefon 	- erwartet beim Aufruf einen String
	 *@Parameter email 		- erwartet beim Aufruf einen String
	 *@Parameter passwort1 	- erwartet beim Aufruf einen String
	 *
	 *@Rückgabetyp void - es wird nichts zurückgegeben
	 */
	public void registrierenFormularabschicken(String vorname, String nachname, String anschrift,
			String rplz, String wohnort, String rtelefon, String email,	String passwort1) {
		try {
			/**
			 * Datenbankverbindung erstellen
			 */
			Connection dbVerbindung = dataSource.getConnection();
			/**
			 * Aufruf der Datenbankverbindung mit einem SQL-Befehl
			 * INSERT INTO benutzer 	-> Speichert den Wert in der Tabelle "benutzer" in den Spalten(...,...,...)
			 * VALUES (?,?,?,?,?,?,?,?) -> legt die entsprechenden Werte die abgelegt werden sollen fest
			 */
			PreparedStatement sqlBefehl = dbVerbindung.prepareStatement("INSERT INTO benutzer (vorname, nachname, anschrift, plz, wohnort, "
																				+ "telefon,email, passwort1) VALUES (?,?,?,?,?,?,?,?)");
			/**
			 * Setzen der ? Werte im SQL-Befehl der Reihe nach
			 */		
			sqlBefehl.setString(1, vorname);
			sqlBefehl.setString(2, nachname);
			sqlBefehl.setString(3, anschrift);
			sqlBefehl.setString(4, rplz);
			sqlBefehl.setString(5, wohnort);
			sqlBefehl.setString(6, rtelefon);
			sqlBefehl.setString(7, email);
			sqlBefehl.setString(8, passwort1);
			/**
			 * executeUpdate -> Beendet den SQL-Befehl
			 */
			sqlBefehl.executeUpdate();
		} 
		/**
		 * catch fägt die Fehler bei der Ausführung der "try" Anweisungen
		 */
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Methoden Beschreibung
	 * 
	 *@Sichtbarkeit public - In anderen Klassen verwendbar
	 *@Methodentyp nonstatic - Instanzmethode für ein bestimtmes Objekt
	 *
	 *@Methodenname emailVorhanden
	 *@Parameter email - erwartet beim Aufruf einen String
	 *
	 *@Rückgabetyp boolean - True / False
	 */
	public boolean emailVorhanden(String email) {
		try {
			/**
			 * Datenbankverbindung erstellen
			 */
			Connection dbVerbindung = dataSource.getConnection();
			/**
			 * Aufruf der Datenbankverbindung mit einem SQL-Befehl
			 * Select * 		-> Wählt alle Spalten in der Datenbank aus
			 * FROM benutzer 	-> legt die zu verwendende DB Tabelle fest "benutzer"
			 * WHERE email		-> filtert den Datensatz nach der Spalte "email"
			 * LIKE ? 			-> sucht in der Spalte anhand eines festgelegten Musters "?"
			 */
			PreparedStatement sqlBefehl = dbVerbindung.prepareStatement("SELECT * FROM benutzer WHERE email LIKE ?");
			/**
			 * Setzen der SQL-Werte ?
			 */
			sqlBefehl.setString(1, email);	
			/**
			 * ResultSet 	-> Behinhaltet gefundene Datenbankeinträge
			 * executeQuery -> Beendet den SQL-Befehl
			 */
			ResultSet resultSet = sqlBefehl.executeQuery();
			/**
			 * Mit "while" wird durch den resultSet interiert mit "next()" gelangt man
			 * auf das 1. Element vom resultSet.
			 * 
			 * Sobald er ein Objekt mit next() findet gibt "true" zurück 
			 * somit ist die E-Mail bereits vorhanden
			 */
			while (resultSet.next()) {
				return true;
			}		
		} 
		/**
		 * catch fägt die Fehler bei der Ausführung der "try" Anweisungen
		 */
		catch (Exception e) {
			e.printStackTrace();
		}
		/**
		 * Gib false zurück sofern die E-Mail noch NICHT vorhanden ist
		 */
		return false;	
	}
	
}