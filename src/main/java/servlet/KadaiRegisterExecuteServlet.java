package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Kadaidao;
import dto.Kadaidto;

/**
 * Servlet implementation class KadaiRegisterExecuteServlet
 */
@WebServlet("/KadaiRegisterExecuteServlet")
public class KadaiRegisterExecuteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KadaiRegisterExecuteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();

    	Kadaidto customer = (Kadaidto)session.getAttribute("input_data");

    	int result = Kadaidao.regisiterAccount(customer);

    	String path ="";
    	if(result == 1) {
    		session.removeAttribute("input_data");
    		path = "WEB-INF/view/kadai-success.jsp";

    	}else {
    		
    		path = "WEB-INF/view/kadai-form.jsp?error=1";
    	}
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		dispatcher.forward(request, response);
    	System.out.println("end");
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
