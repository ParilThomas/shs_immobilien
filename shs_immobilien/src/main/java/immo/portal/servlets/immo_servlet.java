package immo.portal.servlets;

//Servlet-Test--TS
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import immo.portal.bean.Formular_Bean;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class immo_servlet
 */
@WebServlet("/immo_servlet")
public class immo_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public immo_servlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// HTTP-Header setzen
				response.setStatus(HttpServletResponse.SC_OK);	// nicht zwingend erforderlich; ist der default-Wert
				response.setContentType("text/html");
				response.setCharacterEncoding("UTF-8");
				
				
				
				
				Formular_Bean form = new Formular_Bean();
				form.setVorname(request.getParameter("vorname"));
				form.setNachname(request.getParameter("nachname"));
				form.setTelefon(Integer.valueOf(request.getParameter("telefon")));
				form.setEmail(request.getParameter("mail"));
				form.setAnliegen(request.getParameter("anliegen"));
//				form.setFile(request.getParameter(file));  -> funktioniert noch nicht
				
				
				//Scope "Session"
				final HttpSession session = request.getSession();
				session.setAttribute("form", form);
				
				
				
			//Redirect -> Änderung auf Datenbankeinträge durch Reload
			
			response.sendRedirect("jsp/kontakt_formular1.jsp");
				
				
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
