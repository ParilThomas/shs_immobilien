package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import immo.portal.bean.ObjektBean;

public class SuchenData {

	private DataSource dataSource;

	public SuchenData(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public List<ObjektBean> getSuchObjekte(String suchvar) {
		List<ObjektBean> suchObjekte = new ArrayList<ObjektBean>();
		try {
			
			Connection con = dataSource.getConnection();
			PreparedStatement prsmt = con.prepareStatement(
				"Select * FROM objekte WHERE "
				+ "haustyp LIKE ? OR "
				+ "bautyp LIKE ? OR "
				+ "titel LIKE ? OR "
				+ "baujahr LIKE ? OR "
				+ "wohnflaeche LIKE ? OR "
				+ "grundstuecksflaeche LIKE ? OR "
				+ "standort LIKE ? OR "
				+ "beschreibung LIKE ? OR "
				+ "startgebot LIKE ?"
			);

			
			prsmt.setString(1, "%" + suchvar + "%");
			prsmt.setString(2, "%" + suchvar + "%");
			prsmt.setString(3, "%" + suchvar + "%");
			prsmt.setString(4, "%" + suchvar + "%");
			prsmt.setString(5, "%" + suchvar + "%");
			prsmt.setString(6, "%" + suchvar + "%");
			prsmt.setString(7, "%" + suchvar + "%");
			prsmt.setString(8, "%" + suchvar + "%");
			prsmt.setString(9, "%" + suchvar + "%");
			
			
			ResultSet resultSet = prsmt.executeQuery();
			while (resultSet.next()) {
				suchObjekte.add(new ObjektBean(
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
					resultSet.getBytes("bilder"),
					resultSet.getInt("besitzer"),
					resultSet.getInt("hoechstbietender")
				));	
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		return suchObjekte;
	}
	

}
