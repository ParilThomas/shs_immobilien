package immo.portal.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import immo.portal.bean.ObjektBean;
import immo.portal.bean.KaufenBean;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class kaufen_bild_servlet
 */
@WebServlet("/kaufen_bild_servlet")


// Bild Servlet
public class KaufenBildServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(lookup = "java:jboss/datasources/MySqlweb_db_ttsDS")
	private DataSource dataSource;


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
	
		
//		String htyp = (request.getParameter("haustyp"));
		Long id = Long.valueOf(request.getParameter("id"));
		
			
		try (Connection con = dataSource.getConnection();
				//Problem k�nnte sein dass die dynamischen Haustypen in der Tabelle haustypen sind !!!!!
				PreparedStatement pstmt = con.prepareStatement("Select bilder FROM objekte WHERE id=?")) {
//			pstmt.setString(1, htyp);
			pstmt.setLong(1, id);
		
			try (ResultSet rs = pstmt.executeQuery()) {

					if (rs != null && rs.next()) {
						Blob bild = rs.getBlob("bilder"); // Zugriff auf Spalte "bilder"

						response.reset();
						long length = bild.length();
						response.setHeader("Content-Length", String.valueOf(length)); // Header setzen -> Headerl�nge
																						// setzen

						// Stream Verarbeitung von DB -> Browser
						try (InputStream in = bild.getBinaryStream();) { // getBinaryStream() -> inputStream
							final int bufferSize = 256;
							byte[] buffer = new byte[bufferSize]; // Puffer festlegen -> 256 Byte

							// Output-Stream
							ServletOutputStream out = response.getOutputStream(); // zum Browser schreiben deswegen
																					// nat�rlich response
							while ((length = in.read(buffer)) != -1) { // Lesen vom Input-Stream + schreiben in den
																		// Output-Stream in while Schleife
								out.write(buffer, 0, (int) length);
							}
							out.flush();
						}

					}
			
			}
			
		} catch (Exception e) {
			throw new ServletException(e.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
