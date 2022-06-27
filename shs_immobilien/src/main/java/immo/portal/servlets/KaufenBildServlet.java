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
		
		BildData bildData = new BildData(dataSource);
		
		// utf-8
		response.reset();
		response.setCharacterEncoding("UTF-8");

		/**
		 * Hole die id zum Objekt und speichere sie lokal ab
		 */
		Long id = Long.valueOf(request.getParameter("id"));
		
		Blob bild = bildData.getBild(id);
		
		// wenn kein Bild vorhanden ist nichts ausgeben
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
			byte[] buffer = new byte[4096];

			/**
			 * Browserausgabe über response 
			 */
			ServletOutputStream servletOutputStream = response.getOutputStream();
			/**
			 * Liest den InputStram von 0 bis zur vorgegebenen Länge des buffers und speichert
			 * den Integer Wet in "length"
			 * 
			 * Solange der Wert nicht kleiner ist als -1
			 * Schreibe von 0 bis maximal "lenght" 
			 */
			long currentLength;
			while ((currentLength = inputStream.read(buffer)) != -1) {
				servletOutputStream.write(buffer, 0, (int) currentLength);
			}
							
			/**
			 * Schließt den OutputStream
			 */
			servletOutputStream.flush();
						
		} catch (Exception e) {
			System.out.println("KaufenBildServlet Fehlertestung");
			throw new ServletException(e.getMessage());
		}
	}

}
