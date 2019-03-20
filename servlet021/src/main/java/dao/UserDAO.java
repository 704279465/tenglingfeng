package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.User;
import util.DBUtils;

public class UserDAO {
	public List<User> finAll() throws SQLException{
		List<User> users = new ArrayList<User>();
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConn();
			stat = conn.prepareStatement("select * from t_user");
			rs = stat.executeQuery();
			while(rs.next()) {
				int id=rs.getInt("id");
				String username=rs.getString("username");
				String password=rs.getString("password");
				String email = rs.getString("email");
				User user = new User();
				user.setId(id);
				user.setUsername(username);
				user.setPassword(password);
				user.setEmail(email);
				
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}finally{
			DBUtils.colse(rs, stat, conn);
		}
		
		return users;
	}
}
