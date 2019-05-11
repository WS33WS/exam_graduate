package teacher;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.ClassesDao;
import dao.ExamSchDao;
import dao.ExamScoreDao;
import dao.QuestionDao;
import dao.StudentDao;
import entity.Classes;
import entity.ExamSch;
import entity.Student;
import entity.Teacher;

public class TeacherConsole {
	Teacher teacher;
	int classesId;

//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//	}
	public TeacherConsole(Teacher tea) {
		teacher=tea;
	}
	//输出所有的课程名称
	public void findAllClasses() {
		List<Classes> AllClasses=ClassesDao.findByTeacherId(teacher.getId());
		for(int i=0;i<AllClasses.size();i++) {
			//输出所有的课程名称
			System.out.println(AllClasses.get(i).getClassesName());
		}
	}
	
//	//输出本课程所有学生信息
//	public void findAllStudent() {
////		String allStudent=ClassesDao.findByClasses(teacher.getId());
//		String []a=allStudent.split(",");
//		List<Student> SinStudent=new ArrayList<Student>();
//		for(int i=0;i<a.length;i++)
//        {
//			int b = Integer.parseInt(a[i]);
//			Student min;
//			min=StudentDao.findById(b);
//			SinStudent.add(min);
//        }
//		for(int i=0;i<SinStudent.size();i++) {
//			System.out.println(SinStudent.get(i).getName());
//		}
//	}
//	//查看本课程的考试目录表//未完成
//	public void findAllExam() {
//		List<ExamSch> AllExam=ExamSchDao.findByClassesId(classesId);
//		for(int i=0;i<AllExam.size();i++) {
//			System.out.println(AllExam.get(i).getExamName());
//		}
//	}
//	//添加新的考试
//	public void addNewExam(int classes,String examName,Date timeBeg,Date timeEnd) {
//		//插入数据出错判断
//		//判断是否与之前的重名
//		List<ExamSch> judge=ExamSchDao.findByExamName(examName,classes);
//		if(judge.size()==0) {
//			System.out.println("此班级已有此名称的考试");
//		}
//		else {
//			ExamSchDao.addNewExam(classes, examName, timeBeg, timeEnd);
//			//同一个班级考试名称不能重复，因为根据这个找考试id
//			List<ExamSch> getID=ExamSchDao.findByExamName(examName,classes);
//			//添加到学生的考试成绩表，根据classid找到学生，然后insert到examscore
//			String studentsId=ClassesDao.findByClasses(classes);
//			String[] studentId =studentsId.split(",");
//			for(int i=0;i<studentId.length;i++) {
//				try {
//				    int a = Integer.parseInt(studentId[i]);
//				    //添加到学生的考试成绩表
//				    ExamScoreDao.addNewExamToStu(getID.get(0).getExamId(),a);
//				} catch (NumberFormatException e) {
//				    e.printStackTrace();
//				}
//			}
//		}
//	}
	//自动出题按钮
	public void addNewQuestionAuto(String questionType, String Question,String Answer,String RightAnswer,String analysis,String situation,float L) {
		//判断题目啥的是否为空
		if(Question==null||Answer==null||RightAnswer==null){
			System.out.println("问题、答案、正确选项不能为空，请补充完整");
			
		}
		else {
			if(analysis==null||situation==null||L==0) {
				System.out.println("分析、位置、难度为空，您可以日后完善");
			}
			QuestionDao.addNewQuestion(questionType, Question, Answer, RightAnswer, analysis, situation, L);	
		}
				
	}
	
	//手动出题按钮
	public void addNewQuestion(String questionType, String Question,String Answer,String RightAnswer,String analysis,String situation,float L) throws SQLException {
		//判断题目啥的是否为空
		if(Question==null||Answer==null||RightAnswer==null){
			System.out.println("问题、答案、正确选项不能为空，请补充完整");
			
		}
		else {
			if(analysis==null||situation==null||L==0) {
				System.out.println("分析、位置、难度为空，您可以日后完善");
			}
			//放入题库
			QuestionDao.addNewQuestion(questionType, Question, Answer, RightAnswer, analysis, situation, L);	
			//得到本题id，根据questionType, Question
			int id=QuestionDao.findByQuestion(questionType, Question);
			//放入试卷
			//ExamSchDao.addToExam(classes, examName, timeBeg, timeEnd);
		}			
	}
	//自动组卷系统
	
	
	
	
	
	

}
