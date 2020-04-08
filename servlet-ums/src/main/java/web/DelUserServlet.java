package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;

public class DelUserServlet extends HttpServlet{

	public void servlet(HttpServletRequest request,
			HttpServletResponse response) 
	throws ServletException,IOException{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		//��ȡҪɾ����Ա����id
		String id = request.getParameter("id");
		//
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
