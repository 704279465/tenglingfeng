package util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;



public class DBUtils {
	private static BasicDataSource datasoure;
	static{

		Properties prop = new Properties();
		InputStream ips = DBUtils.class.getClassLoader().getResourceAsStream("properties");
		try {
			prop.load(ips);
			String driver = prop.getProperty("driver");	
			String url = prop.getProperty("url");	
			String username = prop.getProperty("username");	
			String password = prop.getProperty("password");	
			int initSize = Integer.parseInt(prop.getProperty("initSize"));	
			String maxSize = prop.getProperty("maxSize");	

		    datasoure = new BasicDataSource();
			datasoure.setDriverClassName(driver);
			datasoure.setUrl(url);
			datasoure.setUsername(username);
			datasoure.setPassword(password);
		
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static Connection getConn() throws SQLException {
		return datasoure.getConnection();
	}
	public static void colse(ResultSet rs,Statement stat,Connection conn) {
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(stat!=null) {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	


}
