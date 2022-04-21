package main.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.svc.AddingPaymentService;
import main.vo.ActionForward;
import main.vo.PaymentBean;

public class paymentCouponAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		ActionForward forward = new ActionForward();
		
		ArrayList<PaymentBean> arr = null;
		
		AddingPaymentService aps = new AddingPaymentService();
		
		arr = aps.getUserList();
		request.setAttribute("list", arr);
		forward.setPath("main/coupon_payment.jsp");

		return forward;
	}

}
