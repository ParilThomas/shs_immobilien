package immo.portal.servlets;

import java.io.IOException;
import java.util.List;

import javax.sql.DataSource;

import data.SuchenData;
import immo.portal.bean.ObjektBean;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/SuchenServlet")
public class SuchenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Ressourcenreferenz deklarieren
	 */
	@Resource(lookup = "java:jboss/datasources/MySqlweb_db_ttsDS")
	private DataSource dataSource;	
      
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/**
		 * Session wird initialisiert
		 */
		HttpSession session = request.getSession();
		
		/**
		 * Neues SuchenData Objekt wird erstellt mit der übergebenen dataSource
		 */
		SuchenData suchenData = new SuchenData(dataSource);		
		
		/**
		 * Lokale Variable suchvar wird mit dem Wert aus dem Such-Textfeld belegt
		 */
		String suchvar = request.getParameter("suchvar");
		/**
		 * In der Klasse suchenData wird die Methode getSuchObjekte mit dem Parameter suchvar aufgerufen
		 * die gefundenen Objekte werden in einer Liste objekte gespeichert und in die Session gelegt
		 */
		List<ObjektBean> objekte = suchenData.getSuchObjekte(suchvar);
		session.setAttribute("objekte", objekte);
		
		/**
		 * Direkte Weiterleitung auf suchen.jsp
		 */
	    response.sendRedirect("jsp/suchen.jsp");
	}

}
