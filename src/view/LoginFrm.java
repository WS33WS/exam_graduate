package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.AdminDao;
import dao.QuestionDao;
import dao.StudentDao;
import dao.TeacherDao;
import entity.Admin;
import entity.Student;
import entity.Teacher;
import admin.AdminConsole;
import student.StudentConsole;
import teacher.TeacherConsole;
import entity.UserType;
import exam.StringUtil;

import javax.swing.JTextField;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class LoginFrm extends JFrame {

	private JPanel contentPane;
	private JTextField userIdTextField;
	private JPasswordField passwordField;
	private JComboBox usertypeComboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrm frame = new LoginFrm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginFrm() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginFrm.class.getResource("/image/考试系统.png")));
		setTitle("登录界面");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 693, 430);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel label = new JLabel("在线考试系统登录界面");
		label.setFont(new Font("微软雅黑", Font.BOLD, 20));
		
		JLabel label_1 = new JLabel("用户编号");
		label_1.setIcon(new ImageIcon(LoginFrm.class.getResource("/image/用户名.png")));
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		userIdTextField = new JTextField();
		userIdTextField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		userIdTextField.setColumns(10);
		
		JLabel label_2 = new JLabel("密    码");
		label_2.setIcon(new ImageIcon(LoginFrm.class.getResource("/image/密码.png")));
		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JLabel label_3 = new JLabel("用户类型");
		label_3.setIcon(new ImageIcon(LoginFrm.class.getResource("/image/userType.png")));
		label_3.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JButton loginButton = new JButton("登陆");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				loginAction(ae);
			}
		});
		loginButton.setIcon(new ImageIcon(LoginFrm.class.getResource("/image/登录.png")));
		loginButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JButton resetButton = new JButton("重置");
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				restValue(e);
			}
		});
		resetButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		resetButton.setIcon(new ImageIcon(LoginFrm.class.getResource("/image/重置.png")));
		
		usertypeComboBox = new JComboBox();
		usertypeComboBox.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		usertypeComboBox.setModel(new DefaultComboBoxModel(new UserType[] {UserType.ADMIN, UserType.TEACHER, UserType.STUDENT}));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(245)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
									.addComponent(label_3)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
											.addComponent(label_2)
											.addGroup(gl_contentPane.createSequentialGroup()
												.addGap(42)
												.addComponent(loginButton, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)))
										.addPreferredGap(ComponentPlacement.RELATED)))
								.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(13)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(userIdTextField)
										.addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(88)
									.addComponent(resetButton, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)))
							.addGap(83))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(usertypeComboBox, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
							.addGap(149))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 218, GroupLayout.PREFERRED_SIZE)
							.addGap(150))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(33)
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
					.addGap(29)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(userIdTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_2))
					.addGap(33)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(usertypeComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_3))
					.addGap(40)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(resetButton, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
						.addComponent(loginButton, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(75, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}

	protected void loginAction(ActionEvent ae) {
		// TODO Auto-generated method stub
		String userId1=userIdTextField.getText().toString();
//		int userId=Integer.parseInt(userIdTextField.getText());
		String password=passwordField.getText().toString();
		UserType selectedItem = (UserType)usertypeComboBox.getSelectedItem();
		if(StringUtil.isEmpty(userId1)){
			JOptionPane.showMessageDialog(this, "用户编号不能为空");
			return;
		}
		if(StringUtil.isEmpty(password)){
			JOptionPane.showMessageDialog(this, "密码不能为空");
			return;
		}
		if("管理员".equals(selectedItem.getName())){
			//管理员登陆
			
			
			Admin admin=(Admin)AdminDao.findById(Integer.parseInt(userIdTextField.getText().toString()));
			System.out.println(Integer.parseInt(userIdTextField.getText()));
			if(admin==null){
				JOptionPane.showMessageDialog(this, "该用户不存在");
				return;
			}
			String passRight=admin.getPass();
			if(password.equals(passRight)) {
				JOptionPane.showMessageDialog(this, "欢迎【"+selectedItem.getName()+"】："+admin.getName()+"登录本系统！");
				this.dispose();
	            new ExamMainFrm(selectedItem, admin).setVisible(true);		
				
			}else {
				System.out.print("密码错误");
				JOptionPane.showMessageDialog(this, "密码错误");
			}
			
			
		}else if("教师".equals(selectedItem.getName())){
			//教师登录
			Teacher teacher=(Teacher)TeacherDao.findById(Integer.parseInt(userIdTextField.getText()));
			System.out.println(Integer.parseInt(userIdTextField.getText()));
			if(teacher==null){
				JOptionPane.showMessageDialog(this, "该用户不存在");
				return;
			}
			String passRight=teacher.getPass();
			if(password.equals(passRight)) {
				JOptionPane.showMessageDialog(this, "欢迎【"+selectedItem.getName()+"】："+teacher.getName()+"登录本系统！");
				this.dispose();
	            new ExamMainFrm(selectedItem, teacher).setVisible(true);	
				
			}else {
				System.out.print("密码错误");
				JOptionPane.showMessageDialog(this, "密码错误");
			}
			
		}else{
			//学生登录
			
			Student student=(Student)StudentDao.findById(Integer.parseInt(userIdTextField.getText()));
			if(student==null){
				JOptionPane.showMessageDialog(this, "该用户不存在");
//				restValue();
				return;
			}
			String passRight=student.getPass();
			if(password.equals(passRight)) {
				
				JOptionPane.showMessageDialog(this, "欢迎【"+selectedItem.getName()+"】："+student.getName()+"登录本系统！");
				this.dispose();
				
				new ExamMainFrm(selectedItem, student).setVisible(true);	
				
			}else {
				System.out.print("密码错误");
				JOptionPane.showMessageDialog(this, "密码错误");
			}
		}
	}
	
	protected void restValue() {
		userIdTextField.setText("");
		passwordField.setText("");
		usertypeComboBox.setSelectedIndex(0);
	}

	protected void restValue(ActionEvent e) {
		// TODO Auto-generated method stub
		userIdTextField.setText("");
		passwordField.setText("");
		usertypeComboBox.setSelectedIndex(0);
	}
}
