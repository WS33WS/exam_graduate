package dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import entity.Classes;
import entity.Student;
import exam.DbUtils;
import dao.ClassesDao;



public class StudentDao {
	private static QueryRunner runner = new QueryRunner(DbUtils.getDataSource());//导入数据库数据
	public static Student findById( int b) {
		try {
			String sql = "select id id,name name,tele tele,sex sex,address address,pass pass,classes classes,exam_state exam_state\r\n" + 
					"from student\r\n" + 
					"where id=?";
			Student stu=runner.query(sql, new BeanHandler<>(Student.class),b);
			if(stu!=null){
				StuClasses(stu);
				return stu;
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
	
	public static void StuClasses(Student stu) {
		String[] clas=null;
		if(stu.getClasses()!=null) {
			clas = stu.getClasses().split(",");
			List<Integer> classList = new ArrayList<Integer>();
			for (int n = 0; n < clas.length; n++) {
				int a=Integer.parseInt(clas[n]);
				classList.add(a);
			}
			stu.setAllClasses(classList);
		}
	}
	
	
	public static Student findByName(String name) {
		try {
			String sql = "select id id,name name,tele tele,sex sex,address address,pass pass,classes classes,exam_state exam_state\r\n" + 
					"from student\r\n" + 
					"where name=?";
			
			return runner.query(sql, new BeanHandler<>(Student.class),name);
		}
		catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	

	
	public static int editPass(String pass,int id){
		String sql = "UPDATE `mysql`.`student` SET `pass` =? WHERE `id` = ?";
        Object[] params={pass,id};
        try {
            runner.update(sql,params);
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
	}
	
	///添加学生信息
	public int addNewStudent(String name,String tele,int sex, String address,String pass,String classes){
		String sql = "INSERT INTO `mysql`.`student`(`name`, `tele`, `sex`, `address`, `pass`, `classes`) "
				+ "VALUES (?,?,?,?,?,?)";
        Object[] params={name,tele,sex,address,pass,classes};
        try {
            runner.update(sql,params);
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
	}
	
	public static boolean update(int id,String updatePass) {
		// TODO Auto-generated method stub
		String sql = "UPDATE `mysql`.`student` SET `pass` = ? WHERE `id` = ?";
		Object[] params={updatePass,id};
		try {
            runner.update(sql,params);
            System.out.println("更新成功");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return false;
	}
	
	public static boolean deleteStudent(int id){
		String sql = "DELETE FROM `mysql`.`student` WHERE `id` = ?";
        try {
            runner.update(sql,id);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
	}
	
	//输入学生的id来更新学生考试状态
		public static int updateState(int id){
			String sql = "UPDATE `mysql`.`student` SET `exam_state` = 1 WHERE `id` = ?";
	        Object[] params={id};
	        try {
	            runner.update(sql,params);
	            return 1;
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return -1;
		}

}
