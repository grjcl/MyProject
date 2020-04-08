package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import entity.User;

public class ActionServlet extends HttpServlet{

	

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
		
		if("/toLogin".equals(action)) {
			request.getRequestDispatcher("/WEB-INF/login.jsp")
			.forward(request, response);
		}else if("/list".equals(action)) {
			
			/*
			 * ����session��֤��ֻ�е�¼�����û�������
			 * ʹ�øù���
            */
			HttpSession session = request.getSession();
			Object obj = session.getAttribute("user");
			if(obj==null) {
				response.sendRedirect("list.jsp");
				return;
			}
			
			
			
			
			/*
			 * ʹ��DAO��ѯ���ݿ⣬�������û���Ϣ��ѯ����
			 */
			UserDAO dao = new UserDAO();
			try {
				List<User> users = dao.findAll();
				/*
				 * ���ݲ�ѯ�����û���Ϣ��������
				 */
				//step1.�����ݵ�request������
				request.setAttribute("users", users);
				//step2.���ת����
				RequestDispatcher rd = 
						request.getRequestDispatcher("/WEB-INF/listUsers.jsp");
				//step3.ת��
				rd.forward(request, response);
				
				
				
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				out.println("ϵͳ��æ");
			}
		}else if("/toAdd".equals(action)) {
			request.getRequestDispatcher("/WEB-INF/addUser.jsp")
			.forward(request, response);
			
		}else  if("/add".equals(action)) {
			
			
			
			
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
		}else if("/login".equals(action)) {
			/*
			 * �ȱȽ���֤���Ƿ���ȷ
			 * 1.�û��ύ����֤��
			 * 2.���Ȱ󶨵�session�����ϵ���֤��
			 */
			String number1= request.getParameter("number");
			HttpSession session = 
					request.getSession();
			String number2 = (String)session.getAttribute("number");
			if(!number1.equals(number2)) {
				//��֤�벻��ȷ
				request.setAttribute("number_error","��֤�����");
				request.getRequestDispatcher("login.jsp").forward(request, response);
				return;
			}
			
			
			//��ȡ�û���������
			String username = request.getParameter("uname");
			String pwd = request.getParameter("pwd");
			System.out.println(username +"  "+pwd);
			//ʹ��DAO��ѯ���ݿ⣬���Ƿ��з��������ļ�¼
			UserDAO dao = new UserDAO();
			try {
				
				User user = dao.find(username);
				if(user!=null&&user.getPwd().equals(pwd)) {
					
					
					session.setAttribute("user", user);

					
					
					//��¼�ɹ�����תҳ��
					response.sendRedirect("list.do");
					
					
					
				}else {
					//��¼ʧ��
					request.setAttribute("login_failed","�û������������");
					request.getRequestDispatcher("login.jsp")
					.forward(request, response);
					
				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
				out.println("ϵͳ��æ���Ժ�����");
				
				
			}
			
		}
		
	}
	
}
