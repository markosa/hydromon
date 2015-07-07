<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@include file="inc/header.jsp"%>


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
		<h1 class="page-header">Dashboard</h1>

		<div class="row placeholders">

			<c:forEach items="${sensors}" var="sensor" varStatus="loop">
				<div class="col-xs-6 col-sm-3 placeholder pointer" link-ref="/user/${uid}/${sensor.id}">
					<div id="chart_div_${sensor.id}" style="margin-left: auto; margin-right: auto; width: 130px;"></div>
					<h4>${sensor.name}</h4>
					<span class="text-muted labels">${sensor.description}, ${sensor.location}</span>
				</div>
			</c:forEach>

		</div>

		<h2 class="sub-header">Sensor data</h2>

		<div class="table-responsive">
			<table class="table table-hover table-striped">
				<thead>
					<tr>
						<th>Sensor</th>
						<th>Timestamp</th>
						<th>Value</th>
						<!-- 	<th>Running average</th>  -->
						<th>Unit</th>
						<th>Location</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${sensors}" var="sensor" varStatus="loop">
						<tr link-ref="/user/${uid}/${sensor.id}">
							<th>${sensor.name} ${sensor.id}<br/>
							<span class="labels">${sensor.description}</span>
							</th>
							<th>${sensor.latestValueTime}</th>
							<th>${sensor.latestValue}</th>
							<!--  <th>Running average</th> -->
							<th>${sensor.unit}</th>
							<th>${sensor.location}</th>
							
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>

<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript">
      google.load("visualization", "1", {packages:["gauge"]});
      google.setOnLoadCallback(drawChart);
      function drawChart() {
    	  
    	  <c:forEach items="${sensors}" var="sensor" varStatus="loop">
			  	  <c:set var="latestValue" value="${sensor.latestValue}"/>
				  <c:if test="${empty sensor.latestValue}">
				  	<c:set var="latestValue" value="0"/>
				  </c:if>
		    	  var data_${sensor.id} = google.visualization.arrayToDataTable([
		    	                                                     ['Label', 'Value'],
		    	                                                     ['Value', ${latestValue}]
		    	                                                   ]);
		          var options_${sensor.id} = options['${sensor.type}'];
		          
		     	 var chart_${sensor.id} = new google.visualization.Gauge(document.getElementById('chart_div_${sensor.id}'));
		     	 chart_${sensor.id}.draw(data_${sensor.id}, options_${sensor.id});
	     	
     	</c:forEach>
     	 
      }

 var options = {
		'DS18B20': {
            width: 200, height: 120,
            greenFrom: 15, 
            greenTo: 23,
            yellowFrom: 24, 
            yellowTo: 27,
            redFrom: 28, 
            redTo: 44,
            minorTicks: 5,
            max: 44,
            min: 0	 
		 },
		'DHT22, AM2302': {
            width: 200, 
            height: 120,
            minorTicks: 5,
            max: 100,
            min: 0	 
		 },
		'YF-S201': {
            width: 200, height: 120,
            minorTicks: 5,
            max: 5,
            min: 0	 			 
		 },
		 'HC-SR04': {
            width: 200, height: 120,
            minorTicks: 10,
            redFrom: 0,
            redTo: 50,
            yellowFrom: 50,
            yellowTo: 100,
            greenFrom: 101,
            greenTo: 400,
            max: 400,
            min: 0	  
		 },
		 'EC-probe #1': {
            width: 200, height: 120,
            minorTicks: 10,
            max: 2,
            min: 0	  
		 },
		 'PH-probe #1': {
            width: 200, height: 120,
            minorTicks: 10,
            redFrom: 0,
            redTo: 7,
            greenFrom: 7,
            greenTo: 14,
            max: 14,
            min: 0	  		 
		 }		
		 
 }     
 


 jQuery( document ).ready(function() {
	 
	 jQuery('tr[link-ref]').on("click", function() {
		 document.location = $(this).attr('link-ref');
	 });

	 
	 jQuery('div[link-ref]').on("click", function() {
		 document.location = $(this).attr('link-ref');
	 });

	 
 });
 
 
 
 </script>

<%@include file="inc/footer.jsp"%>
