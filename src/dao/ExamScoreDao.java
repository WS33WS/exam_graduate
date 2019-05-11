package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import entity.Classes;
import entity.ExamSch;
import entity.Examscore;
import entity.Questionselect;
import entity.Questionsystem;
import entity.ScoreAddStudent;
import entity.Teacher;
import exam.DbUtils;

public class ExamScoreDao {
	private static QueryRunner runner = new QueryRunner(DbUtils.getDataSource());//导入数据库数据
	//发布新考试后插入到
	public static int addNewExamToStu(int TestId){
		//根据testID找到班级id
		ExamSch sch=ExamSchDao.findByExamId(TestId);
		//根据班级id找到学生
		Classes classes=ClassesDao.findByClasses(sch.getClasses());
		//遍历插入学生
		for(int i=0;i<classes.getStudent().size();i++) {
			String sql = "INSERT INTO `mysql`.`examscore`(`Test_id`, `Stu_id`) VALUES (?, ?)";
	        Object[] params={TestId,classes.getStudent().get(i).getId()};
	        try {
	            runner.update(sql,params);
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return -1;
		}
		return 1;
		
	}
	
	
//	INSERT INTO `mysql`.`examscore`(`Test_id`, `Stu_id`, `Select_score`, `SystemF_score`, `SystemT_score`, `Select_answer`, `SystemF_answer`, `SystemT_answer`) VALUES (23, 3, '1', '1', '1', '1', '1', '1')
//	学生提交试卷后插入
	
	public static int addAnswer2(int TestId,int StuId,List<String> selectAnswer,List<String> systemAnswerF,List<String> systemAnswerT) {
		String selectAnswerToString=null;
		String systemFAnswerToString=null;
		String systemTAnswerToString=null;
		for(int i=0;i<selectAnswer.size();i++) {
			if(selectAnswerToString==null) {selectAnswerToString=selectAnswer.get(i);}
			else{selectAnswerToString+=","+selectAnswer.get(i);}
		}
		for(int i=0;i<systemAnswerF.size();i++) {
			if(systemFAnswerToString==null) {systemFAnswerToString=systemAnswerF.get(i);}
			else{systemFAnswerToString+="/"+systemAnswerF.get(i);}
		}
		if(!systemAnswerT.isEmpty()) {
			for(int i=0;i<systemAnswerT.size();i++) {
				if(systemTAnswerToString==null) {systemTAnswerToString=systemAnswerT.get(i);}
				else{systemTAnswerToString+="/"+systemAnswerT.get(i);}
			}
		}
		
		//=================================得到正确答案和每题分数
		int ascore=0;//本学生选择题得分
		String bscore = "";//本学生自动阅卷大题得分
		ExamSch all=ExamSchDao.findByExamId(TestId);//得到本场考试的考试信息
		
		int selectscore=all.getSelectScore();
		int systemFscore=all.getSystemScoreF();
		if(selectscore!=0) {
			//分解出本次考试题号
			String[] selects = all.getSelects().split(",");
			//=========================得到选择题信息,并计算分数
			for(int i=0;i<selects.length;i++) {
				try {
				    int a = Integer.parseInt(selects[i]);
				    Questionselect QueSelect=QuestionDao.findBySelectId(a);
				    if(selectAnswer.get(i).equals(QueSelect.getRightAnswer())) {
						ascore+=selectscore;
					}
				} catch (NumberFormatException e) {
				    e.printStackTrace();
				}
			}
		}
		if(systemFscore!=0) {
			String[] systemF = all.getSystemF().split(",");
			
			//=========================得到大题信息,并计算分数
			for(int i=0;i<systemF.length;i++) {
				try {
				    int a = Integer.parseInt(systemF[i]);
				    Questionsystem QueSystem=QuestionDao.findBySystemId(a);
				    String[] keys=QueSystem.getKeys();
				    int hh=0;
				    for(int j=0;j<keys.length;j++) {
				    	if(systemAnswerF.get(i).contains(keys[j])) {
				    		hh++;
				    	}
				    }
				    float bili=(float)hh/keys.length;
				    bscore=bscore+systemFscore*bili+",";
				} catch (NumberFormatException e) {
				    e.printStackTrace();
				}
			}
		}
		int state=0;
		if(all.getSystemScoreT()==0) {
			state=1;
		}
		
		
		
//		=====数据库修改
		String sql = "INSERT INTO `mysql`.`examscore`"
				+ "(`Test_id`, `Stu_id`, `Select_score`, `SystemF_score`, "
				+ " `Select_answer`, `SystemF_answer`, `SystemT_answer`,`state`) "
				+ "VALUES (?,?,?,?,?,?,?,?);";
        Object[] params={TestId,StuId,ascore,bscore,selectAnswerToString,systemFAnswerToString,systemTAnswerToString,state};
        try {
            runner.update(sql,params);
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
	}
	
	
	
//学生答题后，将答案和成绩放到数据库，
//	豆粉传给豆绿：
//	学生ID，考试ID，学生选择题答案的List（A/B/C/D），简答题手动阅卷答案List，简答题自动阅卷答案List
	
	public static int addAnswer(int TestId,int StuId,List<String> selectAnswer,List<String> systemAnswerF,List<String> systemAnswerT) {
		String selectAnswerToString=null;
		String systemFAnswerToString=null;
		String systemTAnswerToString=null;
		for(int i=0;i<selectAnswer.size();i++) {
			if(selectAnswerToString==null) {selectAnswerToString=selectAnswer.get(i);}
			else{selectAnswerToString+=","+selectAnswer.get(i);}
		}
		for(int i=0;i<systemAnswerF.size();i++) {
			if(systemFAnswerToString==null) {systemFAnswerToString=systemAnswerF.get(i);}
			else{systemFAnswerToString+="/"+systemAnswerF.get(i);}
		}
		if(!systemAnswerT.isEmpty()) {
			for(int i=0;i<systemAnswerT.size();i++) {
				if(systemTAnswerToString==null) {systemTAnswerToString=systemAnswerT.get(i);}
				else{systemTAnswerToString+="/"+systemAnswerT.get(i);}
			}
		}
		
		//=================================得到正确答案和每题分数
		int ascore=0;//本学生选择题得分
		String bscore = "";//本学生自动阅卷大题得分
		ExamSch all=ExamSchDao.findByExamId(TestId);//得到本场考试的考试信息
		
		int selectscore=all.getSelectScore();
		int systemFscore=all.getSystemScoreF();
		if(selectscore!=0) {
			//分解出本次考试题号
			String[] selects = all.getSelects().split(",");
			//=========================得到选择题信息,并计算分数
			for(int i=0;i<selects.length;i++) {
				try {
				    int a = Integer.parseInt(selects[i]);
				    Questionselect QueSelect=QuestionDao.findBySelectId(a);
				    if(selectAnswer.get(i).equals(QueSelect.getRightAnswer())) {
						ascore+=selectscore;
					}
				} catch (NumberFormatException e) {
				    e.printStackTrace();
				}
			}
		}
		if(systemFscore!=0) {
			String[] systemF = all.getSystemF().split(",");
			
			//=========================得到大题信息,并计算分数
			for(int i=0;i<systemF.length;i++) {
				try {
				    int a = Integer.parseInt(systemF[i]);
				    Questionsystem QueSystem=QuestionDao.findBySystemId(a);
				    String[] keys=QueSystem.getKeys();
				    int hh=0;
				    for(int j=0;j<keys.length;j++) {
				    	if(systemAnswerF.get(i).contains(keys[j])) {
				    		hh++;
				    	}
				    }
				    float bili=(float)hh/keys.length;
				    bscore=bscore+systemFscore*bili+",";
				} catch (NumberFormatException e) {
				    e.printStackTrace();
				}
			}
		}
		
		
		
//		=====数据库修改
		String sql = "UPDATE `mysql`.`examscore` "
				+ "SET `Select_score` = ?, `SystemF_score` = ?,"
				+ "`Select_answer` =?, "
				+ "`SystemF_answer` = ?, "
				+ "`SystemT_answer` = ? "
				+ "WHERE Test_id =? And Stu_Id=?;";
        Object[] params={ascore,bscore,selectAnswerToString,systemFAnswerToString,systemTAnswerToString,TestId,StuId};
        try {
            runner.update(sql,params);
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
	}
	
	//将本场考试所有自动题目进行评分
	public static int calAuto(int TestId) {
		ExamSch all=ExamSchDao.findByExamId(TestId);//得到本场考试的考试信息
		int selectscore=all.getSelectScore();
		int systemTscore=all.getSystemScoreT();
		//分解出本次考试题号
		String[] selects = all.getSelects().split(",");
		String[] systemF = all.getSystemF().split(",");
		List<Questionselect> QuesSelect=new ArrayList<Questionselect>();
		List<Questionsystem> QuesSystem=new ArrayList<Questionsystem>();
		//=========================得到选择题信息
		for(int i=0;i<selects.length;i++) {
			try {
			    int a = Integer.parseInt(selects[i]);
			    Questionselect QueSelect=QuestionDao.findBySelectId(a);
			    QuesSelect.add(QueSelect);
			} catch (NumberFormatException e) {
			    e.printStackTrace();
			}
		}
		//=========================得到大题信息
		for(int i=0;i<systemF.length;i++) {
			try {
			    int a = Integer.parseInt(systemF[i]);
			    Questionsystem QueSystem=QuestionDao.findBySystemId(a);
			    QuesSystem.add(QueSystem);
			} catch (NumberFormatException e) {
			    e.printStackTrace();
			}
		}
		//循环所有的学生
		Classes bianli=ClassesDao.findByClasses(all.getClasses());
		for(int j=0;j<bianli.getStudent().size();j++) {
		Examscore sinStudent=ExamScoreDao.Studentfind(TestId, bianli.getStudent().get(j).getId());
		int ascore=0;//选择题得分
		int bscore=0;//自动阅卷大题得分
//		========
		String[] selectStudent = sinStudent.getSelect_answer().split(",");
		String[] systemFStudent = sinStudent.getSystemF_answer().split("/");
		//=========================得到选择题信息
		for(int i=0;i<QuesSelect.size();i++) {
			if(selectStudent[i].equals(QuesSelect.get(i).getRightAnswer())) {
				ascore+=selectscore;
			}
		}
		//=========================得到大题信息
		for(int i=0;i<systemF.length;i++) {
			if(systemFStudent[i].equals(QuesSystem.get(i).getRightAnswer())) {
				bscore+=systemTscore;
			}
		}
//		=====
		String sql = "UPDATE `mysql`.`examscore` "
				+ "SET `Select_score` = ?, `SystemT_score` = ?"
				+ " WHERE Test_id =? And Stu_Id=?;";
        Object[] params={ascore,bscore,TestId,bianli.getStudent().get(j).getId()};
        try {
            runner.update(sql,params);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
	}
		return 1;
	}
	
	
	
	public static Examscore findByTestsStuId( int i,int j) {
		try {
			String sql = "select id id,Test_id test_id,Stu_id stu_id,"
					+ "Select_score select_score,SystemF_score systemF_score,SystemT_score systemT_score,state state,"
					+ "Select_answer select_answer,SystemF_answer systemF_answer,SystemT_answer systemT_answer \r\n" + 
					"from examscore\r\n" + 
					"where Test_id=? And Stu_id=?;";
			
			return runner.query(sql, new BeanHandler<>(Examscore.class),i,j);
		}
		catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
//	根据考试ID查找记录
	public static List<Examscore> findByTestId( int i) {
		try {
			String sql = "select id id,Test_id test_id,Stu_id stu_id,"
					+ "Select_score select_score,SystemF_score systemF_score,SystemT_score systemT_score,state state,"
					+ "Select_answer select_answer,SystemF_answer systemF_answer,SystemT_answer systemT_answer \r\n" + 
					"from examscore\r\n" + 
					"where Test_id=?;";
			
			return runner.query(sql, new BeanListHandler<>(Examscore.class),i);
		}
		catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	//UPDATE `mysql`.`examscore` SET `Select_score` = '1' WHERE `id` = 3
	public static int updateScore(String type,String after,int stuId){
		String sql = "UPDATE `mysql`.`examscore` SET ? = ? WHERE `id` = ?";
        Object[] params={type,after,stuId};
        try {
            runner.update(sql,params);
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
	}
	//打分提交
	//豆粉给豆绿学生Id，考试id和分数List
	//更新分数string 
//	UPDATE `mysql`.`examscore` SET `SystemT_score` = '1，2，3' WHERE `id` = 13
	public static int dafentijiao(int stuId,int examId,List<Integer> score){
		String a=null;
		ExamSch sch=ExamSchDao.findByExamId(examId);
		int systemScore=sch.getSystemScoreT();
		for(Integer sc:score) {
			float b=(float)sc/10;
			b=b*systemScore;
			if(a==null)a=String.valueOf(b);
			else a+=","+String.valueOf(b);
		}
		String sql = "UPDATE `mysql`.`examscore` SET `SystemT_score` = ?, `state` = 1  WHERE Test_id =? And Stu_Id=?";
        Object[] params={a,examId,stuId};
        try {
            runner.update(sql,params);
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
	}
	//根据班级名或和学生名查询成绩
	public static List<ScoreAddStudent> findName(int testId,String studentName) {
		String stuName='%'+studentName+'%';
		try {
			String sql="select e.id id,e.Test_id test_id,e.Stu_id stu_id,"
					+ "e.Select_score select_score,e.SystemF_score systemF_score,e.SystemT_score systemT_score,e.state state,"
					+ "e.Select_answer select_answer,e.SystemF_answer systemF_answer,e.SystemT_answer systemT_answer,s.name studentName "
					+ "FROM examscore e left join student s on e.Stu_id=s.id\r\n" + 
					"where e.Test_id=? and s.name like ?";
			return runner.query(sql, new BeanListHandler<>(ScoreAddStudent.class),testId,stuName);
		}
		catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	//学生查成绩
		//豆粉给豆绿：学生的Id，要查询的考试的Id
		public static Examscore Studentfind( int i,int j) {
			try {
				String sql = "select id id,Test_id test_id,Stu_id stu_id,"
						+ "Select_score select_score,SystemF_score systemF_score,SystemT_score systemT_score,"
						+ "Select_answer select_answer,SystemF_answer systemF_answer,SystemT_answer systemT_answer \r\n" + 
						"from examscore\r\n" + 
						"WHERE Test_id =? And Stu_Id=?;";
				
				return runner.query(sql, new BeanHandler<Examscore>(Examscore.class),i,j);
			}
			catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return null;
		}
		
		

}
