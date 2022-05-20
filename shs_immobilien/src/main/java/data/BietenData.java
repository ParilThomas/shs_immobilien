package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import immo.portal.bean.BautypBean;
import immo.portal.bean.ObjektBean;

public class BietenData {
	
private DataSource dataSource;
	
	
	public BietenData(DataSource dataSource) {
		this.dataSource = dataSource;
	}


	
	public List<ObjektBean> getObjekt(String detailid){
		List<ObjektBean> objektIdDaten = new ArrayList<ObjektBean>();
		try {
			
			Connection con = dataSource.getConnection();
			PreparedStatement prsmt = con.prepareStatement("Select * FROM objekte WHERE id=?");
			prsmt.setString(1, detailid);
			
			ResultSet resultSet = prsmt.executeQuery();
			while (resultSet.next()) {
				objektIdDaten.add(new ObjektBean(
						resultSet.getLong("id"),
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
			return objektIdDaten;
		}
		
		return objektIdDaten;
	}
	
	
	public void gebotAktualisieren (Integer id, Integer gebot) {
		try {
			
			Connection con = dataSource.getConnection();
			PreparedStatement prsmt = con.prepareStatement("UPDATE objekte SET startgebot = ? WHERE id = ");
			prsmt.setInt(1, gebot);
			prsmt.setInt(2, id);
			prsmt.executeUpdate();

		}
		catch (Exception e){
			return ;
		}
	}
	
	
	
}
