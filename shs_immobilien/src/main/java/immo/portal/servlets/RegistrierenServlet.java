/** @author Simon Schrödl */

package immo.portal.servlets;

import java.io.IOException;

import javax.sql.DataSource;

import data.RegistrierenData;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/RegistrierenServlet")
@MultipartConfig(maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5, location = "/tmp", fileSizeThreshold = 1024 * 1024)

public class RegistrierenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Ressourcenreferenz deklarieren
	 */
	@Resource(lookup = "java:jboss/datasources/MySqlweb_db_ttsDS")
	private DataSource dataSource;	
	
	/**
	 * doGet wird standardmäßig aufgerufen
	 * 
	 * @Fehler ServletException - Servlet Ablauffehler 
	 * @Fehler IOException		- Input / Output Fehler
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		/**
		 * Weiterleitung auf registrieren.jsp
		 */
		response.sendRedirect("jsp/registrieren.jsp");	 
	}
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * Neues RegistrierenData Objekt wird erstellt mit der übergebenen dataSource
		 */
		RegistrierenData registrierenData = new RegistrierenData(dataSource);	
		
		/**
		 * Session wird initialisiert
		 * Sessionvariable "emailExistiert" wird auf false gesetzt
		 */
		HttpSession session = request.getSession();
		session.setAttribute("emailExistiert", false);
		
		/**
		 * Wird das Registrieren Formular abgeschickt werden die Werte abgerufen
		 * und in lokale Variable gespeichert
		 */
		String rvorname = request.getParameter("vorname");
		String rnachname = request.getParameter("nachname");
		String ranschrift = request.getParameter("anschrift");
		String rplz = request.getParameter("plz");
		String rwohnort = request.getParameter("wohnort");
		String rtelefon = request.getParameter("telefon");
		String remail = request.getParameter("email");
		String rpasswort = request.getParameter("passwort");
		String passwortwdh = request.getParameter("passwortwdh");
		
		/**
		 * Überprüfen ob die E-mail bereites vorhanden ist
		 * In der Klasse registrierenData wird die Methode "emailVorhanden" mit dem lokalen Parameter remail aufgerufen
		 * ist der Wert true
		 */
		if (registrierenData.emailVorhanden(remail)) {
			/**
			 * Setze die Sessionvariable "emailExistiert" auf true
			 * Weiterleitung auf RegistrierenServlet durch die doGet dann auf registrieren.jsp
			 */
			session.setAttribute("emailExistiert", true);
			response.sendRedirect("RegistrierenServlet");
			return;
			/**
			 * Ist die Email noch nicht vorhanden und die beiden Passwörter stimmen überein
			 * Rufe in der Klasse registrierenData die Methode registrierenFormularabschicken mit den lokalen Variablen auf
			 * dadurch wird ein neuer Benutzer in der Datenbank angelegt
			 */
			} else if (rpasswort.equals(passwortwdh)) {
			registrierenData.registrierenFormularabschicken(rvorname, rnachname, ranschrift, rplz, rwohnort, rtelefon, remail, rpasswort);
			}			
		/**
		 * Weiterleitung auf LoginServlet durch die doGet dann auf login.jsp
		 */
		response.sendRedirect("LoginServlet");			
	}

}