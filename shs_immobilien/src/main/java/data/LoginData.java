package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import immo.portal.bean.RegistrierenBean;

public class LoginData {

	private DataSource dataSource;

	public LoginData(DataSource dataSource) {
		this.dataSource = dataSource;
	}

//	public List<RegistrierenBean> getObjekt(String benutzerid) {
//		List<RegistrierenBean> benutzerIdDaten = new ArrayList<RegistrierenBean>();
//
//		try {
//			Connection con = dataSource.getConnection();
//			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM benutzer WHERE id=?");
//			pstmt.setString(1, benutzerid);
//
//			ResultSet resultSet = pstmt.executeQuery();
//			while (resultSet.next()) {
//				benutzerIdDaten.add(new RegistrierenBean(resultSet.getInt("id"), resultSet.getString("vorname"),
//						resultSet.getString("nachname"), resultSet.getString("anschrift"), resultSet.getInt("plz"),
//						resultSet.getString("wohnort"), resultSet.getInt("telefon"), resultSet.getString("email"),
//						resultSet.getString("passwort1")));
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return benutzerIdDaten;
//	}

//	public boolean istRegistriert(String email, String passwort, String id) {
//		try {
//			Connection connection = dataSource.getConnection();
//			PreparedStatement preparedStatement = connection
//					.prepareStatement("SELECT email AND passwort1 FROM benutzer WHERE id LIKE ?");		
//			preparedStatement.setString(1, id);	
//			
//			ResultSet resultSet = preparedStatement.executeQuery();
//			while (resultSet.next()) {
//				if (resultSet.getString(1).equals(email) && resultSet.getString(2).equals(passwort)) {
//					return true;
//				}
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return false;
//
//	}
	
	public boolean istRegistriert(String email, String passwort) {
		
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM benutzer WHERE email = ? AND passwort1 = ?");
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, passwort);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				return true;
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;	
	}
	

}
