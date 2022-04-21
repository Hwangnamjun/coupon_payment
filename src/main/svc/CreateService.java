package main.svc;

import static main.db.DBConnection.*;

import java.sql.Connection;
import java.util.ArrayList;

import main.dao.ListDAO;
import main.vo.PaymentBean;

public class CreateService {

	public void setCoupon(ArrayList<PaymentBean> arr) {

		Connection con = getinstance().getconnection();
		
		ListDAO dao = ListDAO.getInstance();

		dao.setConnection(con);
		
		try {
			con.setAutoCommit(false);
			dao.setProcedure(arr);
			
		} catch (Exception e) {
			rollback(con);
			e.printStackTrace();
		}
		
		commit(con);
		close(con);
		
	}

	public int setCountCoupon(ArrayList<PaymentBean> arr, String emp_id) {

		Connection con = getinstance().getconnection();
		
		ListDAO dao = ListDAO.getInstance();
		int result = 1;
		dao.setConnection(con);
		
		try {
			con.setAutoCommit(false);
			result = dao.setCountProcedure(arr,emp_id);
			
		} catch (Exception e) {
			rollback(con);
			e.printStackTrace();
		}
		
		commit(con);
		close(con);
		
		return result;
	}

}
