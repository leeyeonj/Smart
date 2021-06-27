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
               title : '월별 판매 현황',
               vAxis: {title: '구매 월'},
               hAxis: {title: '구매 합'}, 
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
고객별 현황<br />
<table>
    <tr><td>구매 월</td><td>상품번호</td><td>구매합</td>
    	</tr>
	<c:forEach items="${list }" var="dto">
    	<tr><td>${dto.purchaseDate }</td>
    		<td>${dto.prodNum }</td>
    		<td>${dto.sumPurchase }</td></tr>
</c:forEach>
</table>
</body>
</html>