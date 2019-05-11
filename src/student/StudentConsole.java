package student;

import java.util.List;

import dao.ClassesDao;
import dao.ExamSchDao;
import entity.ExamSch;

import entity.Student;

public class StudentConsole {
	Student student;
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//	}
	public StudentConsole(Student stu) {
		student=stu;
	}

//	// 输出所有的课程名称
//	public void findAllClasses() {
//		String classes=student.getClasses();
//		String[] myclass = classes.split(",");
//		for(int i=0;i<myclass.length;i++) {
//			try {
//			    int a = Integer.parseInt(myclass[i]);
//			    //根据classesID找到课程名称
//			    System.out.println(ClassesDao.findByClassID(a));
//			} catch (NumberFormatException e) {
//			    e.printStackTrace();
//			}
//		}
//	}
	//查看本课程的考试目录表//同教师
	public void findAllExam() {
		int classesId = 1;
		List<ExamSch> AllExam = ExamSchDao.findByClassesId(classesId);
		for (int i = 0; i < AllExam.size(); i++) {
			System.out.println(AllExam.get(i).getExamName());
		}
	}
	//查看未开始考试在首页显示，查看考试时间
	//查看已完成考试，在各自的课程目录下
	//已完成考试查看题目（题目，本人答案，解析），简易版
	

}
