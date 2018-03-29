<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="DAO.factory.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<form action="" method="post" name="form1">
<table align="center">
			<tr><td>用户名：</td><td><input type="text" name="account" value="${param.account}"></td></tr>
			<tr><td>密    码：</td><td><input type="password" name="password" value="${param.password}"></td></tr>
			<tr><td>身    份：</td>
			<td><select name="shenfen">
			<option value="普通用户" selected>普通用户</option>
			<option value="管理员">管理员</option></select></td></tr>
			<tr><td>验证码:</td><td><input type="text" name="checkcode" />
			<img border="0" src="adminControlAccountServlet?flag=biuldYzm"/></td>
			<td><input type="submit" value="换一张" onclick="form1.action='adminControlAccountServlet?flag=refreshCheckCode'"/></td></tr>
			<tr><td><input type="submit" value="登录" onclick="form1.action='adminControlAccountServlet?flag=loginUse'"/></td>
			<td><input type="reset" value="重置"/></td></tr>
			</table>
		</form>
<font size="20px" color="red">
${info}
</font>
</html>