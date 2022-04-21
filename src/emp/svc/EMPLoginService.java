package emp.svc;

import static emp.db.DBConnection.*;

import java.sql.Connection;

import emp.dao.MemberDAO;

public class EMPLoginService {

	public int EMPLogin(String id, String pass) {
		// TODO Auto-generated method stub

		int result = 0;

		Connection con = getinstance().getconnection();
		
		MemberDAO mdao = MemberDAO.getMdao();
		
		mdao.setConnection(con);
		result = mdao.EMP_Login(id, pass);

		if(result == 1)
		{
			result = mdao.EMP_LEVEL(id);
		}
		close(con);
		
		return result;
	}
}
