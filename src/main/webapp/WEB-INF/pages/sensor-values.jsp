<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@include file="inc/header.jsp"%>

<script type="text/javascript" src="https://www.google.com/jsapi?autoload={
            'modules':[{
              'name':'visualization',
              'version':'1',
              'packages':['corechart']
            }]
          }"></script>


<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<!--  <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>  -->
			<a class="navbar-brand" href="#">Hydromon</a>
		</div>
	</div>
</nav>

<div class="container-fluid">

	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
		<h1 class="page-header">Sensor</h1>

		<div class="row placeholders"></div>

		<div class="table-responsive">

			<div id="sensor">
				<h2>Sensor #${sensor.id}</h2>
				<ul style="list-style-type: none;">
					<li><b>Name:</b>${sensor.name}</li>
					<li><b>Description:</b>${sensor.description}</li>
					<li><b>Location:</b>${sensor.location}</li>
					<li><b>Type:</b>${sensor.type}</li>
					<li><b>24h min:</b> ${dayStatistics.min} <b>max:</b> ${dayStatistics.max} <b>avg:</b> ${dayStatistics.avg}</li>
					<li><b>1w min:</b>  ${weekStatistics.min} <b>max:</b> ${weekStatistics.max} <b>avg:</b> ${weekStatistics.avg}</li>
					<li><b>1m min:</b>  ${weekStatistics.min} <b>max:</b>  ${weekStatistics.max} <b>avg:</b> ${weekStatistics.avg}</li>

					
				</ul>
			</div>
			<div id="day">
				<div id="day_chart" style="width: 800px; height: 500px"></div> <br/>
			</div>

			<div id="week">
				<div id="week_chart" style="width: 800px; height: 500px"></div> <br/>
			</div>

			<div id="month">
				<div id="month_chart" style="width: 800px; height: 500px"></div> <br/>
			</div>
		</div>

	</div>
</div>



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
     //   curveType: 'function',
        legend: { position: 'bottom' },
        
        
        trendlines: { 1: {
            type: 'exponential',
            color: 'green'
          } 
        }
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


<%@include file="inc/footer.jsp"%>