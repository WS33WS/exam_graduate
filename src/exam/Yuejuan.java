package exam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;



import dao.ClassesDao;
import dao.ExamSchDao;
import dao.ExamScoreDao;
import dao.QuestionDao;
import entity.Classes;
import entity.ExamSch;
import entity.Examscore;
import entity.Questionsystem;


public class Yuejuan {
	
	private static  String endtime;
	private static String nowtime;
	private static  String endtime_1;
	
	
	public static List<ExamSch> Yuejuan(int teacherId) throws ParseException{
		//得到所有考试列表
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<Classes> classes=ClassesDao.findByTeacherId(teacherId);
		List<ExamSch> sch=new ArrayList();
		for(int i=0;i<classes.size();i++) {
			List<ExamSch> schc=ExamSchDao.findByClassesId(classes.get(i).getClassesId());
			sch.addAll(schc);
		}
//		//判断时间
//		
//		for(int i=0;i<sch.size();i++){
//			nowtime=df.format(DateUtil.getNow());
//			endtime=df.format(sch.get(i).getTimeEnd());
//			Calendar c = Calendar.getInstance();
//			Date endt=new Date(endtime);
//			c.setTime(endt);
//			c.add(Calendar.DAY_OF_MONTH, 5);
//			endtime_1=df.format(c.getTime());
//			int i1=nowtime.compareTo(endtime);
//			int i2=nowtime.compareTo(endtime_1);
//			if(i1<0||i2<0){//现在>结束时间+5day
//				sch.remove(i);
//			}
//			
//		}
//		
		//判断是否有阅卷项目
		for(int i=sch.size()-1;i>=0;i--) {
			if(sch.get(i).getSystemScoreT()==0) {
				sch.remove(i);
			}
		}		
		//判断是否批阅完成
		for(int i=sch.size()-1;i>=0;i--) {
			List<Examscore> hah=ExamScoreDao.findByTestId(sch.get(i).getExamId());
			if(yueAlready(hah)) {
				sch.remove(i);
			}
		}
		
		return sch;
		
	}
	public static boolean yueAlready(List<Examscore> hah) {
		for(int i=0;i<hah.size();i++) {
			if(hah.get(i).getSystemT_score()==null) {
				return false;
			}
		}
		return true;
	}
	//豆粉给豆绿考试ID
	//豆绿找到ExamScore中没有打分的List给豆粉传过去
	public static List<Examscore> dafenxianshi(int examId) {
		List<Examscore> hah=ExamScoreDao.findByTestId(examId);
		for(int i=hah.size()-1;i>=0;i--) {
			if(hah.get(i).isState()) {
				hah.remove(i);
			}
		}
		return hah;
		
		
	}
	public static List<Questionsystem> systemTxinxi(String a){
		String[] num=a.split(",");
		List<Questionsystem> end=new ArrayList();
		for(int i=0;i<num.length;i++) {
			Questionsystem hah=QuestionDao.findBySystemId(Integer.parseInt(num[i]));
			end.add(hah);
		}
		return end;
	}

}
