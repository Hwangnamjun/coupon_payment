<%@page import="report.vo.ReportBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<!-- "excel download" -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="../js/xlsx.full.min.js"></script>
<script src="../js/FileSaver.min.js"></script>
<script src="../js/bootstrap-select.min.js"></script> 
<script src="../js/defaults-ko-KR.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){ 
	$('#report_view th').each(function (column) { 
		$(this).click(function() { 
			if($(this).is('.asc')) { // 현재 오름차순인 경우 
				$(this).removeClass('asc'); 
				$(this).addClass('desc'); // 내림차순으로 변경 
				$(this).children().attr('src', "resources/ASC.png"); // 이미지 src 수정 
				sortdir=-1; 
				
			} else { // 현재 오름차순 아닌 경우 
				$(this).addClass('asc'); // 오름차순으로 변경 
				$(this).removeClass('desc'); 
				sortdir=1; 
				$(this).children().attr('src', "resources/DESC.png"); // 이미지 src 수정 
			} 
			
			$(this).siblings().removeClass('asc'); 
			$(this).siblings().removeClass('desc'); 
			
			var rec = $('#report_view').find('tbody>tr').get(); 
			
			rec.sort(function (a, b) { 
				var val1 = $(a).children('td').eq(column).text().toUpperCase(); 
				var val2 = $(b).children('td').eq(column).text().toUpperCase();
				if(isNaN(val1))
					{
						return (val1 < val2)?-sortdir:(val1>val2)?sortdir:0; 
					}
				else
					{
						if(sortdir == 1)
							{
								return val1 - val2;
							}
						else
							{
								return val2 - val1;
							}
					}
			}); 
			
			$.each(rec, function(index, row) { 
				$('#report_view tbody').append(row); 
			}); 
		});
	}); 
});
</script>
<script type="text/javascript">
$(document).ready(function() {
	$("#serch_btn").click(function() {
		var tar = document.getElementById("t_body");
		removeAllchild(tar);
		$("#excel_exp").attr("disabled",false);
		var str_ymd = $("#str_ymd").val();
		var end_ymd = $("#end_ymd").val();
		var str_code = $("#str_select").val();
		var dc_rate = $("#dc_select").val();
        $.ajax({
            url : "serch.re",
            type : "post",
             data : {
                 str_d : str_ymd,
                 end_d : end_ymd,
                 str_c : str_code,
                 dc_r : dc_rate
             },
             dataType : "json",
            success : function(tblresult){
            	var str;
                $.each(tblresult, function(i){
                	var ok_user;

                	switch(tblresult[i].dc_rate){
                		case "5": 
                			switch(tblresult[i].team_code){
                				case "2630":
                					ok_user = (tblresult[i].sal_amt >= 200000) ? "Y" : "N";
                				break;
                				
                				case "2640": 
            						ok_user = (tblresult[i].sal_amt >= 300000) ? "Y" : "N";
                				break;
                				
                				case "2650":

            						ok_user = (tblresult[i].sal_amt >= 400000) ? "Y" : "N";
                				break;
                				
                				default:
                					ok_user = "N";
                				break;
                			}
                		break;

                		case "7":
                			switch(tblresult[i].team_code){
                				case "2630":
                					ok_user = (tblresult[i].sal_amt >= 1500000) ? "Y" : "N";
                				break;
                				
                				case "2640":
            						ok_user = (tblresult[i].sal_amt >= 2000000) ? "Y" : "N";
                				break;
                				
                				case "2650":
            						ok_user = (tblresult[i].sal_amt >= 1500000) ? "Y" : "N";
                				break;
                				
                				default:
                					ok_user = "N";
                				break;
                			}
                		break;
                		
                		case "10":
                			switch(tblresult[i].team_code){
                				case "2630":
                					ok_user = (tblresult[i].sal_amt >= 2000000) ? "Y" : "N";
                				break;
                				
                				case "2640":
            						ok_user = (tblresult[i].sal_amt >= 3000000) ? "Y" : "N";
                				break;
                				
                				case "2650":
            						ok_user = (tblresult[i].sal_amt >= 2000000) ? "Y" : "N";
                				break;
                				
                				default:
                					ok_user = "N";
                				break;
                			}
                		break;
                		
                		default: 
                			ok_user = "N";
                		break;
                	}
                	
                	var pos_no = (tblresult[i].pos_no == null) ? "" : tblresult[i].pos_no;
                	var trxn_no = (tblresult[i].trxn_no == null) ? "" : tblresult[i].trxn_no;
                	var sal_amt = (tblresult[i].sal_amt == null) ? "" : tblresult[i].sal_amt; 
                	var dc_amt = (tblresult[i].dc_amt == null) ? "" : tblresult[i].dc_amt;
                	var total_amt = (tblresult[i].total_amt == null) ? "" : tblresult[i].total_amt; 
                	
                    str += "<TR>"
                    str += '<TD>' + tblresult[i].sale_ymd + '</TD><TD style="white-space:nowrap; text-overflow:ellipsis; overflow:hidden;">' + tblresult[i].class_name + '</TD>'
                    str += '<TD>' + tblresult[i].dc_rate + '</TD><TD>' + tblresult[i].str_code + '</TD><TD>' + pos_no + '</TD><TD>' + trxn_no + '</TD>'                   
                    str += '<TD>' + sal_amt + '</TD><TD>' + dc_amt + '</TD><TD>' + total_amt + '</TD><TD>' + ok_user + '</TD>'
                    str += '</TR>'
                });
                $("#t_body").append(str);
                
            },
            error : function(xhr, status, error){
                alert("통신 에러");
            }
        });
	});
	
	function removeAllchild(tar) {
	    while (tar.hasChildNodes()) {
	    	tar.removeChild(tar.firstChild);
	    }
	};
});
</script>

