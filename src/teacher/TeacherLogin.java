package teacher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import dao.TeacherDao;
import entity.Teacher;

public class TeacherLogin {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		new TeacherLogin();

	}
	public TeacherLogin() throws IOException {
		System.out.println("教师登录页面");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		System.out.print("请输入您的姓名:");
//		String name = br.readLine();
		String name="白老师";
		System.out.println(" ");
		System.out.print("请输入您的密码:");
//		String pass = br.readLine();
		String pass="1";
		while(pass==null) {
			System.out.println(" ");
			System.out.print("请输入您的密码");
			pass = br.readLine();
		}
		Teacher teacher=(Teacher)TeacherDao.findByName(name);
		String passRight=teacher.getPass();
//		System.out.print(passRight);
		if(pass.equals(passRight)) {
			System.out.println(" ");
			System.out.print("密码正确，正在进入系统");
			//跳转到界面，将用户信息发送
//			TeacherConsole teacherConsole = new TeacherConsole(teacher);
//            teacherConsole.setVisible(true);
//            dispose();
			
			
		}
		else {
			System.out.println(" ");
			System.out.print("密码错误");
		}
	}

}
