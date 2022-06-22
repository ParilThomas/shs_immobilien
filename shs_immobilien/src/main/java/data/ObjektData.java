package data;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import immo.portal.bean.ObjektBean;
import jakarta.servlet.http.Part;

public class ObjektData {
	
	private DataSource dataSource;
	
	
	public ObjektData(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void verkaufFormularAbschicken(String fhaustyp, String fbautyp, String ftitel, Integer fbaujahr,
			Integer fwohnflaeche, Integer fgrundstuecksflaeche, String fstandort, Integer fstartgebot,
			String fbeschreibung,Part fbilder,String fdatum, Integer fbesitzer) {
		try {
			Connection connection = dataSource.getConnection();
			InputStream inputStream = fbilder.getInputStream();
			PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO objekte (haustyp, bautyp, titel, baujahr, wohnflaeche, "
																			+ "grundstuecksflaeche, standort, datum, startgebot, beschreibung, bilder,"
																			+ "besitzer) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");		
			preparedStatement.setString(1, fhaustyp);
			preparedStatement.setString(2, fbautyp);
			preparedStatement.setString(3, ftitel);
			preparedStatement.setInt(4, fbaujahr);
			preparedStatement.setInt(5, fwohnflaeche);
			preparedStatement.setInt(6, fgrundstuecksflaeche);
			preparedStatement.setString(7, fstandort);
			preparedStatement.setString(8, fdatum);
			preparedStatement.setInt(9, fstartgebot);
			preparedStatement.setString(10, fbeschreibung);
			preparedStatement.setBinaryStream(11, inputStream); //setter ge�ndert 
			preparedStatement.setInt(12, fbesitzer);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<ObjektBean> getObjekte(String haustyp){
		List<ObjektBean> objekte = new ArrayList<ObjektBean>();
		try {			
			Connection con = dataSource.getConnection();
			PreparedStatement prsmt = con.prepareStatement("Select * FROM objekte WHERE haustyp LIKE ?");
			prsmt.setString(1, haustyp);
			ResultSet resultSet = prsmt.executeQuery();
			while (resultSet.next()) {
				objekte.add(new ObjektBean(
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
			return objekte;
		}	
		return objekte;
	}
	
}
