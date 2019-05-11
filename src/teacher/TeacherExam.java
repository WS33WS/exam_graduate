package teacher;

import java.util.ArrayList;
import java.util.List;

import dao.ExamSchDao;
import dao.QuestionDao;
import entity.ExamSch;
import entity.Questionselect;
import entity.Questionsystem;

public class TeacherExam {
	public static void main(String[] args) {
		int id=8;
		getExamById(id);
	}
	public static void getExamById(int examId) {
		//打印出选定考场场次的试卷
		//由场次得到选择和大题题目ID
		//由题目id打印出来
		ExamSch all=ExamSchDao.findByExamId(examId);//得到本场考试的考试信息
		System.out.print("考试题目");
		System.out.println(all.getExamName());
		
		//分解出本次考试题号
		List<Questionselect> QuesSelect=new ArrayList<Questionselect>();
		List<Questionsystem> QuesSystem=new ArrayList<Questionsystem>();
		if(!all.getSelects().equals(null)) {
			
			String[] select = all.getSelects().split(",");
			for(int i=0;i<select.length;i++) {
				try {
				    int a = Integer.parseInt(select[i]);
				    Questionselect QueSelect=QuestionDao.findBySelectId(a);
				    QuesSelect.add(QueSelect);
				} catch (NumberFormatException e) {
				    e.printStackTrace();
				}
			}
		}
		if(!all.getSystemF().equals(null)) {
			String[] systemF = all.getSystemF().split(",");
			for(int i=0;i<systemF.length;i++) {
				try {
				    int a = Integer.parseInt(systemF[i]);
				    Questionsystem QueSystem=QuestionDao.findBySystemId(a);
				    QuesSystem.add(QueSystem);
				} catch (NumberFormatException e) {
				    e.printStackTrace();
				}
			}
		}
		if(!all.getSystemT().equals(null)) {
			System.out.println("不是空的");
			String[] systemT = all.getSystemT().split(",");
			for(int i=0;i<systemT.length;i++) {
				try {
				    int a = Integer.parseInt(systemT[i]);
				    Questionsystem QueSystem=QuestionDao.findBySystemId(a);
				    QuesSystem.add(QueSystem);
				} catch (NumberFormatException e) {
				    e.printStackTrace();
				}
			}
		}
		
		//============================打印出来
		
//		System.out.print("共"+(select.length+systemF.length+systemT.length)+"道题，");
//		System.out.println("其中"+select.length+"道选择题"+(systemF.length+systemT.length)+"道简答题");
		System.out.print("考试开始时间：");
		System.out.print(all.getTimeBeg());
		System.out.print("结束时间");
		System.out.println(all.getTimeEnd());
		//====================选择题
		if(QuesSelect.size()>0) {
			System.out.println("一、选择题");
			for(int i=0;i<QuesSelect.size();i++) {
				System.out.println((i+1)+"、"+QuesSelect.get(i).getQuestion());
				String[] answerSelect = QuesSelect.get(i).getAnswer().split("/");
				for(int j=0;j<answerSelect.length;j++) {
					System.out.println((char)(97+j)+"、"+answerSelect[j]);
				}
			}
		}
		//===================大题
		if(QuesSystem.size()>0) {
			System.out.println("二、简答题");
			for(int i=0;i<QuesSystem.size();i++) {
				System.out.println((i+1)+"、"+QuesSystem.get(i).getQuestion());
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println();
			}
		}
		
		
	}

}
