package immo.portal.servlets;

import java.io.IOException;

import javax.sql.DataSource;

import data.GebotData;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/GebotServlet")
public class GebotServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Ressourcenreferenz deklarieren
	 */
	@Resource(lookup = "java:jboss/datasources/MySqlweb_db_ttsDS")
	private DataSource dataSource;	
  

	/**
	 * @Fehler ServletException - Servlet Ablauffehler 
	 * @Fehler IOException		- Input / Output Fehler
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * Neues GebotData Objekt wird erstellt mit der übergebenen dataSource
		 */
		GebotData gebotData = new GebotData(dataSource);
		/**
		 * Session wird initialisiert
		 * Sessionvariable GebotZuNiedrig wird auf false gesetzt
		 * Sessionvariable GebotIstOk wird auf false gesetzt
		 */
		HttpSession session = request.getSession();	
		session.setAttribute("GebotZuNiedrig", false);
		session.setAttribute("GebotIstOk", false);
		
		/**
		 * Wurde der "gebot_absenden" Button gedrückt
		 */
		if (request.getParameter("gebot_absenden") != null) {
			/**
			 * Hole den Wert aus dem Textfeld "gebot"
			 * Hole den Wert des Buttons "gebot_absenden"
			 * Hole den Wert des versteckten Buttons "benutzer"
			 */
			Integer gebot = (Integer.valueOf(request.getParameter("gebot")));
			String id = request.getParameter("gebot_absenden");
			Integer benutzerid = (Integer.valueOf(request.getParameter("benutzer")));
			
			/**
			 * Wenn das Gebot klein 0 ist return null
			 */
			if (gebot < 0) {
				return;	
			}
			/**
			 * Ruft in der Klasse gebotData die boolean Methode "istGebotZuKlein" mit gebot und id auf
			 * kommt der Wert true zurück setze die Sessionvariable "GebotZuNiedrig" auf true
			 */
			if(gebotData.istGebotZuKlein(gebot, id)) {
				session.setAttribute("GebotZuNiedrig", true);
			}
			/**
			 * Ruft in der Klasse gebotData die boolean Methode "istGebotOk" mit gebot und id auf
			 * kommt der Wert true zurück rufe die Methode "gebotAktualisieren" in der Klasse gebotData auf
			 * und setze anschließend die Sessionvariable "GebotIstOk" auf true
			 */
			if(gebotData.istGebotOk(gebot, id)) {		
				gebotData.gebotAktualisieren(gebot, id, benutzerid);
				session.setAttribute("GebotIstOk", true);
			}
		}
		/**
		 * Weiterleitung auf das KaufenServlet und ruft durch die doGet die kaufen.jsp auf
		 */
		response.sendRedirect("KaufenServlet");
	}
	
}