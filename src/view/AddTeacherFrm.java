package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.TeacherDao;
import exam.StringUtil;


import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class AddTeacherFrm extends JInternalFrame{

	private JPanel contentPane;
	private JTextField teacherNameField;
	private JTextField teacherTelField;
	private JTextField teacherAddressField;
	private JPasswordField teacherPasswordField;
	private JTextField teacherInstiField;
	private ButtonGroup sexButtonGroup;
	private JRadioButton teacherSexradioButton_m;
	private JRadioButton teacherSexradioButton_f;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					AddTeacherFrm frame = new AddTeacherFrm();
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
	public AddTeacherFrm() {
		setTitle("教师信息添加");
		setClosable(true);
		setIconifiable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 427, 392);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel label = new JLabel("教师姓名");
		label.setIcon(new ImageIcon(AddTeacherFrm.class.getResource("/image/老师.png")));
		label.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		teacherNameField = new JTextField();
		teacherNameField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		teacherNameField.setColumns(10);
		
		JLabel label_1 = new JLabel("学生性别");
		label_1.setIcon(new ImageIcon(AddTeacherFrm.class.getResource("/image/性别.png")));
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JLabel lblNewLabel = new JLabel("电话号码");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lblNewLabel.setIcon(new ImageIcon(AddTeacherFrm.class.getResource("/image/电话 (2).png")));
		
		JLabel label_2 = new JLabel("当前地址");
		label_2.setIcon(new ImageIcon(AddTeacherFrm.class.getResource("/image/地址.png")));
		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JLabel label_3 = new JLabel("登录密码");
		label_3.setIcon(new ImageIcon(AddTeacherFrm.class.getResource("/image/password.png")));
		label_3.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JLabel label_4 = new JLabel("所属学院");
		label_4.setIcon(new ImageIcon(AddTeacherFrm.class.getResource("/image/学院 (2).png")));
		label_4.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		teacherTelField = new JTextField();
		teacherTelField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		teacherTelField.setColumns(10);
		
		teacherAddressField = new JTextField();
		teacherAddressField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		teacherAddressField.setColumns(10);
		
		teacherPasswordField = new JPasswordField();
		teacherPasswordField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		teacherInstiField = new JTextField();
		teacherInstiField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		teacherInstiField.setColumns(10);
		
		JButton teaConfirmButton = new JButton("确认");
		teaConfirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addTeacher(e);
			}
		});
		teaConfirmButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JButton TeaResetButton = new JButton("重置");
		TeaResetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetTeacherValue(e);
			}
		});
		TeaResetButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		teacherSexradioButton_m = new JRadioButton("男");
		teacherSexradioButton_m.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		teacherSexradioButton_f = new JRadioButton("女");
		teacherSexradioButton_f.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		sexButtonGroup = new ButtonGroup();
		sexButtonGroup.add(teacherSexradioButton_m);
		sexButtonGroup.add(teacherSexradioButton_f);
		
		JLabel label_5 = new JLabel("*必填");
		label_5.setForeground(Color.RED);
		
		JLabel label_6 = new JLabel("*必填");
		label_6.setForeground(Color.RED);
		
		JLabel label_7 = new JLabel("*必填");
		label_7.setForeground(Color.RED);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(70)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(30)
							.addComponent(teaConfirmButton)
							.addGap(51)
							.addComponent(TeaResetButton))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(label_4)
								.addComponent(label_3)
								.addComponent(label_2)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(lblNewLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(label_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(label, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
							.addGap(34)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(teacherSexradioButton_m)
									.addGap(18)
									.addComponent(teacherSexradioButton_f))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(teacherInstiField)
										.addComponent(teacherPasswordField)
										.addComponent(teacherAddressField)
										.addComponent(teacherTelField)
										.addComponent(teacherNameField, GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(label_5)
										.addComponent(label_6, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
										.addComponent(label_7, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
					.addContainerGap(35, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(39)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(teacherNameField, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_5))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(teacherSexradioButton_m)
						.addComponent(teacherSexradioButton_f))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(teacherTelField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_2)
						.addComponent(teacherAddressField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_3)
						.addComponent(teacherPasswordField, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_6))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_4)
						.addComponent(teacherInstiField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_7))
					.addPreferredGap(ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(teaConfirmButton)
						.addComponent(TeaResetButton))
					.addGap(29))
		);
		contentPane.setLayout(gl_contentPane);
	}

	protected void addTeacher(ActionEvent e) {
		
		// TODO Auto-generated method stub
		String teacherName = teacherNameField.getText().toString();
		String teacherPass = teacherPasswordField.getText().toString();
		String teacherAddress=teacherAddressField.getText().toString();
		String teacherTel=teacherTelField.getText().toString();
		String teacherInstitution=teacherInstiField.getText().toString();
		
		String teacherSex=teacherSexradioButton_m.isSelected()?teacherSexradioButton_m.getText():teacherSexradioButton_f.getText();
		System.out.print(teacherSex);
		int sex;
		if(teacherSex.equals("男")){
			sex=0;
		}else
			sex=1;
		
		System.out.print(sex);
		
		if(StringUtil.isEmpty(teacherName)){
			JOptionPane.showMessageDialog(this, "请填写学生姓名!");
			return;
		}
		if(StringUtil.isEmpty(teacherPass)){
			JOptionPane.showMessageDialog(this, "请填写密码!");
			return;
		}
		if(StringUtil.isEmpty(teacherInstitution)){
			JOptionPane.showMessageDialog(this, "请填写学院");
			return;
		}
		if(teacherPass.length()<6||teacherPass.length()>12){
			JOptionPane.showMessageDialog(this, "请输入正确密码长度");
			return;
		}
		
		
		TeacherDao teacherDao = new TeacherDao();
		
		if(1==teacherDao.addNewTeacher(teacherName,teacherTel,sex, teacherAddress,teacherPass, null,teacherInstitution,"西南财经大学")){
			JOptionPane.showMessageDialog(this, "添加成功!");
		}else{
			JOptionPane.showMessageDialog(this, "添加失败!");
		}
		resetTeacherValue(e);
	}

	protected void resetTeacherValue(ActionEvent e) {
		// TODO Auto-generated method stub
		teacherNameField.setText("");
		teacherTelField.setText("");
		teacherAddressField.setText("");
		teacherInstiField.setText("");
		teacherPasswordField.setText("");
		sexButtonGroup.clearSelection();
		teacherSexradioButton_m.setSelected(true);
	}
}
