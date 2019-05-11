package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import dao.ExamSchDao;
import dao.ExamScoreDao;
import dao.QuestionDao;
import entity.ExamSch;
import entity.Questionselect;
import entity.Questionsystem;
import entity.Student;
import exam.DateUtil;
import view.AdminFrm111.TimeGo;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.EventObject;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.JTextArea;

public class exam extends JFrame {

	private JPanel contentPane;
	private JLabel label;
	private JLabel label_2;
	private ButtonGroup buttongroup;
	private JLabel jlabType ;
	private JLabel jlabTimu ;
	private String endtime;
	private int examid;
	private int selectnum=0;
	private int systemFnum=0;
	private int systemTnum=0;
	private Date endtime_1;
	private Date nowtime;
	private JRadioButton rdbtn1;
	private JTextArea answerArea;
	private JPanel panel;
	private List<String> selectAnswer;
	private List<String> systemAnswerF;
	private List<String> systemAnswerT;
	private List<JTextArea> answerlist;
	private HashMap<String,String> hashmapselect=new HashMap<String,String>();
	
	private long c ;
	private JScrollPane scrollPane;
	/**
	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					exam frame = new exam();
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
	public exam(String str,String endTime,int id) {
		this.endtime=endTime;
		this.examid=id;
		setIconImage(Toolkit.getDefaultToolkit().getImage(examTestFrm.class.getResource("/image/试题.png")));
		setTitle("考试");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 10, 790, 718);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		label = new JLabel();
		label.setIcon(new ImageIcon(examTestFrm.class.getResource("/image/时间 (1).png")));
		label.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JLabel label_1 = new JLabel(str);
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		
		label_2 = new JLabel("距离考试结束还差");
		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JButton button_2 = new JButton();
		if("学生".equals(ExamMainFrm.userType.getName())){
			button_2.setText("提交");
			button_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					submit(e);
				}
			});
		}else if("教师".equals(ExamMainFrm.userType.getName())){
			label_2.setVisible(false);
			label.setVisible(false);
			button_2.setText("确定");
			button_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					submit(e);
				}
			});
		}
		
		button_2.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		scrollPane = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(344)
					.addComponent(label_1)
					.addGap(104)
					.addComponent(label)
					.addContainerGap(196, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(590, Short.MAX_VALUE)
					.addComponent(label_2)
					.addGap(62))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(108)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 571, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(85, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(509, Short.MAX_VALUE)
					.addComponent(button_2, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
					.addGap(177))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(28)
							.addComponent(label))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(label_1)))
					.addGap(8)
					.addComponent(label_2)
					.addGap(41)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 486, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(button_2)
					.addGap(64))
		);
		
		panel = new JPanel();
//		scrollPane.setColumnHeaderView(panel);
		scrollPane.setViewportView(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		contentPane.setLayout(gl_contentPane);
		setExamText();
		
		new TimeGo().start();
		
		while(endtime.equals(DateUtil.getNow())){
			submit();
		}
		
		
	}
	
	

	private void setExamText() {
		// TODO Auto-generated method stub
		//打印出选定考场场次的试卷
				//由场次得到选择和大题题目ID
				//由题目id打印出来
				ExamSch all=ExamSchDao.findByExamId(examid);//得到本场考试的考试信息
				//分解出本次考试题号
				String[] systemT;
				if(!(all.getSystemT()==null)){
					System.out.println("非空");
					systemT = all.getSystemT().split(",");
				}
				else{
					systemT=null;
					System.out.println("空");
				}
				//=========================得到选择题信息
				List<Questionselect> QuesSelect=new ArrayList<Questionselect>();
				if(all.getSelectScore()!=0) {
					String[] select = all.getSelects().split(",");
					
					selectnum=select.length;
					for(int i=0;i<select.length;i++) {
						try {
						    int a = Integer.parseInt(select[i]);
						    Questionselect QueSelect=QuestionDao.findBySelectId(a);
						    QuesSelect.add(QueSelect);
						} catch (NumberFormatException e) {
						    e.printStackTrace();
						}
					}
				}
				else {
					selectnum=0;
				}
				//=========================得到大题信息
				List<Questionsystem> QuesSystem=new ArrayList<Questionsystem>();
				if(all.getSystemScoreF()!=0) {
					String[] systemF = all.getSystemF().split(",");
					systemFnum=systemF.length;
					for(int i=0;i<systemF.length;i++) {
						try {
						    int a = Integer.parseInt(systemF[i]);
						    Questionsystem QueSystem=QuestionDao.findBySystemId(a);
						    QuesSystem.add(QueSystem);
						} catch (NumberFormatException e) {
						    e.printStackTrace();
						}
					}
				}
				else {
					systemFnum=0;
				}
				if(all.getSystemScoreT()!=0) {
					systemTnum=systemT.length;
					System.out.println(systemT.length);
					for(int i=0;i<systemT.length;i++) {
						try {
						    int a = Integer.parseInt(systemT[i]);
						    Questionsystem QueSystem=QuestionDao.findBySystemId(a);
						    QuesSystem.add(QueSystem);
						} catch (NumberFormatException e) {
						    e.printStackTrace();
						}
					}
				}
				else {
					systemTnum=0;
				}
				
				
				//输出
				
				//====================选择题
//				System.out.println("一、选择题");
				jlabType=new JLabel("一、选择题");
				panel.add(jlabType);
				jlabType.setAlignmentX(Component.LEFT_ALIGNMENT);
				
				for(int i=0;i<QuesSelect.size();i++) {
					panel.add(jlabTimu=new JLabel((i+1)+"、"+QuesSelect.get(i).getQuestion()));
					jlabTimu.setAlignmentX(Component.LEFT_ALIGNMENT);
					final int ij=i+1;
//					System.out.println((i+1)+"、"+QuesSelect.get(i).getQuestion());
					String[] answerSelect = QuesSelect.get(i).getAnswer().split("/");
					buttongroup=new ButtonGroup();
					selectAnswer=new ArrayList<String>();
					for(int j=0;j<answerSelect.length;j++) {
						
//						System.out.println((char)(97+j)+"、"+answerSelect[j]);
						panel.add(rdbtn1= new JRadioButton((char)(65+j)+"、"+answerSelect[j]));
						rdbtn1.setAlignmentX(Component.LEFT_ALIGNMENT);
//						if("教师".equals(ExamMainFrm.userType.getName())){
//							rdbtn1.setEnabled(false);
//						}
						rdbtn1.addActionListener(new ActionListener(){
							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
								String selectanswer=((JRadioButton) e.getSource()).getText();
								if(((JRadioButton)e.getSource()).isSelected()){
									System.out.println(selectanswer);
									hashmapselect.put(String.valueOf(ij), selectanswer);
//									selectAnswer.add(selectanswer);
								}	
							}
						});
						buttongroup.add(rdbtn1);
					}
					
				}
				//===================大题
				answerlist=new ArrayList<JTextArea>();
//				System.out.println("二、简答题");
				systemAnswerF=new ArrayList<String>();
				systemAnswerT=new ArrayList<String>();
				panel.add(jlabType=new JLabel("二、简答题"));
				jlabType.setAlignmentX(Component.LEFT_ALIGNMENT);
				int i=0;
				for(i=0;i<QuesSystem.size();i++) {
//					final int ij=i+1;
//					System.out.println((i+1)+"、"+QuesSystem.get(i).getQuestion());
					panel.add(jlabTimu=new JLabel((i+1)+"、"+QuesSystem.get(i).getQuestion()));
					jlabTimu.setAlignmentX(Component.LEFT_ALIGNMENT);
					answerArea=new JTextArea();
//					if("教师".equals(ExamMainFrm.userType.getName())){
//						answerArea.setEditable(false);
//					}
					answerArea.setAlignmentX(Component.LEFT_ALIGNMENT);
					answerlist.add(answerArea);
					
					answerArea.setRows(5);
					
//		
					answerArea.setLineWrap(true);
					answerArea.setWrapStyleWord(true);
					panel.add(answerArea);
				
				}
				
				
				
	}

	protected void addPointEvent(String string) {
		// TODO Auto-generated method stub
		System.out.println(string);
	}
	
	//手动提交
	protected void submit(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println(hashmapselect.size());
		if("学生".equals(ExamMainFrm.userType.getName())){
			if(selectnum==hashmapselect.size()){
				Set<String> keys=hashmapselect.keySet();
				for(String s:keys){
					System.out.println(s+":"+hashmapselect.get(s));
					String selecta[] =hashmapselect.get(s).split("、");
					selectAnswer.add(selecta[0]);
				}
				if(systemTnum==0){
					for(int i=0;i<answerlist.size();i++){
						systemAnswerF.add(answerlist.get(i).getText().toString());
					}
				}else if(systemFnum==0){
					for(int i=0;i<answerlist.size();i++){
						systemAnswerT.add(answerlist.get(i).getText().toString());
					}
				}else{
					for(int i=0;i<answerlist.size();i++){
						if(i<systemFnum){
							systemAnswerF.add(answerlist.get(i).getText().toString());
						}else{
							systemAnswerT.add(answerlist.get(i).getText().toString());
						}
					}
				}
				System.out.println(systemAnswerF.size());
				System.out.println(systemAnswerT.size());
				Student student = (Student)ExamMainFrm.userObject;
				int result=ExamScoreDao.addAnswer2(examid, student.getId(), selectAnswer, systemAnswerF, systemAnswerT);
				if(result==1){
					JOptionPane.showMessageDialog(this, "成功提交考试");
					dispose();
				}else{
					JOptionPane.showMessageDialog(this, "未成功提交");
				}
			}else{
				JOptionPane.showMessageDialog(this, "请全部完成后提交");
//				return;
			}
		}else if("教师".equals(ExamMainFrm.userType.getName())){
			dispose();
		}
		


	}

	//到时间自动提交
	private void submit() {
		// TODO Auto-generated method stub
		System.out.println("提交考试");
		//选择题
		Set<String> keys=hashmapselect.keySet();
		for(String s:keys){
			System.out.println(s+":"+hashmapselect.get(s));
			String selecta[] =hashmapselect.get(s).split("、");
			selectAnswer.add(selecta[0]);
		}
		//分出大题
		if(systemTnum==0){
			for(int i=0;i<answerlist.size();i++){
				systemAnswerF.add(answerlist.get(i).getText().toString());
			}
		}else if(systemFnum==0){
			for(int i=0;i<answerlist.size();i++){
				systemAnswerT.add(answerlist.get(i).getText().toString());
			}
		}else{
			for(int i=0;i<answerlist.size();i++){
				if(i<systemFnum){
					systemAnswerF.add(answerlist.get(i).getText().toString());
				}else{
					systemAnswerT.add(answerlist.get(i).getText().toString());
				}
			}
		}
		Student student = (Student)ExamMainFrm.userObject;
		int result=ExamScoreDao.addAnswer2(examid, student.getId(), selectAnswer, systemAnswerF, systemAnswerT);
		if(result==1){
			JOptionPane.showMessageDialog(this, "成功提交考试");
		}
		
	}

	public exam getContainer() {
		return this;
	}
	
	class TimeGo extends Thread {
		
		public void run() {
			while (true) {
				// getContainer().setTitle(
				// PropertiesUtil.prop.getProperty("MainFrame.title")
				// + "【" + "当前时间:" + sdf.format(new Date()) + "】");
				getContainer().label.setText("当前时间:" + DateUtil.getNow());
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				try {
					nowtime=formatter.parse(DateUtil.getNow());
				} catch (ParseException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				try {
					endtime_1= formatter.parse(endtime);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
//				long c = Math.abs( endtime_1.getTime() - nowtime.getTime()) / (1000 * 1000);
				c = Math.abs( endtime_1.getTime() - nowtime.getTime())/(1000*60) ;

				getContainer().label_2.setText("距离考试结束还有"+c+"分钟");
				
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
