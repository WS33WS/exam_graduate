package dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import entity.ExamSch;
import entity.Questionsystem;
import exam.DbUtils;

public class SchDao {
	private static QueryRunner runner = new QueryRunner(DbUtils.getDataSource());//connect

	public static int addNewTeacher(int classes, String exam_name, Date Time_beg,Date Time_end,List<Integer> toSelect,List<Integer> toSystem,int selectsScore,int systemScore){
		String selects= null; 
		String systemF= null;
		String systemT = null;
		for(int i=0;i<toSelect.size();i++) {
			if(selects==null) {
				selects=toSelect.get(i).toString()+',';
			}
			else {
				selects+=toSelect.get(i).toString()+',';
			}
		}
		Questionsystem systemEntity=new Questionsystem();
		for(int j=0;j<toSystem.size();j++) {
			systemEntity=QuestionDao.findBySystemId(toSystem.get(j));
			if(systemEntity.isAuto()) {//如果是自动阅卷
				if(systemF==null) {
					systemF=toSystem.get(j).toString()+',';
				}
				else {
					systemF+=toSystem.get(j).toString()+',';
				}
			}
			else {
				if(systemT==null) {
					systemT=toSystem.get(j).toString()+',';
				}
				else {
					systemT+=toSystem.get(j).toString()+',';
				}
			}
		}
		int selectScore=0; 
		int systemFScore=0;
		int systemTScore=0;//将空的题型分数设置为0
		if(selects!=null) {selectScore=selectsScore;}
		if(systemF!=null) {systemFScore=systemScore;}
		if(systemT!=null) {systemTScore=systemScore;}
		int sumScore=toSelect.size()*selectsScore+toSystem.size()*systemScore;
		String sql = "INSERT INTO `exam_sch`( `classes`, `exam_name`, `Time_beg`, `Time_end`, `selects`, `systemF`, `systemT`, `selectsScore`, `systemScoreF`, `systemScoreT`, `sumScore`) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?);";
        Object[] params={classes, exam_name, Time_beg,Time_end,selects, systemF, systemT, selectScore, systemFScore, systemTScore, sumScore};
        try {
            runner.update(sql,params);
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
	}
	
	public static List<ExamSch>findByClassesId(int classesId) {
		try {
			String sql = "SELECT exam_id examId,classes classes,exam_name examName,Time_beg timeBeg,Time_end timeEnd,selects selects,systemF systemF,systemT systemT,selectsScore selectsScore,systemScoreF systemScoreF,systemScoreT systemScoreT,sumScore sumScore\r\n" + 
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

	public static boolean deleteExam(int parseInt) {
		// TODO Auto-generated method stub
//		try {
//			String sql = "SELECT exam_id examId,classes classes,exam_name examName,Time_beg timeBeg,Time_end timeEnd,selects selects,systemF systemF,systemT systemT,selectsScore selectsScore,systemScoreF systemScoreF,systemScoreT systemScoreT,sumScore sumScore\r\n" + 
//					"from exam_sch\r\n" + 
//					"where classes=?";
//			return runner.query(sql, new BeanListHandler<>(ExamSch.class),classesId);
//		}
//		catch (SQLException e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
		
		String sql = "DELETE FROM `mysql`.`exam_sch` WHERE `exam_id` = ?";
        try {
            runner.update(sql,parseInt);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
	}

}
