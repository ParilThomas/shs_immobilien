package immo.portal.servlets;

import java.io.IOException;
import java.util.List;

import javax.sql.DataSource;

import data.HaustypData;
import immo.portal.bean.HaustypBean;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class KaufenServlet
 */
@WebServlet("/KaufenServlet")
public class KaufenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(lookup = "java:jboss/datasources/MySqlweb_db_ttsDS")
	private DataSource dataSource;	
	private HttpSession session;
	private HaustypData haustypData;
       

	private void kaufenSeiteAnzeigen(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
    		this.haustypData = new HaustypData(dataSource);
		
            List<HaustypBean> haustyplist = haustypData.alleHaustypen(); 
 
    		session.setAttribute("haustyplist", haustyplist);
    			
            response.sendRedirect("jsp/kaufen.jsp");
 
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession();
		
		
		kaufenSeiteAnzeigen(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession();
		
		response.sendRedirect("jsp/kaufen.jsp");	
	}

}