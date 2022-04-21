package main.svc;

import static main.db.DBConnection.*;

import java.sql.Connection;
import java.util.ArrayList;

import main.dao.ListDAO;
import main.vo.UserBean;

public class AddingUserService {

	public ArrayList<UserBean> getUserList() {
		// TODO Auto-generated method stub
		ArrayList<UserBean> result = null;
		
		Connection con = getinstance().getconnection();
		
		ListDAO dao = ListDAO.getInstance();

		dao.setConnection(con);
		result = dao.getList();
		
		close(con);
		
		return result;
	}

}
