<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>

<head>

<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript">
      google.load("visualization", "1", {packages:["gauge"]});
      google.setOnLoadCallback(drawChart);
      function drawChart() {
    	  
    	  <c:forEach items="${sensors}" var="sensor" varStatus="loop">
	
	    	   var data_${sensor.id} = google.visualization.arrayToDataTable([
	    	                                                     ['Label', 'Value'],
	    	                                                     ['Value', ${sensor.latestValue}]
	    	                                                   ]);
	      
	
		      var options_${sensor.id} = {
		              width: 200, height: 120,
		              greenFrom: 15, greenTo: 28,
		              minorTicks: 5,
		              max: 50,
		              min: 0
		      };
	      
	     	 var chart_${sensor.id} = new google.visualization.Gauge(document.getElementById('chart_div_${sensor.id}'));
	     	 chart_${sensor.id}.draw(data_${sensor.id}, options_${sensor.id});
     	</c:forEach>

     	 
     	 
      }
</script>


</head>

<c:forEach items="${sensors}" var="sensor" varStatus="loop">
	<div id="sensor_${sensor.id}]">
		<div id="chart_div_${sensor.id}" style="width: 200px; height: 120px;"></div>
		<div id="chart_right_${sensor.id}">
			<a href="/hydromon/sensor/${sensor.id}"> ${sensor.name}
				(${sensor.type}) #${sensor.id} @ ${sensor.location} <br /> <i>${sensor.description}</i>
				<br /> <b>Latest value: ${sensor.latestValue}</b>
			</a>

		</div>



	</div>

	<br />
</c:forEach>

<body>
</body>


</html>