<script type="text/javascript">
    // ArrayBuffer 만들어주는 함수
$(document).ready(function() {
	$("#excel_exp").click(function() {
    function s2ab(s) {
        var buf = new ArrayBuffer(s.length); //convert s to arrayBuffer
        var view = new Uint8Array(buf);  //create uint8array as viewer
        for (var i=0; i<s.length; i++) view[i] = s.charCodeAt(i) & 0xFF; //convert to octet
        return buf;
    }

    // workbook 생성
    var wb = XLSX.utils.book_new();

    // 문서 속성세팅 ( 윈도우에서 엑셀 오른쪽 클릭 속성 -> 자세히에 있는 값들
    // 필요 없으면 안써도 괜찮다.

    // sheet명 생성
    wb.SheetNames.push("sheet 1");
    // wb.SheetNames.push("sheet 2"); // 시트가 여러개인 경우

    // 이중 배열 형태로 데이터가 들어간다.
    //var wsData = [['A1' , 'A2', 'A3'],['B1','B2','B3'],['C1','C2']];
	  	var wsData = [];
	  	j = 1;
	  	wsData[0] = ['일자','매장명','할인율','점포명','POS번호','영수증번호','기존금액','할인금액','최종결제금액','올바른 사용유무'];
	$("#t_body tr").each(function() {
		
	    var arrayOfThisRow = [];
	    var tableData = $(this).find('td');
	    
	    for(var i = 0; i < 10; i++)
	    	{
		    	arrayOfThisRow[i]=($(this).find("td:eq("+(i)+")").text());
	    	}
	    wsData[j] = arrayOfThisRow;
	    j++;
	});
    // var wsData2 = [['가1' , '가2', '가3'],['나1','나2','나3']];	// 시트가 여러개인 경우

    // 배열 데이터로 시트 데이터 생성
    var ws = XLSX.utils.aoa_to_sheet(wsData);
	// var ws2 = XLSX.utils.aoa_to_sheet(wsData2); 	//시트가 여러개인 경우
    
    // 시트 데이터를 시트에 넣기 ( 시트 명이 없는 시트인경우 첫번째 시트에 데이터가 들어감 )
    wb.Sheets["sheet 1"] = ws;
    // wb.Sheets["sheet 2"] = ws2;	//시트가 여러개인 경우

    // 엑셀 파일 쓰기
    var wbout = XLSX.write(wb, {bookType:'xlsx',  type: 'binary'});

    // 파일 다운로드
    saveAs(new Blob([s2ab(wbout)],{type:"application/octet-stream"}), '엑셀_다운로드.xlsx');
    });
});
</script>

<meta charset="UTF-8">
<title>쿠폰 지급 통계</title>
</head>
<body>
	<jsp:include page="../inc/Header.jsp" />
<link rel="stylesheet" href="../css/bootstrap.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="../js/bootstrap.js"></script>
<div class="wrapper">
<div class="center">
<table class="table" style="margin-top: 20px">
<tr>
<th style="text-align: center; vertical-align: middle;">지급 일자(입력 필수)</th><td><input class="form-control" type="text" id="str_ymd" placeholder="ex) 20220101" style="border-radius:5px;" required></td><td style="vertical-align: middle;">&nbsp;~&nbsp;</td><td><input class="form-control" type="text" id="end_ymd" placeholder="ex) 20220131" style="border-radius:5px;" required></td>
<th style="text-align: center; vertical-align: middle;">점포명</th><td><select style="border-radius:5px;" id="str_select" class="form-select">
		<option value="">전체</option>
		<option value="11">백화점</option>
		<option value="33">남창원점</option>
		<option value="34">금곡점</option>
	</select>
</td>
<th style="text-align: center; vertical-align: middle;">할인율</th><td><select id="dc_select" class="form-select">
		<option value="">전체</option>
		<option value="3">3%</option>		
		<option value="5">5%</option>
		<option value="7">7%</option>
		<option value="10">10%</option>
	</select>
</td><td><button type="button" class="btn btn-outline-dark" id="serch_btn" style=" margin-left: 10px; margin-right: 10px;">검색</button></td>
</tr>
</table>
<button style="display:scroll;position:fixed;bottom:500px;right:50px;" type="button" class="btn btn-outline-dark btn-sm" id="excel_exp" disabled>엑셀로 다운로드</button>
<table class="table table-striped table-bordered table-hover table-sm"  id="report_view" style="margin-top: 20px; table-layout: fixed; width: 1137px;">
<thead id = "t_head">
<tr style="text-align: center;">
<th width="70">일자&nbsp;<img src="resources/ASC.png"></th>
<th width="70">매장명&nbsp;<img src="resources/ASC.png"></th>
<th width="80">할인율&nbsp;<img src="resources/ASC.png"></th>
<th width="80">점포명&nbsp;<img src="resources/ASC.png"></th>
<th width="90">POS번호&nbsp;<img src="resources/ASC.png"></th>
<th width="110">영수증번호&nbsp;<img src="resources/ASC.png"></th>
<th width="90">기존금액&nbsp;<img src="resources/ASC.png"></th>
<th width="90">할인금액&nbsp;<img src="resources/ASC.png"></th>
<th width="120">최종결제금액&nbsp;<img src="resources/ASC.png"></th>
<th width="120">올바른 사용유무&nbsp;<img src="resources/ASC.png"></th>
</tr>
</thead>
<tbody id="t_body">
</tbody>
</table>
</div>
</div>
<script type="text/javascript" src="../js/bootstrap.js"></script>
</body>
</html>