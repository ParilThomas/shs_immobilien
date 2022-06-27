package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

public class GebotData {
	
private DataSource dataSource;
	
	/**
 	* Konstruktor speichert die dataSource
 	*/
	public GebotData(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
	/**
	 * Methoden Beschreibung
	 * 
	 *@Sichtbarkeit public - In anderen Klassen verwendbar
	 *@Methodentyp nonstatic - Instanzmethode für ein bestimtmes Objekt
	 *
	 *@Methodenname gebotAktualisieren
	 *@Parameter gebot 		- erwartet beim Aufruf einen Integer
	 *@Parameter id 		- erwartet beim Aufruf einen String
	 *@Parameter benutzerid - erwartet beim Aufruf einen Integer
	 *
	 *@Rückgabetyp void - es wird nichts zurückgegeben
	 */
	public void gebotAktualisieren(Integer gebot, String id, Integer benutzerid) {
		/**
		 * Datenbankverbindung erstellen
		 */
		try(Connection dbVerbindung = dataSource.getConnection();){		
			/**
			 * Aufruf der Datenbankverbindung mit einem SQL-Befehl
			 * UPDATE objekte		-> Überspeichert den vorhandenen Datensatz der Tabelle "objekte"
			 * SET startgebot = ? 	-> Setzt das Startgebot mit dem übergebenen Wert "?"
			 * WHERE id	LIKE ? 		-> im Datensatz mit der id = ?
			 */
			PreparedStatement sqlBefehl = dbVerbindung.prepareStatement("UPDATE objekte SET startgebot = ? WHERE id LIKE ?");
			/**
			 * Setzen der Werte im SQL-Befehl der Reihe nach
			 * executeUpdate -> Beendet den SQL-Befehl
			 */
			sqlBefehl.setInt(1, gebot);
			sqlBefehl.setString(2, id);
			sqlBefehl.executeUpdate();
			
			/**
			 * Aufruf der Datenbankverbindung mit einem SQL-Befehl
			 * UPDATE objekte			-> Überspeichert den vorhandenen Datensatz der Tabelle "objekte"
			 * SET hoechstbietender = ? -> Setzt den hoechstbietender auf den übergebenen Wert "?"
			 * WHERE id	LIKE ? 			-> im Datensatz mit der id = ?
			 */
			PreparedStatement sqlBefehl2 = dbVerbindung.prepareStatement("UPDATE objekte SET hoechstbietender = ? WHERE id LIKE ?");
			/**
			 * Setzen der Werte im SQL-Befehl der Reihe nach
			 * executeUpdate -> Beendet den SQL-Befehl
			 */
			sqlBefehl2.setInt(1, benutzerid);
			sqlBefehl2.setString(2, id);
			sqlBefehl2.executeUpdate();

		}
		/**
		 * catch fägt die Fehler bei der Ausführung der "try" Anweisungen
		 */
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Methoden Beschreibung
	 * 
	 *@Sichtbarkeit public - In anderen Klassen verwendbar
	 *@Methodentyp nonstatic - Instanzmethode für ein bestimtmes Objekt
	 *
	 *@Methodenname istGebotZuKlein
	 *@Parameter gebot 		- erwartet beim Aufruf einen Integer
	 *@Parameter id 		- erwartet beim Aufruf einen String
	 *
	 *@Rückgabetyp boolean - True / False
	 */
	public boolean istGebotZuKlein(Integer gebot, String id) {
		/**
		 * Datenbankverbindung erstellen
		 */
		try(Connection dbVerbindung = dataSource.getConnection();){
			/**
			 * Aufruf der Datenbankverbindung mit einem SQL-Befehl
			 * SELECT startgebot	-> Wählt die Spalte "startgebot"
			 * FROM objekte 		-> der Tabelle "objekte"
			 * WHERE id	LIKE ? 		-> im Datensatz mit indem die Spalte "id" den Wertk "Like ?" enthält
			 */
			PreparedStatement sqlBefehl = dbVerbindung.prepareStatement("SELECT startgebot FROM objekte WHERE id LIKE ?");
			/**
			 * Setzen der Werte im SQL-Befehl
			 */
			sqlBefehl.setString(1, id);	
			/**
			 * ResultSet 	-> Behinhaltet gefundene Datenbankeinträge
			 * executeQuery -> Beendet den SQL-Befehl
			 */
			ResultSet resultSet = sqlBefehl.executeQuery();
			/**
			 * Mit "while" wird durch den resultSet interiert mit "next()" gelangt man
			 * auf das 1. Element vom resultSet.
			 * 
			 * Ist ein Datenbankeintrag vorhanden und der IntegerWert größer oder gleich dem
			 * EINGEGEBENEN Gebot so gibt "true" zurück
			 */
			while (resultSet.next()) {
				if(resultSet.getInt(1) >= gebot) {
					return true;
				}
			}

		} 
		/**
		 * catch fägt die Fehler bei der Ausführung der "try" Anweisungen
		 */
		catch (Exception e) {
			e.printStackTrace();
		}
		/**
		 * Andernfalls gibt false zurück
		 */
		return false;	
	}	
	
	
	/**
	 * Methoden Beschreibung
	 * 
	 *@Sichtbarkeit public - In anderen Klassen verwendbar
	 *@Methodentyp nonstatic - Instanzmethode für ein bestimtmes Objekt
	 *
	 *@Methodenname istGebotOk
	 *@Parameter gebot 		- erwartet beim Aufruf einen Integer
	 *@Parameter id 		- erwartet beim Aufruf einen String
	 *
	 *@Rückgabetyp boolean - True / False
	 */
	public boolean istGebotOk(Integer gebot, String id) {
		/**
		 * Datenbankverbindung erstellen
		 */
		try(Connection dbVerbindung = dataSource.getConnection();){
			/**
			 * Aufruf der Datenbankverbindung mit einem SQL-Befehl
			 * SELECT startgebot	-> Wählt die Spalte "startgebot"
			 * FROM objekte 		-> der Tabelle "objekte"
			 * WHERE id	LIKE ? 		-> im Datensatz mit indem die Spalte "id" den Wertk "Like ?" enthält
			 */
			PreparedStatement sqlBefehl = dbVerbindung.prepareStatement("SELECT startgebot FROM objekte WHERE id LIKE ?");
			/**
			 * Setzen der Werte im SQL-Befehl
			 */
			sqlBefehl.setString(1, id);		
			/**
			 * ResultSet 	-> Behinhaltet gefundene Datenbankeinträge
			 * executeQuery -> Beendet den SQL-Befehl
			 */
			ResultSet resultSet = sqlBefehl.executeQuery();
			/**
			 * Mit "while" wird durch den resultSet interiert mit "next()" gelangt man
			 * auf das 1. Element vom resultSet.
			 * 
			 * Ist ein Datenbankeintrag vorhanden und der IntegerWert größer oder gleich dem
			 * EINGEGEBENEN Gebot so gibt "true" zurück
			 */
			while (resultSet.next()) {
				if(resultSet.getInt(1) < gebot) {
					return true;
				}
			}

	} 
	/**
	 * catch fägt die Fehler bei der Ausführung der "try" Anweisungen
	 */
	catch (Exception e) {
		e.printStackTrace();
	}
	/**
	 * Andernfalls gibt false zurück
	 */
	return false;
	}	
	
}
