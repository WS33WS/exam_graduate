package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.StudentDao;
import entity.Classes;
import entity.Student;

import exam.StringUtil;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JPasswordField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class AddStudentFrm extends JInternalFrame {

	private JPanel contentPane;
	private JTextField studentNameField;
	private JPasswordField studentPasswordField;
	private JTextField studentTelField;
	private JTextField studentAddressField;
	private ButtonGroup sexButtonGroup;
	private JRadioButton studentRadioButton_m;
	private JRadioButton studentRadioButton_f;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					AddStudentFrm frame = new AddStudentFrm();
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
	public AddStudentFrm() {
		setFrameIcon(new ImageIcon(AddStudentFrm.class.getResource("/image/学生管理.png")));
		setTitle("学生信息添加");
		setClosable(true);
		setIconifiable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 469, 397);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel label = new JLabel("学生姓名");
		label.setIcon(new ImageIcon(AddStudentFrm.class.getResource("/image/学生管理.png")));
		label.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		studentNameField = new JTextField();
		studentNameField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		studentNameField.setColumns(10);
		
		JLabel label_1 = new JLabel("学生性别");
		label_1.setIcon(new ImageIcon(AddStudentFrm.class.getResource("/image/性别.png")));
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		studentRadioButton_m = new JRadioButton("男");
		studentRadioButton_m.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		studentRadioButton_f = new JRadioButton("女");
		studentRadioButton_f.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		sexButtonGroup = new ButtonGroup();
		sexButtonGroup.add(studentRadioButton_m);
		sexButtonGroup.add(studentRadioButton_f);
		
		JLabel label_2 = new JLabel("学生密码");
		label_2.setIcon(new ImageIcon(AddStudentFrm.class.getResource("/image/password.png")));
		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		studentPasswordField = new JPasswordField();
		studentPasswordField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JLabel label_3 = new JLabel("学生电话");
		label_3.setIcon(new ImageIcon(AddStudentFrm.class.getResource("/image/电话 (2).png")));
		label_3.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		studentTelField = new JTextField();
		studentTelField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		studentTelField.setColumns(10);
		
		JLabel label_4 = new JLabel("学生地址");
		label_4.setIcon(new ImageIcon(AddStudentFrm.class.getResource("/image/地址.png")));
		label_4.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		studentAddressField = new JTextField();
		studentAddressField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		studentAddressField.setColumns(10);
		
		JButton studentInsertButton = new JButton("添加");
		studentInsertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addStudent(e);
			}
		});
		studentInsertButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JButton studentResetButton = new JButton("重置");
		studentResetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetStudentValue(e);
			}
		});
		studentResetButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JLabel label_5 = new JLabel("*必填");
		label_5.setForeground(Color.RED);
		
		JLabel label_6 = new JLabel("*必填");
		label_6.setForeground(Color.RED);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(87)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(label_4)
								.addComponent(label_3)
								.addComponent(label_2)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(label)
									.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(39)
									.addComponent(studentRadioButton_m, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(studentRadioButton_f, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(18)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(studentPasswordField)
										.addComponent(studentNameField)
										.addComponent(studentTelField)
										.addComponent(studentAddressField))
									.addGap(18)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(label_6, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(label_5, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(112)
							.addComponent(studentInsertButton)
							.addGap(45)
							.addComponent(studentResetButton)))
					.addContainerGap(104, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(47)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(studentNameField, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_5))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addComponent(studentRadioButton_m, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
							.addComponent(studentRadioButton_f, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_2)
						.addComponent(studentPasswordField, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_6))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_3)
						.addComponent(studentTelField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_4)
						.addComponent(studentAddressField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(studentInsertButton)
						.addComponent(studentResetButton))
					.addGap(39))
		);
		contentPane.setLayout(gl_contentPane);
	}

	protected void addStudent(ActionEvent e) {
		// TODO Auto-generated method stub
		String studentName = studentNameField.getText().toString();
		String studentPassword = studentPasswordField.getText().toString();
		String studentAdress=studentAddressField.getText().toString();
		String studentTel=studentTelField.getText().toString();
		
		String studentSex=studentRadioButton_m.isSelected()?studentRadioButton_m.getText():studentRadioButton_f.getText();
		System.out.print(studentSex);
		int sex;
		if(studentSex.equals("男")){
			sex=0;
		}else
			sex=1;
		
		System.out.print(sex);
		
		if(StringUtil.isEmpty(studentName)){
			JOptionPane.showMessageDialog(this, "请填写学生姓名!");
			return;
		}
		if(StringUtil.isEmpty(studentPassword)){
			JOptionPane.showMessageDialog(this, "请填写密码!");
			return;
		}
		if(studentPassword.length()<6||studentPassword.length()>12){
			JOptionPane.showMessageDialog(this, "请输入正确密码长度");
			return;
		}
		
		
		
		
		
		StudentDao studentDao = new StudentDao();
		
		
		if(1==studentDao.addNewStudent(studentName,studentTel,sex,studentAdress,studentPassword,null)){
			JOptionPane.showMessageDialog(this, "添加成功!");
		}else{
			JOptionPane.showMessageDialog(this, "添加失败!");
		}
		resetStudentValue(e);
	}

	protected void resetStudentValue(ActionEvent e) {
		// TODO Auto-generated method stub
		studentAddressField.setText("");
		studentTelField.setText("");
		studentNameField.setText("");
		studentPasswordField.setText("");
		sexButtonGroup.clearSelection();
		studentRadioButton_m.setSelected(true);
	}
}
