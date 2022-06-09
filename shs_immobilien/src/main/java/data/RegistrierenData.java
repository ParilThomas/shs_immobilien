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
import immo.portal.bean.BenutzerBean;
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
}