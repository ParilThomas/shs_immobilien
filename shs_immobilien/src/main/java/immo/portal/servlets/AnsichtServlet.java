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

       
	
	
	private void kaufenSeiteAnzeigen(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
			
    		this.ansichtData = new AnsichtData(dataSource);

    		// Hier irgendwie aus dem Session objekt "benutzer" die ID ziehen dann in eigeneObjekte(id) übergeben
    		List<ObjektBean> eigeneObjekte = ansichtData.eigeneObjekte(null)

    				
            response.sendRedirect("jsp/ansicht.jsp");
 
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
