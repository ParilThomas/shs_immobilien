package immo.portal.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;

import javax.sql.DataSource;

import data.BildData;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/kaufen_bild_servlet")

public class KaufenBildServlet extends HttpServlet {
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
		 * Neues BildData Objekt wird erstellt mit der übergebenen dataSource
		 */
		BildData bildData = new BildData(dataSource);
		
		/**
		 * Response wird zurückgesetzt
		 * Setzt den Content Type vom Response auf UTF-8
		 */
		response.reset();
		response.setCharacterEncoding("UTF-8");

		/**
		 * Hole die id aus der Session und speichere sie lokal ab
		 * Rufe in der KLasse bildData die Methode getBild mit der lokalen id auf
		 */
		Long id = Long.valueOf(request.getParameter("id"));
		Blob bild = bildData.getBild(id);
		
		/**
		 * Ist das bild Objekt null mach nichts
		 */
		if (bild == null) {
			return;
		}

		try {	
			
			/**
			 * Setzt die Headerlänge auf die Länge des Blob "bild"
			 */
			response.setHeader("Content-Length", String.valueOf(bild.length()));

			/**
			 * Streamverarbeitung DB -> Browser
			 * Speichern als InputStream
			 */
			
			InputStream inputStream = bild.getBinaryStream();
			/**
			 * bufferSize festlegen
			 */
			byte[] puffer = new byte[4096];

			/**
			 * Browserausgabe über response 
			 */
			ServletOutputStream servletOutputStream = response.getOutputStream();
			/**
			 * Liest den InputStram von 0 bis zur vorgegebenen Länge des puffers und speichert
			 * den Integer Wert in "currentLength"
			 * 
			 * Solange der Wert nicht kleiner ist als -1
			 * Schreibe in den OutputStream von 0 bis maximal "currentLength" 
			 */
			long currentLength;
			while ((currentLength = inputStream.read(puffer)) != -1) {
				servletOutputStream.write(puffer, 0, (int) currentLength);
			}
							
			/**
			 * Schließt den OutputStream und leer den kompletten Cache
			 */
			servletOutputStream.flush();
						
		} catch (Exception e) {
			throw new ServletException(e.getMessage());
		}
	}

}
