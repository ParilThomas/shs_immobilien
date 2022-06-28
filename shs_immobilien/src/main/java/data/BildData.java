package data;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

public class BildData {

	private DataSource dataSource;

	/**
 	* Konstruktor speichert die dataSource
 	*/
	public BildData(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	
	/**
	 * Methoden Beschreibung
	 * 
	 *@Sichtbarkeit public - In anderen Klassen verwendbar
	 *@Methodentyp nonstatic - Instanzmethode für ein bestimtmes Objekt
	 *
	 *@Methodenname getBild
	 *@Parameter id - erwartet beim Aufruf einen Long
	 *
	 *@Rückgabetyp Blob - gibt ein Binary Large Objekt zurück
	 */
	public Blob getBild(Long id) {
		/**
		 * Datenbankverbindung erstellen
		 * Verbindung wird nach Durchlauf geschlossen
		 */
		try(Connection dbVerbindung = dataSource.getConnection();) {		
			/**
			 * Aufruf der Datenbankverbindung mit einem SQL-Befehl
			 * Select bilder 	-> Wählt die Spalte Bilder
			 * FROM objekte 	-> legt die zu verwendende DB Tabelle fest "objekte" fest
			 * WHERE id=?		-> in der die id gleich die lokal gespeicherte Objektid ist
			 */
			PreparedStatement sqlBefehl = dbVerbindung.prepareStatement("Select bilder FROM objekte WHERE id=?");
			/**
			 * Ersetzt im sqlBefehl das ? mit der id
			 */
			sqlBefehl.setLong(1, id);
			/**
			 * ResultSet 	-> Behinhaltet gefundene Datenbankeinträge
			 * executeQuery -> Beendet den SQL-Befehl
			 */
			ResultSet resultSet = sqlBefehl.executeQuery();
			/**
			 * Ist der resultSet NICHT null UND gibt es ein reslutSet Element...
			 */
			if (resultSet != null && resultSet.next()) {
				/**
				 * dann gib das Blob Objekt zurück
				 */
				return resultSet.getBlob("bilder");
			}		
		} 
		/**
		 * catch fängt die Fehler bei der Ausführung der "try" Anweisungen
		 */
		catch (Exception e){
			e.printStackTrace();
		}
		/**
		 * Ansonsten gib nichts zurück
		 */
		return null;
	}

}
