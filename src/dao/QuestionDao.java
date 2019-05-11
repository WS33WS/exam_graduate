package dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import entity.Questionselect;
import entity.Questionsystem;
import entity.Teacher;
import exam.DbUtils;
//import ga.Problem;

public class QuestionDao {
	private static QueryRunner runner = new QueryRunner(DbUtils.getDataSource());//导入数据库数据
	//------------------------------------------
	//查询本科目所有选择题
	
	public static Questionselect findBySelectId(int id) {
		try {
			String sql = "SELECT Que_id que_id,Question question,Answer answer,RightAnswer RightAnswer,analysis analysis,situation situation,l l,points points\r\n" + 
					"from questionselect\r\n" + 
					"where Que_id=?";
			return (Questionselect) runner.query(sql, new BeanHandler<>(Questionselect.class),id);
		}
		catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	public static Questionsystem findBySystemId(int id) {
		try {
			String sql = "SELECT  Que_id que_id,Question question,RightAnswer RightAnswer,analysis analysis,situation situation,l l,points points,auto auto,mykey mykey\r\n" + 
					"from questionsystem\r\n" + 
					"where Que_id=?";
			return (Questionsystem) runner.query(sql, new BeanHandler<>(Questionsystem.class),id);
		}
		catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	//增加题目
	//选择题questionselect
	public static int addNewQuestion(String questionType, String Question,String Answer,String RightAnswer,String analysis,String situation,float L){
		String sql = "INSERT INTO `mysql`.?"
				+ "(`Question`, `Answer`, `RightAnswer`, `analysis`, `situation`, `L`) "
				+ "VALUES (?,?, ?, ?, ?, ?, ?)";
        Object[] params={questionType,Question,Answer,RightAnswer,analysis,situation,L};
        try {
            runner.update(sql,params);
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
	}
	//得到本题id，根据questionType, Question  SQL语句未完成
	public static int findByQuestion(String questionType, String Question) throws SQLException{
		String sql = "INSERT INTO `mysql`.?"
				+ "(`Question`, `Answer`, `RightAnswer`, `analysis`, `situation`, `L`) "
				+ "VALUES (?,?, ?, ?, ?, ?, ?)";
		//case?????
		if(questionType=="") {
			Questionselect a= runner.query(sql, new BeanHandler<>(Questionselect.class),questionType,Question);
			return a.getQue_id();
		}
		else return 0;  
	}
	//题库中得到题来遗传
	//------------------------------------------
		//查询本科目所有选择题
	//输入科目，返回题
	public static List<Questionselect> findAllQuestion(String situation) {
		try {
			String sql="select Que_id que_id,Question question,Answer answer,RightAnswer RightAnswer,analysis analysis,situation situation,l l,points points\r\n" + 
					"from questionselect\r\n" + 
					"where situation=?";
			return runner.query(sql, new BeanListHandler<>(Questionselect.class),situation);
		}
		catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	//------------------------------------------
			//查询本科目所有大题
	public static List<Questionsystem> findAllQuestionSystem(String situation) {
		try {
			String sql="select Que_id que_id,Question question,RightAnswer RightAnswer,analysis analysis,situation situation,L l,points points\r\n" + 
					"from questionsystem\r\n" + 
					"where situation=?";
			return runner.query(sql, new BeanListHandler<>(Questionsystem.class),situation);
		}
		catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	public static int addSelect(double d,String i){
		String sql = "INSERT INTO `mysql`.`questionselect`(`Question`, `Answer`, `RightAnswer`, `situation`, `L`, `points`) "
				+ "VALUES ('科学选择', 'A/B/C/D', 'B', '科学', ?, ?)";
        Object[] params={d,i};
        try {
            runner.update(sql,params);
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
	}
	public static int addSystemA(double l,String i){
		String sql = "INSERT INTO `mysql`.`questionsystem`(`Question`, `RightAnswer`, `situation`, `L`, `points`, `auto`, `mykey`) "
				+ "VALUES ('科学简答题', '嘻嘻', '科学',?,?, 1,'嘻嘻/哈哈');";
        Object[] params={l,i};
        try {
            runner.update(sql,params);
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
	}
	public static int addSystem(double l,String i){
		String sql = "INSERT INTO `mysql`.`questionsystem`(`Question`, `RightAnswer`, `situation`, `L`, `points`, `auto`) "
				+ "VALUES ('科学简答题', '嘻嘻', '科学',?,?, 0);";
        Object[] params={l,i};
        try {
            runner.update(sql,params);
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
	}
	

}
