package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.ClassesDao;
import dao.SchDao;
import dao.StudentDao;
import entity.Classes;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class ExamMessageFrm extends JFrame {

	private JPanel contentPane;
	private JTextField examNameTextField;
	private JTextField selectsScoreTextField;
	private JTextField systemScoreTextField;
	private DateChooserJButton startbutton;
	private DateChooserJButton endbutton;
	private int classid;
	private List<Integer> chooselist;
	private List<Integer> systemlist;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ExamMessageFrm frame = new ExamMessageFrm();
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
	public ExamMessageFrm(List<Integer> choosel,List<Integer> systeml,int i) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ExamMessageFrm.class.getResource("/image/试题.png")));
		setTitle("试题信息");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.classid=i;
		this.chooselist=choosel;
		this.systemlist=systeml;
		setBounds(100, 100, 565, 371);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
//		setClosable(true);
//		setIconifiable(true);
		
		System.out.println(chooselist.size());
		System.out.println(systemlist.size());
		
		JLabel label = new JLabel("试题名称");
		label.setIcon(new ImageIcon(ExamMessageFrm.class.getResource("/image/试题.png")));
		label.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		examNameTextField = new JTextField();
		examNameTextField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		examNameTextField.setColumns(10);
		
		JLabel label_1 = new JLabel("选择题分值");
		label_1.setIcon(new ImageIcon(ExamMessageFrm.class.getResource("/image/积分值.png")));
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JLabel label_2 = new JLabel("程序题分值");
		label_2.setIcon(new ImageIcon(ExamMessageFrm.class.getResource("/image/积分值.png")));
		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		selectsScoreTextField = new JTextField();
		selectsScoreTextField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		selectsScoreTextField.setColumns(10);
		
		systemScoreTextField = new JTextField();
		systemScoreTextField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		systemScoreTextField.setColumns(10);
		
		JLabel label_3 = new JLabel("考试开始时间");
		label_3.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JLabel label_4 = new JLabel("考试结束时间");
		label_4.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JLabel label_5 = new JLabel("*每题分值");
		label_5.setForeground(Color.RED);
		label_5.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		
		JLabel label_6 = new JLabel("*每题分值");
		label_6.setForeground(Color.RED);
		label_6.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		
		JButton button = new JButton("发布试题");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					submit(e);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		startbutton=new DateChooserJButton();
		
		endbutton=new DateChooserJButton();
		

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(92)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(label_4)
								.addComponent(label_3)
								.addComponent(label_2)
								.addComponent(label_1)
								.addComponent(label))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(examNameTextField, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(selectsScoreTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(label_5))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(systemScoreTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(label_6, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE))
								.addComponent(startbutton)
								.addComponent(endbutton))
							.addGap(79))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(216)
							.addComponent(button)))
					.addContainerGap(61, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(36)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(examNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(selectsScoreTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_5))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_2)
						.addComponent(systemScoreTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_6, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_3)
						.addComponent(startbutton))
					.addGap(20)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_4)
						.addComponent(endbutton))
					.addPreferredGap(ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
					.addComponent(button)
					.addGap(29))
		);
		contentPane.setLayout(gl_contentPane);
	}

	protected void submit(ActionEvent e) throws ParseException {
		// TODO Auto-generated method stub
		String beginTime=startbutton.getText().toString();
		String endTime=endbutton.getText().toString();
		
		 int choosescore= Integer.parseInt(selectsScoreTextField.getText().toString());
		 int sysscore=Integer.parseInt(systemScoreTextField.getText().toString());
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date sd1=df.parse(beginTime);
		Date sd2=df.parse(endTime);
		System.out.println(sd1.before(sd2));
		System.out.println(sd1.after(sd2));
		
		SimpleDateFormat dfa = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowTime=dfa.format(new Date()).toString();
		
		Integer i=beginTime.compareTo(endTime);
		Integer ij=nowTime.compareTo(beginTime);

		System.out.println(i);
		
		if(examNameTextField.getText().toString().equals("")){
				JOptionPane.showMessageDialog(this,"请输入试题名称");
		}else if(selectsScoreTextField.getText().toString().equals("")){
			JOptionPane.showMessageDialog(this,"请输入选择题分值");
		}else if(systemScoreTextField.getText().toString().equals("")){
			JOptionPane.showMessageDialog(this,"请输入程序题分值");
		}else if(i>=0||ij>=0){
			JOptionPane.showMessageDialog(this,"请输入正确时间");
		}else{
			
			SchDao.addNewTeacher(classid, examNameTextField.getText().toString(),sd1,sd2, chooselist, systemlist, choosescore, sysscore);
			System.out.println("**********");
			System.out.println(classid);
			Classes classes=ClassesDao.findByClasses(classid);
			
			for(int i1=0;i1<classes.getStudent().size();i1++) {
				System.out.println(classes.getStudent().get(i1).getId());
				StudentDao.updateState(classes.getStudent().get(i1).getId());
			}
			JOptionPane.showMessageDialog(this,"试题发布成功");
			this.dispose();
		}
		
	}
}
