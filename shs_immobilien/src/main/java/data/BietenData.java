package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import immo.portal.bean.ObjektBean;

public class BietenData {
	
private DataSource dataSource;
	
	
	public BietenData(DataSource dataSource) {
		this.dataSource = dataSource;
	}


	
	public List<ObjektBean> getObjekt(Integer id){
		List<ObjektBean> objekte = new ArrayList<ObjektBean>();
		try {
			
			Connection con = dataSource.getConnection();
			PreparedStatement prsmt = con.prepareStatement("Select * FROM objekte WHERE id LIKE ?");
			prsmt.setInt(1, id);
			ResultSet resultSet = prsmt.executeQuery();
			while (resultSet.next()) {
				objekte.add(new ObjektBean(
						resultSet.getInt("id"),
						resultSet.getString("haustyp"),
						resultSet.getString("bautyp"),
						resultSet.getString("titel"),
						resultSet.getInt("baujahr"),
						resultSet.getInt("wohnflaeche"),
						resultSet.getInt("grundstuecksflaeche"),
						resultSet.getString("standort"),
						resultSet.getDate("datum"),
						resultSet.getInt("startgebot"),
						resultSet.getString("beschreibung"),
						resultSet.getBytes("bilder")	
						));	
			}
		}
		catch (Exception e){
			return objekte;
		}
		
		return objekte;
	}
	
	
	
}
