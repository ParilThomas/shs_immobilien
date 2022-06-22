package immo.portal.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

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
	
	@Resource(lookup = "java:jboss/datasources/MySqlweb_db_ttsDS")
	private DataSource dataSource;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		Long id = Long.valueOf(request.getParameter("id"));
			
		try (Connection con = dataSource.getConnection();
			PreparedStatement pstmt = con.prepareStatement("Select bilder FROM objekte WHERE id=?")) {
			pstmt.setLong(1, id);
		
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs != null && rs.next()) {
					Blob bild = rs.getBlob("bilder"); // Zugriff auf Spalte "bilder"
					response.reset();
					long length = bild.length();
					response.setHeader("Content-Length", String.valueOf(length)); // Header setzen -> Headerl�nge

					// Stream Verarbeitung von DB -> Browser
					try (InputStream in = bild.getBinaryStream();) { // getBinaryStream() -> inputStream
						final int bufferSize = 256;
						byte[] buffer = new byte[bufferSize]; // Puffer festlegen -> 256 Byte

						// Output-Stream
						ServletOutputStream out = response.getOutputStream(); // zum Browser schreiben deswegen
																					// nat�rlich response
						while ((length = in.read(buffer)) != -1) { // Lesen vom Input-Stream + schreiben in den
							out.write(buffer, 0, (int) length);	// Output-Stream in while Schleife
						}
						out.flush();
					}
				}
			}
			
		} catch (Exception e) {
			throw new ServletException(e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
