package main.svc;

import static main.db.DBConnection.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import main.dao.ListDAO;
import main.vo.UserBean;

public class AddService {

	public void setADDList(ArrayList<UserBean> arr) {

		Connection con = getinstance().getconnection();
		
		ListDAO dao = ListDAO.getInstance();

		dao.setConnection(con);
		
		try {
			con.setAutoCommit(false);
			dao.setList(arr);
			
		} catch (SQLException e) {
			rollback(con);
			e.printStackTrace();
		}
		
		commit(con);
		close(con);

	}

}
