package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entity.Teacher;
import entity.UserType;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JCheckBoxMenuItem;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.JDesktopPane;
import java.awt.Color;

public class ExamMainFrm extends JFrame {

	private JPanel contentPane;
	private JDesktopPane desktopPane;
	public static UserType userType;
	public static Object userObject;
	private JMenu studentMenu ;
	private JMenu adminMenu;
	private JMenu teacherMenu;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ExamMainFrm frame = new ExamMainFrm();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public ExamMainFrm(UserType userType,Object userObject) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ExamMainFrm.class.getResource("/image/考试系统.png")));
		setFont(new Font("微软雅黑", Font.PLAIN, 16));
		setTitle("在线考试系统");
		this.userType = userType;
		this.userObject = userObject;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(90, 10, 1100, 800);
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("系统设置");
		menu.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		menuBar.add(menu);
		
		JMenuItem menuItem = new JMenuItem("修改密码");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editPassWord(e);
			}
		});
		menuItem.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		menu.add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("退出系统");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(ExamMainFrm.this, "确定退出么？") == JOptionPane.OK_OPTION){
					System.exit(0);
				}
			}
		});
		menuItem_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		menu.add(menuItem_1);
		
		adminMenu = new JMenu("管理员");
		adminMenu.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		menuBar.add(adminMenu);
		
		JMenuItem menuItem_11 = new JMenuItem("教师信息");
		menuItem_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manageAdmin(e);
			}
		});
		menuItem_11.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		adminMenu.add(menuItem_11);
		
		JMenuItem menuItem_12 = new JMenuItem("添加教师");
		menuItem_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addTeacher();
			}
		});
		menuItem_12.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		adminMenu.add(menuItem_12);
		
		teacherMenu = new JMenu("教师");
		teacherMenu.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		menuBar.add(teacherMenu);
		
		JMenu menu_3 = new JMenu("班级管理");
		menu_3.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		teacherMenu.add(menu_3);
		
		JMenuItem menuItem_2 = new JMenuItem("开设课程");
		menuItem_2.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		menu_3.add(menuItem_2);
		
		JMenuItem menuItem_3 = new JMenuItem("课程详情");
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manageStudent(e);
			}
		});
		menuItem_3.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		menu_3.add(menuItem_3);
		
		JMenuItem menuItem_13 = new JMenuItem("添加学生");
		menuItem_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addStudent(e);
			}
		});
		menuItem_13.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		menu_3.add(menuItem_13);
		
		JMenu menu_4 = new JMenu("成绩管理");
		menu_4.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		teacherMenu.add(menu_4);
		
		JMenuItem menuItem_4 = new JMenuItem("班级成绩");
		menuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				serchClassgrade(e);
			}
		});
		menuItem_4.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		menu_4.add(menuItem_4);
		
		JMenuItem menuItem_5 = new JMenuItem("学生成绩");
		menuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				studentGradeList(e);
			}
		});
		menuItem_5.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		menu_4.add(menuItem_5);
		
		JMenu menu_5 = new JMenu("考试管理");
		menu_5.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		teacherMenu.add(menu_5);
		
		JMenuItem menuItem_6 = new JMenuItem("自动组卷");
		menuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				autoGenerate(e);
			}
		});
		menuItem_6.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		menu_5.add(menuItem_6);
		
		JMenuItem menuItem_7 = new JMenuItem("在线出题");
		menuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onlineGenerate(e);
			}
		});
		menuItem_7.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		menu_5.add(menuItem_7);
		
		JMenuItem menuItem_8 = new JMenuItem("考试目录");
		menuItem_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				examListTeacher(e);
			}
		});
		menuItem_8.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		menu_5.add(menuItem_8);
		
		JMenuItem menuItem_14 = new JMenuItem("阅卷");
		menuItem_14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				needyuejuanList(e);
			}
		});
		menuItem_14.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		menu_5.add(menuItem_14);
		
		studentMenu = new JMenu("学生");
		studentMenu.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		menuBar.add(studentMenu);
		
		JMenuItem menuItem_10 = new JMenuItem("考试目录");
		menuItem_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				examListStudent(e);
			}
		});
		menuItem_10.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		studentMenu.add(menuItem_10);
		
		JMenuItem menuItem_9 = new JMenuItem("成绩查看");
		menuItem_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				studentGradeList(e);
			}
		});
		menuItem_9.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		studentMenu.add(menuItem_9);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		desktopPane = new JDesktopPane();
		desktopPane.setBackground(new Color(240, 255, 255));
		contentPane.add(desktopPane, BorderLayout.CENTER);
		setAuthority();
	}
	
	protected void needyuejuanList(ActionEvent e) {
		// TODO Auto-generated method stub
		needyuejuanListFrm yuejuan=new needyuejuanListFrm();
		yuejuan.setVisible(true);
		desktopPane.add(yuejuan);
	}

	protected void serchClassgrade(ActionEvent e) {
		// TODO Auto-generated method stub
		searchClassGradeFrm serchclassfrade=new searchClassGradeFrm();
		serchclassfrade.setVisible(true);
		desktopPane.add(serchclassfrade);
	}

	protected void studentGradeList(ActionEvent e) {
		// TODO Auto-generated method stub
		searchStudentGradeFrm searchstudentGradeFrm = new searchStudentGradeFrm();
		searchstudentGradeFrm.setVisible(true);
		desktopPane.add(searchstudentGradeFrm);
	}

	protected void examListStudent(ActionEvent e) {
		// TODO Auto-generated method stub
		ExamListFrm examlistfrm=new ExamListFrm();
		examlistfrm.setVisible(true);
		desktopPane.add(examlistfrm);
	}

	protected void examListTeacher(ActionEvent e) {
		// TODO Auto-generated method stub
		ExamListFrm examlistfrm=new ExamListFrm();
		examlistfrm.setVisible(true);
		desktopPane.add(examlistfrm);
	}

	private void setAuthority() {
		// TODO Auto-generated method stub
		if("学生".equals(userType.getName())){
			adminMenu.setEnabled(false);
			teacherMenu.setEnabled(false);
		}
		if("教师".equals(userType.getName())){
			adminMenu.setEnabled(false);
			studentMenu.setEnabled(false);
			
		}
		if("管理员".equals(userType.getName())){
			teacherMenu.setEnabled(false);
			studentMenu.setEnabled(false);
		}
	}

	protected void onlineGenerate(ActionEvent e) {
		// TODO Auto-generated method stub
		OnlineChooseFrm onlineChooseFrm=new OnlineChooseFrm();
		onlineChooseFrm.setVisible(true);
		desktopPane.add(onlineChooseFrm);
	}

	protected void autoGenerate(ActionEvent e) {
		// TODO Auto-generated method stub
		AutoGenerating autogenerating=new AutoGenerating();
		autogenerating.setVisible(true);
		desktopPane.add(autogenerating);
	}

	protected void manageStudent(ActionEvent e) {
		// TODO Auto-generated method stub
		ManageStudentFrm managestudentFrm=new ManageStudentFrm();
		managestudentFrm.setVisible(true);
		desktopPane.add(managestudentFrm);
	}

	protected void addStudent(ActionEvent e) {
		// TODO Auto-generated method stub
		AddStudentFrm addstudentFrm = new AddStudentFrm();
		addstudentFrm.setVisible(true);
		desktopPane.add(addstudentFrm);
	}

	protected void addTeacher() {
		// TODO Auto-generated method stub
		AddTeacherFrm addteacherFrm = new AddTeacherFrm();
		addteacherFrm.setVisible(true);
		desktopPane.add(addteacherFrm);
	}

	protected void manageAdmin(ActionEvent e) {
		// TODO Auto-generated method stub
		ManageAdminFrm manageAdminFrm=new ManageAdminFrm();
		manageAdminFrm.setVisible(true);
		desktopPane.add(manageAdminFrm);
	}

	protected void editPassWord(ActionEvent e) {
		// TODO Auto-generated method stub
		EditPasswordFrm editPasswordFrm = new EditPasswordFrm();
		editPasswordFrm.setVisible(true);
		desktopPane.add(editPasswordFrm);
	}
}
