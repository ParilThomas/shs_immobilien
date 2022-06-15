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
	
	@Resource(lookup = "java:jboss/datasources/MySqlweb_db_ttsDS")
	private DataSource dataSource;	
      

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("doPost SuchenServlet");
		HttpSession session = request.getSession();
		
		SuchenData suchenData = new SuchenData(dataSource);			
		String suchvar = request.getParameter("suchvar");
		System.out.println(suchvar);
		List<ObjektBean> objekte = suchenData.getSuchObjekte(suchvar);
		session.setAttribute("objekte", objekte);
		
	    response.sendRedirect("jsp/suchen.jsp");
	}

}
