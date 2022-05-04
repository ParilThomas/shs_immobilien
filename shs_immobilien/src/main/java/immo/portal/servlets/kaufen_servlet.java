package immo.portal.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import immo.portal.bean.FormBean;
import immo.portal.bean.kaufen_bean;
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
 * Servlet implementation class kaufen_servlet
 */
@WebServlet("/kaufen_servlet")
//Test Filebehandlung - Thomas Schwarzmeier
//location tmp -> erst anlegen ?
@MultipartConfig(maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5
		* 5, location = "/tmp", fileSizeThreshold = 1024 * 1024)
public class kaufen_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(lookup = "java:jboss/datasources/MySqlweb_db_ttsDS")
	private DataSource ds;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public kaufen_servlet() {
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
		String haustyp = request.getParameter("haustyp");
		
		
        //Filebehandlung
		Part filepart = request.getPart("bilder");

        //DB-Zugriff
		kaufen_bean kform =read(haustyp);
		
		
        // Scope -Session
		final HttpSession session = request.getSession();
		session.setAttribute("kform", kform);

		// Weiterleiten an JSP
		// Redirect -> Änderung auf Datenbankeinträge durch Reload

		response.sendRedirect("jsp/kaufen.jsp");

	}

	
	//Lesen
	private kaufen_bean read(String haustyp) throws ServletException{
		
		kaufen_bean kform = new kaufen_bean();
		kform.setHaustyp(haustyp);
		
		//DB-Zugriff
		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement("SELECT * FROM objekte WHERE haustyp=?")){
			for(int i = 1; i<10; i++) {
		
				pstmt.setString(i, haustyp);
			
			}
			
			try(ResultSet rs= pstmt.executeQuery()) {
				if(rs !=null & rs.next()) {
					kform.setHaustyp(rs.getString("haustyp"));
					kform.setStartgebot(rs.getInt("startgebot"));
				}
			}
		}catch(Exception e) {
			throw new ServletException(e.getMessage());
		}
				
		return kform;
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
