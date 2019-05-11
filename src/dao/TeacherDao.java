package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import entity.Admin;
import entity.Institution;
import entity.Teacher;
import exam.DbUtils;


//reference CustomerDao
public class TeacherDao {
	private static QueryRunner runner = new QueryRunner(DbUtils.getDataSource());//connect
	
	public static  Teacher findByName(String name) {
		System.out.println("--导入数据库数据---");
		try {
			String sql="select id id,name name,tele tele,sex sex,address address,pass pass,school school,institution institution\r\n" + 
					"from teacher \r\n" + 
					"where name=?";
			System.out.println("--导入开始查询---");
			Teacher teacher=runner.query(sql, new BeanHandler<>(Teacher.class),name);
			System.out.println("--查询出数据---");
//			String a=admin.getName();
//			System.out.println(a);
			if(teacher!=null){
				return teacher;
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
	
	public static  Teacher findById(int id) {
		System.out.println("--导入数据库数据---");
		try {
			String sql="select id id,name name,tele tele,sex sex,address address,pass pass,school school,institution institution\r\n" + 
					"from teacher \r\n" + 
					"where id=?";
			System.out.println("--导入开始查询---");
			Teacher teacher=runner.query(sql, new BeanHandler<>(Teacher.class),id);
			System.out.println("--查询出数据---");
//			String a=teacher.getName();
//			System.out.println(a);
			if(teacher!=null){
				return teacher;
			}else{
				return null;
			}
		}
		catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
//		,name name,tele tele,sex sex,address address,pass pass,school school
	}
	
	
	public static List<Teacher> findAll(String school,String institution) {
		try {
			String sql="select id id,name name,tele tele,sex sex,address address,pass pass,classes classes,school school,institution institution\r\n" + 
					"from teacher\r\n" + 
					"where school=? and institution=?";
			return runner.query(sql, new BeanListHandler<>(Teacher.class),school,institution);
		}
		catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	//根据学校得到学院列表和老师个数
	public static List<Institution> findAllInstitution(String school) {
		try {
			String sql="select institution institution,COUNT(id) count\r\n" + 
					"from teacher\r\n" + 
					"where school=?\r\n" + 
					"group BY institution;";
			return runner.query(sql, new BeanListHandler<>(Institution.class),school);
		}
		catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	
	

	public static int addNewTeacher(String name,String tele,int sex, String address,String pass, String classes,String institution,String school){
		String sql = "INSERT INTO `mysql`.`teacher`\r\n" + 
				"(`name`, `tele`, `sex`, `address`, `pass`, `classes`, `institution`, `school`) \r\n" + 
				"VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] params={name,tele,sex,address,pass,classes,institution,school};
        try {
            runner.update(sql,params);
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
	}

	public static boolean deleteTeacher(int id){
		String sql = "DELETE FROM `mysql`.`teacher` WHERE `id` = ?";
        try {
            runner.update(sql,id);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
	}

	public static int editPass(String pass,int id){
		String sql = "UPDATE `mysql`.`teacher` SET `pass` =? WHERE `id` = ?";
        Object[] params={pass,id};
        try {
            runner.update(sql,params);
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
	}

	public static List<Teacher> findBySomething(String school,String institution,String something) {
		if(institution==null) {
			try {
				String sql="select  id,name,tele,sex,address,pass,classes\r\n" + 
						"from teacher\r\n" + 
						"where id=? or name =? and school=?";
				return runner.query(sql, new BeanListHandler<>(Teacher.class),something,something,school);
			}
			catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return null;
			
		}
		else {
			try {
				String sql="select  id,name,tele,sex,address,pass,classes\r\n" + 
						"from teacher\r\n" + 
						"where id=? or name =? \r\n" + 
						"and school=? and institution=?";
				return runner.query(sql, new BeanListHandler<>(Teacher.class),something,something,school,institution);
			}
			catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return null;
			
		}
		
	}
	
	public static int resetTeacher(String updateName,String updateCon,int id) {
		String sql = "UPDATE `mysql`.`teacher` SET `?` = '?' WHERE `id` = ?";
		Object[] params={updateName,updateCon,id};
		
		try {
            runner.update(sql,params);
            System.out.println("更新成功");
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return -1;
	}

	public static boolean update(int id,String updateName,String updateTele,String updateInstitution,String updatePass) {
		// TODO Auto-generated method stub
		String sql = "UPDATE `mysql`.`teacher` SET `name` = ?,`tele` = ?,`institution` = ?,`pass` = ? WHERE `id` = ?";
		Object[] params={updateName,updateTele,updateInstitution,updatePass,id};
		try {
            runner.update(sql,params);
            System.out.println("更新成功");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return false;
	}
	
//	public static List<Teacher> getTeacherList(String names,String institutions) {
//		String name='%'+names+'%';
//		String institution='%'+institutions+'%';
//		try {
//			String sql="select id id,name name,tele tele,sex sex,address address,pass pass,classes classes,school school,institution institution \r\n" + 
//					"from teacher \r\n" + 
//					"where teacher.`name` like ? or institution like ?";
//			return runner.query(sql, new BeanListHandler<>(Teacher.class),name,institution);
//		}
//		catch (SQLException e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return null;
//	}
	
	public static List<Teacher> findNameIn(String names,String institutions) {
		String name='%'+names+'%';
		String institution='%'+institutions+'%';
		try {
			String sql="select id id,name name,tele tele,sex sex,address address,pass pass,classes classes,school school,institution institution \r\n" + 
					"from teacher \r\n" + 
					"where teacher.`name` like ? and institution like ?";
			return runner.query(sql, new BeanListHandler<>(Teacher.class),name,institution);
		}
		catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	public static List<Teacher> findName(String names) {
		String name='%'+names+'%';
		try {
			String sql="select id id,name name,tele tele,sex sex,address address,pass pass,classes classes,school school,institution institution \r\n" + 
					"from teacher \r\n" + 
					"where teacher.`name` like ? ";
			return runner.query(sql, new BeanListHandler<>(Teacher.class),name);
		}
		catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	public static List<Teacher> findIn(String institutions) {
		String institution='%'+institutions+'%';
		try {
			String sql="select id id,name name,tele tele,sex sex,address address,pass pass,classes classes,school school,institution institution \r\n" + 
					"from teacher \r\n" + 
					"where institution like ?";
			return runner.query(sql, new BeanListHandler<>(Teacher.class),institution);
		}
		catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
}
