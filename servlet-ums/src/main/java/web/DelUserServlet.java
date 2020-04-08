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
		//读取要删除的员工的id
		String id = request.getParameter("id");
		//
		UserDAO dao = new UserDAO();
		try {
			dao.delete(Integer.parseInt(id));
			//重定向到用户列表
			response.sendRedirect("list.do");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			out.println("系统繁忙");
		}
	}
}
