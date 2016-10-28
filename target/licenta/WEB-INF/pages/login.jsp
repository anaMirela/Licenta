<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<body>
	<h1>Log in form</h1>
	<form method="POST" action="login">
		<label for="username">Username</label>
		<input id="username" type="text" name="username"><br><br>
		<label for="password">Password</label>
		<input id="password" type="password" name="password"><br>
     	<input type="submit" value="Log in"/><br>
     </form>
     
     <a href="/register"> Register here </a>
</body>
</html>