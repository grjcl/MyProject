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
			 * 进行session验证，只有登录过的用户，才能
			 * 使用该功能
            */
			HttpSession session = request.getSession();
			Object obj = session.getAttribute("user");
			if(obj==null) {
				response.sendRedirect("list.jsp");
				return;
			}
			
			
			
			
			/*
			 * 使用DAO查询数据库，将所有用户信息查询出来
			 */
			UserDAO dao = new UserDAO();
			try {
				List<User> users = dao.findAll();
				/*
				 * 依据查询到的用户信息，输出表格
				 */
				//step1.绑定数据到request对象上
				request.setAttribute("users", users);
				//step2.获得转发器
				RequestDispatcher rd = 
						request.getRequestDispatcher("/WEB-INF/listUsers.jsp");
				//step3.转发
				rd.forward(request, response);
				
				
				
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				out.println("系统繁忙");
			}
		}else if("/toAdd".equals(action)) {
			request.getRequestDispatcher("/WEB-INF/addUser.jsp")
			.forward(request, response);
			
		}else  if("/add".equals(action)) {
			
			
			
			
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
		}else if("/login".equals(action)) {
			/*
			 * 先比较验证码是否正确
			 * 1.用户提交的验证码
			 * 2.事先绑定到session对象上的验证码
			 */
			String number1= request.getParameter("number");
			HttpSession session = 
					request.getSession();
			String number2 = (String)session.getAttribute("number");
			if(!number1.equals(number2)) {
				//验证码不正确
				request.setAttribute("number_error","验证码错误");
				request.getRequestDispatcher("login.jsp").forward(request, response);
				return;
			}
			
			
			//读取用户名和密码
			String username = request.getParameter("uname");
			String pwd = request.getParameter("pwd");
			System.out.println(username +"  "+pwd);
			//使用DAO查询数据库，看是否有符合条件的记录
			UserDAO dao = new UserDAO();
			try {
				
				User user = dao.find(username);
				if(user!=null&&user.getPwd().equals(pwd)) {
					
					
					session.setAttribute("user", user);

					
					
					//登录成功，跳转页面
					response.sendRedirect("list.do");
					
					
					
				}else {
					//登录失败
					request.setAttribute("login_failed","用户名或密码错误");
					request.getRequestDispatcher("login.jsp")
					.forward(request, response);
					
				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
				out.println("系统繁忙，稍后重试");
				
				
			}
			
		}
		
	}
	
}
