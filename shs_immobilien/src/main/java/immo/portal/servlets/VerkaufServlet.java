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
@MultipartConfig(maxFileSize = 1024 * 1024 * 25, maxRequestSize = 1024 * 1024 * 25, location = "/tmp", fileSizeThreshold = 1024 * 1024)
public class VerkaufServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(lookup = "java:jboss/datasources/MySqlweb_db_ttsDS")
	private DataSource dataSource;
	private HttpSession session;

	private BautypData bautypData;
	private HaustypData haustypData;
	private ObjektData objektData;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Scope -Session -> So funktionierts aber nur wenn Session gestartet ist also
		// erst servlet dann html gestartet wird -> unknown datum in field list
		session = request.getSession();

		this.bautypData  = new BautypData(dataSource);
		this.haustypData = new HaustypData(dataSource);
		this.objektData  = new ObjektData(dataSource);

		List<HaustypBean> haustyplist = haustypData.alleHaustypen();
		List<BautypBean> bautyplist   = bautypData.alleBautypen();

		this.session.setAttribute("haustyplist", haustyplist);
		this.session.setAttribute("bautyplist", bautyplist);

		response.sendRedirect("jsp/verkaufen.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		this.bautypData = new BautypData(dataSource);
		this.haustypData = new HaustypData(dataSource);
		this.objektData = new ObjektData(dataSource);
		// Scope -Session -> So funktionierts aber nur wenn Session gestartet ist also
		// erst servlet dann html gestartet wird -> unknown datum in field list
		session = request.getSession();

		session.setAttribute("bautypExistiert", false);
		session.setAttribute("haustypExistiert", false);

		// Check ob ein Haustyp hinzugef�gt werden soll
		if (request.getParameter("htyp_edit_absenden") != null) {
			String hausTyp = request.getParameter("htyp_edit");
			if (hausTyp.isEmpty()) {
				return;
			}
			// Check ob Haustyp bereits vorhanden
			if (haustypData.istHaustypVorhanden(hausTyp)) {
				session.setAttribute("haustypExistiert", true);
				// Falls nicht neuen Haustyp hinzuf�gen
			} else {
				haustypData.neuenHaustypHinzufuegen(hausTyp);
			}
		}

		// Bautyp check ob schon vorhanden
		if (request.getParameter("btyp_edit_absenden") != null) {
			String bauTyp = request.getParameter("btyp_edit");
			if (bauTyp.isEmpty()) {
				return;
			}
			// Check ob Bautyp bereits vorhanden
			if (bautypData.istBautypVorhanden(bauTyp)) {
				session.setAttribute("bautypExistiert", true);
				// Falls nicht neuen Bautyp hinzuf�gen
			} else {
				bautypData.neuenBautypHinzufuegen(bauTyp);
			}
		}

		if (request.getParameter("vformular_absenden") != null) {

			Integer fbesitzer = (Integer.valueOf(request.getParameter("vformular_absenden")));
			String fhaustyp = request.getParameter("haustyp");
			String fbautyp = request.getParameter("bautyp");
			String ftitel = request.getParameter("titel");
			String fbaujahr = request.getParameter("baujahr");
			Integer fwohnflaeche = (Integer.valueOf(request.getParameter("wohnflaeche")));
			Integer fgrundstuecksflaeche = (Integer.valueOf(request.getParameter("grundstuecksflaeche")));
			java.sql.Date fdatum = null;
			try {
				// Eclipse internal browser date format
				DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
				fdatum = new java.sql.Date(dateFormat.parse(request.getParameter("datum")).getTime());
				System.out.println(dateFormat.parse(request.getParameter("datum")).getTime());
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

			objektData.verkaufFormularAbschicken(fhaustyp, fbautyp, ftitel, fbaujahr, fwohnflaeche,
					fgrundstuecksflaeche, fstandort, fstartgebot, fbeschreibung, fbilder, fdatum, fbesitzer);
		}

		response.sendRedirect("VerkaufServlet");
//		request.getRequestDispatcher("jsp/verkaufen.jsp").forward(request, response);

	}

}