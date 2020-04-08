package test;



import java.util.List;



import dao.UserDAO;
import entity.User;

public class Asd {

	public static void test1(){
		UserDAO dao = new UserDAO();
		List<User> users = dao.findAll();
		System.out.println(users);
	}

	public static void test2() {
		User user = new User();
		UserDAO dao = new UserDAO();
		user.setUsername("TOM");
		user.setPhone("123");
		user.setPwd("test");
		dao.save(user);
	}
	public static void test3() {
		UserDAO dao = new UserDAO();
		dao.delete(1);
	}
	
	public static void test4() {
		UserDAO dao = new UserDAO();
		User user = dao.find("уе");
		System.out.println(user);
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		test1();
//		test2();
//		test3();
		test4();
	}

}
