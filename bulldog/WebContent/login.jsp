<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<% if( session.getAttribute("login_err") != null){
		%> <p style="color:red;"> <%=session.getAttribute("login_err")%></p> <%} %>
		
<form action="login" method="post">

Email or user name:  <input type="text" name="e_name" placeholders="email or password"><br>
password:    <input type="text" name="pin" placeholders="password"><br>
<input type="submit">
</form>

</body>
</html>