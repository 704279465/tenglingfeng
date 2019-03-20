package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import entity.User;

public class ListUserServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
	
		try {
			UserDAO dao = new UserDAO();
			List<User> users = dao.finAll();
			out.println("<table border='2' width=60%>");
			out.println("<tr><td>ID</td><td>用户名</td><td>密码</td><td>邮箱</td></tr>");
			for(User user:users) {
				int id = user.getId();
				String username=user.getUsername();
				String password=user.getPassword();
				String email=user.getEmail();
				out.println("<tr><td>"+id+"</td><td>"+username+"</td><td>"+password+"</td><td>"+email+"</td></tr>");
				
			}
			out.print("</table>");
		} catch (SQLException e) {
			e.printStackTrace();
			out.println("系统繁忙 稍后重试");
		}



	}

}
