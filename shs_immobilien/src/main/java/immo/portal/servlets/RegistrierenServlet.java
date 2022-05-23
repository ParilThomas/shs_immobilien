package immo.portal.servlets;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.sql.DataSource;


import data.RegistrierenData;
import immo.portal.bean.RegistrierenBean;
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
 * Servlet implementation class RegistrierenServlet
 */
@WebServlet("/RegistrierenServlet")
@MultipartConfig(maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5
		* 5, location = "/tmp", fileSizeThreshold = 1024 * 1024)
public class RegistrierenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(lookup = "java:jboss/datasources/MySqlweb_db_ttsDS")
	private DataSource dataSource;	
	private HttpSession session;
	private RegistrierenData registrierenData;
	
	
	
	private void registrierenSeiteAnzeigen(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	 
	        	this.registrierenData = new RegistrierenData(dataSource);
	    	
	    		
	            response.sendRedirect("jsp/registrieren.jsp");
	 
	    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
	        // Scope -Session -> So funktionierts aber nur wenn Session gestartet ist also erst servlet dann html gestartet wird -> unknown datum in field list 
	   		session = request.getSession();
			
	  		registrierenSeiteAnzeigen(request, response);

		}
		
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.registrierenData = new RegistrierenData(dataSource);
		
  		session = request.getSession();
  		
  		//Anfänglich auf false setzen 
//  		session.setAttribute("IstRegistriert", false);
  		
  		

		if (request.getParameter("rformular_absenden") != null) {
			String rvorname = request.getParameter("vorname");
			String rnachname = request.getParameter("nachname");
			String ranschrift = request.getParameter("anschrift");
			Integer rplz = (Integer.valueOf(request.getParameter("plz")));
			String rwohnort = request.getParameter("wohnort");
			Integer rtelefon = (Integer.valueOf(request.getParameter("telefon")));
			String remail = request.getParameter("email");
			String rpasswort1 = request.getParameter("passwort1");
			
			//Check ob Benutzer bereits registriert
			
//			if(registrierenData.istRegistriert(remail, rpasswort1)) {
//				session.setAttribute("istRegistriert", true);
//			}else {
//				//Was passiert wenn der Benutzer noch nicht registriert ist 
//			}
//			

			if (rvorname.isEmpty() || rnachname.isEmpty() || ranschrift.isEmpty() || rplz <0 || rwohnort.isEmpty()
					|| rtelefon < 0 || remail.isEmpty() || rpasswort1.isEmpty())
				return;

			registrierenData.registrierenFormularabschicken(rvorname, rnachname, ranschrift, rplz, rwohnort, rtelefon, remail, rpasswort1);
		}
		//Wieder eine HTML Seite damit Seite wieder aktualisert wird -> Es wird geprüft ob Benutzer registriert ist 
		
		response.sendRedirect("jsp/registrieren.jsp");		
		
	}


}