<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>

<head>
</head>

<c:forEach items="${sensors}" var="sensor" varStatus="loop">
	<div id="sensor_${sensor.id}]">
		${sensor.name} (${sensor.type}) #${sensor.id} @ ${sensor.location} <br />
		<i>${sensor.description}</i> <br /> <b>Latest value:
		${sensor.latestValue}</b>
	</div>
</c:forEach>

<body>
</body>


</html>