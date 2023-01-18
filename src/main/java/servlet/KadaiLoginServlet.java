package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Kadaidao;
import dto.Kadaidto;
import util.GenerateHashedPw;

/**
 * Servlet implementation class KadaiLoginServlet
 */
@WebServlet("/KadaiLoginServlet")
public class KadaiLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KadaiLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getParameter("UTF-8");
		
		String mail =request.getParameter("mail");
		
		String password =request.getParameter("pw");
		
		String salt = Kadaidao.getSalt(mail);
		
		if(salt == null) {
			String view ="WEB-INF/view/kadai-login.jsp?error=1";
			RequestDispatcher dispatcher =request.getRequestDispatcher(view);
			dispatcher.forward(request, response);
			return;
		}
	
	String Hashedpw = GenerateHashedPw.getSafetyPassword(password, salt);
	
	Kadaidto customer = Kadaidao.login(mail, Hashedpw);
	
	if(customer == null) {
		String view ="WEB-INF/view/kadai-login.jsp?error=1";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request,response);
	}else {
		String view ="WEB-INF/view/kadai-menu.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request,response);
	
	}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
