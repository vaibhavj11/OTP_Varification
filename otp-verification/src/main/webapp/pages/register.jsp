<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title>Register</title>
</head>
<body>
	<h2>User Registration</h2>
	<form action="/register" method="post" modelAttribute="user">
        Email: <input type="text" name="email" />
		<br />
        Password: 
        <input type="text" name="password">
		<br />
		<input type="submit" value="Register" />
	</form>
</body>
</html>
