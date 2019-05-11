package exam;

import java.util.ArrayList;
import java.util.List;

import dao.ExamSchDao;
import dao.ExamScoreDao;
import dao.QuestionDao;
import entity.ExamSch;
import entity.Examscore;
import entity.LookSin;
import entity.LookSinSelect;
import entity.LookSinSystem;
import entity.Questionselect;
import entity.Questionsystem;

public class Look {
//	输入学号和考试ID，得到LookSin
//	选择题题目List，学生答案list，正确答案list
//	自动阅卷大题题目List，学生答案list，正确答案list。
//	手工阅卷大题题目List，学生答案list，正确答案list。
	public static LookSin Look(int examid,int stuId) {
		LookSin my=new LookSin();
//		学生答案
		Examscore stu=ExamScoreDao.findByTestsStuId(examid, stuId);
//		题目和正确答案
		ExamSch all=ExamSchDao.findByExamId(examid);
//		========================================
		List<LookSinSelect> QuesSelect=new ArrayList<>();
		List<LookSinSystem> QuesSystem=new ArrayList<>();
		//==============选择题
		if(all.getSelectScore()!=0) {
			String[] myselect=stu.getSelect_answer().split(",");
			int selectScore=all.getSelectScore();
			String[] select = all.getSelects().split(",");
			for(int i=0;i<select.length;i++) {
				try {
				    int a = Integer.parseInt(select[i]);
				    Questionselect QueSelect=QuestionDao.findBySelectId(a);
				    LookSinSelect lselect=new LookSinSelect();
				    lselect.setContent(QueSelect);
				    lselect.setMy(myselect[i]);
				    if(select[i]==QueSelect.getAnswer()) {
				    	lselect.setScore(selectScore+"/"+selectScore);
				    }
				    else {
				    	lselect.setScore("0/"+selectScore);
				    }
				    QuesSelect.add(lselect);
				} catch (NumberFormatException e) {
				    e.printStackTrace();
				}
			}
		}
		
		//==============大题
		
		int systemScore=all.getSystemScoreT();
		//F
		if(all.getSystemScoreF()!=0) {
			String[] mysystemscoreF=stu.getSystemF_score().split(",");
			String[] mysystemF=stu.getSystemF_answer().split("/");
			String[] systemF = all.getSystemF().split(",");
			for(int i=0;i<systemF.length;i++) {
				try {
				    int a = Integer.parseInt(systemF[i]);
				    Questionsystem Quesystem=QuestionDao.findBySystemId(a);
				    LookSinSystem lsystem=new LookSinSystem();
				    lsystem.setContent(Quesystem);
				    lsystem.setMy(mysystemF[i]);
				    lsystem.setScore(mysystemscoreF[i]);
				    QuesSystem.add(lsystem);
				} catch (NumberFormatException e) {
				    e.printStackTrace();
				}
			}
		}
		
		// T
		if(stu.isState()) {
			if(all.getSystemScoreT()!=0) {
				String [] mysystemscoreT=stu.getSystemT_score().split(",");
				String[] mysystemT = stu.getSystemT_answer().split("/");
				String[] systemT = all.getSystemT().split(",");
				for (int i = 0; i < systemT.length; i++) {
					try {
						int a = Integer.parseInt(systemT[i]);
						Questionsystem Quesystem = QuestionDao.findBySystemId(a);
						LookSinSystem lsystem = new LookSinSystem();
						lsystem.setContent(Quesystem);
						lsystem.setMy(mysystemT[i]);
						lsystem.setScore(mysystemscoreT[i]);
						QuesSystem.add(lsystem);
					} catch (NumberFormatException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		my.setSelect(QuesSelect);
		my.setSystem(QuesSystem);		
		return my;
		
	}

}
