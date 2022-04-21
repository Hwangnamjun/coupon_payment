package main.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.svc.CreateService;
import main.vo.ActionForward;
import main.vo.PaymentBean;

public class CreateCouponAction implements Action  {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = new ActionForward();

		ArrayList<PaymentBean> arr = new ArrayList<>();
		int counter = Integer.parseInt(request.getParameter("counter"));
		
		for(int i = 0; i < counter; i++)
		{
			int NUM = Integer.parseInt(request.getParameter("NUM_3_"+i));
			NUM += Integer.parseInt(request.getParameter("NUM_5_"+i));
			NUM += Integer.parseInt(request.getParameter("NUM_7_"+i));
			NUM += Integer.parseInt(request.getParameter("NUM_10_"+i));
			
			if(NUM != 0)
			{
			PaymentBean bean = new PaymentBean();
			bean.setMEMBER_NO(request.getParameter("MEMBER_NO_"+i));
			bean.setNUM_3(Integer.parseInt(request.getParameter("NUM_3_"+i)));
			bean.setNUM_5(Integer.parseInt(request.getParameter("NUM_5_"+i)));
			bean.setNUM_7(Integer.parseInt(request.getParameter("NUM_7_"+i)));
			bean.setNUM_10(Integer.parseInt(request.getParameter("NUM_10_"+i)));
			arr.add(bean);
			}
		}
		CreateService cs = new CreateService();
		HttpSession session = request.getSession();
		int level = (int)session.getAttribute("EMP_LEVEL");
		int result = 1;
		if(level <= 20)
		{			
			cs.setCoupon(arr);
		}
		else
		{
			String emp_id = (String)session.getAttribute("EMP_ID");
			result = cs.setCountCoupon(arr,emp_id);
		}
		
		if(result == 1)
		{
			response.setContentType("text/html;charset=UTF-8"); 
			PrintWriter out = response.getWriter(); 
			out.println("<script>"); 
			out.println("alert('작업완료')"); 
			out.println("location.href='main.vi';"); 
			out.println("</script>");
		}
		else
		{
			response.setContentType("text/html;charset=UTF-8"); 
			PrintWriter out = response.getWriter(); 
			out.println("<script>"); 
			out.println("alert('작업실패 : 지급 수량을 확인해주세요')"); 
			out.println("location.href='payment.vi';"); 
			out.println("</script>");
		}
		
		return forward;
	}

}
