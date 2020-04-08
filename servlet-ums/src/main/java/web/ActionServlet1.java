package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import entity.User;

public class ActionServlet1 extends HttpServlet{

	public void service(HttpServletRequest request,
			     HttpServletResponse response) 
	     throws ServletException,IOException{
		System.out.println("actionServlet's service()");
		String uri = request.getRequestURI();
		String action = uri.substring(uri.lastIndexOf("/"),
				    uri.lastIndexOf("."));
		System.out.println("action:"+action);
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		if("/list".equals(action)) {
			
			
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
				
				System.out.println(users);
				for(int i=0;i<users.size();i++) {
					User u = users.get(i);
					out.println("<tr><td>" + u.getId() 
								+ "</td><td>" + u.getUsername() 
								+ "</td><td>" + u.getPwd() 
								+ "</td><td>" + u.getPhone() 
								+ "</td><td><a href='del?id=" 
								+ u.getId() 
								+ "'>ɾ��</a></td></tr>");
				}
				out.println("</table>");
				
				out.println("<a href='/servlet-ums/addUser.html'>"+
				               "����û�</a>");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				out.println("ϵͳ��æ");
			}
		}else if("/add".equals(action)) {
			
			
			
			
			//��ȡ�û���Ϣ
			String username = request.getParameter("uname");
			System.out.println("uname:"+username);
			String pwd = request.getParameter("pwd");
			System.out.println("upwd:"+pwd);
			String phone = request.getParameter("phone");
			System.out.println("uphone:"+phone);
			
			
			/*
			 * ��������Ӧ�ö��û��ύ�����ݽ��кϷ��Լ��
			 * ���磬����û����Ƿ�Ϊ�յ�
			 */
			
			/*
			 * ʹ��DAO���û���Ϣ���뵽���ݿ�
			 */
			UserDAO dao = new UserDAO();
			User user = new User();
			user.setUsername(username);
			user.setPwd(pwd);
			user.setPhone(phone);
			try {
				dao.save(user);
				
				response.sendRedirect("list.do");
			} catch (Exception e) {
				e.printStackTrace();
				/*
				 * ���쳣�ܷ�ָ���������ܹ��ָ�
				 * �����磬�������ݿ����ֹͣ�������쳣�ȵȣ�
				 *     �������쳣��֮Ϊϵͳ�쳣��������ʾ�û�
				 *     �Ժ�����
				 */
				out.print("ϵͳ��æ���Ժ�����");
			}
		}else if("/del".equals(action)) {
			//��ȡҪɾ����Ա����id
			String id = request.getParameter("id");
			
			//����DAO�ṩ�ķ�����ɾ�����ݿ��еĶ�Ӧ��Ա����¼
			UserDAO dao = new UserDAO();
			try {
				dao.delete(Integer.parseInt(id));
				//�ض����û��б�
				response.sendRedirect("list.do");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				out.println("ϵͳ��æ");
			}
		}
		
		
		
		
		
		
	}
	
}
