package admin;

import java.util.ArrayList;
import java.util.List;

import dao.TeacherDao;
import entity.Admin;
import entity.Teacher;

public class AdminConsole{
	Admin admin;
	String insiti;

//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		
//
//	}
	public AdminConsole(Admin ad) {
		//将传输过来的admin信息赋值为全局变量
		admin=ad;
	}
	public void getInsitution() {
		//根据传送来的学校打开学院列表,学院列表
//		List<String> insititution=TeacherDao.findBySchool(school);
		if(admin.getSchool().equals("西南财经大学")) {
			List<String> insititution=new ArrayList<String>();
			insititution.add("经济信息工程学院");
			insititution.add("会计学院");
			insititution.add("工商管理学院");
			int i=insititution.size();//计算条数
			System.out.println(i);
		}
	}
	public void onClickInstitution() {
		//全局变量的学院设置为点击的学院
//		insti=insitution ;
		
	}
	//点击学院得到的教师信息与上一个功能合起来
	public void getTeacher() {
		List<Teacher> AllTeacher=TeacherDao.findAll(admin.getSchool(),insiti);
		//显示出teacher列表
	}
	public void addTeacher() {//添加新的教师信息，已验证可用
		System.out.println("输入新的教师信息");
		String names="张老师";
		int sex=1;
		String tele="123";
		String address="111";
		String passs="111";
		String classes="6";
		String institution="经济信息工程学院";
		String school=admin.getSchool();
		TeacherDao.addNewTeacher(names,tele,sex,address,passs,classes,institution,school);
	}
	public void deleteTeacher() {//删除选定行的教师信息
		//根据用户点击的位置来得到用户id
//		1. 得到本行用户id 2.得到这是第几行，查出用户id
		int id =9;
		TeacherDao.deleteTeacher(id);
	}
	
	public void resetTeacher() {//修改选定位置的教师记录（id不可以改）
		//根据点击的行列修改
		String a="naem";
		String b="席老师";
		int id=1;
		TeacherDao.resetTeacher(a,b,id);
	}
	public void findSomething() {
		//管理者在搜索栏输入的文字定义为something，id或者名字，更多要改dao
		String something="老师";
		List<Teacher> teacher=TeacherDao.findBySomething(admin.getSchool(),insiti,something);
	}
	
	
	
	

}
