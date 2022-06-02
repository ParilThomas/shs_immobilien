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
				loginData.holeBenutzer(email, passwort);
				List<RegistrierenBean> benutzer = this.loginData.holeBenutzer(email, passwort);
				session.setAttribute("benutzer", benutzer );
				session.setAttribute("istRegistriert", true);
				session.setAttribute("email", email);
			} else {
				session.setAttribute("istNichtRegistriert", true);
			}
		}
		
//		
//		//Cookies
//		
//		String action = request.getParameter("login_absenden");
//		String email = null;
//		String passwort = null;
//		
//		switch (action) {
//		case "absenden":
//			email = request.getParameter("email");
//			passwort = request.getParameter("passwort");
//			Cookie cookie1 = new Cookie("email", email);
//			cookie1.setMaxAge(60 * 60 * 24 * 7);
//			cookie1.setPath("/");
//			response.addCookie(cookie1);
//			
//			Cookie cookie2 = new Cookie("passwort", passwort);
//			cookie2.setMaxAge(60 * 60 * 24 * 7);
//			cookie2.setPath("/");
//			response.addCookie(cookie2);
//			
//		case "readFromCookies":
//			Cookie[] cookies = request.getCookies();
//			for (Cookie cookie:cookies) {
//				switch (cookie.getName()) {
//				case "email":
//					email = cookie.getValue();
//					break;
//				case "passwort":
//					passwort = cookie.getValue();
//					break;
//				}
//			}
//		
//		}
		
		
		response.sendRedirect("jsp/login.jsp");
	}

}
