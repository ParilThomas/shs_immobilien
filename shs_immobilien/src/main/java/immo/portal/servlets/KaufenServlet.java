package immo.portal.servlets;

import java.io.IOException;
import java.util.List;

import javax.sql.DataSource;

import data.HaustypData;
import data.ObjektData;
import immo.portal.bean.HaustypBean;
import immo.portal.bean.ObjektBean;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/KaufenServlet")
public class KaufenServlet extends HttpServlet {
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
		 * Neues HaustypData Objekt wird erstellt mit der übergebenen dataSource
		 * 
		 * Ruft in der Klasse haustpData die Methode "alleHaustypen()" auf bekommt dadurch
		 * alles Haustypen die in der DB vorhanden sind und speichert diese in haustyplist
		 */
		HaustypData haustypData = new HaustypData(dataSource);
		List<HaustypBean> haustyplist = haustypData.alleHaustypen(); 
		
		/**
		 * Session wird initialisiert
		 * Sessionvariable haustyplist wird gesetzt
		 * Sessionvariable haustypSelektiert wird auf false gesetzt
		 */
		HttpSession session = request.getSession();	    		
		session.setAttribute("haustyplist", haustyplist);
		session.setAttribute("haustypSelektiert", false);  		
			
		/**
		 * leitet auf die kaufen.jsp weiter
		 */
        response.sendRedirect("jsp/kaufen.jsp");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * Session wird initialisiert
		 */
		HttpSession session = request.getSession();
		
		/**
		 * Neues HaustypData & ObjektData Objekt wird erstellt mit der übergebenen dataSource
		 * 
		 * Ruft in der Klasse haustpData die Methode "alleHaustypen()" auf bekommt dadurch
		 * alles Haustypen die in der DB vorhanden sind und speichert diese in haustyplist
		 */
		HaustypData haustypData = new HaustypData(dataSource);
		ObjektData objektData   = new ObjektData(dataSource);
		
		List<HaustypBean> haustyplist = haustypData.alleHaustypen(); 
		
		/**
		 * Datentyp: 	HaustypBean
		 * Element: 	bean
		 * Kollektion: 	haustyplist
		 * 
		 * Für jedes Element vom Typ HaustypBean...
		 */
		for (HaustypBean bean : haustyplist) {
			/**
			 * Ist der Typ des Elements nicht NULL
			 */
			if (request.getParameter(bean.getTyp()) != null) {
				/**
				 * Rufe in der Klasse objektData die Methode "getObjekte" mit dem Wert des Typs des Elements auf
				 * und speichere den Rückgabewert in objekte und lege die variable in die Session
				 * 
				 * Setze die Sessionvariable "haustypSelektiert" auf true -> Liste des gewählten Haustyps öffnet sich
				 * 
				 * leite direkt weiter auf die kaufen.jsp, da wir dort zwei Seiten ineinander integriert haben,
				 * deshalb keine Weiterleitung auf die doGet, sonst werden die Sessionvariablen überspeichert
				 */
				List<ObjektBean> objekte = objektData.getObjekte(bean.getTyp());
				session.setAttribute("objekte", objekte);
				
				session.setAttribute("haustypSelektiert", true);
				response.sendRedirect("jsp/kaufen.jsp");	
				return;
			}
		}	
	}
	
}
