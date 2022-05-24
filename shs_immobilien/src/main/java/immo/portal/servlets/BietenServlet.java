package immo.portal.servlets;

import java.io.IOException;
import java.util.List;

import javax.sql.DataSource;

import data.BietenData;
import data.HaustypData;
import data.ObjektData;
import immo.portal.bean.ObjektBean;
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
@WebServlet("/BietenServlet")
public class BietenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(lookup = "java:jboss/datasources/MySqlweb_db_ttsDS")
	private DataSource dataSource;	
	private HttpSession session;
	private BietenData bietenData;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BietenServlet() {
        super();
    }


//    private void bietenSeiteAnzeigen(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
// 
//    		this.bietenData = new BietenData(dataSource);
//    		
//    		session = request.getSession();
//    		
//            response.sendRedirect("jsp/bieten.jsp");
//
//    }
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession();
		
//		bietenSeiteAnzeigen(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.bietenData = new BietenData(dataSource);
		session = request.getSession();
				
		
			// if abfragen funktionieren nicht hausid sowie id findet er nicht 
		if (request.getParameter("detailid") != null) {
            String hid = request.getParameter("detailid");
			if (hid != null) {
				List<ObjektBean> objekt = this.bietenData.getObjekt(hid);
				session.setAttribute("objekt", objekt);
				
			
			} 
			
			else {
				return;
			}	
	}

		response.sendRedirect("jsp/bieten.jsp");
}
}