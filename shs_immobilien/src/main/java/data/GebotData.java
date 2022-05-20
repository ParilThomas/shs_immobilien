package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

public class GebotData {
	
private DataSource dataSource;
	
	
	public GebotData(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public void gebotAktualisieren (Integer gebot, String id) {
		try {
			
			Connection con = dataSource.getConnection();
			PreparedStatement prsmt = con.prepareStatement("UPDATE objekte SET startgebot = ? WHERE id LIKE ?");
			prsmt.setInt(1, gebot);
			prsmt.setString(2, id);
			prsmt.executeUpdate();

		}
		catch (Exception e){
			return ;
		}
	}
	
//	public boolean istGebotOk(Integer gebot, String id) {
//		try {
//			Connection connection = dataSource.getConnection();
//			PreparedStatement preparedStatement = connection.prepareStatement("SELECT startgebot FROM objekte WHERE id LIKE ?");
//			preparedStatement.setString(1, id);			 
//			ResultSet resultSet = preparedStatement.executeQuery();
//			while (resultSet.next()) {
//			if(resultSet.getInt(2) < gebot) {
//				return true;
//			}
//			}
//
//	} catch (SQLException e) {
//		e.printStackTrace();
//	}
//	return false;
//	
//	
//	}	
}
