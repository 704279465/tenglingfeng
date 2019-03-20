package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import util.DBUtils;

public class AddUserServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");

		System.out.println(
				"username:" + username + " pwd:" 
				+ password+ " email:" + email);
		Connection conn=null;
		PreparedStatement stat = null;
		try {
			conn = DBUtils.getConn();
			stat = conn.prepareStatement("insert into t_user values(null,?,?,?)");
			stat.setString(1, username);
			stat.setString(2, password);
			stat.setString(3, email);
			stat.executeUpdate();
			
			out.println("添加成功");
		} catch (SQLException e) {
			e.printStackTrace();
			out.println("系统繁忙 稍后再试");
		}finally {
			DBUtils.colse(null, stat, conn);
		}
		
		
		
	}

}
