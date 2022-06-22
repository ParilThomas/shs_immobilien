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
	
	@Resource(lookup = "java:jboss/datasources/MySqlweb_db_ttsDS")
	private DataSource dataSource;
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("jsp/ansicht.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		AnsichtData ansichtData = new AnsichtData(dataSource);
		HttpSession session = request.getSession();
		
		if (request.getParameter("ihrangebot") != null) {
			Integer benutzerid = Integer.valueOf(request.getParameter("ihrangebot"));
			if (benutzerid != null) {
				List<ObjektBean> eigeneobjekte = ansichtData.eigeneAngebote(benutzerid);
				session.setAttribute("eigeneobjekte", eigeneobjekte);
			}
		}
		

		if (request.getParameter("ihrgebot") != null) {
			Integer benutzerid = Integer.valueOf(request.getParameter("ihrgebot"));
			if (benutzerid != null) {
				List<ObjektBean> eigeneobjekte = ansichtData.eigeneGebote(benutzerid);
				session.setAttribute("eigeneobjekte", eigeneobjekte);	
			}
		}
		this.doGet(request, response);
	}
		
}
