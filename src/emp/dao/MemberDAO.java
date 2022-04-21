package emp.dao;

import static emp.db.DBConnection.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberDAO {

	
public static MemberDAO getMdao() {
		return mdao;
	}

private MemberDAO() {
	
}

public static MemberDAO mdao=new MemberDAO();

Connection con;

public void setConnection(Connection con) {
	this.con = con;
}

public int EMP_Login(String id, String pass) {
	int isLoginsuccess=0;
	PreparedStatement pstmt = null;
	ResultSet rs=null;
	
	try {
		String sql = "select EMP_PWD from sc010 where EMP_ID=?";

		pstmt=con.prepareStatement(sql);
		pstmt.setString(1, id);
		rs=pstmt.executeQuery();
		if(rs.next()) {
				isLoginsuccess=-1;
			if(rs.getString("EMP_PWD").equals(pass)) {
				isLoginsuccess=1;
			}
		}
	} catch (SQLException e) {
		System.out.println("Login 오류"+e);
		e.printStackTrace();
	}finally {
		close(rs);
		close(pstmt);
	}
	
	return isLoginsuccess;
}
public int EMP_LEVEL(String id) {
	
	int EMP_LEVEL = 0;
	PreparedStatement pstmt = null;
	ResultSet rs=null;
	
	try {
		String sql = "select ADD_LEVEL from COUPON_ADDING where EMP_ID=?";

		pstmt=con.prepareStatement(sql);
		pstmt.setString(1, id);
		rs=pstmt.executeQuery();

		if(rs.next())
		{
			EMP_LEVEL = rs.getInt("ADD_LEVEL");
		}
	} catch (SQLException e) {
		System.out.println("Login 오류"+e);
		e.printStackTrace();
	}finally {
		close(rs);
		close(pstmt);
	}
	
	return EMP_LEVEL;
}

}

