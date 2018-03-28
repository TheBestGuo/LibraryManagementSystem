<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="adminControlAccountServlet?flag=adduser" method="post">
账号：<input type="text" name="user_account"><br>
密码：<input type="text" name="user_password"><br>
电话：<input type="text" name="user_phone"><br>
类型：<select name="user_job">
	<option balue="管理员">管理员</option>
	<option balue="普通用户">普通用户</option>
	</select><br>
<input type="submit" value="确定">&nbsp;<input type="reset" value="重置">
<br>


</form>


</body>
</html>