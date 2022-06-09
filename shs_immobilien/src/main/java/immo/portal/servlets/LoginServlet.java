package immo.portal.servlets;

import java.io.IOException;
import java.util.List;

import javax.sql.DataSource;

import data.LoginData;
import immo.portal.bean.ObjektBean;
import immo.portal.bean.BenutzerBean;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Resource(lookup = "java:jboss/datasources/MySqlweb_db_ttsDS")
	private DataSource dataSource;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("jsp/login.jsp"); 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LoginData loginData = new LoginData(dataSource);		
		HttpSession session = request.getSession();
		this.resetSession(session);

		String email    = request.getParameter("email");
		String passwort = request.getParameter("passwort");
		
		// wenn email oder passwort fehlt nichts machen
		if (email.isEmpty() || passwort.isEmpty()) {
			return;
		}
		
		// prüfen ob der Benutzer nicht registriert ist
		if (!loginData.istRegistriert(email)) {
			session.setAttribute("istNichtRegistriert", true);
			response.sendRedirect("LoginServlet");
			return;
		}

		BenutzerBean benutzer = loginData.holeBenutzer(email, passwort);
		if (benutzer != null) {
			// Passwort korrekt
			session.setAttribute("benutzer", benutzer );
			response.sendRedirect("jsp/startseite.jsp");
			return;
		} else {
			// Passwort nicht korrekt
			session.setAttribute("falscheLoginDaten", true);
			response.sendRedirect("LoginServlet");
			return;
		}

		
	}
	
	
	private void resetSession(HttpSession session) {
		session.setAttribute("benutzer", null);
		session.setAttribute("istNichtRegistriert", null);
		session.setAttribute("falscheLoginDaten", null);	
	}

}
