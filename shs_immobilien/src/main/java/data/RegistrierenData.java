package data;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import immo.portal.bean.ObjektBean;
import immo.portal.bean.RegistrierenBean;
import jakarta.servlet.http.Part;

public class RegistrierenData {
private DataSource dataSource;
	
	
	public RegistrierenData(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	
	public void registrierenFormularabschicken(String vorname, String nachname, String anschrift,
			Integer plz, String wohnort, Integer telefon, String email,
			String passwort1) {
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO benutzer (vorname, nachname, anschrift, plz, wohnort, "
					+ "telefon,email, passwort1) VALUES (?,?,?,?,?,?,?,?)");
					
			preparedStatement.setString(1, vorname);
			preparedStatement.setString(2, nachname);
			preparedStatement.setString(3, anschrift);
			preparedStatement.setInt(4, plz);
			preparedStatement.setString(5, wohnort);
			preparedStatement.setInt(6, telefon);
			preparedStatement.setString(7, email);
			preparedStatement.setString(8, passwort1);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Funktion um Objekte zu bekommen 
	
//	public List<RegistrierenBean> getObjekt(String benutzerid){
//		List<RegistrierenBean> benutzerIdDaten = new ArrayList<RegistrierenBean>();
//		
//		try {
//			Connection con = dataSource.getConnection();
//			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM benutzer WHERE id=?");
//			pstmt.setString(1, benutzerid);
//			
//			ResultSet resultSet = pstmt.executeQuery();
//			while (resultSet.next()) {
//				benutzerIdDaten.add(new RegistrierenBean(
//						resultSet.getInt("id"),
//						resultSet.getString("vorname"),
//						resultSet.getString("nachname"),
//						resultSet.getString("anschrift"),
//						resultSet.getInt("plz"),
//						resultSet.getString("wohnort"),
//						resultSet.getInt("telefon"),
//						resultSet.getString("email"),
//						resultSet.getString("passwort1")	
//						));	
//		}
//	 }
//	catch (Exception e) {
//		e.printStackTrace();
//	}
//		return benutzerIdDaten;
//}
	
	
	//Funktion ist Registriert Versuch -> man muss Passwort und email prüfen -> wenn gleich mit dem was eingeben ist -> Benutzer ist registriert 
	// Gehört wsl sogar in LoginData .....
	
	
	
//	public boolean istRegistriert(String email, String passwort) {
//		try {
//			Connection connection = dataSource.getConnection();
//			PreparedStatement preparedStatement = connection.prepareStatement("SELECT email AND passwort1 FROM benutzer WHERE id LIKE ?");
////			preparedStatement.setString(1, id);			 
//			ResultSet resultSet = preparedStatement.executeQuery();
//			while (resultSet.next()) {
//			if(resultSet.getString(1)== email && resultSet.getString(2)==passwort) {
//				return true;
//			}
//			}
//
//	} catch (SQLException e) {
//		e.printStackTrace();
//	}
//	return false;
//	
//}
}