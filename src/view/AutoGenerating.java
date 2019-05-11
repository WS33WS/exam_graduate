package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import dao.AdminDao;
import dao.ClassesDao;
import dao.ExamSchDao;
import dao.ExamScoreDao;
import dao.KnowledgeDao;
import dao.SchDao;
import dao.StudentDao;
import dao.TeacherDao;
import entity.Admin;
import entity.Classes;
import entity.ExamSch;
import entity.Knowledge;
import entity.Teacher;
import ga.Paper;
import ga.Test;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class AutoGenerating extends JInternalFrame {

	private JPanel contentPane;
	private JComboBox autoClassesComboBox;
	private JTextField difficultyTextField;
	private String situation;
	private int classid;
	private JTextField fitnessTextField;
	private JTextField chooseNumTextField;
	private JTextField discussNumTextField;
	private JTextField choosePerScoreTextField;
	private JTextField discussPerScoreTextField;
	private JTextField examNameTextField;
	private JPanel classesPointPanel ;
	private JComboBox jcombobox;
	private JCheckBox pointItem;
	private String[] choosePoint;
	private DateChooserJButton startbutton;
	private DateChooserJButton endbutton;
	private double difficultyNum;
	private double difficultyWeight;
	private double fitnessNum;
//	HashSet<String> set=new HashSet<String>();//定义一个集合来存选中的复选框字符串
	ArrayList<String> set=new ArrayList<String>();
	
	List<Integer> selectList=new ArrayList<Integer>();
	List<Integer> systemList=new ArrayList<Integer>();
	
	private int[] points;
	private String[] ab=null;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					AutoGenerating frame = new AutoGenerating();
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
	public AutoGenerating() {
		setTitle("自动组卷");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 669, 531);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		Teacher teacher = (Teacher)ExamMainFrm.userObject;
		setClosable(true);
		setIconifiable(true);
		
		JLabel lblNewLabel = new JLabel("欢迎"+teacher.getName()+"   请选择下列信息进行自动组卷");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JLabel label = new JLabel("考试班级");
		label.setIcon(new ImageIcon(AutoGenerating.class.getResource("/image/班级名称.png")));
		label.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		autoClassesComboBox = new JComboBox();
		autoClassesComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    // 选择的下拉框选项
//                    System.out.println(e.getItem());
//                	classesPointPanel.removeAll();
//                	classesPointPanel.repaint();
                    addPointEvent(e.getItem().toString());
                }
            }
        });
		autoClassesComboBox.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JLabel label_1 = new JLabel("考试范围");
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		classesPointPanel = new JPanel();
		
		JLabel lbla = new JLabel("试题难度");
		lbla.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		difficultyTextField = new JTextField();
		difficultyTextField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		difficultyTextField.setColumns(10);
		
		JLabel label_3 = new JLabel("");
		
		JLabel lblb = new JLabel("难度权重");
		lblb.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		fitnessTextField = new JTextField();
		fitnessTextField.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		fitnessTextField.setColumns(10);
		
		JLabel label_5 = new JLabel("题目数量");
		label_5.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JLabel label_6 = new JLabel("选择题 个数");
		label_6.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JLabel label_7 = new JLabel("论述题 个数");
		label_7.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		chooseNumTextField = new JTextField();
		chooseNumTextField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		chooseNumTextField.setColumns(10);
		
		discussNumTextField = new JTextField();
		discussNumTextField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		discussNumTextField.setColumns(10);
		
		JLabel label_8 = new JLabel("每题分值");
		label_8.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JLabel label_9 = new JLabel("每题分值");
		label_9.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		choosePerScoreTextField = new JTextField();
		choosePerScoreTextField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		choosePerScoreTextField.setColumns(10);
		
		discussPerScoreTextField = new JTextField();
		discussPerScoreTextField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		discussPerScoreTextField.setColumns(10);
		
		JLabel label_11 = new JLabel("试题名称");
		label_11.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		examNameTextField = new JTextField();
		examNameTextField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		examNameTextField.setColumns(10);
		
		JLabel startTimeLabel = new JLabel("开始时间");
		startTimeLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JLabel endTimeLabel = new JLabel("结束时间");
		endTimeLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		startbutton=new DateChooserJButton();
		startbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		endbutton=new DateChooserJButton();
		
		JButton button = new JButton("确认组卷");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
					try {
						try {
							generateTest(e);
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} catch (IllegalAccessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (InvocationTargetException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
			}
		});
		button.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JLabel lblab_1 = new JLabel("*请输入0-1区间内的小数");
		lblab_1.setForeground(Color.RED);
		
		JLabel lblab = new JLabel("*请输入0-1区间内的小数");
		lblab.setForeground(Color.RED);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(32)
							.addComponent(lblNewLabel))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(44)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(label_1)
								.addComponent(label)
								.addComponent(lbla)
								.addComponent(lblb)
								.addComponent(label_5)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(startTimeLabel)
									.addComponent(label_11)))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(classesPointPanel, GroupLayout.PREFERRED_SIZE, 494, GroupLayout.PREFERRED_SIZE)
								.addComponent(examNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(autoClassesComboBox, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(84)
									.addComponent(label_3))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
											.addGroup(gl_contentPane.createSequentialGroup()
												.addComponent(label_6)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(chooseNumTextField, 0, 0, Short.MAX_VALUE))
											.addGroup(gl_contentPane.createSequentialGroup()
												.addComponent(label_7, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(discussNumTextField, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)))
										.addComponent(startbutton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGap(18)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(label_9, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
											.addGap(18)
											.addComponent(discussPerScoreTextField, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(label_8)
											.addGap(18)
											.addComponent(choosePerScoreTextField, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(endTimeLabel)
											.addGap(18)
											.addComponent(endbutton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(fitnessTextField, Alignment.LEADING)
										.addComponent(difficultyTextField, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
									.addGap(18)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(lblab_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblab, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))))))
					.addContainerGap(153, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(533, Short.MAX_VALUE)
					.addComponent(button)
					.addGap(21))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(autoClassesComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(label_1)
						.addComponent(classesPointPanel, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbla)
						.addComponent(difficultyTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_3)
						.addComponent(lblab_1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblb)
						.addComponent(fitnessTextField, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblab, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_5)
						.addComponent(label_6)
						.addComponent(chooseNumTextField, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_8)
						.addComponent(choosePerScoreTextField, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(label_7, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
							.addComponent(discussNumTextField, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(label_9, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
							.addComponent(discussPerScoreTextField, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)))
					.addGap(15)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_11)
						.addComponent(examNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(startTimeLabel)
						.addComponent(endTimeLabel)
						.addComponent(startbutton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(endbutton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(button)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
		
		setStudentClassesInfo();
		
		
		
	}

	protected void generateTest(ActionEvent e) throws IllegalAccessException, InvocationTargetException, ParseException {
		// TODO Auto-generated method stub
		
		String autoClass=autoClassesComboBox.getSelectedItem().toString();
		Teacher teacher1 = (Teacher)ExamMainFrm.userObject;
		Teacher teacher=TeacherDao.findById(teacher1.getId());
		Classes Clas=ClassesDao.findByClassesName(teacher1.getId(),autoClass);
		situation=Clas.getSituation();
		classid=Clas.getClassesId();
		
		System.out.println("***************");
		System.out.println(situation);
		System.out.println(classid);
		
		List<Integer> setnew=new ArrayList<Integer>();
		
		
		if("".equals(difficultyTextField.getText().toString())&&"".equals(fitnessTextField.getText().toString())){
			difficultyNum=0.5;
			fitnessNum=0.5;
		}
//		else if("".equals(fitnessTextField.getText().toString())&&!"".equals(difficultyTextField.getText().toString())){
//			difficultyNum=Double.parseDouble(difficultyTextField.getText().toString());
//			fitnessNum=1-difficultyNum;
//			
//		}else if(!"".equals(fitnessTextField.getText().toString())&&"".equals(difficultyTextField.getText().toString())){
//			fitnessNum=Double.parseDouble(fitnessTextField.getText().toString());
//			difficultyNum=1-fitnessNum;
//		}else if(Double.parseDouble(fitnessTextField.getText().toString())+Double.parseDouble(difficultyTextField.getText().toString())!=1){
//			JOptionPane.showMessageDialog(this,"请输入正确难度值和覆盖率值");
//		}
		else{
			difficultyNum=Double.parseDouble(difficultyTextField.getText().toString());
			fitnessNum=Double.parseDouble(fitnessTextField.getText().toString());
		}
		
		difficultyWeight=1-difficultyNum;
		System.out.println(difficultyNum);
		System.out.println(fitnessNum);

		
		String examName=examNameTextField.getText().toString();
		
		String beginTime=startbutton.getText().toString();
		String endTime=endbutton.getText().toString();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowTime=df.format(new Date()).toString();
		Date sd1=df.parse(beginTime);
		Date sd2=df.parse(endTime);
		System.out.println(sd1.before(sd2));
		System.out.println(sd1.after(sd2));
		
		Integer i=beginTime.compareTo(endTime);
		Integer abc=nowTime.compareTo(beginTime);
		
		if(i>=0||abc>=0){
			JOptionPane.showMessageDialog(this,"请输入正确时间");
		}else if("".equals(examName)){
			JOptionPane.showMessageDialog(this,"请输入试题名称");
		}else if("".equals(chooseNumTextField.getText().toString())||"".equals(choosePerScoreTextField.getText().toString())||"".equals(discussPerScoreTextField.getText().toString())||"".equals(discussPerScoreTextField.getText().toString())){
			JOptionPane.showMessageDialog(this,"请输入试题个数或分数");
		}else if(set.size()<1){
			JOptionPane.showMessageDialog(this,"请选择考试范围");
		}else if(difficultyNum>1||difficultyNum<0||fitnessNum>1||fitnessNum<0){
			JOptionPane.showMessageDialog(this,"请输入正确难度系数");
			fitnessTextField.setText("");
			difficultyTextField.setText("");
		}
		else{
			int chooseNum=Integer.parseInt(chooseNumTextField.getText().toString());
			int choosePerScore=Integer.parseInt(choosePerScoreTextField.getText().toString());
			int discussNum=Integer.parseInt(discussNumTextField.getText().toString());
			int discussPerScore=Integer.parseInt(discussPerScoreTextField.getText().toString());
			int sum=chooseNum*choosePerScore+discussNum*discussPerScore;
			System.out.println(sum);
			System.out.println(set.size());
			
			for(int j=0;j<set.size();j++){
				System.out.println(set.get(j).toString());
				String [] arr = set.get(j).toString().split("\\s+");
				System.out.println(arr[0]);
				setnew.add(Integer.parseInt(arr[0].toString()));
			}
			System.out.println(setnew.size());
			
			int[] EachType={chooseNum,discussNum};
			for(int ab=0;ab<2;ab++){
				System.out.println(EachType[ab]);
			}
			
			Paper endUnit=Test.mymain(situation,sum,setnew,EachType,choosePerScore, discussPerScore,difficultyNum,fitnessNum,0.5);
			
			
			//没有成功加入各个列表 题号
    		for(int c=0;c<endUnit.getProblemList().size();c++) {
    			int myid=endUnit.getProblemList().get(c).ID;
    			int mytype=endUnit.getProblemList().get(c).Type;
    			if(mytype==0) selectList.add(myid);
    			else if(mytype==1)systemList.add(myid);
    		}
    		
//    		int res=JOptionPane.showConfirmDialog(null, "组卷成功，是否发布", "确认发布", JOptionPane.YES_NO_OPTION);
//			if(res==JOptionPane.YES_OPTION){ 
//				System.out.println("选择是后执行的代码");    //点击“是”后执行这个代码块
//    		JOptionPane.showMessageDialog(this,"试题发布成功");
//			}else{
//				System.out.println("选择否后执行的代码");    //点击“否”后执行这个代码块
//				return;
//				} 
			
    		if(selectList.size()<=0||systemList.size()<=0){
    			JOptionPane.showMessageDialog(this, "请重新进行组卷");
				return;
    		}else{
    			int res=JOptionPane.showConfirmDialog(null, "组卷成功，是否发布", "确认发布", JOptionPane.YES_NO_OPTION);
    			if(res==JOptionPane.YES_OPTION){ 
//    				System.out.println("选择是后执行的代码");    //点击“是”后执行这个代码块
    				
    				SchDao.addNewTeacher(classid, examName,sd1,sd2, selectList, systemList, choosePerScore, discussPerScore);
    				
//    				ExamSch find=ExamSchDao.findByExamName(examName, classid);
//    				ExamScoreDao.addNewExamToStu(find.getExamId());
    				
    				System.out.println("**********");
    				System.out.println(classid);
    				Classes classes=ClassesDao.findByClasses(classid);
    				
    				for(int i1=0;i1<classes.getStudent().size();i1++) {
    					System.out.println(classes.getStudent().get(i1).getId());
    					StudentDao.updateState(classes.getStudent().get(i1).getId());
    				}
    				
    				JOptionPane.showMessageDialog(this,"试题发布成功");
    				return;
    				
    				
    			}else{
//    				System.out.println("选择否后执行的代码");    //点击“否”后执行这个代码块
    				return;
    				} 
    		}			
		}		
	}

	protected void addPointEvent(String classesName) {
		// TODO Auto-generated method stub
		Teacher teacher1 = (Teacher)ExamMainFrm.userObject;
		Classes classes=ClassesDao.findByTeacherIdClassesName(teacher1.getId(),autoClassesComboBox.getSelectedItem().toString());
		System.out.println(classes.getSituation());
		
		classesPointPanel.removeAll();
    	classesPointPanel.repaint();
		
		//获得对应的知识点
		List<Knowledge> knowledge=KnowledgeDao.findBySituation(classes.getSituation());
		int a=knowledge.size();
		System.out.println(a);
//		int b;
		
		for(Knowledge k:knowledge){
			System.out.println(k.getPointName());
			pointItem=new JCheckBox(k.getId()+" "+k.getPointName());
			classesPointPanel.add(pointItem);
			
			pointItem.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String selectName=((JCheckBox) e.getSource()).getText();
					if(((JCheckBox)e.getSource()).isSelected()){
						set.add(selectName);
					}
					else{
						set.remove(selectName);//删除取消选中的复选框名字
					}
				}
			});
		}
		
		
		classesPointPanel.revalidate() ;
		classesPointPanel.repaint();
	}

	private void setStudentClassesInfo() {
		// TODO Auto-generated method stub
		Teacher teacher1 = (Teacher)ExamMainFrm.userObject;
		Teacher teacher=TeacherDao.findById(teacher1.getId());
		List<Classes> allClasses=ClassesDao.findByTeacherId(teacher1.getId());
		
		for(int i=0;i<allClasses.size();i++){
			autoClassesComboBox.addItem(allClasses.get(i).getClassesName());
		}
		
	}
}
