<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="vo.JavaBean.*,java.util.*,DAO.factory.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 <form action="adminControlBookServlet?flag=findbook" method="post">
书号：<input type="text" name="book_id">
书名：<input type="text" name="book_name">
出版社：<input type="text" name="book_from">
分类：<select name="book_class">
<%
	  List<String> list =bookFactory.getInterfaceAdminDao().findClass();
	%><option balue=""></option><%
	 for(int i=0;i<list.size();i++)
	 {%>
		<option value="<%=list.get(i)%>"><%=list.get(i) %></option>
	 <%}
	 %>
<br>
<input type="submit" value="查找">&nbsp;<input type="reset" value="重置">

</form>

 
 

 
 
 <div id="one">
   			<%List<BookBean> ls = (List<BookBean>)request.getAttribute("book"); %>   			   			
   			 <table border="2" width="700">
                 <tr align="center">
                    <td>书号</td> <td>书名</td>
                   <td>出版社</td><td>类型</td> <td>总库存量</td><td>剩余库存</td><td>操作</td>
                 </tr>
                <%   for(int i=0;i<ls.size();i++) 
					{
					String id=ls.get(i).getBook_id();
					%>
                     <tr align="center">
                       <td><%= ls.get(i).getBook_id()%></td>
                       <td><%= ls.get(i).getBook_name() %></td>
                       <td><%= ls.get(i).getBook_from() %></td>
                       <td><%= ls.get(i).getBook_class() %></td>
                       <td><%= ls.get(i).getBook_all() %></td>
                       <td><%= ls.get(i).getBook_last() %></td>
                       <td><a href="adminControlBookServlet?flag=delbook&id=<%=id %>">删除</a>
                       	   <a href="adminControlBookServlet?flag=finbook&id=<%=id %>">修改</a>
                       </td>
                    </tr>

                <% }%>
            </table>   			   			    		    		    		
    </div>
</body>
</html>