package emp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import emp.vo.ActionForward;

public class EMPLogoutAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=null;
		
		HttpSession session=request.getSession();
		
		response.setContentType("text/html;charset=UTF-8"); 
		session.invalidate();
		
		forward=new ActionForward();
		forward.setPath("login.dd");
		forward.setRedirect(false);
		
		return forward;
	}

}
