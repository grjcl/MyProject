package util;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;





public class DBUtils {

	//1.获得配置参数
	static String driverClassName;
	static String url;
	static String userName;
	static String password;
	static int initialSize;
	static int maxActive;
	static BasicDataSource bs = null;
	static {
		
		Properties cfg = new Properties();
		InputStream inStream = DBUtils.class.getClassLoader()
				.getResourceAsStream("db.properties");
		try {
			cfg.load(inStream);
			driverClassName = cfg.getProperty("jdbc.className");
		    url = cfg.getProperty("jdbc.url");
		    userName = cfg.getProperty("jdbc.user");
		    password = cfg.getProperty("jdbc.password");
		    initialSize = Integer.parseInt(cfg.getProperty("InitialSize"));
		    maxActive = Integer.parseInt(cfg.getProperty("MaxActive"));
		    bs = new BasicDataSource();
		    bs.setDriverClassName(driverClassName);
		    bs.setUrl(url);
		    bs.setUsername(userName);
		    bs.setPassword(password);
		    bs.setInitialSize(initialSize);
		    bs.setMaxActive(maxActive);
		    
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	//2.获得连接对象
	public static Connection getConnection() throws Exception {
		Connection conn = bs.getConnection();
		return conn;
	}
	//3.归还连接对象
	public static void closeConnection (Connection conn) {
		if(conn!=null) {
			try {
				conn.setAutoCommit(true);
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	//4.rollback方法
	public static void rollBack(Connection conn) {
		if(conn!=null) {
			try {
				conn.rollback();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
}
