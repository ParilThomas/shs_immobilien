package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import immo.portal.bean.ObjektBean;

public class SuchenData {

	private DataSource dataSource;

	/**
 	* Konstruktor speichert die dataSource
 	*/
	public SuchenData(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * Methoden Beschreibung
	 * 
	 *@Sichtbarkeit public - In anderen Klassen verwendbar
	 *@Methodentyp nonstatic - Instanzmethode für ein bestimtmes Objekt
	 *
	 *@Methodenname eigeneAngebote
	 *@Parameter suchvar - erwartet beim Aufruf einen String
	 *
	 *@Rückgabetyp List<> - gibt eine Liste von Objekten zurück
	 *
	 *Methode gibt alle DB-Einträge zum eingegebenen Such-String zurück
	 */
	public List<ObjektBean> getSuchObjekte(String suchvar) {
		/**
		 * Erzeugen einer ArrayList
		 */
		List<ObjektBean> suchObjekte = new ArrayList<>();
		/**
		 * Datenbankverbindung erstellen
		 * Verbindung wird nach Durchlauf geschlossen
		 */
		try(Connection dbVerbindung = dataSource.getConnection();) {		
			/**
			 * Aufruf der Datenbankverbindung mit einem SQL-Befehl
			 * Select * 			-> Wählt alle Spalten in der Datenbank aus
			 * FROM objekte 		-> legt die zu verwendende DB Tabelle fest "objekte"
			 * WHERE x LIKE ? OR	-> sucht in allen Spalten den eingegegen Wert und gibt 
			 * 							alle gefunden Objekte die den Wert enthalten zurück
			 */
			PreparedStatement sqlBefehl = dbVerbindung.prepareStatement(
				"Select * FROM objekte WHERE "
				+ "haustyp LIKE ? OR "
				+ "bautyp LIKE ? OR "
				+ "titel LIKE ? OR "
				+ "baujahr LIKE ? OR "
				+ "wohnflaeche LIKE ? OR "
				+ "grundstuecksflaeche LIKE ? OR "
				+ "standort LIKE ? OR "
				+ "beschreibung LIKE ? OR "
				+ "gebot LIKE ?"
			);
			
			/**
			 * Setzen der ? Werte im SQL-Befehl der Reihe nach
			 * % vor und hinter dem Suchbegriff ermöglicht die
			 * Suche eines "Substrings" 
			 * 
			 * Bsp. Suchbegriff "tz"
			 * Somit werden alle Objekte die in den oben angegebenen Zeilen
			 * irgendwo das Fragmet "tz" behinhalten ausgegeben.
			 * 
			 * Katze; Baum -> Das gesamte Objekt "Katze" würde hinzugefügt werden.
			 * 
			 */	
			sqlBefehl.setString(1, "%" + suchvar + "%");
			sqlBefehl.setString(2, "%" + suchvar + "%");
			sqlBefehl.setString(3, "%" + suchvar + "%");
			sqlBefehl.setString(4, "%" + suchvar + "%");
			sqlBefehl.setString(5, "%" + suchvar + "%");
			sqlBefehl.setString(6, "%" + suchvar + "%");
			sqlBefehl.setString(7, "%" + suchvar + "%");
			sqlBefehl.setString(8, "%" + suchvar + "%");
			sqlBefehl.setString(9, "%" + suchvar + "%");
			
			/**
			 * ResultSet 	-> Behinhaltet gefundene Datenbankeinträge
			 * executeQuery -> Beendet den SQL-Befehl
			 */		
			ResultSet resultSet = sqlBefehl.executeQuery();
			/**
			 * Mit "while" wird durch den resultSet interiert mit "next()" gelangt man
			 * auf das 1. Element vom resultSet.
			 * 
			 * Der Liste "suchObjekte" wird für jedes Element des resultSets ein neues
			 * Objekt mit dessen Variablen hinzugefügt "add"
			 */
			while (resultSet.next()) {
				suchObjekte.add(new ObjektBean(
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
		catch (Exception e){
			e.printStackTrace();
		}
		/**
		 * Rückgabe der Liste "suchObjekte"
		 */
		return suchObjekte;
	}

}
