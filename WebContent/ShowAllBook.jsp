<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="vo.JavaBean.*,java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 <div id="one">
书号：<input type="text" name="bookid">
书名：<input type="text" name="bookname">
类型：

 
 
 </div>
 
 
 <div id="two">
   			<%List<BookBean> ls = (List<BookBean>)request.getAttribute("book"); %>   			   			
   			 <table border="2" width="650">
                 <tr align="center">
                    <td>书号</td> <td>书名</td>
                   <td>出版社</td><td>类型</td> <td>总库存量</td><td>剩余库存</td>
                 </tr>
                <%   for(int i=0;i<ls.size();i++) 
					{%>
                     <tr align="center">
                       <td><%= ls.get(i).getBook_id()%></td>
                       <td><%= ls.get(i).getBook_name() %></td>
                       <td><%= ls.get(i).getBook_from() %></td>
                       <td><%= ls.get(i).getBook_class() %></td>
                       <td><%= ls.get(i).getBook_all() %></td>
                       <td><%= ls.get(i).getBook_last() %></td>
                    </tr>
                <% }%>
            </table>   			   			    		    		    		
    </div>
</body>
</html>