package immo.portal.servlets;

import java.io.IOException;

import javax.sql.DataSource;

import data.LoginData;
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
	@Resource(lookup = "java:jboss/datasources/MySqlweb_db_ttsDS")
	private DataSource dataSource;
	private HttpSession session;
	private LoginData loginData;


	private void loginSeiteAnzeigen(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        	this.loginData = new LoginData(dataSource);
		
            response.sendRedirect("jsp/login.jsp");
 
    }
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   		session = request.getSession();
		
   		loginSeiteAnzeigen(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.loginData = new LoginData(dataSource);
		session = request.getSession();

		session.setAttribute("istRegistriert", false);
		session.setAttribute("istNichtRegistriert", false);

		if (request.getParameter("login_absenden") != null) {
			String email = request.getParameter("email");
			String passwort = request.getParameter("passwort");
			if (email.isEmpty()|| passwort.isEmpty()) {
				return;
			}
			if (loginData.istRegistriert(email,passwort)) {
				session.setAttribute("istRegistriert", true);
				session.setAttribute("email", email);
			} else {
				session.setAttribute("istNichtRegistriert", true);
			}
		}
		response.sendRedirect("jsp/login.jsp");
	}

}
