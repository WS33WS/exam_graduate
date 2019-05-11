package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entity.Student;
import entity.UserType;

import java.awt.Toolkit;

public class StudentFrm extends JFrame {
	Student student;
	private JPanel contentPane;
	public static UserType userType;
	public static Object userObject;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					StudentFrm frame = new StudentFrm();
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
	public StudentFrm(Student stu) {
		student=stu;
//		this.userType=userType;
//		this.userObject=userObject;
		setIconImage(Toolkit.getDefaultToolkit().getImage(StudentFrm.class.getResource("/image/学生.png")));
		setTitle("学生端");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 589, 394);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
