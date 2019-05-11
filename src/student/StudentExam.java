package student;

import java.util.ArrayList;
import java.util.List;

import dao.ExamScoreDao;
import dao.SchDao;
import entity.ExamSch;
import entity.Examscore;

public class StudentExam {
	
	public static List<Examscore> StudentClassExam(int classesID,int studentId) {
		// TODO Auto-generated method stub
//		首先找到此课程的所有考试ID
		List<ExamSch> all=SchDao.findByClassesId(classesID);
		
//		
		List<Examscore> ess=new ArrayList();
		for(int i=0;i<all.size();i++) {
			System.out.println("++++"+all.get(i).getExamId());
			Examscore es=ExamScoreDao.findByTestsStuId(all.get(i).getExamId(),studentId);
			System.out.println(es);
			if(es!=null){
				ess.add(es);
			}
			
		}
		System.out.println("----------"+ess);
		return ess;
	}


}
