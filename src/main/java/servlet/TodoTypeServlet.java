package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TodoDao;

/**
 * Servlet implementation class TodoTypeServlet
 */
@WebServlet("/TodoTypeServlet")
public class TodoTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	TodoDao dao = null;
    /**
     * Default constructor. 
     */
    public TodoTypeServlet() {
    	super();
		dao = new TodoDao();
    }

 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String type = request.getParameter("type");

		if (type.equals("TODO"))
			type = "DOING";
		else if (type.equals("DOING"))
			type = "DONE";

		dao.updateTodo(id, type);
		
		PrintWriter out = response.getWriter();
		out.print("success");
		out.close();

	}

}
