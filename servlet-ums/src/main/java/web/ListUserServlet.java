package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import entity.User;

public class ListUserServlet extends 
          HttpServlet{

	public void service(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.setContentType("text/html);charset=utf-8");
		PrintWriter out = response.getWriter();
		/*
		 * ʹ��DAO��ѯ���ݿ⣬�������û���Ϣ��ѯ����
		 */
		UserDAO dao = new UserDAO();
		try {
			List<User> users = dao.findAll();
			/*
			 * ���ݲ�ѯ�����û���Ϣ��������
			 */
			out.println("<table width='60%' border='1' "
					+"cellpadding='0' cellspacing='0'>");
			out.println("<tr><td>ID</td><td>�û���</td>"
					+ "<td>����</td><td>�绰</td><td></td></tr>");
			
			
			for(int i=0;i<users.size();i++) {
				User u = new User();
				out.println("<tr><td>" + u.getId() 
				+ "</td><td>" + u.getUsername() 
				+ "</td><td>" + u.getPwd() 
				+ "</td><td>" + u.getPhone() 
				+ "</td><td><a href='del?id=" + u.getId() + "'>ɾ��</a></td></tr>");
			}
			out.println("</table>");
			
			out.println("<a href='/addUser.html'>"+
			               "����û�</a>");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			out.println("ϵͳ��æ");
		}
		
		
		
		
	}
	
}
