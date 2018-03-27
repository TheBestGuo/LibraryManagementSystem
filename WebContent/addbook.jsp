<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="adminControlBookServlet?flag=addbook" method="post">
书号：<input type="text" name="book_id"><br>
书名：<input type="text" name="book_name"><br>
出版社：<input type="text" name="book_from"><br>
类型：<input type="text" name="book_class"><br>
总量：<input type="text" name="book_all"><br>
<input type="submit" value="确定">&nbsp;<input type="reset" value="重置">
<br>
<%if(request.getAttribute("res")!=null) {%>

<%=request.getAttribute("res")%>

<%} %>
</form>


</body>
</html>