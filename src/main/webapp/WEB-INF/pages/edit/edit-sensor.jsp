<html>
<body>
	<form action="/edit/" method="post">
		<input type="hidden" name="id" value="${sensor.id}" />
		<br />
		<input type="text" name="description" value="${sensor.description}" />
		<br />
		<input type="text" name="name" value="${sensor.name}" />
		<br />
		<input type="text" name="location" value="${sensor.location}" />
		<br />
		<input type="text" name="type" value="${sensor.type}" />
		<br />
		<input type="text" name="chart" value="${sensor.chart}" />
		<br />
		<input type="submit" name="submit" value="submit" />
		<br />
	</form>
</body>
</html>
