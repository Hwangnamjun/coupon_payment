<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<script type="text/javascript">
	
$(document).ready(function() {
	var session_id = '<%=(String)session.getAttribute("EMP_ID")%>';
	
	if(session_id == '' || session_id == 'null')
		{
		location.href = 'login.dd';
	}
})

</script>
<head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="css/styles.css" rel="stylesheet" />
<meta charset="UTF-8">
</head>
<body>
<%
String id = "";
int level = 0;
if (session != null || session.getAttribute("EMP_ID") != null || !(session.getAttribute("EMP_ID").equals("")) || !request.isRequestedSessionIdValid())
{
id=(String)session.getAttribute("EMP_ID");
level=(int)session.getAttribute("EMP_LEVEL");
}
%>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="container">
                <a class="navbar-brand" href="main.vi">쿠폰지급 페이지</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav mr-auto">
				      <li class="nav-item active">
				        <a class="nav-link" href="payment.vi">쿠폰지급</a>
				      </li>
				      <%if(level <= 20){ %>
				      <li class="nav-item">
				        <a class="nav-link" href="addingList.vi">쿠폰 갯수 부여</a>
				      </li> <%} %>
				      <li class="nav-item">
				        <a class="nav-link" href="report.re">쿠폰지급현황</a>
				      </li>
				    </ul>
                    <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                    	<li class="nav-item"><a class="nav-link"><%=id %>님</a></li>
                        <li class="nav-item"><a class="nav-link active" aria-current="page" href="main.vi">Home</a></li>
                        <li class="nav-item"><a class="nav-link" href="EMPLogout.dd">logout</a></li>
                    </ul>
                </div>
            </div>
        </nav>
                    <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="js/scripts.js"></script>
</body>
</html>