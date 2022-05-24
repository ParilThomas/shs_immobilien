package immo.portal.servlets;

import java.io.IOException;
import java.util.List;

import javax.sql.DataSource;

import data.LoginData;
import immo.portal.bean.ObjektBean;
import immo.portal.bean.RegistrierenBean;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(lookup = "java:jboss/datasources/MySqlweb_db_ttsDS")
	private DataSource dataSource;
	private HttpSession session;
	private LoginData loginData;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		session = request.getSession();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.loginData = new LoginData(dataSource);
		session = request.getSession();

		session.setAttribute("istRegistriert", false);
		session.setAttribute("istNichtRegistriert", false);

		// Bautyp check ob schon vorhanden
		if (request.getParameter("login_absenden") != null) {
			String email = request.getParameter("email");
			String passwort = request.getParameter("passwort");
			if (email.isEmpty()|| passwort.isEmpty()) {
				return;
			}
			// Check ob Bautyp bereits vorhanden
			if (loginData.istRegistriert(email,passwort)) {
				session.setAttribute("istRegistriert", true);
				
				// Falls nicht neuen Bautyp hinzufügen
			} else {
				session.setAttribute("istNichtRegistriert", true);
			}
		}
		response.sendRedirect("jsp/login.jsp");
	}

}
