package immo.portal.servlets;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {	
	private static final long serialVersionUID = 1L;
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		/**
		 * Session wird initialisiert
		 * Sessionvariable benutzer wird auf null gesetzt
		 * Sessionvariable ausgeloggt wird auf true gesetzt
		 * Weiterleitung auf das LoginServlet durch die doGet dann auf login.jsp
		 */
		HttpSession session = request.getSession();
		session.setAttribute("benutzer", null);
		session.setAttribute("ausgeloggt", true);
		response.sendRedirect("LoginServlet");
	}
}
