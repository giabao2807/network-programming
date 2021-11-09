<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import= "model.bean.People" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome|giabao</title>
<% People people = (People)request.getAttribute("people"); %>
</head>
<body>
	<div class="container">
		<h1>Detail infor</h1>
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
}
</style>
</html>
