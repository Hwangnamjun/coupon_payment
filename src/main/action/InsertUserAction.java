package main.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.svc.InsertUserService;
import main.vo.ActionForward;
import main.vo.UserBean;

public class InsertUserAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();
		
		ArrayList<UserBean> arr = new ArrayList<>();
		int counter = Integer.parseInt(request.getParameter("counter"));
		for(int i = 1; i < counter+1; i++)
		{
			UserBean bean = new UserBean();
			bean.setEMP_ID(request.getParameter("ADD_ID_"+i));
			bean.setNM(request.getParameter("ADD_NM_"+i));
			arr.add(bean);
		}
		InsertUserService ius = new InsertUserService();
		
		ius.CreateUser(arr);

		response.setContentType("text/html;charset=UTF-8"); 
		PrintWriter out = response.getWriter(); 
		out.println("<script>"); 
		out.println("alert('작업완료')"); 
		out.println("location.href='addingList.vi';"); 
		out.println("</script>");
		
		return forward;
	}
}
