package data;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import immo.portal.bean.ObjektBean;
import jakarta.servlet.http.Part;

public class ObjektData {
	
	private DataSource dataSource;
	
	/**
	 * Konstruktor speichert die dataSource
	 */
	public ObjektData(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	
	/**
	 * Methoden Beschreibung
	 * 
	 *@Sichtbarkeit public - In anderen Klassen verwendbar
	 *@Methodentyp nonstatic - Instanzmethode für ein bestimtmes Objekt
	 *
	 *@Methodenname verkaufFormularAbschicken
	 *@Parameter fhaustyp				- erwartet beim Aufruf einen String
	 *@Parameter fbautyp 				- erwartet beim Aufruf einen String
	 *@Parameter ftitel					- erwartet beim Aufruf einen String
	 *@Parameter fbaujahr 				- erwartet beim Aufruf einen Integer
	 *@Parameter fwohnflaeche 			- erwartet beim Aufruf einen Integer
	 *@Parameter fgrundstuecksflaeche 	- erwartet beim Aufruf einen Integer
	 *@Parameter fstandort 				- erwartet beim Aufruf einen String
	 *@Parameter fstartgebot 			- erwartet beim Aufruf einen Integer
	 *@Parameter fbeschreibung 			- erwartet beim Aufruf einen String
	 *@Parameter fbilder 				- erwartet beim Aufruf einen Part(Bildformat)
	 *@Parameter fdatum 				- erwartet beim Aufruf einen String
	 *@Parameter fbesitzer 				- erwartet beim Aufruf einen Integer
	 *
	 *@Rückgabetyp void - es wird nichts zurückgegeben
	 *
	 *Methode speichert ein neues Verkaufsobjekt in der Datenbank
	 */
	public void verkaufFormularAbschicken(String fhaustyp, String fbautyp, String ftitel, Integer fbaujahr,
			Integer fwohnflaeche, Integer fgrundstuecksflaeche, String fstandort, Integer fstartgebot,
			String fbeschreibung,Part fbilder,String fdatum, Integer fbesitzer) {
		
		/**
		 * Datenbankverbindung erstellen
		 * Verbindung wird nach Durchlauf geschlossen
		 */
		try(Connection dbVerbindung = dataSource.getConnection();) {
			/**
			 * Speichern des inputStreams
			 */
			InputStream inputStream = fbilder.getInputStream();
			/**
			 * Aufruf der Datenbankverbindung mit einem SQL-Befehl
			 * INSERT INTO objekte 				-> Speichert den Wert in der Tabelle "objekte" in den Spalten(...,...,...)
			 * VALUES (?,?,?,?,?,?,?,?,?,?,?,?) -> legt die entsprechenden Werte die abgelegt werden sollen fest
			 */
			PreparedStatement sqlBefehl = dbVerbindung.prepareStatement("INSERT INTO objekte (haustyp, bautyp, titel, baujahr, wohnflaeche, "
																			+ "grundstuecksflaeche, standort, datum, gebot, beschreibung, bilder,"
																			+ "besitzer) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");	
			
			/**
			 * Setzen der ? Werte im SQL-Befehl der Reihe nach
			 */
			sqlBefehl.setString(1, fhaustyp);
			sqlBefehl.setString(2, fbautyp);
			sqlBefehl.setString(3, ftitel);
			sqlBefehl.setInt(4, fbaujahr);
			sqlBefehl.setInt(5, fwohnflaeche);
			sqlBefehl.setInt(6, fgrundstuecksflaeche);
			sqlBefehl.setString(7, fstandort);
			sqlBefehl.setString(8, fdatum);
			sqlBefehl.setInt(9, fstartgebot);
			sqlBefehl.setString(10, fbeschreibung);
			sqlBefehl.setBinaryStream(11, inputStream);
			sqlBefehl.setInt(12, fbesitzer);
			/**
			 * executeUpdate -> Beendet den SQL-Befehl
			 */
			sqlBefehl.executeUpdate();
		} 
		/**
		 * catch fängt die Fehler bei der Ausführung der "try" Anweisungen
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
	 *@Methodenname getObjekte
	 *@Parameter haustyp				- erwartet beim Aufruf einen String
	 *
	 *@Rückgabetyp List - Methode gibt eine Liste zurück
	 *
	 *Methode holt sich alle Objekte mit dem Wert "haustyp"
	 *und gibt diese zurück
	 */
	public List<ObjektBean> getObjekte(String haustyp){
		/**
		 * Erzeugen einer ArrayList
		 */
		List<ObjektBean> objekte = new ArrayList<>();
		/**
		 * Datenbankverbindung erstellen
		 * Verbindung wird nach Durchlauf geschlossen
		 */
		try(Connection dbVerbindung = dataSource.getConnection();) {	
			/**
			 * Aufruf der Datenbankverbindung mit einem SQL-Befehl
			 * Select * 		-> Wählt alle Spalten in der Datenbank aus
			 * FROM objekte 	-> legt die zu verwendende DB Tabelle fest "objekte"
			 * WHERE haustyp	-> filtert den Datensatz nach der Spalte "haustyp"
			 * LIKE ? 			-> sucht in der Spalte anhand eines festgelegten Musters "?"
			 */
			PreparedStatement sqlBefehl = dbVerbindung.prepareStatement("Select * FROM objekte WHERE haustyp LIKE ?");
			/**
			 * Setzen der SQL-Werte ?
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
			 * Der Liste "objekte" wird für jedes Element des resultSets ein neues
			 * Objekt mit dessen Variablen hinzugefügt "add"
			 */
			while (resultSet.next()) {
				objekte.add(new ObjektBean(
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
		 * Rückgabe der Liste "objekte"
		 */
		return objekte;
	}
	
}
