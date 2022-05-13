package data;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.sql.DataSource;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Part;

public class ObjektData {
	
	private DataSource dataSource;
	
	
	public ObjektData(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	
	public void verkaufFormularAbschicken(String fhaustyp, String fbautyp, String ftitel, String fbaujahr,
			Integer fwohnflaeche, Integer fgrundstuecksflaeche, String fstandort, Integer fstartgebot,
			String fbeschreibung, Part fbilder, java.sql.Date fdatum) {
		try {
			Connection connection = dataSource.getConnection();
			InputStream inputStream = fbilder.getInputStream();
			PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO objekte (haustyp, bautyp, titel, baujahr, wohnflaeche, "
					+ "grundstuecksflaeche, standort, datum, startgebot, beschreibung, bilder) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
					
			preparedStatement.setString(1, fhaustyp);
			preparedStatement.setString(2, fbautyp);
			preparedStatement.setString(3, ftitel);
			preparedStatement.setString(4, fbaujahr);
			preparedStatement.setInt(5, fwohnflaeche);
			preparedStatement.setInt(6, fgrundstuecksflaeche);
			preparedStatement.setString(7, fstandort);
			preparedStatement.setDate(8, fdatum);
			preparedStatement.setInt(9, fstartgebot);
			preparedStatement.setString(10, fbeschreibung);
			preparedStatement.setBlob(11, inputStream);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
}
