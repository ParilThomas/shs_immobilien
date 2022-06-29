/** @author Thomas Paril */

package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import immo.portal.bean.HaustypBean;

public class HaustypData {
	
	private DataSource dataSource;
	
	/**
	 * Konstruktor speichert die dataSource
	 */
	public HaustypData(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
	/**
	 * Methoden Beschreibung
	 * 
	 *@Sichtbarkeit public - In anderen Klassen verwendbar
	 *@Methodentyp nonstatic - Instanzmethode für ein bestimtmes Objekt
	 *
	 *@Methodenname alleHaustypen
	 *
	 *@Rückgabetyp List<> - es wird eine Liste zurückgegeben
	 *
	 *Methode gibt alle Haustypen aus der DB zurück
	 */
	public List<HaustypBean> alleHaustypen() {
		/**
		 * Erzeugen einer ArrayList
		 */
		List<HaustypBean> haustypen = new ArrayList<>();	
		/**
		 * Datenbankverbindung erstellen
		 * Verbindung wird nach Durchlauf geschlossen
		 */
		try(Connection dbVerbindung = dataSource.getConnection();){
			/**
			 * ResultSet		 -> Behinhaltet gefundene Datenbankeinträge
			 * createStatement() -> Erstellt einen SQL-Befehl
			 * 
			 * Aufruf der Datenbankverbindung mit einem SQL-Befehl
			 * Select * 		-> Wählt alle Spalten in der Datenbank aus
			 * FROM haustyp 	-> legt die zu verwendende DB Tabelle fest "haustyp"
			 * ORDER BY typ		-> sortiert Alphabetisch von A - Z nach dem "typ"
			 * 
			 * executeQuery		-> Beendet den SQL-Befehl
			 */
			ResultSet resultSet = dbVerbindung.createStatement().executeQuery("SELECT * FROM haustyp ORDER BY typ");
			/**
			 * Mit "while" wird durch den resultSet interiert mit "next()" gelangt man
			 * auf das 1. Element vom resultSet.
			 * 
			 * Der Liste "haustypen" wird für jedes Element des resultSets ein neues
			 * Objekt mit dessen Variablen hinzugefügt "add"
			 */
			while (resultSet.next()) {	
				haustypen.add(new HaustypBean(
						resultSet.getInt("id"),
						resultSet.getString("typ")
				));
			}
		}
		/**
		 * catch fängt die Fehler bei der Ausführung der "try" Anweisungen
		 */
		catch (Exception e) {
			e.printStackTrace();
		}
		/**
		 * Rückgabe der Liste "haustypen"
		 */
		return haustypen;
	}

	
	/**
	 * Methoden Beschreibung
	 * 
	 *@Sichtbarkeit public - In anderen Klassen verwendbar
	 *@Methodentyp nonstatic - Instanzmethode für ein bestimtmes Objekt
	 *
	 *@Methodenname istHaustypVorhanden
	 *@Parameter haustyp - erwartet beim Aufruf einen String
	 *
	 *@Rückgabetyp boolean - True / False
	 *
	 *Methode überprüft ob der eingegebene Haustyp bereits
	 *in der DB vorhanden ist
	 */
	public boolean istHaustypVorhanden(String haustyp) {
		/**
		 * Datenbankverbindung erstellen
		 * Verbindung wird nach Durchlauf geschlossen
		 */
		try(Connection dbVerbindung = dataSource.getConnection();){
			/**
			 * Aufruf der Datenbankverbindung mit einem SQL-Befehl
			 * Select * 	-> Wählt alle Spalten in der Datenbank aus
			 * FROM haustyp 	-> legt die zu verwendende DB Tabelle fest "haustyp"
			 * WHERE typ	-> filtert den Datensatz nach der Spalte "typ"
			 * LIKE ? 		-> sucht in der Spalte anhand eines festgelegten Musters "?"
			 */
			PreparedStatement sqlBefehl = dbVerbindung.prepareStatement("SELECT * FROM haustyp WHERE typ LIKE ?");
			/**
			 * Setzen der Werte im SQL-Befehl
			 */
			sqlBefehl.setString(1, haustyp);	
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
			 * somit ist der Haustyp bereits vorhanden
			 */
			while (resultSet.next()) {
				return true;
			}		
		}
		/**
		 * catch fängt die Fehler bei der Ausführung der "try" Anweisungen
		 */
		catch (Exception e) {
			e.printStackTrace();
		}
		/**
		 * Gib false zurück sofern der Haustyp noch NICHT vorhanden ist
		 */
		return false;	
	}
	
	
	/**
	 * Methoden Beschreibung
	 * 
	 *@Sichtbarkeit public - In anderen Klassen verwendbar
	 *@Methodentyp nonstatic - Instanzmethode für ein bestimtmes Objekt
	 *
	 *@Methodenname neuenHaustypHinzufuegen
	 *@Parameter haustyp - erwartet beim Aufruf einen String
	 *
	 *@Rückgabetyp void - es wird nichts zurückgegeben
	 */
	public void neuenHaustypHinzufuegen(String haustyp) {
		/**
		 * Datenbankverbindung erstellen
		 */
		try(Connection dbVerbindung = dataSource.getConnection();) {
			/**
			 * Aufruf der Datenbankverbindung mit einem SQL-Befehl
			 * INSTER INTO 	-> Speichert den Wert in der Tabelle "haustyp" in der Spalte "typ"
			 * VALUES ? 	-> Wert der mit dem Methodenaufruf übergeben wird
			 */
			PreparedStatement sqlBefehl = dbVerbindung.prepareStatement("INSERT INTO haustyp (typ) VALUES (?)");
			/**
			 * Setzen des Wertes "VALUES (?)" der in die DB geschrieben werden soll
			 */
			sqlBefehl.setString(1, haustyp);
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

}
