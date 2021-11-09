<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Form1|Login</title>
<script>
	function checkInput() {
		var a = document.getElementById("username").value;
		var b = document.getElementById("password").value;
		if (a == "" || b == "") {
			alert("Mời nhập đủ thông tin!");
		}
	}
</script>
</head>
<body>
	<div class="container">
		<h1>Login</h1>
		<form action="<%= request.getContextPath() %>/CheckLoginServlet" method="POST" name="form1">
			<label for="username">Username</label> <input type="text"
				name="username" id="username" /> <br /> <label for="password">Password</label>
			<input type="password" name="password" id="password" /> <br /> <input
				type="submit" value="OK" onClick="checkInput()" /> <input
				type="reset" id="reset" /> <br />
		</form>
	</div>
</body>
<style>
body {
	background-color: lightblue;
	color: rgb(35, 26, 97);
	font-family: Arial, Helvetica, sans-serif;
	height: 100vh;
	display: flex;
	justify-content: center;
	align-items: center;
	margin: 0;
}

.container {
	background-color: white;
	border: 2px solid rgb(35, 26, 97);
	border-radius: 3px;
	box-shadow: rgb(35, 26, 97) 0px 3px 8px;
	padding: 20px;
	text-align: center;
}

input {
	border: 1px solid rgb(35, 26, 97);
	border-radius: 3px;
	padding: 4px;
	margin-top: 10px;
}

input[type="submit"], input[type="reset"] {
	background-color: rgb(35, 26, 97);
	color: white;
	width: 80px;
	margin-top: 20px;
	cursor: pointer;
}

input[type="submit"]:hover, input[type="reset"]:hover {
	background-color: slateblue;
}
</style>
</html>