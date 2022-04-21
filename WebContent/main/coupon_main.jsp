<%@page import="java.io.PrintWriter"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>쿠폰지급 페이지</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="css/styles.css" rel="stylesheet" />
    </head>
    <body>
        <jsp:include page="../inc/Header.jsp" />
    <%
	int level=(int)session.getAttribute("EMP_LEVEL");
	int add_num_3=(int)session.getAttribute("ADD_NUM_3");
	int add_num_5=(int)session.getAttribute("ADD_NUM_5");
	int add_num_7=(int)session.getAttribute("ADD_NUM_7");
	int add_num_10=(int)session.getAttribute("ADD_NUM_10");
	%>
        <!-- Page content-->
        <div class="container">
            <div class="text-center mt-5">
                <a class="btn btn-outline-secondary btn-lg" href="payment.vi">쿠폰지급</a><br><br>
				<%if(level <= 20){ %><a class="btn btn-outline-secondary btn-lg" href="addingList.vi">쿠폰 갯수 부여</a><br><br> <%} %>
				<a class="btn btn-outline-secondary btn-lg" href="report.re">쿠폰지급현황</a><br><br>
				<%if(level > 20){ %><h3>(보유쿠폰 수량) </h3>
                <h5>(3%: <%=add_num_3 %>)</h5>
                <h5>(5%: <%=add_num_5 %>)</h5>
                <h5>(7%: <%=add_num_7 %>)</h5>
                <h5>(10%: <%=add_num_10 %>)</h5><%} %>
            </div>
        </div>
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="js/scripts.js"></script>
    </body>
</html>