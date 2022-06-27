package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import immo.portal.bean.BautypBean;

public class BautypData {

	private DataSource dataSource;
	
	/**
	 * Konstruktor speichert die dataSource
	 */
	public BautypData(DataSource dataSource) {
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
	 *@Rückgabetyp List<> - gibt eine Liste von Objekten zurück
	 */
	public List<BautypBean> alleBautypen() {
		/**
		 * Erzeugen einer ArrayList
		 */
		List<BautypBean> bautypen = new ArrayList<>();	
		/**
		 * Datenbankverbindung erstellen
		 */
		try(Connection dbVerbindung = dataSource.getConnection();) {	
			/**
			 * ResultSet		 -> Behinhaltet gefundene Datenbankeinträge
			 * createStatement() -> Erstellt einen SQL-Befehl
			 * 
			 * Aufruf der Datenbankverbindung mit einem SQL-Befehl
			 * Select * 		-> Wählt alle Spalten in der Datenbank aus
			 * FROM bautyp 	-> legt die zu verwendende DB Tabelle fest "bautyp"
			 * ORDER BY typ	-> sortiert Alphabetisch von A - Z nach dem "typ"
			 * 
			 * executeQuery		 -> Beendet den SQL-Befehl
			 */
			ResultSet resultSet = dbVerbindung.createStatement().executeQuery("SELECT * FROM bautyp ORDER BY typ");
			/**
			 * Mit "while" wird durch den resultSet interiert mit "next()" gelangt man
			 * auf das 1. Element vom resultSet.
			 * 
			 * Der Liste "bautypen" wird für jedes Element des resultSets ein neues
			 * Objekt mit dessen Variablen hinzugefügt "add"
			 */
			while (resultSet.next()) {	
				bautypen.add(new BautypBean(
						resultSet.getInt("id"),
						resultSet.getString("typ")
				));
			}
		} 
		/**
		 * catch fägt die Fehler bei der Ausführung der "try" Anweisungen
		 */
		catch (Exception e) {
			e.printStackTrace();
		}
		/**
		 * Rückgabe der Liste "bautypen"
		 */
		return bautypen;
	}
	
	
	
	/**
	 * Methoden Beschreibung
	 * 
	 *@Sichtbarkeit public - In anderen Klassen verwendbar
	 *@Methodentyp nonstatic - Instanzmethode für ein bestimtmes Objekt
	 *
	 *@Methodenname istBautypVorhanden
	 *@Parameter bautyp - erwartet beim Aufruf einen String
	 *
	 *@Rückgabetyp boolean - True / False
	 */
	public boolean istBautypVorhanden(String bautyp) {
		/**
		 * Datenbankverbindung erstellen
		 */
		try(Connection dbVerbindung = dataSource.getConnection();) {
			/**
			 * Aufruf der Datenbankverbindung mit einem SQL-Befehl
			 * Select * 	-> Wählt alle Spalten in der Datenbank aus
			 * FROM bautyp 	-> legt die zu verwendende DB Tabelle fest "bautyp"
			 * WHERE typ	-> filtert den Datensatz nach der Spalte "typ"
			 * LIKE ? 		-> sucht in der Spalte anhand eines festgelegten Musters "?"
			 */
			PreparedStatement sqlBefehl = dbVerbindung.prepareStatement("SELECT * FROM bautyp WHERE typ LIKE ?");
			/**
			 * Setzen des Musters "LIKE ?" nachdem im sqlBefehl gesucht wird 
			 */
			sqlBefehl.setString(1, bautyp);	
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
			 * somit ist der Bautyp bereits vorhanden
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
		 * Gib false zurück sofern der Bautyp noch NICHT vorhanden ist
		 */
		return false;	
	}
	
	
	
	/**
	 * Methoden Beschreibung
	 * 
	 *@Sichtbarkeit public - In anderen Klassen verwendbar
	 *@Methodentyp nonstatic - Instanzmethode für ein bestimtmes Objekt
	 *
	 *@Methodenname neuenBautypHinzufuegen
	 *@Parameter bautyp - erwartet beim Aufruf einen String
	 *
	 *@Rückgabetyp void - es wird nichts zurückgegeben
	 */
	public void neuenBautypHinzufuegen(String bautyp) {
		/**
		 * Datenbankverbindung erstellen
		 */
		try(Connection dbVerbindung = dataSource.getConnection();) {
			/**
			 * Aufruf der Datenbankverbindung mit einem SQL-Befehl
			 * INSTER INTO 	-> Speichert den Wert in der Tabelle "bautyp" in der Spalte "typ"
			 * VALUES ? 	-> Wert der mit dem Methodenaufruf übergeben wird
			 */
			PreparedStatement sqlBefehl = dbVerbindung.prepareStatement("INSERT INTO bautyp (typ) VALUES (?)");
			/**
			 * Setzen des Wertes "VALUES (?)" der in die DB geschrieben werden soll
			 */
			sqlBefehl.setString(1, bautyp);
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
