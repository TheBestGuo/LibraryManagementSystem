<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="DAO.factory.*,java.util.*,vo.JavaBean.*,java.text.SimpleDateFormat"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>精确查询</title>
</head>
<body>
<%session.setAttribute("account", "10004"); %>
  <form action="userControlServlet?flag=find" method="post">
	  书号：<input type="text" name="book_id"></br>
	  书名：<input type="text" name="book_name"></br>
	  出版社：<input type="text" name="book_from"></br>
	  分类：<select name="book_class">
	  <option value="">全部</option>
	 <%
	  List<String> list =bookFactory.getInterfaceUseDao().findBookClass();
	 for(int i=0;i<list.size();i++)
	 {%>
		<option value="<%=list.get(i)%>"><%=list.get(i) %></option>
	 <%}
	 %>
	 </select>
  	<input type="submit" value="查询">
  </form>
  
  
  <font size="30px" color="red">${lendResult}</font>
</body>
</html>