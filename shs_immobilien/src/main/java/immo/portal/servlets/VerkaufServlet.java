package immo.portal.servlets;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.sql.DataSource;

import data.BautypData;
import data.HaustypData;
import data.ObjektData;
import immo.portal.bean.BautypBean;
import immo.portal.bean.HaustypBean;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;



@WebServlet("/VerkaufServlet")
@MultipartConfig(maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5
		* 5, location = "/tmp", fileSizeThreshold = 1024 * 1024)
public class VerkaufServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(lookup = "java:jboss/datasources/MySqlweb_db_ttsDS")
	private DataSource dataSource;	
	private HttpSession session;
	
	private BautypData bautypData;
	private HaustypData haustypData;
	private ObjektData objektData;
	
	
	private void verkaufsSeiteAnzeigen(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	 
	        	this.bautypData = new BautypData(dataSource);
	    		this.haustypData = new HaustypData(dataSource);
	    		this.objektData = new ObjektData(dataSource);
	    		
	            List<HaustypBean> haustyplist = haustypData.alleHaustypen(); 
	            List<BautypBean> bautyplist = bautypData.alleBautypen();
	 
	    		session.setAttribute("haustyplist", haustyplist);
	    		session.setAttribute("bautyplist", bautyplist);
	    			
	            response.sendRedirect("jsp/verkaufen.jsp");
	 
	    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
	        // Scope -Session -> So funktionierts aber nur wenn Session gestartet ist also erst servlet dann html gestartet wird -> unknown datum in field list 
	   		session = request.getSession();
	   		//Bautyp check ob schon vorhanden
	   		session.setAttribute("bautypExistiert", false);
	   		//Haustyp check ob schon vorhanden
	  		session.setAttribute("haustypExistiert", false);
			
	  		verkaufsSeiteAnzeigen(request, response);

		}
		
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 
		 	this.bautypData = new BautypData(dataSource);
 			this.haustypData = new HaustypData(dataSource);
 			this.objektData = new ObjektData(dataSource);
       // Scope -Session -> So funktionierts aber nur wenn Session gestartet ist also erst servlet dann html gestartet wird -> unknown datum in field list 
  		session = request.getSession();
  		
  		
  		//Haustyp check ob schon vorhanden
  		session.setAttribute("bautypExistiert", false);
  		//Check ob ein Haustyp hinzugefügt werden soll
		if (request.getParameter("htyp_edit_absenden") != null) {
			String hausTyp = request.getParameter("htyp_edit");
			if (hausTyp.isEmpty())
				return;
			//Check ob Haustyp bereits vorhanden
			if(haustypData.istHaustypVorhanden(hausTyp)) {
				session.setAttribute("haustypExistiert", true);
			//Falls nicht neuen Haustyp hinzufügen
			} else {
				haustypData.neuenHaustypHinzufuegen(hausTyp);
			}
		}
		
		//Bautyp check ob schon vorhanden
		if (request.getParameter("btyp_edit_absenden") != null) {
			String bauTyp = request.getParameter("btyp_edit");
			if (bauTyp.isEmpty())
				return;
			//Check ob Bautyp bereits vorhanden
			if(bautypData.istBautypVorhanden(bauTyp)) {
				session.setAttribute("bautypExistiert", true);
			//Falls nicht neuen Bautyp hinzufügen
			} else {
				bautypData.neuenBautypHinzufuegen(bauTyp);
			}
		}
  		

		if (request.getParameter("vformular_absenden") != null) {
			DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
			String fhaustyp = request.getParameter("haustyp");
			String fbautyp = request.getParameter("bautyp");
			String ftitel = request.getParameter("titel");
			String fbaujahr = request.getParameter("baujahr");
			Integer fwohnflaeche = (Integer.valueOf(request.getParameter("wohnflaeche")));
			Integer fgrundstuecksflaeche = (Integer.valueOf(request.getParameter("grundstuecksflaeche")));
			java.sql.Date fdatum = null;
			try {
				fdatum = new java.sql.Date(dateFormat.parse(request.getParameter("datum")).getTime());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			String fstandort = request.getParameter("standort");
			Integer fstartgebot = (Integer.valueOf(request.getParameter("startgebot")));
			String fbeschreibung = request.getParameter("beschreibung");
			Part fbilder = request.getPart("bilder");

			java.util.Date date = new java.util.Date();
			java.sql.Date aktdatum = new Date(date.getTime());

			if (fhaustyp.isEmpty() || fbautyp.isEmpty() || ftitel.isEmpty() || fbaujahr.isEmpty() || fwohnflaeche < 0
					|| fgrundstuecksflaeche < 0 || fdatum.before(aktdatum) || fstandort.isEmpty() || fbilder == null)
				return;

			objektData.verkaufFormularAbschicken(fhaustyp, fbautyp, ftitel, fbaujahr, fwohnflaeche, fgrundstuecksflaeche, fstandort, fstartgebot, fbeschreibung, fbilder, fdatum);
		}

		response.sendRedirect("jsp/verkaufen.jsp");		
		
	}


}