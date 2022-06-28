package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import immo.portal.bean.ObjektBean;

public class BietenData {
	
private DataSource dataSource;
	
	/**
	 * Konstruktor speichert die dataSource
	 */
	public BietenData(DataSource dataSource) {
		this.dataSource = dataSource;	
	}


	/**
	 * Methoden Beschreibung
	 * 
	 *@Sichtbarkeit public - In anderen Klassen verwendbar
	 *@Methodentyp nonstatic - Instanzmethode für ein bestimtmes Objekt
	 *
	 *@Methodenname getObjekt
	 *@Parameter detailid - erwartet beim Aufruf einen String
	 *
	 *@Rückgabetyp List<> - gibt eine Liste von Objekten zurück
	 *
	 *Methode gibt ein Objekt aus der DB mit der gesuchen ID zurück
	 */
	public List<ObjektBean> getObjekt(String detailid) {
		/**
		 * Erzeugen einer ArrayList
		 */
		List<ObjektBean> objektIdDaten = new ArrayList<>();
		/**
		 * Datenbankverbindung erstellen
		 * Verbindung wird nach Durchlauf geschlossen
		 */
		try(Connection dbVerbindung = dataSource.getConnection();) {	
			/**
			 * Aufruf der Datenbankverbindung mit einem SQL-Befehl
			 * Select * 	-> Wählt alle Spalten in der Datenbank aus
			 * FROM objekte -> legt die zu verwendende DB Tabelle fest "objekte"
			 * WHERE id		-> filtert den Datensatz nach der Spalte "id"
			 * = ? 			-> sucht in der Spalte anhand eines festgelegten Musters "?"
			 */
			PreparedStatement sqlBefehl = dbVerbindung.prepareStatement("Select * FROM objekte WHERE id = ?");
			/**
			 * Setzen des Wertes "?" der in die DB geschrieben werden soll
			 */
			sqlBefehl.setString(1, detailid);	
			/**
			 * ResultSet 	-> Behinhaltet gefundene Datenbankeinträge
			 * executeQuery -> Beendet den SQL-Befehl
			 */
			ResultSet resultSet = sqlBefehl.executeQuery();
			/**
			 * Mit "while" wird durch den resultSet interiert mit "next()" gelangt man
			 * auf das 1. Element vom resultSet.
			 * 
			 * Der Liste "objektIdDaten" wird für jedes Element des resultSets ein neues
			 * Objekt mit dessen Variablen hinzugefügt "add"
			 */
			while (resultSet.next()) {
				objektIdDaten.add(new ObjektBean(
					resultSet.getLong("id"),
					resultSet.getString("haustyp"),
					resultSet.getString("bautyp"),
					resultSet.getString("titel"),
					resultSet.getInt("baujahr"),
					resultSet.getInt("wohnflaeche"),
					resultSet.getInt("grundstuecksflaeche"),
					resultSet.getString("standort"),
					resultSet.getDate("datum"),
					resultSet.getInt("gebot"),
					resultSet.getString("beschreibung"),
					resultSet.getBytes("bilder"),
					resultSet.getInt("besitzer"),
					resultSet.getInt("hoechstbietender")
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
		 * Rückgabe der Liste "objektIdDaten"
		 */
		return objektIdDaten;
	}
	
}
