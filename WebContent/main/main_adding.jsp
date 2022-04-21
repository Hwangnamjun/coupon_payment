<%@page import="main.vo.UserBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	ArrayList<UserBean> list = (ArrayList<UserBean>)request.getAttribute("list");
    	UserBean bean = null;
    %>
<!DOCTYPE html>
<html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
 $(document).ready(function() {
	 var ins_chk = 0;
	$("#ins_button").on("click",function(){
		if($(this).attr('value') == "직원 추가")
			{
				var btn = '<button type="button" class="btn btn-outline-info btn-sm" id="end_ins" style="margin-right : 5px">추가 완료</button>';
				var canc_btn = '<button type="button" class="btn btn-outline-danger btn-sm" onclick="location.reload()">취소</button>';
				$("#ins_target").append(btn);
				$("#ins_target").append(canc_btn);
				$("#ins_coupon").attr("disabled",true);
				$("#t_user input").attr("disabled",true);
				ins_chk += 1;
				var input_txt =
					"<input type='text' name='ADD_ID_1'style='margin: 5px 30px 5px 5px; border-radius:5px;' placeholder='아이디'  required>"+
					"<input type='text' name='ADD_NM_1' style='border-radius:5px;' placeholder='이름' >"+
					"<br>"
				$("#ins_field").append(input_txt);
				$(this).val("입력 필드추가");
			}
		else if($(this).attr('value') == "입력 필드추가")
			{
				ins_chk += 1;
				var input_txt = 					
					"<input type='text' name='ADD_ID_"+ins_chk+"' style='margin: 5px 30px 5px 5px; border-radius:5px;' placeholder='아이디'  required>"+
					"<input type='text' name='ADD_NM_"+ins_chk+"' style='border-radius:5px;' placeholder='이름' >"+
					"<br>"
				$("#ins_field").append(input_txt);
			}
	});
	$(document).on("click","#end_ins",function(){
		$("#counter").val(ins_chk);
		$("#inserting").submit();
	});
})
</script>
<head>
<meta charset="UTF-8">
<title>쿠폰횟수 부여 페이지</title>
</head>
<style type="text/css">
.wrapper {
  display: flex;
  justify-content: center;
  min-height: 100vh;
}

.content {
  font-family: system-ui, serif;
  font-size: 2rem;
  padding: 3rem;
  border-radius: 1rem;
}
</style>
<body>
	<jsp:include page="../inc/Header.jsp" />
<div class="wrapper">
<div class="center">
<form action="add.vi" id="adding" method="post">
<table id="t_user" class="table table-striped table-bordered table-hover table-sm" style="margin-top: 20px">
	<tr style="text-align: center;">
		<th rowspan="2" style="vertical-align: middle;">아이디</th><th rowspan="2" style="vertical-align: middle;">이름</th><th colspan="4">횟수</th><td rowspan="2" style="vertical-align: middle;"><input class="btn btn-outline-dark btn-sm" type="submit" id="ins_coupon" value="쿠폰 갯수 저장"></td>
	</tr>
	<tr>
		<th style="text-align: center;">3%</th><th style="text-align: center;">5%</th><th style="text-align: center;">7%</th><th style="text-align: center;">10%</th>
	</tr>
<%
	for(int i = 0; i < list.size(); i++)
	{
		bean = list.get(i);
		%>
	<tr>
		<td><input type="text" class="form-control" style="color: gray;" name="EMP_ID_<%=i %>" value="<%=bean.getEMP_ID() %>" readonly="readonly"></td>
		<td><input type="text" class="form-control" style="color: gray;" name="NM_<%=i %>" value="<%=bean.getNM() %>" readonly="readonly"></td>
		<td><input type="number" class="form-control" name="NUM_3_<%=i %>"value="<%=bean.getNUM_3() %>" ></td>
		<td><input type="number" class="form-control" name="NUM_5_<%=i %>"value="<%=bean.getNUM_5() %>" ></td>
		<td><input type="number" class="form-control" name="NUM_7_<%=i %>"value="<%=bean.getNUM_7() %>" ></td>
		<td><input type="number" class="form-control" name="NUM_10_<%=i %>"value="<%=bean.getNUM_10() %>" ></td>
		<td align="center"><input type="button" class="btn btn-outline-danger btn-sm" value="삭제" onclick="location.href='DelID.vi?emp_id=<%=bean.getEMP_ID() %>&nm=<%=bean.getNM() %>'"></td>
	</tr>
		<%
	}
%>
</table>
<input type="hidden" name="counter" value="<%=list.size() %>">
</form>
	
<form action="InsertID.vi" id="inserting" method="post">
<div id="ins_field" style="width: 500px;">
</div>
<div id="ins_target"><input type="button" class="btn btn-outline-dark btn-sm" id="ins_button" value="직원 추가" style="margin: 5px 5px 5px 5px"></div>
<input type="hidden" id="counter" name="counter">
</form>
<script type="text/javascript" src="/js/bootstrap.js"></script>
</body>
</html>