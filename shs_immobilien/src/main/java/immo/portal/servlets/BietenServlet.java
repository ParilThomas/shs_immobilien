package immo.portal.servlets;

import java.io.IOException;

import javax.sql.DataSource;

import data.BietenData;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/BietenServlet")
public class BietenServlet extends HttpServlet {
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
		 * Leitet auf die bieten.jsp weiter
		 */
        response.sendRedirect("jsp/bieten.jsp");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * Neues BietenData Objekt wird erstellt mit der übergebenen dataSource
		 */
		BietenData bietenData = new BietenData(dataSource);
		/**
		 * Session wird initialisiert
		 */
		HttpSession session = request.getSession();
				
		/**
		 * Ist der Button "detailid" gedrück worden
		 */
		if (request.getParameter("detailid") != null) {
			/**
			 * Hole den Wert des Buttons "detailid" und speichere ihn in "hausId"
			 */
            String hausId = request.getParameter("detailid");
			/**
			 * In der Klasse bietenData wird die Methode "getObjekt" mit dem
			 * von oben gespeicherten Buttenwert aufgerufen um die Daten zu 
			 * dem angeklickten Objekt zu erhalten
			 * 
			 * Anschließend wird der Wert in die Session gespeichert
			 */
			session.setAttribute("objekt", bietenData.getObjekt(hausId));
		}
		/**
		 * ruft die doGet auf und leitet dadurch auf die bieten.jsp weiter
		 */
		this.doGet(request, response);
	}
	
}