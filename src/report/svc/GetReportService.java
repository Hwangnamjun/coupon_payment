package report.svc;

import static main.db.DBConnection.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import report.dao.ListDAO;
import report.vo.ReportBean;

public class GetReportService {

	public ArrayList<ReportBean> getPaymentList(HashMap<String, String> map) {
		// TODO Auto-generated method stub
		ArrayList<ReportBean> result = null;
		
		Connection con = getinstance().getconnection();
		
		ListDAO dao = ListDAO.getInstance();

		dao.setConnection(con);
		result = dao.getList(map);
		
		close(con);
		
		return result;
	}

}
