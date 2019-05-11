package admin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import dao.AdminDao;
import entity.Admin;

public class AdminLogin {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		new AdminLogin();
		
		

	}
	public AdminLogin() throws IOException {
		System.out.println("管理员登录页面");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		System.out.print("请输入您的姓名:");
//		String name = br.readLine();
		String name="白管理者";
		System.out.println(" ");
		System.out.print("请输入您的密码:");
//		String pass = br.readLine();
		String pass="cute";
		while(pass==null) {
			System.out.println(" ");
			System.out.print("请输入您的密码");
			pass = br.readLine();
		}
		Admin admin=(Admin)AdminDao.findByName(name);
		String passRight=admin.getPass();
//		System.out.print(passRight);
		if(pass.equals(passRight)) {
			System.out.println(" ");
			System.out.print("密码正确，正在进入系统");
			//跳转到界面，将用户信息发送
//			AdminConsole adminConsole = new AdminConsole(admin);
//            adminConsole.setVisible(true);
//            dispose();
			
			
		}else {
			System.out.println(" ");
			System.out.print("密码错误");
		}
	}

}
