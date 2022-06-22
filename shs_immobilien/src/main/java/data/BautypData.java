package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import immo.portal.bean.BautypBean;

public class BautypData {

	private DataSource dataSource;
	
	
	public BautypData(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public List<BautypBean> alleBautypen() {
		List<BautypBean> bautypen = new ArrayList<>();	
		try {
			Connection connection = dataSource.getConnection();
			ResultSet bautyp = connection.createStatement().executeQuery("SELECT * FROM bautyp ORDER BY typ");
			
			while (bautyp.next()) {	
				bautypen.add(new BautypBean(bautyp.getInt("id"), bautyp.getString("typ")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bautypen;
	}
	
	public boolean istBautypVorhanden(String bautyp) {
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM bautyp WHERE typ LIKE ?");
			preparedStatement.setString(1, bautyp);			 
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				return true;
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;	
	}
	
	
	
	public void neuenBautypHinzufuegen(String bautyp) {
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO bautyp (typ) VALUES (?)");
			//1 -> 1. Fragezeichen aus SQL Abfrage
			preparedStatement.setString(1, bautyp);
			preparedStatement.executeUpdate();			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
