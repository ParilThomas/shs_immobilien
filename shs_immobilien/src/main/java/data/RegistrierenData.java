package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

public class RegistrierenData {
private DataSource dataSource;
	
	
	public RegistrierenData(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	
	public void registrierenFormularabschicken(String vorname, String nachname, String anschrift,
			String rplz, String wohnort, Integer telefon, String email,	String passwort1) {
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO benutzer (vorname, nachname, anschrift, plz, wohnort, "
																				+ "telefon,email, passwort1) VALUES (?,?,?,?,?,?,?,?)");				
			preparedStatement.setString(1, vorname);
			preparedStatement.setString(2, nachname);
			preparedStatement.setString(3, anschrift);
			preparedStatement.setString(4, rplz);
			preparedStatement.setString(5, wohnort);
			preparedStatement.setInt(6, telefon);
			preparedStatement.setString(7, email);
			preparedStatement.setString(8, passwort1);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean emailVorhanden(String email) {
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM benutzer WHERE email LIKE ?");
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