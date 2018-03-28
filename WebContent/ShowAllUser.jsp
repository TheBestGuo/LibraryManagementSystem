<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="vo.JavaBean.*,java.util.*,DAO.factory.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="adminControlAccountServlet?flag=finduser" method="post">
账号：<input type="text" name="user_account">
电话：<input type="text" name="user_phone">
账户类型<select name="user_job">
	<option balue=""></option>
	<option balue="管理员">管理员</option>
	<option balue="普通用户">普通用户</option>
	</select>
<br>
<input type="submit" value="查找">&nbsp;<input type="reset" value="重置">

</form>


 <div id="one">
   			<%List<UserBean> ls = (List<UserBean>)request.getAttribute("user"); %>   			   			
   			 <table border="2" width="700">
                 <tr align="center">
                    <td>账号</td> <td>账户类型</td>
                   <td>电话</td><td>操作</td>
                 </tr>
                <%   for(int i=0;i<ls.size();i++) 
					{
					String id=ls.get(i).getAccount();
					%>
                     <tr align="center">
                       <td><%= ls.get(i).getAccount()%></td>
                       <td><%= ls.get(i).getJob() %></td>
                       <td><%= ls.get(i).getPhone() %></td>
                       <td><a href="adminControlAccountServlet?flag=deluser&id=<%=id %>">删除</a>
                       	   <a href="adminControlAccountServlet?flag=finuser&id=<%=id %>">修改</a>
                       </td>
                    </tr>

                <% }%>
            </table>   			   			    		    		    		
    </div>
</body>
</html>