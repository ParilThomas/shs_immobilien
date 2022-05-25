package immo.portal.servlets;

import java.io.IOException;
import java.util.List;

import javax.sql.DataSource;

import data.AnsichtData;
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

@WebServlet("/AnsichtServlet")
public class AnsichtServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(lookup = "java:jboss/datasources/MySqlweb_db_ttsDS")
	private DataSource dataSource;
	private HttpSession session;
	private AnsichtData ansichtData;


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		session = request.getSession();
	}

	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.ansichtData = new AnsichtData(dataSource);
		session = request.getSession();
		
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
		
		
		
		
		response.sendRedirect("jsp/ansicht.jsp");
	}
		
}
