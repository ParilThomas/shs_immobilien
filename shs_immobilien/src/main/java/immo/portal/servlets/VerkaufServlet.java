package immo.portal.servlets;

import java.io.IOException;
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
@MultipartConfig(maxFileSize = 1024 * 1024 * 25, maxRequestSize = 1024 * 1024 * 25, location = "img/tmp", fileSizeThreshold = 1024 * 1024)
public class VerkaufServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Ressourcenreferenz deklarieren
	 */
	@Resource(lookup = "java:jboss/datasources/MySqlweb_db_ttsDS")
	private DataSource dataSource;
	
	/**
	 * doGet wird standardmäßig aufgerufen
	 * 
	 * @Fehler ServletException - Servlet Ablauffehler 
	 * @Fehler IOException		- Input / Output Fehler
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * Session wird initialisiert
		 */
		HttpSession session = request.getSession();

		/**
		 * Neues BautypData Objekt wird erstellt mit der übergebenen dataSource
		 * Neues HaustypData Objekt wird erstellt mit der übergebenen dataSource
		 */
		BautypData bautypData  = new BautypData(dataSource);
		HaustypData haustypData = new HaustypData(dataSource);

		/**
		 * In der Klasse haustypData wird die Methode alleHaustypen aufgerufen und gibt alle Haustypen zurück
		 * anschließend werdne die Objekte in die haustyplist gespeichert
		 * 
		 * In der Klasse bautyplist wird die Methode alleBautypen aufgerufen und gibt alle Bautypen zurück
		 * anschließend werdne die Objekte in die haustyplist bautyplist
		 */
		List<HaustypBean> haustyplist = haustypData.alleHaustypen();
		List<BautypBean> bautyplist   = bautypData.alleBautypen();

		/**
		 * Die haustyplist & bautyplist werden in die Session gespeichert
		 */
		session.setAttribute("haustyplist", haustyplist);
		session.setAttribute("bautyplist", bautyplist);

		/**
		 * Weiterleitung auf die verkaufen.jsp
		 */
		response.sendRedirect("jsp/verkaufen.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * Neues BautypData Objekt wird erstellt mit der übergebenen dataSource
		 * Neues HaustypData Objekt wird erstellt mit der übergebenen dataSource
		 * Neues ObjektData Objekt wird erstellt mit der übergebenen dataSource
		 */
		BautypData bautypData = new BautypData(dataSource);
		HaustypData haustypData = new HaustypData(dataSource);
		ObjektData objektData = new ObjektData(dataSource);

		/**
		 * Session wird initialisiert
		 * Sessionvariable bautypExistiert wird auf false gesetzt
		 * Sessionvariable haustypExistiert wird auf false gesetzt
		 */
		HttpSession session = request.getSession();
		session.setAttribute("bautypExistiert", false);
		session.setAttribute("haustypExistiert", false);

		/**
		 * Wird der Button "htyp_edit_absenden" gedrückt
		 */
		if (request.getParameter("htyp_edit_absenden") != null) {
			/**
			 * Speichere den Buttonwert in die lokale Variable
			 */
			String hausTyp = request.getParameter("htyp_edit");
			/**
			 * Ist der hausTyp leer mach nichts
			 */
			if (hausTyp.isEmpty()) {
				return;
			}
			/**
			 * Ruft in der Klasse haustypData die Methode istHaustypVorhanden mit der lokalen Variable auf
			 * und gibt einen boolean Wert zurück.
			 * 
			 */
			if (haustypData.istHaustypVorhanden(hausTyp)) {
				/**
				 * Ist der Haustyp vorhanden setze die Sessionvariable haustypExistiert auf true
				 */
				session.setAttribute("haustypExistiert", true);
			} else {
				/**
				 * Ist der Haustyp NICHT vorhanden
				 * Rufe in der Klasse haustypData die Methode neuenHaustypHinzufuegen mit der lokalen Variable aus
				 * 
				 * Legt in der DB einen neuen Haustyp an
				 */
				haustypData.neuenHaustypHinzufuegen(hausTyp);
			}
		}

		/**
		 * Wird der Button "btyp_edit_absenden" gedrückt
		 */
		if (request.getParameter("btyp_edit_absenden") != null) {
			/**
			 * Speichere den Buttonwert in die lokale Variable
			 */
			String bauTyp = request.getParameter("btyp_edit");
			/**
			 * Ist der bauTyp leer mach nichts
			 */
			if (bauTyp.isEmpty()) {
				return;
			}
			/**
			 * Ruft in der Klasse bautypData die Methode istBautypVorhanden mit der lokalen Variable auf
			 * und gibt einen boolean Wert zurück.
			 * 
			 */
			if (bautypData.istBautypVorhanden(bauTyp)) {
				/**
				 * Ist der Bautyp vorhanden setze die Sessionvariable bautypExistiert auf true
				 */
				session.setAttribute("bautypExistiert", true);
				
			} else {
				/**
				 * Ist der Bautyp NICHT vorhanden
				 * Rufe in der Klasse bautypData die Methode neuenBautypHinzufuegen mit der lokalen Variable aus
				 * 
				 * Legt in der DB einen neuen Bautyp an
				 */
				bautypData.neuenBautypHinzufuegen(bauTyp);
			}
		}

		
		/**
		 * Wenn das Verkaufsformular abgeschickt wird, hole alle Werte des Formulars und speichere
		 * sie in lokale Variable
		 */
		if (request.getParameter("vformular_absenden") != null) {

			Integer fbesitzer = (Integer.valueOf(request.getParameter("vformular_absenden")));
			String fhaustyp = request.getParameter("haustyp");
			String fbautyp = request.getParameter("bautyp");
			String ftitel = request.getParameter("titel");
			Integer fbaujahr = (Integer.valueOf(request.getParameter("baujahr")));
			Integer fwohnflaeche = (Integer.valueOf(request.getParameter("wohnflaeche")));
			Integer fgrundstuecksflaeche = (Integer.valueOf(request.getParameter("grundstuecksflaeche")));
			String fdatum = request.getParameter("datum");
			String fstandort = request.getParameter("standort");
			Integer fstartgebot = (Integer.valueOf(request.getParameter("startgebot")));
			String fbeschreibung = request.getParameter("beschreibung");
			Part fbilder = request.getPart("bilder");

			/**
			 * Rufe in der Klasse objektData die Methode verkaufFormularAbschicken auf mit den lokalen Variablen
			 * 
			 * Legt ein neuen Verkaufsobjekt in der DB an
			 */
			objektData.verkaufFormularAbschicken(fhaustyp, fbautyp, ftitel, fbaujahr, fwohnflaeche,
					fgrundstuecksflaeche, fstandort, fstartgebot, fbeschreibung, fbilder, fdatum, fbesitzer);
		}
		
		/**
		 * Weiterleitung an die doGet und dadurch auf verkaufen.jsp
		 */
		this.doGet(request, response);
	}

}