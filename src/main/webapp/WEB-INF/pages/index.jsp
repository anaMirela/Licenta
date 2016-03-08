<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>My page</title>
</head>
<body>
	<h1>Licenta</h1>
	<form method="POST" action="/licenta/checkout">
		<h3> Checkout from Git form</h3>
		<label for="remoteUrl">Remote URL</label>
		<input id="remoteUrl" type="text"><br><br>
		<label for="directoryPath">Path for checkout directory:</label>
		<input id="directoryPath" type="text"><br>
     	<input type="submit" value="Submit"/><br>
     </form>
</body>
</html>