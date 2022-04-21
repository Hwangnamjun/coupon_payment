package main.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.svc.MainTitleService;
import main.vo.ActionForward;

public class MainTitleAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		String emp_id = (String)session.getAttribute("EMP_ID");
		int num[] = new int[4];
		
		MainTitleService mts = new MainTitleService();
		
		num = mts.getCountNum(emp_id);
		session.setAttribute("ADD_NUM_3", num[0]);
		session.setAttribute("ADD_NUM_5", num[1]);
		session.setAttribute("ADD_NUM_7", num[2]);
		session.setAttribute("ADD_NUM_10", num[3]);
		forward.setPath("main/coupon_main.jsp");

		return forward;
	}

}
