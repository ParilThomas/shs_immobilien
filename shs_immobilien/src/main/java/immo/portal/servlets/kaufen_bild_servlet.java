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

import immo.portal.bean.kaufen_bean;
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

public class kaufen_bild_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(lookup = "java:jboss/datasources/MySqlweb_db_ttsDS")
	private DataSource ds;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public kaufen_bild_servlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String haustyp = (request.getParameter("haustyp"));
		List<kaufen_bean> bildliste = new ArrayList<kaufen_bean>();
		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement("Select bilder FROM objekte WHERE haustyp= ?")) {
			pstmt.setString(1, haustyp);
		
			try (ResultSet rs = pstmt.executeQuery()) {

					if (rs != null && rs.next()) {
						Blob bild = rs.getBlob("bilder"); // Zugriff auf Spalte "bilder"

						response.reset();
						long length = bild.length();
						response.setHeader("Content-Length", String.valueOf(length)); // Header setzen -> Headerlänge
																						// setzen

						// Stream Verarbeitung von DB -> Browser
						try (InputStream in = bild.getBinaryStream();) { // getBinaryStream() -> inputStream
							final int bufferSize = 256;
							byte[] buffer = new byte[bufferSize]; // Puffer festlegen -> 256 Byte

							// Output-Stream
							ServletOutputStream out = response.getOutputStream(); // zum Browser schreiben deswegen
																					// natürlich response
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
