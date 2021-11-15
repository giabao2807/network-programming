<%@page import="model.bean.People"%>
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
	<%
	People people = (People) request.getSession().getAttribute("people");
	if (people != null) {
		response.sendRedirect("detail.jsp");
	}
	%>
	<div class="container">
		<h1>Login</h1>
		<form action="<%=request.getContextPath()%>/CheckLoginServlet"
			method="POST" name="form1">
			<label for="username">Username</label> <input type="text"
				name="username" id="username" /> <br /> <label for="password">Password</label>
			<input type="password" name="password" id="password" /> 
			<br />
			<input type="submit" value="OK" onClick="checkInput()" /> 
			<input type="reset" id="reset" /> 
			<br />
			<a href="<%= request.getContextPath()%>/RegisterServlet">Register</a>
		</form>
	</div>
</body>
<style>
    body {
      color: #253e63;
      height: 100vh;
      display: flex;
      justify-content: center;
      align-items: center;
      margin: 0;
    }
    .container {
    font-family: Arial, Helvetica, sans-serif;
    display: flex;
    flex-direction: column;
    border: 1px solid #253e63;
    border-radius: 4px;
    padding: 20px;
    box-shadow: #253e63 0px 1px 2px 0px, #253e63 0px 1px 3px 1px;
    text-align: center;
  }
  input {
    border: 1px solid #253e63;
    border-radius: 3px;
    padding: 4px;
    margin-top: 10px;
  }
  select {
    border: 1px solid #253e63;
    border-radius: 3px;
    padding: 4px;
    margin-top: 10px;
  }
  input[type="submit"],
  input[type="reset"] {
    color: #253e63;
    width: 100px;
    margin-top: 20px;
    margin-left: 15px;
    margin-bottom: 20px;
    
    cursor: pointer;
  }
  input[type="submit"]:hover,
  input[type="reset"]:hover {
    background-color: #aad4ef;
  }
  a {
    width: 300px;
    color: #253e63;
    text-align: center;
    text-decoration: none;
    border: 1px solid #253e63;
    border-radius: 4px;
    margin-top: 10px;
    margin-bottom:10px;
    
    padding: 5px;
  }
  a:hover {
    background-color: #aad4ef;
    box-shadow: #aad4ef 0px 1px 2px 0px, #aad4ef 0px 1px 3px 1px;
  }
  </style>
  
</html>