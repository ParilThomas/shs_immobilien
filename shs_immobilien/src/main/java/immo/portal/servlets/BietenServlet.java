/**
 * @author Tom1, Tom2
 * 
 * fgdfg
 */

package immo.portal.servlets;

import java.io.IOException;
import java.util.List;

import javax.sql.DataSource;

import data.BietenData;
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

/**
 * Servlet implementation class BietenServlet
 */
@WebServlet("/BietenServlet")
public class BietenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(lookup = "java:jboss/datasources/MySqlweb_db_ttsDS")
	private DataSource dataSource;	
     

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("jsp/bieten.jsp");
	}
	
	
	/**
	 * Description of the method
	 * 
	 * @param request  - HttpServletRequest asdasdasdasd
	 * @param response - HttpServletResponse asdasdasdasd
	 * 
	 * @throws ServletException
	 * @throws IOException
	 * 
	 * @return void
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BietenData bietenData = new BietenData(dataSource);
		HttpSession session = request.getSession();
				
		if (request.getParameter("detailid") != null) {
            String hausId = request.getParameter("detailid");
			session.setAttribute("objekt", bietenData.getObjekt(hausId));
		}

		this.doGet(request, response);
	}
}