package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.ExamSchDao;
import dao.ExamScoreDao;
import entity.ExamSch;
import entity.Examscore;
import entity.Questionsystem;
import exam.Yuejuan;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class YuejuanFrm extends JFrame {

	private JPanel contentPane;
	private int examid;
	private String examname;
	private String classname;
	private JPanel panel;
	private JLabel examtimu;
	private JLabel answerR;
	private JLabel answerO;
	private int totalnum=0;
	private JScrollPane scrollPane ;
	private JTextField gradetext;
	private int sumnum=0;
	private int studentid;
	private List<JTextField> gradelist;
	private List<Integer> gradestu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					YuejuanFrm frame = new YuejuanFrm(26,"aaa","ziran");
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
	public YuejuanFrm(int id,String name,String classn) {
		
		this.examname=name;
		this.classname=classn;
		setTitle(classname+"班级-考试："+examname+"（手动阅卷）");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 752, 564);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		scrollPane = new JScrollPane();
		
		JButton button = new JButton("下一位");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				next(e);
			}
		});
		button.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JLabel label = new JLabel("请按0-10的范围在每题下方进行打分");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		label.setForeground(Color.red);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(20)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 682, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(24, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(585, Short.MAX_VALUE)
					.addComponent(button)
					.addGap(66))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(242)
					.addComponent(label)
					.addContainerGap(258, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(label)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 427, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
					.addComponent(button)
					.addContainerGap())
		);
		
//		panel = new JPanel();
//		scrollPane.setViewportView(panel);
//		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		contentPane.setLayout(gl_contentPane);
		setText(id);
		
	}

	protected void next(ActionEvent e) {
		// TODO Auto-generated method stub
//		if(sumnum==)
		gradestu=new  ArrayList();
		for(int i=0;i<gradelist.size();i++){
			if("".equals(gradelist.get(i).getText().toString())){
				JOptionPane.showMessageDialog(this, "请输入分值");
			}else{
				int j=Integer.parseInt(gradelist.get(i).getText().toString());
				if(j>=0&&j<=10){
					gradestu.add(j);
				}else{
					JOptionPane.showMessageDialog(this, "请输入正确分值");
					return;
				}
			}
			
		}
		if(gradestu.size()==gradelist.size()){
//			sumnum+=1;
			ExamScoreDao.dafentijiao(studentid, examid, gradestu);
			
			setText(examid);
			
		}
	}

	private void setText(int id) {
		// TODO Auto-generated method stub
		panel = new JPanel();
		this.examid=id;
		scrollPane.setViewportView(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		ExamSch sch=ExamSchDao.findByExamId(examid);
		//根据考试Id找到未打分学生
		
		List<Examscore> hah=Yuejuan.dafenxianshi(examid);
		System.out.println("***"+hah.size());
		if(hah.size()==0){
			JOptionPane.showMessageDialog(this, "已完成本次考试所有阅卷");
//			return;
			dispose();
		}
		
		List<Questionsystem> timu=Yuejuan.systemTxinxi(sch.getSystemT());
		String[] num=hah.get(sumnum).getSystemT_answer().split("/");
		gradelist=new ArrayList<JTextField>();
		this.studentid=hah.get(sumnum).getStu_id();
		for(int i=0;i<timu.size();i++){
			panel.add(examtimu=new JLabel(i+1+"、"+timu.get(i).getQuestion()));
			examtimu.setFont(new Font("微软雅黑", Font.PLAIN, 14));
			examtimu.setAlignmentX(Component.LEFT_ALIGNMENT);
			panel.add(answerR=new JLabel("正确答案："+timu.get(i).getRightAnswer()));
			answerR.setFont(new Font("微软雅黑", Font.PLAIN, 14));
			panel.add(answerO=new JLabel("学生作答："+num[i]));
			answerO.setFont(new Font("微软雅黑", Font.PLAIN, 14));
			panel.add(gradetext=new JTextField());
			
			gradetext.setAlignmentX(Component.RIGHT_ALIGNMENT);
			gradelist.add(gradetext);
		}
		
	}
}
