<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="DAO.factory.*,vo.JavaBean.*,java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>还书操作</title>
</head>
<body>
<%
List<LendBean> list = (List<LendBean>)request.getAttribute("returnList");
 %>

 <table border="1">
<tr>
<td>账号</td> <td>书号</td><td>借出时间</td><td>归还时间</td><td>是否归还</td><td>缴费金额</td><td>归还状态</td>
 </tr>
 <%
 for (int i = 0; i < list.size(); i++){%>
  <tr align="center">
                       <td><%=list.get(i).getL_account() %></td>
                       <td><%=list.get(i).getL_id() %></td>
                       <td><%=list.get(i).getL_time() %></td>
                       <%
                       if(list.get(i).getR_time()==null)
                       {
                    	   out.print("<td>"+"未还</td>");
                       }
                       else
                       {
                    	   out.print("<td>"+list.get(i).getR_time()+"</td>");
                       } 
                       %>
                       <td><%=list.get(i).getIs_retrun() %></td>
                       <%if(list.get(i).getIs_retrun().equals("是"))
                       {
                    	   out.print("<td>"+list.get(i).getToll()+"</td>");
                       }
                       else
                       {
                    	   out.print("<td>"+"此书未还，金额请点击现在还书查看"+"</td>");
                       }                    	   
                    	%>
                    	<%
                    	if(list.get(i).getIs_retrun().equals("否"))
                        {
                     	   out.print("<td><a href=\""+"userControlServlet?flag=returnBook&book_id="+list.get(i).getL_id()+"\""+">归还此书</a></td>");
                        }
                        else
                        {
                     	   out.print("<td>"+"此书无需归还"+"</td>");
                        } 
                    	%>
  </tr>
  <%} %>
</table>


<font size="30px" color="red">
${returnResult}<br>
${returnToll}
</font>

</body>
</html>