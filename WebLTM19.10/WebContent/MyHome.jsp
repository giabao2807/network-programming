<%@page import="model.BO.CheckLoginBO"%>
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
		Account a = (Account)request.getSession().getAttribute("acount");
		if (a!=null){
	%>
			<h1> My Home</h1>
			Id: <%=a.getId() %> <br>
			Tên: <%=a.getName() %> <br>
			Giới tính: <%=a.getSex() %> <br>
			Sở thích: <%=a.getSt() %> <br>
			<form action="LogoutServlet">
				<input type="submit" value="Đăng xuất">
			</form>
	<%
		} else{
			response.sendRedirect("Login.jsp");
		}
	%>
</body>
</html>