package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import entity.User;
import util.DBUtils;

public class AddUserServlet extends HttpServlet{

	public void service (HttpServletRequest request, 
			HttpServletResponse response)
	     throws ServletException,IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		
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
			out.println("��ӳɹ�");
			response.sendRedirect("list");
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
		
	}
		
}
