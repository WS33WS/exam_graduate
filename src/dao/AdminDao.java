package dao;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;


import entity.Admin;
import exam.DbUtils;


public class AdminDao {
	
	private static QueryRunner runner = new QueryRunner(DbUtils.getDataSource());//导入数据库数据
	
	public static  Admin findByName(String name) {
		System.out.println("--导入数据库数据---");
		try {
			String sql="select id id,name name,tele tele,sex sex,address address,pass pass,school school\r\n" + 
					"from admin \r\n" + 
					"where name=?";
			System.out.println("--导入开始查询---");
			Admin admin=runner.query(sql, new BeanHandler<>(Admin.class),name);
			System.out.println("--查询出数据---");
//			String a=admin.getName();
//			System.out.println(a);
			return admin;
		}
		catch (SQLException e) {
			// TODO: handle exception
			System.out.println("为成功链接数据库");
//			JOptionPane.showMessageDialog(null, "未成功连接数据库");
			e.printStackTrace();
		}
		return null;
	}
	
	public static  Admin findById(int id) {
		System.out.println("--导入数据库数据---");
		try {
//			String sql="select id id\r\n" + 
//					"from admin \r\n" + 
//					"where id=?";
			
			String sql="select id id,name name,tele tele,sex sex,address address,pass pass,school school\r\n" + 
					"from admin \r\n" + 
					"where id=?";
			System.out.println("--导入开始查询---");
			Admin admin=runner.query(sql, new BeanHandler<>(Admin.class),id);
			System.out.println("--查询出数据---");
			if(admin!=null){
				String a=admin.getName();
				int b=admin.getId();
				System.out.println(a);
				System.out.println(b);
				return admin;
			}else{
				return null;
			}

		}
		catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;

	}
	
	public static int editPass(String pass,int id){
		String sql = "UPDATE `mysql`.`admin` SET `pass` =? WHERE `id` = ?";
        Object[] params={pass,id};
        try {
            runner.update(sql,params);
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
	}

}
