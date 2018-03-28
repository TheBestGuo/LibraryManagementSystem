<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="DAO.factory.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
重复：<%=bookFactory.getInterfaceUseDao().judgeUserDistinctBook("10001", "1") %><br><br>
超时：<%=bookFactory.getInterfaceUseDao().judgeUserLendBookTimeOut("10001") %><br><br>
总数：<%=bookFactory.getInterfaceUseDao().judgeUserLendBookCount("10001") %><br>
余量：<%=bookFactory.getInterfaceUseDao().BookLastCount("5") %>
hello
</body>
</html>