package exam;

import org.apache.commons.dbcp2.BasicDataSource;

import com.mysql.jdbc.Connection;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;
import javax.swing.JOptionPane;

public class DbUtils {
	
	private static BasicDataSource dataSource;
	
	
//	private String dbUrl="jdbc:mysql://localhost:3306/db_student?useUnicode=true&characterEncoding=utf8"; // 数据库连接地址
//	private String dbUserName="root"; // 用户名
//	private String dbPassword="bean"; // 密码
//	private String jdbcName="com.mysql.jdbc.Driver"; // 驱动名称
//	/**
//	 * 获取数据库连接
//	 * @return
//	 * @throws Exception
//	 */
//	public Connection getCon(){
//		try {
//			Class.forName(jdbcName);
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		Connection con = null;
//		try {
//			con = (Connection) DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return con;
//	}
//	/**
//	 * 关闭数据库连接
//	 * @param con
//	 * @throws Exception
//	 */
//	public void closeCon(Connection con)throws Exception{
//		if(con!=null){
//			con.close();
//		}
//	}
//	
//	public static void main(String[] args) {
//		DbUtils dbUtil=new DbUtils();
//		try {
//			dbUtil.getCon();
//			System.out.println("数据库连接成功！");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			System.out.println("数据库连接失败");
//		}
//	}
	
	static {
		
//		Properties p = new Properties();
//		try {
//			p.load(DBUtil.class.getClassLoader()
//				.getResourceAsStream("db.properties"));
//		} catch (IOException e) {
//			e.printStackTrace();
//			throw new RuntimeException("读取配置文件失败",e);

		
		
		
		
	}
	
	
	public static DataSource getDataSource() {
		
		dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/mysql?useUnicode=true&characterEncoding=utf8");
		
		dataSource.setUsername("root");
		dataSource.setPassword("bean");
		
		return dataSource;
	}
	
	public static DataSource getAdminDataSource() {
		
		dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/mysql?useUnicode=true&characterEncoding=utf8");
		
		dataSource.setUsername("admin");
		dataSource.setPassword("bean");
		
		return dataSource;
	}
	
	public static DataSource getTeacherDataSource() {
		
		dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/mysql?useUnicode=true&characterEncoding=utf8");
		
		dataSource.setUsername("teacher");
		dataSource.setPassword("bean");
		
		return dataSource;
	}
	
	public static DataSource getStudentDataSource() {
		
		dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/mysql?useUnicode=true&characterEncoding=utf8");
		
		dataSource.setUsername("student");
		dataSource.setPassword("bean");
		
		return dataSource;
	}
}
