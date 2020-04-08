<%@ page contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body style="font-size:30px">
    用户中心页
    <p>你登录的ID：${uid}</p>
    
    <p><a href="${pageContext.request.contextPath}/user/logout.do ">退出登录</a>
    <p><a href="${pageContext.request.contextPath}/index.do">index</a>
    <p><a href="${pageContext.request.contextPath}/login.do">login</a>
    <p><a href="user/userCenter.do">userCenter</a>
    
    
    
    
    
    
    
    



</body>
</html>