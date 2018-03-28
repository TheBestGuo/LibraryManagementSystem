<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="DAO.factory.*,java.util.*,vo.JavaBean.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>精确查询结果</title>
</head>
<body>


<%
  //String sex=new String(request.getParameter("sex").getBytes("ISO-8859-1"),"utf-8");
  request.setCharacterEncoding("utf-8");
  List<BookBean> list =(List<BookBean>)request.getAttribute("result");
  request.setAttribute("oldResult", list);
	%>
<table border="1">
<tr>
<td>书号</td> <td>书名</td><td>出版社</td><td>分类</td><td>总量</td><td>余量</td><td>借阅</td>
 </tr>
 <%
 for (int i = 0; i < list.size(); i++){%>
  <tr align="center">
                       <td><%= list.get(i).getBook_id() %></td>
                       <td><%= list.get(i).getBook_name() %></td>
                       <td><%= list.get(i).getBook_from() %></td>
                       <td><%= list.get(i).getBook_all() %></td>
                       <td><%= list.get(i).getBook_last() %></td>
                       <td><%= list.get(i).getBook_class() %></td>
                       <td><a href="userControlServlet?flag=lend&book_id=<%=list.get(i).getBook_id() %>">借阅此书</a></td>
  </tr>
  <%} %>
</table>

</br></br>

</br></br>
<!-- <form action="" method="post">
	请输入您要借阅的书本信息：
	书号：<input type="text" name="book_id">（必填）
	书名：<input type="text" name="book_name">
	<input type="submit" value="借阅">&nbsp;<input type="reset" value="重置">
</form> -->


</body>
</html>