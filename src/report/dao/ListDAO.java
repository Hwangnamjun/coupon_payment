package report.dao;

import static report.db.DBConnection.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import report.vo.ReportBean;

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

public ArrayList<ReportBean> getList(HashMap<String, String> map) {
	ArrayList<ReportBean> arr= new ArrayList<ReportBean>();
	PreparedStatement pstmt = null;
	ResultSet rs=null;
	
	try {
		String sql = 
				"select str_code, sale_ymd, class_name, dc_rate, pos_no, trxn_no, sal_amt,dc_amt, (sal_amt - dc_amt) as total_amt, team_code " +
						"from(  " +
						"select a.str_ymd as sale_ymd, (select class_name from c_code_v where member_no = a.member_no) as class_name, nvl(b.str_code,'N') as str_code, a.cupon_no as coupon_no, a.disc_rate as dc_rate, b.pos_no, b.trxn_no, sum(b.sal_amt) as sal_amt, sum(b.dc_amt) as dc_amt, (select team_code from c_code_v where member_no = a.member_no) as team_code   " +
						"from cm150 a   " +
						"left outer join sl061 b   " +
						"on( a.sale_ymd = b.sale_ymd   " +
						"and a.str_code = b.str_code   " +
						"and a.trxn_no = b.trxn_no    " +
						"and a.pos_no = b.pos_no   " +
						")   " +
						"where a.iemp_no = 'teamC'   " +
						"group by a.str_ymd, a.member_no, b.str_code, a.disc_rate, b.pos_no, b.trxn_no, a.cupon_no, b.dc_amt) " +
						"where sale_ymd between ? and ? " +
						"and str_code like ?||'%' " +
						"and dc_rate like ?||'%' ";
		pstmt=con.prepareStatement(sql);
		pstmt.setString(1, map.get("str_ymd"));
		pstmt.setString(2, map.get("end_ymd"));
		pstmt.setString(3, map.get("str_code"));
		pstmt.setString(4, map.get("dc_rate"));
		rs=pstmt.executeQuery();
		
		while(rs.next()) {
			ReportBean bean = new ReportBean();
			bean.setStr_code(rs.getString("str_code"));
			bean.setSale_ymd(rs.getString("sale_ymd"));
			bean.setClass_name(rs.getString("class_name"));
			bean.setDc_rate(rs.getString("dc_rate"));
			bean.setPos_no(rs.getString("pos_no"));
			bean.setTrxn_no(rs.getString("trxn_no"));
			bean.setAmt(rs.getString("sal_amt"));
			bean.setDc_amt(rs.getString("dc_amt"));
			bean.setTotal_amt(rs.getString("total_amt"));
			bean.setTeam_code(rs.getString("team_code"));
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
}

