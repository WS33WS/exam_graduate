package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import view.EditPasswordFrm;

import entity.Teacher;
import entity.UserType;

import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JList;
import javax.swing.JToolBar;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JDesktopPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TeacherFrm extends JFrame {
	static Teacher teacher;
	int classesId;
	private JDesktopPane desktopPane;
	public UserType userType;
	public static Object userObject;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TeacherFrm frame = new TeacherFrm();
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
	public TeacherFrm(Teacher teh) {
//		this.userType=userType;
		teacher=teh;
		setIconImage(Toolkit.getDefaultToolkit().getImage(TeacherFrm.class.getResource("/image/老师.png")));
		setTitle("教师端");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 737, 442);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("系统设置");
		menu.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		menuBar.add(menu);
		
		JMenuItem menuItem = new JMenuItem("修改密码");
		menuItem.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editPassWord(e);
			}
		});
		menu.add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("退出系统");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(TeacherFrm.this, "确定退出么？") == JOptionPane.OK_OPTION){
					System.exit(0);
			}
			}
		});
		menuItem_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		menu.add(menuItem_1);
		
		JMenu menu_1 = new JMenu("学生管理");
		menu_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		menuBar.add(menu_1);
		
		JMenuItem menuItem_2 = new JMenuItem("学生添加");
		menuItem_2.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		menu_1.add(menuItem_2);
		
		JMenuItem menuItem_3 = new JMenuItem("学生列表");
		menuItem_3.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		menu_1.add(menuItem_3);
		
		JMenu menu_2 = new JMenu("班级管理");
		menu_2.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		menuBar.add(menu_2);
		
		JMenuItem menuItem_4 = new JMenuItem("班级添加");
		menuItem_4.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		menu_2.add(menuItem_4);
		
		JMenuItem menuItem_5 = new JMenuItem("班级列表");
		menuItem_5.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		menu_2.add(menuItem_5);
		
		JMenu menu_3 = new JMenu("考试管理");
		menu_3.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		menuBar.add(menu_3);
		
		JMenuItem menuItem_6 = new JMenuItem("自动组卷");
		menuItem_6.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		menu_3.add(menuItem_6);
		
		JMenuItem menuItem_7 = new JMenuItem("在线出题");
		menuItem_7.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		menu_3.add(menuItem_7);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGap(0, 636, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGap(0, 462, Short.MAX_VALUE)
		);
		contentPane.setLayout(gl_contentPane);
	}


	protected void editPassWord(ActionEvent e) {
		// TODO Auto-generated method stub
		EditPasswordFrm editPasswordFrm = new EditPasswordFrm();
		editPasswordFrm.setVisible(true);
//		desktopPane.add(editPasswordFrm);
	}
}
