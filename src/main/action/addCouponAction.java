package main.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.svc.AddService;
import main.vo.ActionForward;
import main.vo.UserBean;

public class addCouponAction implements Action  {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = new ActionForward();

		ArrayList<UserBean> arr = new ArrayList<>();
		int counter = Integer.parseInt(request.getParameter("counter"));
		for(int i = 0; i < counter; i++)
		{
			UserBean bean = new UserBean();
			bean.setEMP_ID(request.getParameter("EMP_ID_"+i));
			bean.setNUM_3(Integer.parseInt(request.getParameter("NUM_3_"+i)));
			bean.setNUM_5(Integer.parseInt(request.getParameter("NUM_5_"+i)));
			bean.setNUM_7(Integer.parseInt(request.getParameter("NUM_7_"+i)));
			bean.setNUM_10(Integer.parseInt(request.getParameter("NUM_10_"+i)));
			arr.add(bean);
		}
		
		AddService as = new AddService();
		
		as.setADDList(arr);
		
		response.setContentType("text/html;charset=UTF-8"); 
		PrintWriter out = response.getWriter(); 
		out.println("<script>"); 
		out.println("alert('작업완료')"); 
		out.println("location.href='addingList.vi';"); 
		out.println("</script>");
		
		return forward;
	}

}
