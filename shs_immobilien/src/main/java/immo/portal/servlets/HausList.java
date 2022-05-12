package immo.portal.servlets;
import immo.portal.bean.Haustyp_Bean;
import jakarta.annotation.Resource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;



public class HausList {
	@Resource(lookup = "java:jboss/datasources/MySqlweb_db_ttsDS")
	private DataSource ds;
	
//	public List<Haustyp_Bean> list() throws SQLException {
//		ArrayList<Haustyp_Bean> listHaustyp_Bean = new ArrayList<Haustyp_Bean>();
//		
//		try (Connection con = ds.getConnection()){
//			String sql = "SELECT * FROM haustyp ORDER BY typ";
//			Statement statement = con.createStatement();
//			ResultSet result = statement.executeQuery(sql);
//			
//			while (result.next()) {
//				int id = result.getInt("id");
//				String typ = result.getString("typ");
//				Haustyp_Bean haustyp_bean = new Haustyp_Bean(id, typ);
//				
//				listHaustyp_Bean.add(haustyp_bean);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw e;
//		}
//		
//		return listHaustyp_Bean;
//	}
}
