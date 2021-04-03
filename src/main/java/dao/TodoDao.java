package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.TodoDto;


public class TodoDao {
	private static String dburl = "jdbc:mysql://localhost:3306/todolist";
	private static String dbUser = "todouser"; //input dbUser
	private static String dbpasswd = "todo123!@#";  // input dbPasswd

	public int addTodo(TodoDto dto) {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String query = "insert into todo(title, name, sequence) values(?,?,?)";

		try (Connection conn = DriverManager.getConnection(dburl,dbUser,dbpasswd);
				PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setString(1, dto.getTitle());
			ps.setString(2, dto.getName());
			ps.setInt(3, dto.getSequence());
			return ps.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	public List<TodoDto> getTodo() {
		List<TodoDto> list = new ArrayList<>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String query = "select  id, title, name, sequence, type, date_format(regdate, '%Y.%m.%d') as date from todo order by regdate";
		
		try (Connection conn =DriverManager.getConnection(dburl,dbUser,dbpasswd);
				PreparedStatement ps = conn.prepareStatement(query)){
		try (ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				TodoDto dto = new TodoDto();
				dto.setId(rs.getLong("id"));
				dto.setName(rs.getString("name"));
				dto.setRegdate(rs.getString("date"));
				dto.setSequence(rs.getInt("sequence"));
				dto.setTitle(rs.getString("title"));
				dto.setType(rs.getString("type"));
				list.add(dto);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		}catch (SQLException ex) {
			ex.printStackTrace();
		}
		return list;
	}


	public int updateTodo(int id, String type) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String query = "update todo set type = ? where id = ?";

		try (Connection conn = DriverManager.getConnection(dburl,dbUser,dbpasswd);
				PreparedStatement ps = conn.prepareStatement(query)) {
				ps.setString(1, type);
				ps.setLong(2, id);
		
			return ps.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
			return 0;
		}
	}
}
