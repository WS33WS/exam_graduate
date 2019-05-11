package dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import entity.ExamSch;
import exam.DbUtils;

public class ExamSchDao {
	private static QueryRunner runner = new QueryRunner(DbUtils.getDataSource());//导入数据库数据
	//
	public static List<ExamSch>findByClassesName( String classesName) {
		try {
			String sql = "SELECT exam_id examId,classes classes,exam_name examName,Time_beg timeBeg,Time_end timeEnd,\"\r\n" + 
					"					+ \"selects selects,systemF systemF，systemT systemT,selectsScore selectsScore,systemScoreF systemScoreF,systemScoreT systemScoreT,sumScore sumScore\r\n" + 
					"from exam_sch\r\n" + 
					"where classes=?";
			return runner.query(sql, new BeanListHandler<>(ExamSch.class),classesName);
		}
		catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	public static List<ExamSch>findByClassesId(int classesId) {
		try {
			String sql = "SELECT exam_id examId,classes classes,exam_name examName,Time_beg timeBeg,Time_end timeEnd,"
					+ "selects selects,systemF systemF,systemT systemT,selectsScore selectScore,systemScoreF systemScoreF,systemScoreT systemScoreT,sumScore sumScore\r\n" + 
					"from exam_sch\r\n" + 
					"where classes=?";
			return runner.query(sql, new BeanListHandler<>(ExamSch.class),classesId);
		}
		catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	public static int addNewExam(int classes,String examName,Date timeBeg,Date timeEnd){
		String sql = "INSERT INTO `mysql`.`exam_sch`\r\n" + 
				"(`classes`, `exam_name`, `	Time_beg`, `Time_end`)\r\n" + 
				" VALUES (?,?,?,?)";
        Object[] params={classes,examName,timeBeg,timeEnd};
        try {
            runner.update(sql,params);
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
	}
	public static ExamSch findByExamId(int examId) {
		try {
			String sql = "SELECT exam_id examId,classes classes,exam_name examName,Time_beg timeBeg,Time_end timeEnd,"
					+ "selects selects,systemF systemF,systemT systemT,selectsScore selectScore,systemScoreF systemScoreF,systemScoreT systemScoreT,sumScore sumScore\r\n" + 
					"from exam_sch\r\n" + 
					"where exam_id=?";
			return runner.query(sql, new BeanHandler<>(ExamSch.class),examId);
		}
		catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	public static ExamSch findByExamName( String examName,int classes) {
		try {
			String sql = "SELECT exam_id examId,classes classes,exam_name examName,Time_beg timeBeg,Time_end timeEnd,"
					+ "selects selects,systemF systemF,systemT systemT,selectsScore selectScore,systemScoreF systemScoreF,systemScoreT systemScoreT,sumScore sumScore\r\n" + 
					"from exam_sch\r\n" + 
					"where exam_name=? and classes=? ";
			return runner.query(sql, new BeanHandler<>(ExamSch.class),examName,classes);
		}
		catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	//将题目加入试卷
	//UPDATE `mysql`.`exam_sch` SET `selects` = '1,' WHERE `exam_id` = 2
	public static int addToExam(int classes,String examName,Date timeBeg,Date timeEnd){
		String sql = "UPDATE `mysql`.`exam_sch` SET ? = ? WHERE `exam_id` = ?";
        Object[] params={classes,examName,timeBeg,timeEnd};
        try {
            runner.update(sql,params);
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
	}
	
	public static void serchexamName(String examid){
		
	}
	
	
	

	
}
