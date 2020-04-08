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
		
		
		//读取用户信息
		String username = request.getParameter("uname");
		System.out.println("uname:"+username);
		String pwd = request.getParameter("pwd");
		System.out.println("upwd:"+pwd);
		String phone = request.getParameter("phone");
		System.out.println("uphone:"+phone);
		
		
		/*
		 * 服务器端应该对用户提交的数据进行合法性检查
		 * 比如，检查用户名是否为空等
		 */
		
		/*
		 * 使用DAO将用户信息插入到数据库
		 */
		UserDAO dao = new UserDAO();
		User user = new User();
		user.setUsername(username);
		user.setPwd(pwd);
		user.setPhone(phone);
		try {
			dao.save(user);
			out.println("添加成功");
			response.sendRedirect("list");
		} catch (Exception e) {
			e.printStackTrace();
			/*
			 * 看异常能否恢复，如果不能够恢复
			 * （比如，由于数据库服务停止产生的异常等等，
			 *     这样的异常称之为系统异常），则提示用户
			 *     稍后重试
			 */
			out.print("系统繁忙，稍后再试");
		}
		
	}
		
}
