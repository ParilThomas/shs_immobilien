package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import immo.portal.bean.BenutzerBean;

public class LoginData {

	private DataSource dataSource;

	public LoginData(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public BenutzerBean holeBenutzer(String email, String passwort) {

		try {
			Connection con = dataSource.getConnection();
			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM benutzer WHERE email = ? AND passwort1 = ?");
			pstmt.setString(1, email);
			pstmt.setString(2, passwort);

			ResultSet resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				return new BenutzerBean(
						resultSet.getInt("id"),
						resultSet.getString("vorname"),
						resultSet.getString("nachname"),
						resultSet.getString("anschrift"),
						resultSet.getInt("plz"),
						resultSet.getString("wohnort"),
						resultSet.getInt("telefon"),
						resultSet.getString("email"),
						resultSet.getString("passwort1")
				);			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	public boolean istRegistriert(String email) {
		
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM benutzer WHERE email = ?");
			preparedStatement.setString(1, email);
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
