package main.svc;

import static main.db.DBConnection.*;

import java.sql.Connection;
import java.util.ArrayList;

import main.dao.ListDAO;
import main.vo.PaymentBean;


public class AddingPaymentService {

	public ArrayList<PaymentBean> getUserList() {
		// TODO Auto-generated method stub
		ArrayList<PaymentBean> result = null;
		
		Connection con = getinstance().getconnection();
		
		ListDAO dao = ListDAO.getInstance();

		dao.setConnection(con);
		
		result = dao.getPaymentList();
		
		close(con);
		
		return result;
	}

}
