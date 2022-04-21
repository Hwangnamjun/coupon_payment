package main.dao;

import static main.db.DBConnection.*;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import main.vo.PaymentBean;
import main.vo.UserBean;

public class ListDAO {

	
public static ListDAO getInstance() {
		return dao;
	}

private ListDAO() {
	
}

public static ListDAO dao=new ListDAO();

Connection con;

public void setConnection(Connection con) {
	this.con = con;
}

public ArrayList<UserBean> getList() {
	ArrayList<UserBean> arr= new ArrayList<UserBean>();
	PreparedStatement pstmt = null;
	ResultSet rs=null;
	
	try {
		String sql = "select * from COUPON_ADDING where ADD_LEVEL = '30'";
		pstmt=con.prepareStatement(sql);
		rs=pstmt.executeQuery();
		
		while(rs.next()) {
			UserBean bean = new UserBean();
			bean.setEMP_ID(rs.getString("EMP_ID"));
			bean.setNM(rs.getString("NM"));
			bean.setADD_LEVEL(rs.getString("ADD_LEVEL"));
			bean.setNUM_3(rs.getInt("ADD_NUM_3"));
			bean.setNUM_5(rs.getInt("ADD_NUM_5"));
			bean.setNUM_7(rs.getInt("ADD_NUM_7"));
			bean.setNUM_10(rs.getInt("ADD_NUM_10"));
			arr.add(bean);
		}
	} catch (SQLException e) {
		System.out.println("Login 오류"+e);
		e.printStackTrace();
	}finally {
		close(rs);
		close(pstmt);
	}
	
	return arr;
}

public void setList(ArrayList<UserBean> param) {
	
	PreparedStatement pstmt = null;
	
	try {
		for(int i = 0; i < param.size(); i++)
		{
			String sql = "update coupon_adding set ADD_NUM_3=?, ADD_NUM_5=?, ADD_NUM_7=?, ADD_NUM_10=? where EMP_ID=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, param.get(i).getNUM_3());
			pstmt.setInt(2, param.get(i).getNUM_5());
			pstmt.setInt(3, param.get(i).getNUM_7());
			pstmt.setInt(4, param.get(i).getNUM_10());
			pstmt.setString(5, param.get(i).getEMP_ID());
			pstmt.execute();	
		}
	} catch (Exception e) {
		System.out.println("Login 오류"+e);
		rollback(con);
		e.printStackTrace();
	}finally {
		close(pstmt);
	}
	
}

public ArrayList<PaymentBean> getPaymentList()
{
	ArrayList<PaymentBean> arr= new ArrayList<PaymentBean>();
	PreparedStatement pstmt = null;
	ResultSet rs=null;
	
	try {
		String sql = "select CLASS_NAME,MEMBER_NO,STR_CODE,TEAM_CODE from C_CODE_V where SAL_AMT > '100'";
		pstmt=con.prepareStatement(sql);
		rs=pstmt.executeQuery();
		
		while(rs.next()) {
			PaymentBean bean = new PaymentBean();
			bean.setCLASS_NAME(rs.getString("CLASS_NAME"));
			bean.setMEMBER_NO(rs.getString("MEMBER_NO"));
			bean.setSTR_CODE(rs.getInt("STR_CODE"));
			bean.setTEAM_CODE(rs.getInt("TEAM_CODE"));
			arr.add(bean);
		}
	} catch (SQLException e) {
		System.out.println("Login 오류"+e);
		e.printStackTrace();
	}finally {
		close(rs);
		close(pstmt);
	}
	
	return arr;
}

public void setProcedure(ArrayList<PaymentBean> param)
{
	CallableStatement cs = null;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	Calendar c1 = Calendar.getInstance();
	String date = (sdf.format(c1.getTime())).substring(0,8);
	int j_count[] = {3,5,7,10};
	try {
		for(int i = 0; i < param.size(); i++)
		{
			for(int j = 0; j < j_count.length; j++)
			{
				cs = con.prepareCall("{call PR_CM150_003_TMP_2(?,?,?,?,?,?,?)}");
				cs.setString(1, param.get(i).getMEMBER_NO());//회원번호
				cs.setInt(2,j_count[j]);//할인율
				cs.setString(3, date);//시작일
				cs.setString(4, date);//종료일
				switch (j) {
				case 0:
					cs.setInt(5, param.get(i).getNUM_3());//수량
					break;
				case 1:
					cs.setInt(5, param.get(i).getNUM_5());//수량
					break;
				case 2:
					cs.setInt(5, param.get(i).getNUM_7());//수량
					break;
				case 3:
					cs.setInt(5, param.get(i).getNUM_10());//수량
					break;					
				}
				cs.registerOutParameter(6, java.sql.Types.VARCHAR);//성공 0 실패 9
				cs.registerOutParameter(7, java.sql.Types.VARCHAR);//에러 내용
				cs.execute();
			}
		}
	} catch (Exception e) {
		System.out.println("Login 오류"+e);
		rollback(con);
		e.printStackTrace();
	}finally {
		close(cs);
	}
}

public int setCountProcedure(ArrayList<PaymentBean> param, String emp_id) {
	
	int result = 1;
	PreparedStatement pstmt = null;
	CallableStatement cs = null;
	ResultSet rs = null;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	Calendar c1 = Calendar.getInstance();
	String date = (sdf.format(c1.getTime())).substring(0,8);
	
	try {
		String sql = "select ADD_NUM_3,ADD_NUM_5,ADD_NUM_7,ADD_NUM_10 from COUPON_ADDING where EMP_ID = ?";
		pstmt=con.prepareStatement(sql);
		pstmt.setString(1, emp_id);
		rs=pstmt.executeQuery();
		if(rs.next())
		{
			int[] j_count = {3,5,7,10};
			int target_3 = rs.getInt("ADD_NUM_3");
			int target_5 = rs.getInt("ADD_NUM_5");
			int target_7 = rs.getInt("ADD_NUM_7");
			int target_10 = rs.getInt("ADD_NUM_10");

			for(int i = 0; i < param.size(); i++)
			{
				target_3 -= param.get(i).getNUM_3();
				target_5 -= param.get(i).getNUM_5();
				target_7 -= param.get(i).getNUM_7();
				target_10 -= param.get(i).getNUM_10();
			}

			if(target_3 >= 0 && target_5 >= 0 && target_7 >= 0 && target_10 >= 0)
			{
				for(int i = 0; i < param.size(); i++)
				{
					for(int j = 0; j < j_count.length; j++)
					{
						cs = con.prepareCall("{call PR_CM150_003_TMP_2(?,?,?,?,?,?,?)}");
						cs.setString(1, param.get(i).getMEMBER_NO());//회원번호
						cs.setInt(2, j_count[j]);//할인율
						cs.setString(3, date);//시작일
						cs.setString(4, date);//종료일
						switch (j) {
						case 0:
							cs.setInt(5, param.get(i).getNUM_3());//수량
							break;
						case 1:
							cs.setInt(5, param.get(i).getNUM_5());//수량
							break;
						case 2:
							cs.setInt(5, param.get(i).getNUM_7());//수량
							break;
						case 3:
							cs.setInt(5, param.get(i).getNUM_10());//수량
							break;					
						}
						cs.registerOutParameter(6, java.sql.Types.VARCHAR);//성공 0 실패 9
						cs.registerOutParameter(7, java.sql.Types.VARCHAR);//에러 내용
						cs.execute();
					}
				}
				
				sql = "update COUPON_ADDING set ADD_NUM_3 = ?, ADD_NUM_5 = ?, ADD_NUM_7 = ?, ADD_NUM_10 = ? where EMP_ID = ?";
				PreparedStatement pstmt2 = con.prepareStatement(sql);
				pstmt2.setInt(1, target_3);
				pstmt2.setInt(2, target_5);
				pstmt2.setInt(3, target_7);
				pstmt2.setInt(4, target_10);
				pstmt2.setString(5, emp_id);
				pstmt2.execute();
			}
			else
			{
				result = -1;
				return result;
			}
		}
		
	} catch (Exception e) {
		System.out.println("Login 오류"+e);
		rollback(con);
		e.printStackTrace();
	}finally {
		close(cs);
	}
	return result;
}

public int[] getAddNum(String emp_id)
{
	int result[] = new int[4];
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	try {
		String sql = "select ADD_NUM_3, ADD_NUM_5, ADD_NUM_7, ADD_NUM_10 from COUPON_ADDING where EMP_ID = ?";
		pstmt=con.prepareStatement(sql);
		pstmt.setString(1, emp_id);
		rs=pstmt.executeQuery();
		if(rs.next())
		{
			result[0] = rs.getInt("ADD_NUM_3");
			result[1] = rs.getInt("ADD_NUM_5");
			result[2] = rs.getInt("ADD_NUM_7");
			result[3] = rs.getInt("ADD_NUM_10");
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	return result;
}

public void insertUser(ArrayList<UserBean> param)
{
	PreparedStatement pstmt = null;
	
	try {
		for(int i = 0; i < param.size(); i++)
		{
			String sql = "Insert into coupon_adding(EMP_ID, NM, ADD_LEVEL) Values(?,?,30)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, param.get(i).getEMP_ID());
			pstmt.setString(2, param.get(i).getNM());
			pstmt.execute();	
		}
	} catch (Exception e) {
		System.out.println("Login 오류"+e);
		rollback(con);
		e.printStackTrace();
	}finally {
		close(pstmt);
	}
	
}

public void deleteUser(UserBean bean) {
	// TODO Auto-generated method stub
	PreparedStatement pstmt = null;
	
	try {
		String sql = "delete from coupon_adding where emp_id = ? and nm = ?";
		pstmt=con.prepareStatement(sql);
		pstmt.setString(1, bean.getEMP_ID());
		pstmt.setString(2, bean.getNM());
		pstmt.execute();	
	} catch (Exception e) {
		System.out.println("Login 오류"+e);
		rollback(con);
		e.printStackTrace();
	}finally {
		close(pstmt);
	}
}


}

