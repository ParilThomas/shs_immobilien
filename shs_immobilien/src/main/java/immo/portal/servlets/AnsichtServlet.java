package immo.portal.servlets;

import java.io.IOException;
import java.util.List;

import javax.sql.DataSource;

import data.AnsichtData;
import immo.portal.bean.ObjektBean;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/AnsichtServlet")
public class AnsichtServlet extends HttpServlet {
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
		 * Leitet auf die ansicht.jsp weiter
		 */
		response.sendRedirect("jsp/ansicht.jsp");
	}

	
	/**
	 * @Fehler ServletException - Servlet Ablauffehler 
	 * @Fehler IOException		- Input / Output Fehler
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * Neues AnsichtData Objekt wird erstellt mit der übergebenen dataSource
		 */
		AnsichtData ansichtData = new AnsichtData(dataSource);
		/**
		 * Session wird initialisiert
		 */
		HttpSession session = request.getSession();
		
		/**
		 * Wenn der "ihrangebot"-Button gedrückt wurde
		 */
		if (request.getParameter("ihrangebot") != null) {
			/**
			 * hole den Wert des Buttons und speichere Ihn in benutzerid
			 */
			Integer benutzerid = Integer.valueOf(request.getParameter("ihrangebot"));
			/**
			 * Wenn die benutzerid NICHT null ist
			 */
			if (benutzerid != null) {		
				/**
				 * In der Klasse ansichtData wird die Methode "eigeneAngebote" mit dem
				 * von oben gespeicherten Buttenwert aufgerufen um die eigenen Objekte
				 * des eingeloggten Benutzers zu erhalten.
				 */
				List<ObjektBean> eigeneobjekte = ansichtData.eigeneAngebote(benutzerid);
				/**
				 * Speichere die "eigeneobjekte" in der Session
				 */
				session.setAttribute("eigeneobjekte", eigeneobjekte);
			}
		}
		

		/**
		 * Wenn der "ihrangebot"-Button gedrückt wurde
		 */
		if (request.getParameter("ihrgebot") != null) {
			/**
			 * hole den Wert des Buttons und speichere Ihn in benutzerid
			 */
			Integer benutzerid = Integer.valueOf(request.getParameter("ihrgebot"));
			/**
			 * Wenn die benutzerid NICHT null ist
			 */
			if (benutzerid != null) {
				/**
				 * In der Klasse ansichtData wird die Methode "eigeneGebote" mit dem
				 * von oben gespeicherten Buttenwert aufgerufen um die eigenen Objekte
				 * in denen man höchstbietender ist, des eingeloggten Benutzers zu erhalten.
				 */
				List<ObjektBean> eigeneobjekte = ansichtData.eigeneGebote(benutzerid);
				/**
				 * Speichere die "eigeneobjekte" in der Session
				 */
				session.setAttribute("eigeneobjekte", eigeneobjekte);	
			}
		}
		/**
		 * ruft die doGet auf und leitet dadurch auf die ansicht.jsp weiter
		 */
		this.doGet(request, response);
	}
		
}
