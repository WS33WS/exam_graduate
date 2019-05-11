package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;


import entity.Classes;
import entity.Student;
import exam.DbUtils;



public class ClassesDao {
	private static QueryRunner runner = new QueryRunner(DbUtils.getDataSource());//导入数据库数据
	//根据老师ID获取开课ClassesLIst
	public static List<Classes> findByTeacherId( int i) {
		//全部的就是已开课程
		try {
			String sql = "SELECT classesId classesId,classesName classesName,studentId allStudent,situation situation\r\n" + 
					"from classes\r\n" + 
					"where teacherId=?;";
			
			List<Classes> classes= runner.query(sql, new BeanListHandler<>(Classes.class),i);
			for(int m=0;m<classes.size();m++) {
				ClassesStu(classes.get(m));
			}
			return classes;
			
		}
		catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	//查询某个课程的学生id
	//根据课程ID获取开课Classes
	public static Classes findByClasses( int i) {
//		System.out.println("--导入数据库数据---");
		try {
			String sql="select classesId classesId,classesName classesName,studentId allStudent,situation situation,teacherId teacherId,teachername teachername\r\n" + 
					"from classes\r\n" + 
					"where classesId=?;\r\n";
//			System.out.println("--导入开始查询---");
			Classes classes=runner.query(sql, new BeanHandler<>(Classes.class),i);
//			System.out.println("--查询出数据---");
			ClassesStu(classes);
			System.out.println(classes);
			return classes;
		}
		catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	public static void ClassesStu(Classes classes) {
		String[] student=null;
		if(classes.getAllStudent()!=null) {
			student = classes.getAllStudent().toString().split(",");
			List<Student> studentList = new ArrayList<Student>();
			Student sinStudent;
			for (int n = 0; n < student.length; n++) {
				sinStudent = new Student();
				sinStudent = StudentDao.findById(Integer.parseInt(student[n]));
				studentList.add(sinStudent);
			}
			classes.setStudent(studentList);
		}
	}
	
	public static Classes findByTeacherIdClassesName( int i,String classesName) {
		//选中查询
		try {
			String sql = "SELECT classesId classesId,classesName classesName,studentId allStudent,situation situation\r\n" + 
					"from classes\r\n" + 
					"where teacherId=? and classesName=?;";
			
			Classes classes= runner.query(sql, new BeanHandler<>(Classes.class),i,classesName);
			ClassesStu(classes);
			return classes;
			
		}
		catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	public static Classes findByClassesName( int i,String classesName) {
		//选中查询
		try {
			String sql = "SELECT classesId classesId,classesName classesName,studentId allStudent,situation situation\r\n" + 
					"from classes\r\n" + 
					"where teacherId=? and classesName=?;";
			
			Classes classes= runner.query(sql, new BeanHandler<>(Classes.class),i,classesName);
			ClassesStu(classes);
			return classes;
			
		}
		catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
}
