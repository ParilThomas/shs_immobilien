package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import immo.portal.bean.ObjektBean;

public class AnsichtData {
	
private DataSource dataSource;
		
	public AnsichtData(DataSource dataSource) {
		this.dataSource = dataSource;
	}

public List<ObjektBean> eigeneAngebote(Integer besitzerid){ 
		List<ObjektBean> eigeneobjekte = new ArrayList<ObjektBean>();
		try {
			
			Connection con = dataSource.getConnection();
			PreparedStatement prsmt = con.prepareStatement("Select * FROM objekte WHERE besitzer LIKE ?");
			prsmt.setInt(1,besitzerid);
			ResultSet resultSet = prsmt.executeQuery();
			while (resultSet.next()) {
				eigeneobjekte.add(new ObjektBean(
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
		}
		catch (Exception e){
			return eigeneobjekte;
		}
		return eigeneobjekte;
	}

public List<ObjektBean> eigeneGebote(Integer besitzerid){ 
	List<ObjektBean> eigeneobjekte = new ArrayList<ObjektBean>();
	try {
		Connection con = dataSource.getConnection();
		PreparedStatement prsmt = con.prepareStatement("Select * FROM objekte WHERE hoechstbietender LIKE ?");
		prsmt.setInt(1,besitzerid);
		ResultSet resultSet = prsmt.executeQuery();
		while (resultSet.next()) {
			eigeneobjekte.add(new ObjektBean(
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
	}
	catch (Exception e){
		return eigeneobjekte;
	}	
	return eigeneobjekte;
}

}
