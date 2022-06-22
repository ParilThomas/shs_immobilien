package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import immo.portal.bean.HaustypBean;

public class HaustypData {
	
	private DataSource dataSource;
	
	public HaustypData(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public List<HaustypBean> alleHaustypen() {
		List<HaustypBean> haustypen = new ArrayList<>();	
		try {
			Connection connection = dataSource.getConnection();
			ResultSet haustyp = connection.createStatement().executeQuery("SELECT * FROM haustyp ORDER BY typ");		
			while (haustyp.next()) {	
				haustypen.add(new HaustypBean(haustyp.getInt("id"), haustyp.getString("typ")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return haustypen;
	}

	
	public boolean istHaustypVorhanden(String haustyp) {
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM haustyp WHERE typ LIKE ?");
			preparedStatement.setString(1, haustyp);			 
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				return true;
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;	
	}
	
	
	
	public void neuenHaustypHinzufuegen(String haustyp) {
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO haustyp (typ) VALUES (?)");
			//1 -> 1. Fragezeichen aus SQL Abfrage
			preparedStatement.setString(1, haustyp);
			preparedStatement.executeUpdate();			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
