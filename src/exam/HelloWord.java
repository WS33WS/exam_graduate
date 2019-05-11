package exam;

import dao.AdminDao;
import entity.Admin;

public class HelloWord {
	public static void main(String args[]) {
		
		System.out.println("begin process");
		Admin admin=(Admin)AdminDao.findByName("白管理者");
		String school=admin.getSchool();
		System.out.println(school);
		

		
			
	}
}

