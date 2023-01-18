package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.Kadaidto;

/**
 * Servlet implementation class KadaiRegisterConfirmServlet
 */
@WebServlet("/KadaiRegisterConfirmServlet")
public class KadaiRegisterConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KadaiRegisterConfirmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String name = request.getParameter("name");
		String oldStr = request.getParameter("old");
		String genderStr = request.getParameter("gender");
		int ge = Integer.parseInt(genderStr);
		String gender = ge == 1 ?"男":"女";
				
		String telStr= request.getParameter("tel");
		String mail = request.getParameter("mail");
		String password = request.getParameter("password");
		String hashedpw = request.getParameter("hashedpw");
		String salt = request.getParameter("salt");
		
		int old = Integer.parseInt(oldStr);
		int tel = Integer.parseInt(telStr);
		
		Kadaidto customer = new Kadaidto(name, old, gender, tel, mail, password, null, null);
		
		HttpSession session = request.getSession();
		
		session.setAttribute("input_data", customer);
		
		String view ="WEB-INF/view/kadai-confirm.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
