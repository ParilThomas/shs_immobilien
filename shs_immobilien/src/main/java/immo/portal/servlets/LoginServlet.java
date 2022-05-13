package immo.portal.servlets;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import immo.portal.bean.LoginBean;
import datenbank.LoginDao;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoginDao loginDao;
       
    public void init() {
        loginDao = new LoginDao();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String loginname = request.getParameter("loginname");
        String passwort = request.getParameter("passwort");
        LoginBean loginBean = new LoginBean();
        loginBean.setLoginname(loginname);
        loginBean.setPasswort(passwort);

        try {
            if (loginDao.validate(loginBean)) {
                //HttpSession session = request.getSession();
                //session.setAttribute("loginname",loginname);
                response.sendRedirect("jsp/loginsuccess.jsp");
            } else {
                //HttpSession session = request.getSession();
                //session.setAttribute("loginname", loginname);
                response.sendRedirect("jsp/login.jsp");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
