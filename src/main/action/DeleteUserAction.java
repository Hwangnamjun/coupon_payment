package main.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.svc.DeleteUserService;
import main.vo.ActionForward;
import main.vo.UserBean;

public class DeleteUserAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();
		
		UserBean bean = new UserBean();
		bean.setEMP_ID(request.getParameter("emp_id"));
		bean.setNM(request.getParameter("nm"));
		
		DeleteUserService dus = new DeleteUserService();
		
		dus.DeleteUser(bean);
		
		response.setContentType("text/html;charset=UTF-8"); 
		PrintWriter out = response.getWriter(); 
		out.println("<script>"); 
		out.println("alert('작업완료')"); 
		out.println("location.href='addingList.vi';"); 
		out.println("</script>");
		
		return forward;
	}

}
