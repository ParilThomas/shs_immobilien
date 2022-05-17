package immo.portal.servlets;

import java.io.IOException;
import java.util.List;

import javax.sql.DataSource;

import data.BautypData;
import data.BietenData;
import data.HaustypData;
import data.ObjektData;
import immo.portal.bean.BautypBean;
import immo.portal.bean.HaustypBean;
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
	private ObjektData objektData;
	private BietenData bietenData;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BietenServlet() {
        super();
    }


    private void bietenSeiteAnzeigen(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
    		this.objektData = new ObjektData(dataSource);
    		this.bietenData = new BietenData(dataSource);
	
            response.sendRedirect("jsp/bieten.jsp");
 
    }
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession();
		bietenSeiteAnzeigen(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.objektData = new ObjektData(dataSource);
		
		session = request.getSession();
		
		if (request.getParameter("hausid") != null) {
			Integer hid = Integer.valueOf(request.getParameter("hausid"));
			if (hid != null) {
				List<ObjektBean> objekt = this.bietenData.getObjekt(hid);
				session.setAttribute("objekt", objekt);
				response.sendRedirect("jsp/bieten.jsp");
				return;	
			
			} else {
				return;
			}
		
	}
 doGet(request, response);
}
}
