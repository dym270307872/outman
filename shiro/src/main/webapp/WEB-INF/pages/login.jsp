<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${greeting}</title>
</head>
<body>
    <shiro:guest> 
 
	Hi there!  Please <a href="login.jsp">Login</a> or <a href="signup.jsp">Signup</a> today! 
 
	</shiro:guest>
</body>
</html>