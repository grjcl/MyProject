<%@page import="entity.User"%>
<%@page import="dao.UserDAO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.*"
      contentType="text/html;charset=utf-8"
      pageEncoding="utf-8"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>listUsers</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="css/style.css" />
	</head>
	<body>
		<div id="wrap">
			<div id="top_content"> 
				<div id="header">
					<div id="rightheader">
						<p>
							<%
							   Date date = new Date();
							   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
							   out.println(sdf.format(date));
							%>

							<br />
						</p>
					</div>
					<div id="topheader">
						<h1 id="title">
							<a href="#">main</a>
						</h1>
					</div>
					<div id="navigation">
					</div>
				</div>
				<div id="content">
					<p id="whereami">
					</p>
					<h1>
						Welcome!
					</h1>
					<table class="table">
						<tr class="table_header">
							<td>
								ID
							</td>
							<td>
								用户名
							</td>
							<td>
								密码
							</td>
							<td>
								电话
							</td>
							<td>
								操作
							</td>
						</tr>
						<%
						   UserDAO dao = new UserDAO();
						   List<User> users =dao.findAll();
						   for(int  i=0;i<users.size();i++){
							   User u = users.get(i);
						   
						%>
						<tr class="row<%=i%2+1%>">
							<td>
								<%=u.getId()
								%>
							</td>
							<td>
								<%=u.getUsername() 
								%>
							</td>
							<td>
								<%=u.getPwd() %>
							</td>
							<td>
								<%=u.getPhone() %>
							</td>
							<td>
								<a href="del.do?id=<%=u.getId()%>">delete</a>&nbsp;
							</td>
							
						</tr>
						<%} %>
						
					</table>
					<p>
						<input type="button" class="button" value="Add User" onclick="location='addUser.html'"/>
					</p>
				</div>
			</div>
			<div id="footer">
				<div id="footer_bg">
				ABC@126.com
				</div>
			</div>
		</div>
	</body>
</html>
