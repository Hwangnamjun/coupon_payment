package main.svc;

import static main.db.DBConnection.*;

import java.sql.Connection;

import main.dao.ListDAO;

public class MainTitleService {

	public int[] getCountNum(String emp_id) {

		int result[];
		
		Connection con = getinstance().getconnection();
		
		ListDAO dao = ListDAO.getInstance();

		dao.setConnection(con);
		
		result = dao.getAddNum(emp_id);
		
		close(con);
		
		return result;
	}

}
