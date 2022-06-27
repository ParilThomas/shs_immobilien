package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import immo.portal.bean.ObjektBean;

public class AnsichtData {

	private DataSource dataSource;

	/**
	 * Konstruktor speichert die dataSource
	 */
	public AnsichtData(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * Methoden Beschreibung
	 * 
	 *@Sichtbarkeit public - In anderen Klassen verwendbar
	 *@Methodentyp nonstatic - Instanzmethode für ein bestimtmes Objekt
	 *
	 *@Methodenname eigeneAngebote
	 *@Parameter besitzerid - erwartet beim Aufruf einen Integer
	 *
	 *@Rückgabetyp List<> - gibt eine Liste von Objekten zurück
	 */
	public List<ObjektBean> eigeneAngebote(Integer besitzerid){ 
		/**
		 * Erzeugen einer ArrayList
		 */
		List<ObjektBean> eigeneobjekte = new ArrayList<>();
		try {
			/**
			 * Datenbankverbindung erstellen
			 */
			Connection dbVerbindung = dataSource.getConnection();
			/**
			 * Aufruf der Datenbankverbindung mit einem SQL-Befehl
			 * Select * 		-> Wählt alle Spalten in der Datenbank aus
			 * FROM objekte 	-> legt die zu verwendende DB Tabelle fest "objekte"
			 * WHERE besitzer	-> filtert den Datensatz nach der Spalte "besitzer"
			 * LIKE ? 			-> sucht in der Spalte anhand eines festgelegten Musters "?"
			 */
			PreparedStatement sqlBefehl = dbVerbindung.prepareStatement("Select * FROM objekte WHERE besitzer LIKE ?");
			/**
			 * Setzen des Musters "LIKE ?" nachdem im sqlBefehl gesucht wird 
			 */
			sqlBefehl.setInt(1,besitzerid);
			/**
			 * ResultSet 	-> Behinhaltet gefundene Datenbankeinträge
			 * executeQuery -> Beendet den SQL-Befehl
			 */
			ResultSet resultSet = sqlBefehl.executeQuery();
			/**
			 * Mit "while" wird durch den resultSet interiert mit "next()" gelangt man
			 * auf das 1. Element vom resultSet.
			 * 
			 * Der Liste "eigeneobjekte" wird für jedes Element des resultSets ein neues
			 * Objekt mit dessen Variablen hinzugefügt "add"
			 */
			while (resultSet.next()) {
				eigeneobjekte.add(new ObjektBean(
					resultSet.getLong("id"),
					resultSet.getString("haustyp"),
					resultSet.getString("bautyp"),
					resultSet.getString("titel"),
					resultSet.getInt("baujahr"),
					resultSet.getInt("wohnflaeche"),
					resultSet.getInt("grundstuecksflaeche"),
					resultSet.getString("standort"),
					resultSet.getDate("datum"),
					resultSet.getInt("startgebot"),
					resultSet.getString("beschreibung"),
					resultSet.getBytes("bilder"),
					resultSet.getInt("besitzer"),
					resultSet.getInt("hoechstbietender")
				));	
			}
		}
		/**
		 * catch fägt die Fehler bei der Ausführung der "try" Anweisungen
		 */
		catch (Exception e){
			e.printStackTrace();
		}
		/**
		 * Rückgabe der Liste "eigeneobjekte"
		 */
		return eigeneobjekte;
	}

	
	
	/**
	 * Methoden Beschreibung
	 * 
	 *@Sichtbarkeit public - In anderen Klassen verwendbar
	 *@Methodentyp nonstatic - Instanzmethode für ein bestimtmes Objekt
	 *
	 *@Methodenname eigeneGebote
	 *@Parameter besitzerid - erwartet beim Aufruf einen Integer
	 *
	 *@Rückgabetyp List<> - gibt eine Liste von Objekten zurück
	 */
	public List<ObjektBean> eigeneGebote(Integer besitzerid){ 
		/**
		 * Erzeugen einer ArrayList vom Typ ObjektBean
		 */
		List<ObjektBean> eigeneobjekte = new ArrayList<>();
		try {
			/**
			 * Datenbankverbindung erstellen
			 */
			Connection dbVerbindung = dataSource.getConnection();
			/**
			 * Aufruf der Datenbankverbindung mit einem SQL-Befehl
			 * Select * 				-> Wählt alle Spalten in der Datenbank aus
			 * FROM objekte 			-> legt die zu verwendende DB Tabelle fest "objekte"
			 * WHERE hoechstbietender	-> filtert den Datensatz nach der Spalte "hoechstbietender"
			 * LIKE ? 					-> sucht in der Spalte anhand eines festgelegten Musters "?"
			 */
			PreparedStatement sqlBefehl = dbVerbindung.prepareStatement("Select * FROM objekte WHERE hoechstbietender LIKE ?");
			/**
			 * Setzen des Musters "LIKE ?" nachdem im sqlBefehl gesucht wird 
			 */
			sqlBefehl.setInt(1,besitzerid);
			/**
			 * ResultSet 	-> Behinhaltet gefundene Datenbankeinträge
			 * executeQuery -> Beendet den SQL-Befehl
			 */
			ResultSet resultSet = sqlBefehl.executeQuery();
			/**
			 * Mit "while" wird durch den resultSet interiert mit "next()" gelangt man
			 * auf das 1. Element vom resultSet.
			 * 
			 * Der Liste "eigeneobjekte" wird für jedes Element des resultSets ein neues
			 * Objekt mit dessen Variablen hinzugefügt "add"
			 */
			while (resultSet.next()) {
				eigeneobjekte.add(new ObjektBean(
					resultSet.getLong("id"),
					resultSet.getString("haustyp"),
					resultSet.getString("bautyp"),
					resultSet.getString("titel"),
					resultSet.getInt("baujahr"),
					resultSet.getInt("wohnflaeche"),
					resultSet.getInt("grundstuecksflaeche"),
					resultSet.getString("standort"),
					resultSet.getDate("datum"),
					resultSet.getInt("startgebot"),
					resultSet.getString("beschreibung"),
					resultSet.getBytes("bilder"),
					resultSet.getInt("besitzer"),
					resultSet.getInt("hoechstbietender")
				));	
			}
		}
		/**
		 * catch fägt die Fehler bei der Ausführung der "try" Anweisungen
		 */
		catch (Exception e){
			e.printStackTrace();
		}	
		/**
		 * Rückgabe der Liste "eigeneobjekte"
		 */
		return eigeneobjekte;
	}

}
