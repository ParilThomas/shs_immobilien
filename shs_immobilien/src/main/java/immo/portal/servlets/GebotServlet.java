package immo.portal.servlets;

import java.io.IOException;

import javax.sql.DataSource;

import data.BietenData;
import data.GebotData;
import data.HaustypData;
import data.ObjektData;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class BietenServlet
 */
@WebServlet("/GebotServlet")
public class GebotServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(lookup = "java:jboss/datasources/MySqlweb_db_ttsDS")
	private DataSource dataSource;	
	private HttpSession session;
	private ObjektData objektData;
	private BietenData bietenData;
	private HaustypData haustypData;
	private GebotData gebotData;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GebotServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession();
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.gebotData = new GebotData(dataSource);
		session = request.getSession();
				
//		if (request.getParameter("gebot_absenden") != null) {
//			Integer gebot = (Integer.valueOf(request.getParameter("gebot")));
//			String id = request.getParameter("gebot_absenden");
//			if (gebot < 0) {
//				return ;	
//			
//			} else {
//				gebotData.gebotAktualisieren(gebot, id);
//			}
//		}
		
		session.setAttribute("GebotZuNiedrig", false);
		session.setAttribute("GebotIstOk", false);
		
		if (request.getParameter("gebot_absenden") != null) {
			Integer gebot = (Integer.valueOf(request.getParameter("gebot")));
			String id = request.getParameter("gebot_absenden");
			if (gebot < 0) {
				return;	
			}
			//Check ob Haustyp bereits vorhanden
			if(gebotData.istGebotZuKlein(gebot, id)) {
				session.setAttribute("GebotZuNiedrig", true);
			//Falls nicht neuen Haustyp hinzufügen
			}
			if(gebotData.istGebotOk(gebot, id)) {
				gebotData.gebotAktualisieren(gebot, id);
//				session.setAttribute("GebotIstOk", true);
			}
		}

		response.sendRedirect("html/geboterfolgreich.html");
}
}