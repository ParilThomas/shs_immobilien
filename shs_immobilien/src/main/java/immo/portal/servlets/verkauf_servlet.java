package immo.portal.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import immo.portal.bean.Haustyp_Bean;
import immo.portal.bean.kaufen_bean;
import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

/**
 * Servlet implementation class verkauf_servlet
 */
@WebServlet("/verkauf_servlet")
@MultipartConfig(maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5
		* 5, location = "/tmp", fileSizeThreshold = 1024 * 1024)
public class verkauf_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(lookup = "java:jboss/datasources/MySqlweb_db_ttsDS")
	private DataSource ds;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public verkauf_servlet() {
		super();
	}

	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		

		
		try {
			List<Haustyp_Bean> listHaustyp_Bean = list();
			request.setAttribute("listHaustyp_Bean", listHaustyp_Bean);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/verkaufen.jsp");
			dispatcher.forward(request, response);
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}
	
	
	public List<Haustyp_Bean> list() throws SQLException {
		ArrayList<Haustyp_Bean> listHaustyp_Bean = new ArrayList<>();
		
		try (Connection con = ds.getConnection()){
			String sql = "SELECT * FROM haustyp ORDER BY typ";
			Statement statement = con.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			while (result.next()) {
				int id = result.getInt("id");
				String typ = result.getString("typ");
				Haustyp_Bean haustyp_bean = new Haustyp_Bean(id, typ);
				
				listHaustyp_Bean.add(haustyp_bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		
		return listHaustyp_Bean;
	}
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("htyp_edit_absenden") != null) {
			String hausTyp = request.getParameter("htyp_edit");
			if (hausTyp.isEmpty())
				return;

			HausTypHinzufuegen(hausTyp);
		}
		if (request.getParameter("btyp_edit_absenden") != null) {
			String bauTyp = request.getParameter("btyp_edit");
			if (bauTyp.isEmpty())
				return;

			BauTypHinzufuegen(bauTyp);
		}

		if (request.getParameter("vformular_absenden") != null) {
			DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
			String fhaustyp = request.getParameter("haustyp");
			String fbautyp = request.getParameter("bautyp");
			String ftitel = request.getParameter("titel");
			String fbaujahr = request.getParameter("baujahr");
			Integer fwohnflaeche = (Integer.valueOf(request.getParameter("wohnflaeche")));
			Integer fgrundstuecksflaeche = (Integer.valueOf(request.getParameter("grundstuecksflaeche")));
			java.sql.Date fdatum = null;
			try {
				fdatum = new java.sql.Date(dateFormat.parse(request.getParameter("datum")).getTime());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String fstandort = request.getParameter("standort");
			Integer fstartgebot = (Integer.valueOf(request.getParameter("startgebot")));
			String fbeschreibung = request.getParameter("beschreibung");
			Part fbilder = request.getPart("bilder");

			java.util.Date date = new java.util.Date();
			java.sql.Date aktdatum = new Date(date.getTime());

			if (fhaustyp.isEmpty() || fbautyp.isEmpty() || ftitel.isEmpty() || fbaujahr.isEmpty() || fwohnflaeche < 0
					|| fgrundstuecksflaeche < 0 || fdatum.before(aktdatum) || fstandort.isEmpty() || fbilder == null)
				return;

			VerkaufFormularAbschicken(fhaustyp, fbautyp, ftitel, fbaujahr, fwohnflaeche, fgrundstuecksflaeche,
					fstandort, fstartgebot, fbeschreibung, fbilder, fdatum);
		}

		response.sendRedirect("jsp/verkaufen.jsp");

	}

	private void BauTypHinzufuegen(String bauTyp) throws ServletException {
		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement("SELECT * FROM bautyp WHERE typ LIKE?")) {
			pstmt.setString(1, bauTyp);
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					return;
				}
				PreparedStatement statement = con.prepareStatement("INSERT INTO bautyp (typ) VALUES (?)");
				statement.setString(1, bauTyp);
				statement.executeUpdate();
			}
		} catch (Exception e) {
			throw new ServletException(e.getMessage());
		}
	}

	private void HausTypHinzufuegen(String hausTyp) throws ServletException {
		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement("SELECT * FROM haustyp WHERE typ LIKE?")) {
			pstmt.setString(1, hausTyp);
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					return;
				}
				PreparedStatement statement = con.prepareStatement("INSERT INTO haustyp (typ) VALUES (?)");
				statement.setString(1, hausTyp);
				statement.executeUpdate();
			}
		} catch (Exception e) {
			throw new ServletException(e.getMessage());
		}
	}

	private void VerkaufFormularAbschicken(String fhaustyp, String fbautyp, String ftitel, String fbaujahr,
			Integer fwohnflaeche, Integer fgrundstuecksflaeche, String fstandort, Integer fstartgebot,
			String fbeschreibung, Part fbilder, java.sql.Date fdatum) throws ServletException {
		try (Connection con = ds.getConnection();
				InputStream is = fbilder.getInputStream();
				PreparedStatement pstmt = con.prepareStatement(
						"INSERT INTO objekte (haustyp, bautyp, titel, baujahr, wohnflaeche, grundstuecksflaeche, standort, datum, startgebot, beschreibung, bilder) VALUES (?,?,?,?,?,?,?,?,?,?,?)")) {
			pstmt.setString(1, fhaustyp);
			pstmt.setString(2, fbautyp);
			pstmt.setString(3, ftitel);
			pstmt.setString(4, fbaujahr);
			pstmt.setInt(5, fwohnflaeche);
			pstmt.setInt(6, fgrundstuecksflaeche);
			pstmt.setString(7, fstandort);
			pstmt.setDate(8, fdatum);
			pstmt.setInt(9, fstartgebot);
			pstmt.setString(10, fbeschreibung);
			pstmt.setBlob(11, is);
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new ServletException(e.getMessage());
		}
	}

}
