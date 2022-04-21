package report.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import report.svc.GetReportService;
import report.vo.ActionForward;
import report.vo.ReportBean;

public class ReportAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = new ActionForward();
		ArrayList<ReportBean> arr = null;
		HashMap<String, String> map = new HashMap<>();
		map.put("str_ymd",request.getParameter("str_d"));
		map.put("end_ymd",request.getParameter("end_d"));
		map.put("str_code",request.getParameter("str_c"));
		map.put("dc_rate",request.getParameter("dc_r"));
		
		GetReportService grs = new GetReportService();
		
		arr = grs.getPaymentList(map);
		
		JSONArray json = new JSONArray();

		for(int i = 0; i < arr.size(); i++)
		{
			JSONObject jobject = new JSONObject();
			jobject.put("sale_ymd", arr.get(i).getSale_ymd());
			jobject.put("str_code", arr.get(i).getStr_code());
			jobject.put("class_name", arr.get(i).getClass_name());
			jobject.put("dc_rate", arr.get(i).getDc_rate());
			jobject.put("pos_no", arr.get(i).getPos_no());
			jobject.put("trxn_no", arr.get(i).getTrxn_no());
			jobject.put("sal_amt", arr.get(i).getAmt());
			jobject.put("dc_amt", arr.get(i).getDc_amt());
			jobject.put("total_amt", arr.get(i).getTotal_amt());
			jobject.put("team_code", arr.get(i).getTeam_code());
			json.add(jobject);
		}
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(json.toString());
		out.close();

		return null;
	}
}
