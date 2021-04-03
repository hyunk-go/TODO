package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TodoDao;
import dto.TodoDto;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	TodoDao todoDao = null;
    /**
     * Default constructor. 
     */
    public MainServlet() {
    	super();
		todoDao = new TodoDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<TodoDto> todoList = todoDao.getTodo();
		
		request.setAttribute("todoList", todoList);

		String[] todoTypes = { "TODO", "DOING", "DONE" };
		request.setAttribute("todoTypes", todoTypes);

		RequestDispatcher requestDispatehcer = request.getRequestDispatcher("main.jsp");
		requestDispatehcer.forward(request, response);
		}

}
