package emp.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import emp.svc.EMPLoginService;
import emp.vo.ActionForward;

public class EMPLoginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = null;
		HttpSession session = request.getSession();
		int isLogin = 0;
		
		String id = request.getParameter("EMP_id");
		String pass = request.getParameter("EMP_pass");
		
		EMPLoginService EMP_s = new EMPLoginService();
		isLogin = EMP_s.EMPLogin(id, pass);

		if(isLogin==0) {
			response.setContentType("text/html;charset=UTF-8"); 
			PrintWriter out = response.getWriter(); 
			out.println("<script>"); 
			out.println("alert('아이디 오류')"); 
			out.println("history.back()");
			out.println("</script>");
			
		}else if(isLogin==-1) {
			response.setContentType("text/html;charset=UTF-8"); 
			PrintWriter out = response.getWriter(); 
			out.println("<script>"); 
			out.println("alert('비밀번호 오류')"); 
			out.println("history.back()");
			out.println("</script>");
		}else{
			session.setAttribute("EMP_ID", id);
			session.setAttribute("EMP_LEVEL", isLogin);
			forward=new ActionForward();
			forward.setPath("main.vi");
			forward.setRedirect(false);
		}
	
		return forward;
	}

}
