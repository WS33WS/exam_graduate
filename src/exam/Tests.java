package exam;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import dao.ClassesDao;
import dao.ExamSchDao;
import dao.ExamScoreDao;
import dao.SchDao;
import dao.StudentDao;
import entity.Classes;
import entity.ExamSch;
import entity.Examscore;
import entity.LookSin;
import entity.ScoreAddStudent;
import entity.Student;
import student.StudentExam;
import teacher.TeacherScore;

public class Tests {
	
	private static  String endtime;
	private static String nowtime;
	private static  String endtime_1;
	private int sumnum=0;

	public static void main(String[] args) {
//		List<String> selectAnswer= new ArrayList();
//		selectAnswer.add("B");
//		selectAnswer.add("B");
//		List<String> systemAnswerF= new ArrayList();
//		systemAnswerF.add("hahhah");
//		List<String> systemAnswerT= new ArrayList();
////		
//		ExamScoreDao.addAnswer(22, 3, selectAnswer, systemAnswerF, systemAnswerT);
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		nowtime=df.format(DateUtil.getNow());
//		Calendar c = Calendar.getInstance();
//		Date endt=new Date(nowtime);
//		c.setTime(endt);
//		c.add(Calendar.DAY_OF_MONTH, 5);
//		endtime_1=df.format(c.getTime());
//		System.out.println(endtime_1);
		
//		System.out.println(WorkWithData.anal(25));

//		int examid=8;
//		int stuId=2;
//		LookSin a=Look.Look(examid, stuId);
//		System.out.print(a.getSelect().get(0).getContent());//选择题目
//		System.out.print(a.toString());
		
//		ExamSch all=ExamSchDao.findByExamId(examid);
//		System.out.println(all.getSystemF());
//		String examName="afwef";
//		int classid=5;
//		ExamSch find=ExamSchDao.findByExamName(examName, classid);
////		System.out.println(find.getExamId());
//		ExamScoreDao.addNewExamToStu(find.getExamId());
		
		int examId=26;
		//根据考试Id找到题目
		ExamSch sch=ExamSchDao.findByExamId(examId);
		//根据考试Id找到未打分学生
		 List<Examscore> hah=Yuejuan.dafenxianshi(examId);
		 System.out.println(sch.toString());
		 System.out.println(hah.size());


		//打分提交
//		 List<Integer> score=new ArrayList();
//		 score.add(8);
//		 ExamScoreDao.dafentijiao(2, examId, score);
		

	}
}
