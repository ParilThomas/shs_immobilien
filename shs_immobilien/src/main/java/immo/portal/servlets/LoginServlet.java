/** @author Thomas Schwarzmeier */

package immo.portal.servlets;

import java.io.IOException;

import javax.sql.DataSource;

import data.LoginData;
import immo.portal.bean.BenutzerBean;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {	
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
		 * Weiterleitung auf login.jsp
		 */
		response.sendRedirect("jsp/login.jsp"); 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * Neues LoginData Objekt wird erstellt mit der übergebenen dataSource
		 */
		LoginData loginData = new LoginData(dataSource);
		
		/**
		 * Session wird initialisiert
		 * Methode resetSession wird aufgerufen um die Session zurückzusetzen
		 */
		HttpSession session = request.getSession();
		this.resetSession(session);

		/**
		 * Varialbe email wird mit dem Wert aus der in der Session gespeicherten "email" belegt
		 * Varialbe passwort wird mit dem Wert aus der Session gespeicherten "passwort" belegt
		 */
		String email    = request.getParameter("email");
		String passwort = request.getParameter("passwort");
		
		/**
		 * Ist die email ODER das passwort leer mach nichts
		 */
		if (email.isEmpty() || passwort.isEmpty()) {
			return;
		}
		
		/**
		 * Rufe in der Klasse loginData die Methode "istRegistriert" mit dem Parameter "email" auf
		 * 
		 * Ist die email NICHT registriert
		 */
		if (!loginData.istRegistriert(email)) {
			/**
			 * Setze die Sessionvariable istNichtRegistriert auf true
			 * leite weiter auf das LoginServlet und durch die doGet auf die login.jsp
			 */
			session.setAttribute("istNichtRegistriert", true);
			response.sendRedirect("LoginServlet");
			return;
		}

		/**
		 * Rufe in der Klasse loginData die Methode "holeBenutzer" mit dem Parameter "email" & "passwort" auf
		 * ist ein Benutzer vorhanden wird er in "benutzer" gespeichert
		 */
		BenutzerBean benutzer = loginData.holeBenutzer(email, passwort);
		/**
		 * Ist der benutzer NICHT null
		 */
		if (benutzer != null) {
			/**
			 * Speichere den Benutzer in die Session
			 * Passwort ist Korrekt
			 * leite auf die Startseite weiter
			 */
			session.setAttribute("benutzer", benutzer );
			response.sendRedirect("jsp/startseite.jsp");
			return;
		} else {
			/**
			 * Passwort ist NICHT Korrekt
			 * Setze die Sessionvariable "falscheLoginDaten" auf true
			 * 
			 * Leite auf das LoginServlet weiter durch die doGet auf die login.jsp
			 */
			session.setAttribute("falscheLoginDaten", true);
			response.sendRedirect("LoginServlet");
			return;
		}	
	}
		
	
	/**
	 * Methode um Variablen der Session auf null zu setzern
	 * Setzt den benutzer, istNichtRegistriert & falscheLoginDaten null
	 * aufgrund der Fehlerbehandlung
	 */
	private void resetSession(HttpSession session) {
		session.setAttribute("benutzer", null);
		session.setAttribute("istNichtRegistriert", null);
		session.setAttribute("falscheLoginDaten", null);	
	}

}
