package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.AdminDao;
import dao.StudentDao;
import dao.TeacherDao;

import exam.StringUtil;

import entity.Admin;
import entity.Student;
import entity.Teacher;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class EditPasswordFrm extends JInternalFrame {

	private JPanel contentPane;
	private JPasswordField currentPasswordField;
	private JPasswordField newPasswordField;
	private JPasswordField confirmPasswordField;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					EditPasswordFrm frame = new EditPasswordFrm();
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
	public EditPasswordFrm() {
		setClosable(true);
		setIconifiable(true);
		setFrameIcon(new ImageIcon(EditPasswordFrm.class.getResource("/image/password.png")));
		setTitle("修改密码");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 564, 399);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setClosable(true);
		setIconifiable(true);
		
		JLabel label = new JLabel("当前用户：");
		label.setIcon(new ImageIcon(EditPasswordFrm.class.getResource("/image/用户名.png")));
		label.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JLabel label_1 = new JLabel("原密码：");
		label_1.setIcon(new ImageIcon(EditPasswordFrm.class.getResource("/image/password.png")));
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JLabel label_2 = new JLabel("新密码：");
		label_2.setIcon(new ImageIcon(EditPasswordFrm.class.getResource("/image/修改密码.png")));
		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JLabel label_3 = new JLabel("确认密码：");
		label_3.setIcon(new ImageIcon(EditPasswordFrm.class.getResource("/image/修改密码.png")));
		label_3.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JLabel userLb = new JLabel("");
		userLb.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		currentPasswordField = new JPasswordField();
		currentPasswordField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		newPasswordField = new JPasswordField();
		newPasswordField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		confirmPasswordField = new JPasswordField();
		confirmPasswordField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JButton confirmButton = new JButton("确认修改");
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				submitEdit(e);
			}
		});
		confirmButton.setIcon(new ImageIcon(EditPasswordFrm.class.getResource("/image/确认.png")));
		confirmButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JButton resetButton = new JButton("重置");
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetValue(e);
			}
		});
		resetButton.setIcon(new ImageIcon(EditPasswordFrm.class.getResource("/image/重置.png")));
		resetButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JLabel lblNewLabel = new JLabel("*请输入6--12位密码");
		lblNewLabel.setForeground(Color.RED);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(126)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(label_3)
								.addGap(18)
								.addComponent(confirmPasswordField))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(label)
									.addComponent(label_1)
									.addComponent(label_2))
								.addGap(18)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
									.addComponent(newPasswordField)
									.addComponent(currentPasswordField)
									.addComponent(userLb, GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(confirmButton)
							.addGap(39)
							.addComponent(resetButton)
							.addGap(18)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblNewLabel)
					.addContainerGap(51, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(68)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(userLb, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addGap(31)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(currentPasswordField, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addGap(35)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_2)
						.addComponent(newPasswordField, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel))
					.addGap(30)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_3)
						.addComponent(confirmPasswordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(42)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(confirmButton)
						.addComponent(resetButton))
					.addContainerGap(42, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		
		if("管理员".equals(ExamMainFrm.userType.getName())){
			Admin admin = (Admin)ExamMainFrm.userObject;
			userLb.setText("【管理员】" + admin.getName());
		}else if("学生".equals(ExamMainFrm.userType.getName())){
			Student student = (Student)ExamMainFrm.userObject;
			userLb.setText("【学生】" + student.getName());
		}else{
			Teacher teacher = (Teacher)ExamMainFrm.userObject;
			userLb.setText("【教师】" + teacher.getName());
		}
	}

	protected void submitEdit(ActionEvent e) {
		// TODO Auto-generated method stub
		String oldPassword = currentPasswordField.getText().toString();
		String newPassword = newPasswordField.getText().toString();
		String conformPassword = confirmPasswordField.getText().toString();
		if(StringUtil.isEmpty(oldPassword)){
			JOptionPane.showMessageDialog(this, "请填写旧密码！");
			return;
		}
		if(StringUtil.isEmpty(newPassword)){
			JOptionPane.showMessageDialog(this, "请填写新密码！");
			return;
		}
		if(StringUtil.isEmpty(conformPassword)){
			JOptionPane.showMessageDialog(this, "请确认新密码！");
			return;
		}
		if(!newPassword.equals(conformPassword)){
			JOptionPane.showMessageDialog(this, "两次密码输入不一致！");
			return;
		}if(newPasswordField.getText().toString().length()<6||newPasswordField.getText().toString().length()>12){
			JOptionPane.showMessageDialog(this, "请输入正确密码长度");
			return;
		}
		if("管理员".equals(ExamMainFrm.userType.getName())){
			Admin admin = (Admin)ExamMainFrm.userObject;
			if(oldPassword.equals(admin.getPass())){
				AdminDao.editPass(newPassword, admin.getId());
				JOptionPane.showMessageDialog(this, "修改成功");
			}else{
				JOptionPane.showMessageDialog(this, "请输入正确原密码");
			}
			
			return;
		}
		if("学生".equals(ExamMainFrm.userType.getName())){
			Student student = (Student)ExamMainFrm.userObject;
			if(oldPassword.equals(student.getPass())){
				StudentDao.editPass(newPassword, student.getId());
				JOptionPane.showMessageDialog(this, "修改成功");
			}else{
				JOptionPane.showMessageDialog(this, "请输入正确原密码");
			}
			return;
		}
		if("教师".equals(ExamMainFrm.userType.getName())){
			Teacher teacher = (Teacher)ExamMainFrm.userObject;
			if(oldPassword.equals(teacher.getPass())){
				TeacherDao.editPass(newPassword, teacher.getId());
				JOptionPane.showMessageDialog(this, "修改成功");
			}else{
				JOptionPane.showMessageDialog(this, "请输入正确原密码");
			}
			return;
		}
	}

	protected void resetValue(ActionEvent e) {
		// TODO Auto-generated method stub
			// TODO Auto-generated method stub
			currentPasswordField.setText("");
			newPasswordField.setText("");
			confirmPasswordField.setText("");
		
	}
}
