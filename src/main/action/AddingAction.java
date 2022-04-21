package main.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.svc.AddingUserService;
import main.vo.ActionForward;
import main.vo.UserBean;

public class AddingAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

		ActionForward forward = new ActionForward();

		ArrayList<UserBean> arr = null;
		
		AddingUserService aus = new AddingUserService();
		
		arr = aus.getUserList();
		
		request.setAttribute("list", arr);
		forward.setPath("main/main_adding.jsp");
		
		return forward;
	}

}
