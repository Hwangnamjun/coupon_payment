<%@page import="main.vo.PaymentBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	ArrayList<PaymentBean> list = (ArrayList<PaymentBean>)request.getAttribute("list");
    	PaymentBean bean = null;
    %>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
table, td, th {
  border : 1px solid black;
  border-collapse : collapse;
}
th, td {
  text-align: center;
}
.wrapper {
  display: flex;
  justify-content: center;
  min-height: 100vh;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link rel="stylesheet" href="/css/bootstrap.css">
<script type="text/javascript" src="/resource/js/bootstrap.js"></script>
<script>
$(document).ready(function() {
    $("#search").keyup(function() {
        var value = $(this).val().toLowerCase();
        var chk_str = $('#str_select').val();
        $("#class_name tr").filter(function() {
        	$(this).toggle($(this).children('td:nth-child(2)').text().toLowerCase().indexOf(value) > -1)
        });
    });
    
    $("#str_select").change(function() {
        var value = $('#str_select').val();
        if(value != '11')
        	{
        		$("#team_select").prop('disabled',true);
        	}
        else
        	{
    			$("#team_select").prop('disabled',false);
        	}
        $("#class_name tr").filter(function() {
            $(this).toggle($(this).children('td:nth-child(1)').text().toLowerCase().indexOf(value) > -1)
        });
    });
    $("#team_select").change(function() {
        var value = $('#team_select').val();
        $("#class_name tr").filter(function() {
            $(this).toggle($(this).children('td:nth-child(5)').text().toLowerCase().indexOf(value) > -1)
        });
    });
});
</script>
<script type="text/javascript">
$(document).ready(function() {
	$("#all_btn").on("click",function(){
		var all_3 = ($("#all_3").val() == 0) ? 0 : $("#all_3").val();
		var all_5 = ($("#all_5").val() == 0) ? 0 : $("#all_5").val();
		var all_7 = ($("#all_7").val() == 0) ? 0 : $("#all_7").val();
		var all_10 = ($("#all_10").val() == 0) ? 0 : $("#all_10").val();
		
		var str = $('#str_select').val();
		var team = $('#team_select').val();
		 $('#class_name tr').each(function() {
			 $(this).find("td").eq(2).children("input").val(all_3);
			 $(this).find("td").eq(3).children("input").val(all_5);
			 $(this).find("td").eq(4).children("input").val(all_7);
			 $(this).find("td").eq(5).children("input").val(all_10);
		 });
 
	});
});
</script>
<meta charset="UTF-8">
<title>매장 쿠폰지급 페이지</title>
</head>
<body>
	<jsp:include page="../inc/Header.jsp" />
<div class="wrapper">
<div class="center">
<table class="table" style="margin-top: 20px">
<tr>
<td><select id="str_select" class="form-select">
		<option value="">점포명</option>
		<option value="11">백화점</option>
		<option value="33">남창원점</option>
		<option value="34">금곡점</option>
	</select>
</td>
<td><select id="team_select" class="form-select" disabled="disabled">
		<option value="26">팀명</option>
		<option value="2630">잡화팀</option>
		<option value="2640">여성팀</option>
		<option value="2650">남성팀</option>
	</select>
</td><td colspan="3"><input type="text" class="form-control" placeholder="매장명 검색" id="search"></td>
</tr>
<tr>
<td><input type="number" class="form-control" id="all_3" placeholder="3%"></td>
<td><input type="number" class="form-control" id="all_5" placeholder="5%"></td>
<td><input type="number" class="form-control" id="all_7" placeholder="7%"></td>
<td><input type="number" class="form-control" id="all_10" placeholder="10%"></td>
<td><button type="button" class="btn btn-outline-dark btn-sm" id="all_btn">일괄 입력</button></td>
</tr>
<tr>
<td colspan="5"><input class="btn btn-outline-dark" type="button" style="width: 500px" onclick="document.getElementById('coupon').submit();" value="쿠폰지급"></td>
</tr>
</table>
<table class="table table-striped table-bordered table-hover table-sm" id="procedureTableBody">
<thead style="text-align: center;">
<tr>
<th rowspan="2" width="120" style="vertical-align: middle;">점포명</th><th rowspan="2" width="170" style="vertical-align: middle;">매장명</th><th colspan="4" width="30">할인율</th>
</tr>
<tr>
<td>3%</td><td>5%</td><td>7%</td><td>10%</td>
</tr>
</thead>
<form id="coupon" action="creating.vi" method="post">
<tbody id="class_name">
<%
	for(int i = 0; i < list.size(); i++)
	{
		bean = list.get(i);
		%>
	<tr>
		<td>
		<%switch(bean.getSTR_CODE())
			{
		case(11):
			%>백화점(<%
			break;
		case(33):
			%>남창원점(<%
			break;
		case(34):
			%>금곡점(<%
			break;
		default:
			break;
			};%><%=bean.getSTR_CODE() %>)</td>
		<td><input type="hidden" name="MEMBER_NO_<%=i %>" value="<%=bean.getMEMBER_NO() %>" id="<%=bean.getSTR_CODE() %>"><%=bean.getCLASS_NAME() %></td>
		<td><input type="number" class="form-control" name="NUM_3_<%=i %>" value="<%=bean.getNUM_3() %>" ></td>
		<td><input type="number" class="form-control" name="NUM_5_<%=i %>" value="<%=bean.getNUM_5() %>" ></td>
		<td><input type="number" class="form-control" name="NUM_7_<%=i %>" value="<%=bean.getNUM_7() %>" ></td>
		<td><input type="number" class="form-control" name="NUM_10_<%=i %>" value="<%=bean.getNUM_10() %>" ></td>
		<td style="display: none;"><%=bean.getTEAM_CODE() %></td>
	</tr>
		<%
	}
%>
<input type="hidden" name="counter" value="<%=list.size() %>">
</tbody>
</form>
</table>
</div>
</div>
<script type="text/javascript" src="/js/bootstrap.js"></script>
</body>
</html>