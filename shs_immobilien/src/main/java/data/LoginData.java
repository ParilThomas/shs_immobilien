package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import immo.portal.bean.BenutzerBean;

public class LoginData {

	private DataSource dataSource;

	/**
	 * Konstruktor speichert die dataSource
	 */
	public LoginData(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	
	/**
	 * Methoden Beschreibung
	 * 
	 *@Sichtbarkeit public - In anderen Klassen verwendbar
	 *@Methodentyp nonstatic - Instanzmethode für ein bestimtmes Objekt
	 *
	 *@Methodenname alleBautypen
	 *
	 *@Rückgabetyp BenutzerBean - gibt eine Liste von Objekten zurück
	 */
	public BenutzerBean holeBenutzer(String email, String passwort) {
		try {
			/**
			 * Datenbankverbindung erstellen
			 */
			Connection dbVerbindung = dataSource.getConnection();
			/**
			 * Aufruf der Datenbankverbindung mit einem SQL-Befehl
			 * Select * 			-> Wählt alle Spalten in der Datenbank aus
			 * FROM benutzer 		-> legt die zu verwendende DB Tabelle fest "benutzer"
			 * WHERE email = ?		-> filtert den Datensatz nach der Zeile in der die E-Mail = ? ist
			 * AND passwort1 = ? 	-> und zugleich das passwort1 = ? ist
			 */
			PreparedStatement sqlBefehl = dbVerbindung.prepareStatement("SELECT * FROM benutzer WHERE email = ? AND passwort1 = ?");
			/**
			 * Setzen der Werte im SQL-Befehl der Reihe nach
			 */
			sqlBefehl.setString(1, email);
			sqlBefehl.setString(2, passwort);
			/**
			 * ResultSet 	-> Behinhaltet gefundene Datenbankeinträge
			 * executeQuery -> Beendet den SQL-Befehl
			 */
			ResultSet resultSet = sqlBefehl.executeQuery();
			/**
			 * Mit "while" wird durch den resultSet interiert mit "next()" gelangt man
			 * auf das 1. Element vom resultSet.
			 * 
			 * Gibt ein BenutzerBean Objekt zurück mit den dazugehörigen Attributen
			 */
			while (resultSet.next()) {
				return new BenutzerBean(
					resultSet.getInt("id"),
					resultSet.getString("vorname"),
					resultSet.getString("nachname"),
					resultSet.getString("anschrift"),
					resultSet.getString("plz"),
					resultSet.getString("wohnort"),
					resultSet.getString("telefon"),
					resultSet.getString("email"),
					resultSet.getString("passwort1")
				);			
			}
		}
		/**
		 * catch fägt die Fehler bei der Ausführung der "try" Anweisungen
		 */
		catch (Exception e) {
			e.printStackTrace();
		}
		/**
		 * Ansonsten gib null zurück, wenn kein Benutzer mit passender E-Mail und Passwort Kombination
		 * gefunden werden konnte
		 */
		return null;
	}


	/**
	 * Methoden Beschreibung
	 * 
	 *@Sichtbarkeit public - In anderen Klassen verwendbar
	 *@Methodentyp nonstatic - Instanzmethode für ein bestimtmes Objekt
	 *
	 *@Methodenname istRegistriert
	 *@Parameter email - erwartet beim Aufruf einen String
	 *
	 *@Rückgabetyp boolean - True / False
	 */
	public boolean istRegistriert(String email) {	
		try {
			/**
			 * Datenbankverbindung erstellen
			 */
			Connection dbVerbindung = dataSource.getConnection();
			/**
			 * Aufruf der Datenbankverbindung mit einem SQL-Befehl
			 * Select * 	-> Wählt alle Spalten in der Datenbank aus
			 * FROM benutzer 	-> legt die zu verwendende DB Tabelle fest "benutzer"
			 * WHERE email	-> filtert den Datensatz nach der Spalte "email"
			 * LIKE ? 		-> sucht in der Spalte anhand eines festgelegten Musters "?"
			 */
			PreparedStatement sqlBefehl = dbVerbindung.prepareStatement("SELECT * FROM benutzer WHERE email = ?");
			/**
			 * Setzen der Werte im SQL-Befehl
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
			 * somit ist die eingegebene E-Mail bereits registriert
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
		 * Gib false zurück sofern die E-Mail noch nicht registriert ist
		 */
		return false;	
	}
	
}
