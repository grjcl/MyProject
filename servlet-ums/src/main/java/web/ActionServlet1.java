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
			 * 使用DAO查询数据库，将所有用户信息查询出来
			 */
			UserDAO dao = new UserDAO();
			try {
				List<User> users = dao.findAll();
				/*
				 * 依据查询到的用户信息，输出表格
				 */
				out.println("<table width='60%' border='1' "
						+"cellpadding='0' cellspacing='0'>");
				out.println("<tr><td>ID</td><td>用户名</td>"
						+ "<td>密码</td><td>电话</td><td></td></tr>");
				
				System.out.println(users);
				for(int i=0;i<users.size();i++) {
					User u = users.get(i);
					out.println("<tr><td>" + u.getId() 
								+ "</td><td>" + u.getUsername() 
								+ "</td><td>" + u.getPwd() 
								+ "</td><td>" + u.getPhone() 
								+ "</td><td><a href='del?id=" 
								+ u.getId() 
								+ "'>删除</a></td></tr>");
				}
				out.println("</table>");
				
				out.println("<a href='/servlet-ums/addUser.html'>"+
				               "添加用户</a>");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				out.println("系统繁忙");
			}
		}else if("/add".equals(action)) {
			
			
			
			
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
				
				response.sendRedirect("list.do");
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
		}else if("/del".equals(action)) {
			//读取要删除的员工的id
			String id = request.getParameter("id");
			
			//调用DAO提供的方法，删除数据库中的对应的员工记录
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
	
}
