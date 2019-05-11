package teacher;

import java.util.ArrayList;
import java.util.List;

import dao.ClassesDao;
import dao.ExamScoreDao;
import dao.SchDao;
import entity.Classes;
import entity.ExamSch;
import entity.ScoreAddStudent;

public class TeacherScore {
	public static List<ScoreAddStudent> find(int j,String className,String studentName) {
		
//		首先根据课程名称找到课程ID
		Classes haha=ClassesDao.findByClassesName(j, className);
//		再根据课程ID找到所有的考试目录
		List<ExamSch> all=SchDao.findByClassesId(haha.getClassesId());
//		遍历考试目录得到信息
		List<ScoreAddStudent> end=new ArrayList();
		for(int i=0;i<all.size();i++) {
			List<ScoreAddStudent> sin=ExamScoreDao.findName(all.get(i).getExamId(), studentName);
			end.addAll(sin);
		}
		System.out.println(end);
		return end;
		
	}

}
