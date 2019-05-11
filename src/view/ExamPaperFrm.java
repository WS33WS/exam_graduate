package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import dao.ExamSchDao;
import entity.ExamSch;
import entity.LookSin;
import entity.Student;
import exam.Look;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import java.awt.Color;

public class ExamPaperFrm extends JFrame {

	private JPanel contentPane;
	private int examid;
	private int studentid;
	private JLabel jlabType ;
	private JLabel myAnswer;
	private JLabel rightanswer;
	private JLabel analysis;
	private JPanel panel;
	private JLabel jlabTimu ;
	private JRadioButton rdbtn1;
	private ButtonGroup buttongroup;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					String aaa="aaa";
					ExamPaperFrm frame = new ExamPaperFrm(aaa,26,2);
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
	public ExamPaperFrm(String str,int examid,int studentid) {
		this.examid=examid;
		this.studentid=studentid;
		setIconImage(Toolkit.getDefaultToolkit().getImage(ExamPaperFrm.class.getResource("/image/考试系统 (2).png")));
		setTitle("答题详情");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 10, 782, 718);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel(str);
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(294)
							.addComponent(lblNewLabel))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(52)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 640, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(72, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 609, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		contentPane.setLayout(gl_contentPane);
		setText();
		
	}

	private void setText() {
		// TODO Auto-generated method stub
		
		LookSin a=Look.Look(examid, studentid);
		panel.add(jlabType=new JLabel("一、选择题"));
		jlabType.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		jlabType.setAlignmentX(Component.LEFT_ALIGNMENT);
		for(int i=0;i<a.getSelect().size();i++){
			panel.add(jlabTimu=new JLabel((i+1)+"、"+a.getSelect().get(i).getContent().getQuestion()));
			String[] answerSelect =a.getSelect().get(i).getContent().getAnswer().split("/");
			buttongroup=new ButtonGroup();
			for(int j=0;j<answerSelect.length;j++) {
				
//				System.out.println((char)(97+j)+"、"+answerSelect[j]);
				panel.add(rdbtn1= new JRadioButton((char)(65+j)+"、"+answerSelect[j]));
				
//				rdbtn1.isSelected();
				System.out.println(String.valueOf((char)(65+j)));
				if(a.getSelect().get(i).getMy().equals(String.valueOf((char)(65+j)))){
					rdbtn1.setSelected(true);
				}
				rdbtn1.setAlignmentX(Component.LEFT_ALIGNMENT);
				rdbtn1.setEnabled(false);
				buttongroup.add(rdbtn1);
				
			}
			panel.add(rightanswer=new JLabel("正确选项"+":"+a.getSelect().get(i).getContent().getRightAnswer()));
			panel.add(analysis=new JLabel(a.getSelect().get(i).getContent().getAnalysis()));
			
		}
		
		panel.add(jlabType=new JLabel("二、简答题"));
		jlabType.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		jlabType.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		for(int i=0;i<a.getSystem().size();i++){
			jlabTimu=new JLabel((i+1)+"、"+a.getSystem().get(i).getContent().getQuestion()+"(得分："+a.getSystem().get(i).getScore()+")");
			panel.add(jlabTimu);
			jlabTimu.setFont(new Font("微软雅黑", Font.PLAIN, 14));
			panel.add(myAnswer=new JLabel("作答数据："+a.getSystem().get(i).getMy()));
			myAnswer.setFont(new Font("微软雅黑", Font.PLAIN, 14));
			panel.add(rightanswer=new JLabel("正确答案："+a.getSystem().get(i).getContent().getRightAnswer()));
			rightanswer.setFont(new Font("微软雅黑", Font.PLAIN, 14));
			panel.add(analysis=new JLabel("解析："+a.getSystem().get(i).getContent().getAnalysis()));
			analysis.setFont(new Font("微软雅黑", Font.PLAIN, 14));
			analysis.setForeground(Color.orange);
		}

	}
}
