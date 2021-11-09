<%@page import="model.Bean.Account"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		Account a = (Account)request.getSession().getAttribute("account");
		if (a!=null){
			response.sendRedirect("MyHome.jsp");
		}
	%>
	<form action="CheckLoginServlet" method="post">
		ID: <input type="text" name="id"><br>
		PW: <input type="password" name="pw"><br>
		<input type="submit" value="Login">
	</form>
</body>
</html>