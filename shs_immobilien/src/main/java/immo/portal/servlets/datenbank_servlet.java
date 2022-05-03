package immo.portal.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import immo.portal.bean.FormBean;
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
 * Servlet implementation class datenbank_servelet
 */
@WebServlet("/datenbank_servlet")
//Test Filebehandlung - Thomas Schwarzmeier
//location tmp -> erst anlegen ?
@MultipartConfig(maxFileSize = 1024*1024*5, maxRequestSize = 1024*1024*5*5, location = "/tmp", fileSizeThreshold = 1024*1024)



public class datenbank_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(lookup = "java:jboss/datasources/MySqlweb_db_ttsDS")
	private DataSource ds;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public datenbank_servlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("UTF-8");

		FormBean form1 = new FormBean();
		form1.setVorname(request.getParameter("vorname"));
		form1.setNachname(request.getParameter("nachname"));
		form1.setTelefon(Integer.valueOf(request.getParameter("telefon")));
		form1.setEmail(request.getParameter("mail"));
		form1.setAnliegen(request.getParameter("anliegen"));
		
		//Logausgabe über empfangene Parts
		for (Part p: request.getParts()) {
			log("Part received: "+p.getName());
			if(p.getSubmittedFileName()!=null) {
				log("Filename written via BinaryStream: "+p.getSubmittedFileName());
			}
		}
		
	
		//Bild übertragen
		
		
	    //Filebehandlung
		
		Part filepart = request.getPart("image");
		form1.setFilename(filepart.getSubmittedFileName());

		// DB-Zugriff

		persist(form1,filepart);

		// Scope -Session
		final HttpSession session = request.getSession();
		session.setAttribute("form1", form1);
		

		// Weiterleiten an JSP
		// Redirect -> Änderung auf Datenbankeinträge durch Reload

		response.sendRedirect("jsp/kontakt_formular1.jsp");

	}

	private void persist(FormBean form1, Part filepart) throws ServletException {

		// DB-Zugriff - Test - Thomas Schwarzmeier

		String[] generateKeys = new String[] { "id" }; // Primärschlüssel festlegen

		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(
						"INSERT INTO customer(firstname,lastname,telefon,email,anliegen, filename,file) VALUES (?,?,?,?,?,?,?)",
						generateKeys)) {

			pstmt.setString(1, form1.getVorname());
			pstmt.setString(2, form1.getNachname());
			pstmt.setInt(3, form1.getTelefon());;
			pstmt.setString(4, form1.getEmail());
			pstmt.setString(5, form1.getAnliegen());
			pstmt.setString(6, form1.getFilename());
			pstmt.setBinaryStream(7, filepart.getInputStream());
			pstmt.executeUpdate();

			// Generierten Schlüssel auslesen

			try (ResultSet rs = pstmt.getGeneratedKeys()) {
				int i = 1;
				while (rs.next()) {
					form1.setId(rs.getLong(1));
				}
			} 
			
		}catch (Exception e) {
				throw new ServletException(e.getMessage());
			}

		}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
