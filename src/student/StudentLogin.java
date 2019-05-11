package student;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import dao.StudentDao;
import dao.TeacherDao;
import entity.Student;
import entity.Teacher;

public class StudentLogin {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public StudentLogin() throws IOException {
		System.out.println("学生登录页面");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		System.out.print("请输入您的姓名:");
//		String name = br.readLine();
		String name="白学生";
		System.out.println(" ");
		System.out.print("请输入您的密码:");
//		String pass = br.readLine();
		String pass="1";
		while(pass==null) {
			System.out.println(" ");
			System.out.print("请输入您的密码");
			pass = br.readLine();
		}
		Student student=(Student)StudentDao.findByName(name);
		while(student==null) {
			System.out.println(" ");
			System.out.print("请输入正确的姓名");
			pass = br.readLine();
		}
		String passRight=student.getPass();
//		System.out.print(passRight);
		if(pass.equals(passRight)) {
			System.out.println(" ");
			System.out.print("密码正确，正在进入系统");
			//跳转到界面，将用户信息发送
//			StudentConsole studentConsole = new StudentConsole(student);
//            studentConsole.setVisible(true);
//            dispose();
			
			
		}
		else {
			System.out.println(" ");
			System.out.print("密码错误");
		}
	}

}
