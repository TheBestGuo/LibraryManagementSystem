<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="vo.JavaBean.*,java.util.*,DAO.factory.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%List<UserBean> ls = (List<UserBean>)request.getAttribute("user"); %>
<form action="adminControlAccountServlet?flag=altuser&id=<%=ls.get(0).getAccount() %>" method="post">

账号：<input type="text" name="user_account" value="<%=ls.get(0).getAccount() %>"><br>
密码：<input type="text" name="user_password" value="<%=ls.get(0).getPassword() %>"><br>
类型：<select name="user_job">
<%String type = ls.get(0).getJob(); %>
	<option balue="管理员" <%if("管理员".equals(type)) {%>selected="selected" <%}%>>管理员</option>
	<option balue="普通用户" <%if("普通用户".equals(type)) {%>selected="selected" <%} %>>普通用户</option>
	</select><br>
电话<input type="text" name="user_phone" value="<%=ls.get(0).getPhone() %>"><br>

<input type="submit" value="提交">&nbsp;<input type="reset" value="重置">

</form>
</body>
</html>