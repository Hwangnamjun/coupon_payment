package report.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import report.vo.ActionForward;

public interface Action {
	
	ActionForward execute(HttpServletRequest request, HttpServletResponse response)throws Exception;

}
