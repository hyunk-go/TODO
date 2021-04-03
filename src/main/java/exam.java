import java.util.List;

import dao.TodoDao;
import dto.TodoDto;

public class exam {

	public static void main(String[] args) {
		TodoDao dao=new TodoDao();
		List<TodoDto> todoList=dao.getTodo();
	for(TodoDto role:todoList) {
		System.out.println(role);
	}
	}
}
