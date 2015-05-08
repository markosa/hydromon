<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<head>
<script type="text/javascript"
	src="https://www.google.com/jsapi?autoload={
            'modules':[{
              'name':'visualization',
              'version':'1',
              'packages':['corechart']
            }]
          }"></script>

<script type="text/javascript">
      google.setOnLoadCallback(drawChart);

      function drawChart() {
        var dayData = google.visualization.arrayToDataTable([
          ['Date', '${sensor.name}'],
          
          <c:forEach items="${dayValues}" var="value" varStatus="loop">
         	 ['${value.formattedTime}',  ${value.value}] <c:if test="${!loop.last}">,</c:if>
          </c:forEach>

         ]);

        var dayOptions = {
          title: '24h',
          curveType: 'function',
          legend: { position: 'bottom' }
        };

        var dayChart = new google.visualization.LineChart(document.getElementById('day_chart'));
        dayChart.draw(dayData, dayOptions);
        
        var weekData = google.visualization.arrayToDataTable([
        ['Date', '${sensor.name}'],
        
        <c:forEach items="${weekValues}" var="value" varStatus="loop">
       	 ['${value.formattedTime}',  ${value.value}] <c:if test="${!loop.last}">,</c:if>
        </c:forEach>

       ]);

      var weekOptions = {
        title: '1 week',
        curveType: 'function',
        legend: { position: 'bottom' }
      };

      var weekChart = new google.visualization.LineChart(document.getElementById('week_chart'));
      weekChart.draw(weekData, weekOptions);
        
      var monthData = google.visualization.arrayToDataTable([
      ['Date', '${sensor.name}'],
      
      <c:forEach items="${monthValues}" var="value" varStatus="loop">
     	 ['${value.formattedTime}',  ${value.value}] <c:if test="${!loop.last}">,</c:if>
      </c:forEach>

     ]);

    var monthOptions = {
      title: '1 month',
      curveType: 'function',
      legend: { position: 'bottom' }
    };

    var monthChart = new google.visualization.LineChart(document.getElementById('month_chart'));
    monthChart.draw(monthData, monthOptions);  
      }
    </script>
</head>
<body>
	<div id="sensor">
		<h2>Sensor #${sensor.id}</h2>
		<ul style="list-style-type: none;">
			<li><b>Name:</b>${sensor.name}</li>
			<li><b>Description:</b>${sensor.description}</li>
			<li><b>Location:</b>${sensor.location}</li>
			<li><b>Type:</b>${sensor.type}</li>
		</ul>
	</div>
	<div id="day_chart" style="width: 900px; height: 500px"></div>
	<div id="week_chart" style="width: 900px; height: 500px"></div>
	<div id="month_chart" style="width: 900px; height: 500px"></div>

</body>
</html>
