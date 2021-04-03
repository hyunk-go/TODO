package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TodoDao;
import dto.TodoDto;

/**
 * Servlet implementation class TodoAddServlet
 */
@WebServlet("/TodoAddServlet")
public class TodoAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	TodoDao dao = null;
    /**
     * Default constructor. 
     */
    public TodoAddServlet() {
    	super();
		dao = new TodoDao();
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TodoDto dto = new TodoDto();

		request.setCharacterEncoding("utf-8");

		String title = request.getParameter("title");
		String name = request.getParameter("name");
		int sequence = Integer.parseInt(request.getParameter("sequence"));
		dto.setTitle(title);
		dto.setName(name);
		dto.setSequence(sequence);

		dao.addTodo(dto);

		response.sendRedirect("./MainServlet");
	}

}
