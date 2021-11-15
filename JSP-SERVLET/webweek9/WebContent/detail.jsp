<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.bean.People"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome|giabao</title>
</head>
<body>
	<% People people = (People)request.getSession().getAttribute("people");
		if (people !=null ) 
		{
		%>
	<div class="container">
		<h1>Detail Info</h1>
		<form action="<%=request.getContextPath() %>/LogoutServlet"
			method="POST" name="form1">
			<p>
				ID:
				<%=people.getId()%></p>
			<p>
				Họ Tên:
				<%=people.getName()%></p>
			<p>
				Sở thích:
				<%=people.getHobbies()%></p>
			<p>
				Giới tính:
				<%=people.isGender()%></p>
			<input type="submit" value="Logout" name="Logout" />
		</form>
	</div>
	<%
		} else{
			response.sendRedirect("login.jsp");
		}
	%>
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
  input[type="submit"] {
    color: #253e63;
    width: 100px;
    margin-top: 20px;
    margin-left: 15px;
    cursor: pointer;
  }
  input[type="submit"]:hover {
    background-color: #aad4ef;
  }
</style>
</html>
