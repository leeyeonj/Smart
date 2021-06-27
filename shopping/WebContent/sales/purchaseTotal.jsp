<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
   <script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawVisualization);
   
      function drawVisualization() { 
         var data = google.visualization.arrayToDataTable(${googleList});
         var options = {
               title : '상품별 판매 현황',
               vAxis: {title: '상품번호'},
               hAxis: {title: '구매수량'}, 
               seriesType: 'bars',
               series: {5: {type: 'line'}}
            };
         
         var chart = new google.visualization.ComboChart(document.getElementById('chart_div'));
         chart.draw(data, options);
      }
   </script>
</head>
<body>
<div id="chart_div" style="width:900px; height: 500px;"></div>
상품별 현황<br />
<table>
    <tr><td>상품번호</td><td>구매수량</td></tr>
	<c:forEach items="${list }" var="dto">
    	<tr><td>${dto.prodNum }/${dto.sumPrice }</td>
    		</tr>
</c:forEach>
</table>
</body>
</html>