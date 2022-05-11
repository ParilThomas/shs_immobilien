package immo.portal.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;

import javax.sql.DataSource;

import immo.portal.bean.verkauf_bean;
import jakarta.annotation.Resource;
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
//Test Filebehandlung - Thomas Schwarzmeier
//location tmp -> erst anlegen ?
@MultipartConfig(maxFileSize = 1024*1024*5, maxRequestSize = 1024*1024*5*5, location = "/tmp", fileSizeThreshold = 1024*1024)
public class verkauf_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(lookup = "java:jboss/datasources/MySqlweb_db_ttsDS")
	private DataSource ds;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public verkauf_servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		response.setCharacterEncoding("UTF-8");
		
		verkauf_bean vform = new verkauf_bean();
		vform.setHaustyp(request.getParameter("haustyp"));
		vform.setBautyp(request.getParameter("bautyp"));
		vform.setTitel(request.getParameter("titel"));
		vform.setBaujahr(Integer.valueOf(request.getParameter("baujahr")));
		vform.setWohnflaeche(Integer.valueOf(request.getParameter("wohnflaeche")));
		vform.setGrundstuecksflaeche(Integer.valueOf(request.getParameter("grundstuecksflaeche")));
		vform.setStandort(request.getParameter("standort"));
		vform.setStartgebot(Integer.valueOf(request.getParameter("startgebot")));
		vform.setBeschreibung(request.getParameter("beschreibung"));
		
		
		//Logausgabe über empfangene Parts
				for (Part p: request.getParts()) {
					log("Part received: "+p.getName());
					if(p.getSubmittedFileName()!=null) {
						log("Filename written via BinaryStream: "+p.getSubmittedFileName());
					}
				}
				
		
		//Filebehandlung
		Part filepart = request.getPart("bilder");
		vform.setDateiname(filepart.getSubmittedFileName());
		
		//DB-Zugriff
		persist(vform,filepart);
//		
//		// Datumsfeld auswerten - Eingangsformat yyyy-mm-dd
//				String dateString = request.getParameter("datum");
//				String[] dateArray = dateString.split("-");
//				Calendar cal = Calendar.getInstance();
//				int day = Integer.parseInt(dateArray[0]);
//				int month = Integer.parseInt(dateArray[1])-1;
//				int year = Integer.parseInt(dateArray[2]);
//				cal.set(year, month, day);
//				vform.setDatum(cal.getTime());
//		
//		
//		
		
		// Scope -Session
				final HttpSession session = request.getSession();
				session.setAttribute("vform", vform);
				

				// Weiterleiten an JSP
				// Redirect -> Änderung auf Datenbankeinträge durch Reload

				response.sendRedirect("jsp/verkaufen.jsp");
	
	}
	
	private void persist(verkauf_bean vform, Part filepart) throws ServletException {

		// DB-Zugriff - Test - Thomas Schwarzmeier

		String[] generateKeys = new String[] { "id" }; // Primärschlüssel festlegen

		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(
						"INSERT INTO objekte (haustyp,bautyp,titel,baujahr,wohnflaeche,grundstuecksflaeche, standort, startgebot, beschreibung, dateiname, bilder) VALUES (?,?,?,?,?,?,?,?,?,?,?)",
						generateKeys)) {

			pstmt.setString(1, vform.getHaustyp());
			pstmt.setString(2, vform.getBautyp());
			pstmt.setString(3, vform.getTitel());
			pstmt.setInt(4, vform.getBaujahr());
			pstmt.setInt(5, vform.getWohnflaeche());
			pstmt.setInt(6, vform.getGrundstuecksflaeche());
			pstmt.setString(7, vform.getStandort());
//			pstmt.setDate(8, new java.sql.Date(vform.getDatum().getTime()));
			pstmt.setInt(9, vform.getStartgebot());
			pstmt.setString(10, vform.getBeschreibung());
			pstmt.setString(11,vform.getDateiname());
			pstmt.setBinaryStream(12, filepart.getInputStream());
			pstmt.executeUpdate();

			// Generierten Schlüssel auslesen

			try (ResultSet rs = pstmt.getGeneratedKeys()) {
				int i = 1;
				while (rs.next()) {
					vform.setId(rs.getLong(1));
				}
			} 
			
		}catch (Exception e) {
				throw new ServletException(e.getMessage());
			}

		}

	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
