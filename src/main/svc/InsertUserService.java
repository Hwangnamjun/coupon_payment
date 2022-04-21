package main.svc;

import static main.db.DBConnection.close;
import static main.db.DBConnection.commit;
import static main.db.DBConnection.getinstance;
import static main.db.DBConnection.rollback;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import main.dao.ListDAO;
import main.vo.UserBean;

public class InsertUserService {

	public void CreateUser(ArrayList<UserBean> arr) {
		// TODO Auto-generated method stub

		Connection con = getinstance().getconnection();
		
		ListDAO dao = ListDAO.getInstance();

		dao.setConnection(con);
		
		try {
			con.setAutoCommit(false);
			dao.insertUser(arr);
			
		} catch (SQLException e) {
			rollback(con);
			e.printStackTrace();
		}
		
		commit(con);
		close(con);
	}

}
