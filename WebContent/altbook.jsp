<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="vo.JavaBean.*,java.util.*,DAO.factory.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%List<BookBean> ls = (List<BookBean>)request.getAttribute("book"); %>
<form action="adminControlBookServlet?flag=altbook&id=<%=ls.get(0).getBook_id() %>" method="post">

书号：<input type="text" name="book_id" value="<%=ls.get(0).getBook_id() %>"><br>
书名：<input type="text" name="book_name" value="<%=ls.get(0).getBook_name() %>"><br>
出版商：<input type="text" name="book_from" value="<%=ls.get(0).getBook_from() %>"><br>
种类：<input type="text" name="book_class" value="<%=ls.get(0).getBook_class() %>"><br>
总库存量：<input type="text" name="book_all" value="<%=ls.get(0).getBook_all() %>"><br>
剩余库存：<input type="text" name="book_last" value="<%=ls.get(0).getBook_last() %>"><br>
<input type="submit" value="提交">&nbsp;<input type="reset" value="重置">

</form>
</body>
</html>