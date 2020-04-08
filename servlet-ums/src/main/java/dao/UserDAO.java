package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;

import entity.User;
import util.DBUtils;

/**
 * DAO�ࣺ
 *     ���ڷ�װ���ݷ����߼�
 * @author ASUS
 *
 */
public class UserDAO {

	public User find(String username) {
		User user = null;
		Connection conn = null;
		try {
			conn = DBUtils.getConnection();
			String sql = "select * from t_user "+
			            "where username = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(username);
				user.setPhone(rs.getString("phone"));
				user.setPwd(rs.getString("pwd"));
				
				
				
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			DBUtils.closeConnection(conn);
		}
		return user;
	}
	
	public void delete(int id) {
		Connection conn = null;
		try {
			conn = DBUtils.getConnection();
			String sql = "delete from t_user where id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			DBUtils.closeConnection(conn);
		}
	}
	
	public void save(User user) {
		Connection conn = null;
		try {
			conn = DBUtils.getConnection();
			String sql = "insert into t_user"+"(username,pwd,phone)"
	                +"values(?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPwd());
			ps.setString(3, user.getPhone());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			DBUtils.closeConnection(conn);
		}
	}
	
	
	public List<User> findAll(){
		List<User> users = new ArrayList<User>();
		
		Connection conn = null;
		try {
			conn = DBUtils.getConnection();
		    String sql = "select * from t_user";
		   PreparedStatement ps = conn.prepareStatement(sql);
		    ResultSet rs = ps.executeQuery();
		    while(rs.next()) {
		    	int id = rs.getInt("id");
		    	String username = rs.getString("username");
		    	String pwd = rs.getString("pwd");
		    	String phone = rs.getString("phone");
		    	
		    	
		    	
		    	User user = new User();
		    	user.setId(id);
		    	user.setUsername(username);
		    	user.setPwd(pwd);
		    	user.setPhone(phone);
		    	
//		    	user.getId();
//		    	user.getUsername();
//		    	user.getPwd();
//		    	user.getPhone();
		    	
		    	users.add(user);
		    	
		    	
		    	
		    }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			DBUtils.closeConnection(conn);
		}
		
		
		return users;
		
	}

	
}
