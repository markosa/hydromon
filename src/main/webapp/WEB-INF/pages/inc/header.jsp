<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<!--  <link rel="icon" href="../../favicon.ico"> -->

<title>Welcome to Hydromon</title>

<!-- Bootstrap core CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

<!-- Custom styles for this template -->
<!--  Local dev, remove at some point -->
<link href="/hydromon/css/dashboard.css" rel="stylesheet">

<!-- Production:  -->
<link href="/css/dashboard.css" rel="stylesheet">

<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
<script src="../assets/js/ie-emulation-modes-warning.js"></script>

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    
    
</head>
<body>


	<div class="row">
		<div class="col-sm-3 col-md-2 sidebar">
			<ul class="nav nav-sidebar">
				<c:set var="overviewUrl" value="/user/${uid}" />
				<li class="<c:if test="${requestScope['javax.servlet.forward.request_uri'] eq overviewUrl}">active</c:if>"><a href="/user/${uid}">Overview <span class="sr-only">(current)</span></a></li>
				<c:forEach items="${sensors}" var="sensor" varStatus="loop">
					<c:set var="sensorUrl" value="/user/${uid}/${sensor.id}" />
					<li class="<c:if test="${requestScope['javax.servlet.forward.request_uri'] eq sensorUrl}">active</c:if>"><a href="/user/${uid}/${sensor.id}">${sensor.name}<span class="sr-only">(current)</span></a></li>
				</c:forEach>
			</ul>

		</div>