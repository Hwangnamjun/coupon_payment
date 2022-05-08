package main.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {

	private static DBConnection instance = new DBConnection();
	
	public static DBConnection getinstance() {
		return instance;
	}
	
	private DBConnection() {}
	
	public Connection getconnection()
	{
		Connection conn = null;
		try 
		{
			String user = "ADMIN";
			String password = "mihwum-gojby3-hetgYf";
			String url = "jdbc:oracle:thin:@hwangnamjun_medium?TNS_ADMIN=/Users/hwangnamjun/Oracle_backup/Wallet_Hwangnamjun/";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, password);
			
		}catch(ClassNotFoundException e){
			System.out.println("db드라이버 로딩 실패" + e.toString());
		}catch(SQLException e) {
			System.out.println("db접속 실패" + e.toString());
		}catch(Exception e) {
			System.out.println("에러" + e.toString());
		}

		return conn;
	}
	public static void close(Connection con) {
		if(con != null) try { con.close(); } catch (Exception e) {}
	}
	
	
	public static void close(PreparedStatement pstmt) {
		if(pstmt != null) try { pstmt.close(); } catch (Exception e) {}
	}
	
	
	public static void close(ResultSet rs) {
		if(rs != null) try { rs.close(); } catch (Exception e) {}
	}
	
	
	public static void commit(Connection con) {
		try {
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	public static void rollback(Connection con) {
		try {
			con.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
