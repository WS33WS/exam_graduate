package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.ClassesDao;
import dao.ExamSchDao;
import dao.StudentDao;
import dao.TeacherDao;
import entity.Classes;
import entity.ExamSch;
import entity.Examscore;
import entity.ScoreAddStudent;
import entity.Student;
import entity.Teacher;
import student.StudentExam;
import teacher.TeacherScore;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class searchStudentGradeFrm extends JInternalFrame {

	private JPanel contentPane;
	private JTable table;
	private JComboBox classComboBox;
	private JLabel classLabel ;
	private JLabel studentNameLabel;
	private JTextField studentNameTextField;
	private JScrollPane scrollPane;
	private JButton button_1 ;
	private JLabel label;
	private Map studentmap;
	private Map exammap;
	private Map studentidname;
	private Set studentset;
	private Iterator studente;
	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					searchGradeFrm frame = new searchGradeFrm();
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
	public searchStudentGradeFrm() {
		setClosable(true);
		setFrameIcon(new ImageIcon(searchStudentGradeFrm.class.getResource("/image/成绩.png")));
		setTitle("成绩查询");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
//		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 679, 515);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		
		classLabel = new JLabel("班级");
		classLabel.setIcon(new ImageIcon(searchStudentGradeFrm.class.getResource("/image/班级列表.png")));
		classLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		classLabel.setVisible(false);
		
		classComboBox = new JComboBox();
		classComboBox.setVisible(false);
		
		JButton button = new JButton("查询");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				serchGrade();
			}
		});
		button.setIcon(new ImageIcon(searchStudentGradeFrm.class.getResource("/image/搜索.png")));
		button.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		scrollPane = new JScrollPane();
		
		button_1 = new JButton("查看详情");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchExamPaper(e);
			}
		});
		button_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		studentNameLabel = new JLabel("学生姓名");
		studentNameLabel.setIcon(new ImageIcon(searchStudentGradeFrm.class.getResource("/image/用户名.png")));
		studentNameLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		studentNameLabel.setVisible(false);
		
		studentNameTextField = new JTextField();
		studentNameTextField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		studentNameTextField.setColumns(10);
		studentNameTextField.setVisible(false);
		
		label = new JLabel();
		label.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		label.setVisible(false);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(89)
					.addComponent(classLabel)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(classComboBox, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(studentNameLabel)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(studentNameTextField, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
					.addComponent(button)
					.addGap(41))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(23)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 609, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(21, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(499, Short.MAX_VALUE)
					.addComponent(button_1)
					.addGap(65))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(529, Short.MAX_VALUE)
					.addComponent(label)
					.addGap(40))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(6)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(studentNameLabel)
						.addComponent(studentNameTextField, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(classComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(classLabel)
						.addComponent(button))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 357, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(label)
					.addPreferredGap(ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
					.addComponent(button_1)
					.addContainerGap())
		);
		
		table = new JTable();
		table.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u5B66\u751F\u7F16\u53F7", "\u8BD5\u9898id", "\u603B\u5206", "\u9009\u62E9\u9898\u5F97\u5206", "\u5927\u9898\u5F97\u5206"
			}
		));
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		
		if("学生".equals(ExamMainFrm.userType.getName())){
			classComboBox.setVisible(true);
			classLabel.setVisible(true);
			setStudentClassesInfo();
			
		}
		if("教师".equals(ExamMainFrm.userType.getName())){
			classComboBox.setVisible(true);
			classLabel.setVisible(true);
			studentNameTextField.setVisible(true);
			studentNameLabel.setVisible(true);
			setTeacherClassesInfo();
			
		}

	}

	protected void searchExamPaper(ActionEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		if(row == -1){
			JOptionPane.showMessageDialog(this, "请选中查看的考试信息！");
			return;
		}
//		if("教师".equals(ExamMainFrm.userType.getName())){
//			
//		}else if("学生".equals(ExamMainFrm.userType.getName())){
			String name=table.getValueAt(row, 1).toString();
			System.out.print(name);
			int examid=Integer.parseInt(exammap.get(table.getValueAt(row, 1)).toString());
//			int examid=(int) exammap.get(table.getValueAt(row, 1).toString());
			System.out.print(examid);
			int studentid=Integer.parseInt(String.valueOf(studentidname.get(table.getValueAt(row, 0).toString())));
//			int studentid=Integer.parseInt(table.getValueAt(row, 0).toString());
			
			ExamPaperFrm exampaperdetails=new ExamPaperFrm(name,examid,studentid);
			exampaperdetails.setVisible(true);
//		}
		
	}

	protected void serchGrade() {
		// TODO Auto-generated method stub
		//教师查询
		if("教师".equals(ExamMainFrm.userType.getName())){
			exammap=new HashMap();
			studentidname=new HashMap();
			Teacher teacher = (Teacher)ExamMainFrm.userObject;
			List<ScoreAddStudent> a=TeacherScore.find(teacher.getId(), classComboBox.getSelectedItem().toString(), studentNameTextField.getText().toString());
			if(a.size()>0){
				DefaultTableModel dft = (DefaultTableModel) table.getModel();
				dft.setRowCount(0);
				label.setText("共有"+a.size()+"条数据");
				int ij=0;
				
				for(ScoreAddStudent sastu:a){
					if(sastu.isState()){
						ij+=1;
						Vector v = new Vector();
						v.add(sastu.getStudentName());
						studentidname.put(sastu.getStudentName(), Integer.parseInt(sastu.getStu_id()));
						ExamSch eee=ExamSchDao.findByExamId(Integer.parseInt(sastu.getTest_id()));
						v.add(eee.getExamName());
						exammap.put(eee.getExamName(), sastu.getTest_id());
						v.add(sastu.getScore());
						v.add(sastu.getSelect_score());
						v.add(sastu.getSystemScore());
//						if(sastu.getSystemT_score()==null){
//							v.add(sastu.getSystemF_score());
//						}else{
//							v.add(Integer.parseInt(sastu.getSystemF_score())+Integer.parseInt(sastu.getSystemT_score()));
//						}
						dft.addRow(v);
					}
				}
				if(ij==0){
					JOptionPane.showMessageDialog(this, "请先完成阅卷");
				}
			}else{
				DefaultTableModel dft = (DefaultTableModel) table.getModel();
				dft.setRowCount(0);
				label.setText("共有0条数据");
				JOptionPane.showMessageDialog(this, "未查询到相关信息");
				return;
			}
		}
		
		//学生查询
		if("学生".equals(ExamMainFrm.userType.getName())){
			exammap=new HashMap();
			studentidname=new HashMap();
			Object stuobj=classComboBox.getSelectedItem();
			System.out.println("***"+ExamMainFrm.userType.getIndex());
			int i=0;
			if(studentmap.containsKey(stuobj)){
//				System.out.println(studentmap.get(stuobj));
				int classid=(int) studentmap.get(stuobj);
				List<Examscore> ess=StudentExam.StudentClassExam(classid,ExamMainFrm.userType.getIndex());
				System.out.println(ess);
				int ij=0;
				if(ess.size()>0){
					DefaultTableModel dft = (DefaultTableModel) table.getModel();
					dft.setRowCount(0);
					label.setText("共有"+ess.size()+"条数据");
					System.out.println(ess.size());
					
					
					for(Examscore stuexamscore:ess){
						System.out.print(stuexamscore.isState());
						if(stuexamscore.isState()){
							ij+=1;
							Vector v = new Vector();
							Student sss=StudentDao.findById(stuexamscore.getStu_id());
							v.add(sss.getName());
							studentidname.put(sss.getName(), stuexamscore.getStu_id());
//							v.add(stuexamscore.);
							ExamSch eee=ExamSchDao.findByExamId(stuexamscore.getTest_id());
							v.add(eee.getExamName());
							exammap.put(eee.getExamName(), stuexamscore.getTest_id());
//							v.add(stuexamscore.getClass());
							v.add(stuexamscore.getScore());
							v.add(stuexamscore.getSelect_score());
							v.add(stuexamscore.getSystemScore());
							
							dft.addRow(v);
						}
					
					}
					if(ij==0){
						JOptionPane.showMessageDialog(this, "请等待教师阅卷完成");
					}
					
					
				}else{
					DefaultTableModel dft = (DefaultTableModel) table.getModel();
					dft.setRowCount(0);
					label.setText("共有0条数据");
					JOptionPane.showMessageDialog(this, "未查询到相关信息");
					return;
				}
			}
		}
		
	}

	private void setStudentClassesInfo() {
		// TODO Auto-generated method stub
		Student student1 = (Student)ExamMainFrm.userObject;
		Student student=StudentDao.findById(student1.getId());
//		System.out.println(student.toString());
		studentmap=new HashMap();
		for(int i=0;i<student.getAllClasses().size();i++){
			Classes ca=ClassesDao.findByClasses(student.getAllClasses().get(i));
			studentmap.put(ca.getClassesName(), ca.getClassesId());
		}
		studentset=studentmap.keySet();
		studente=studentset.iterator();
		while(studente.hasNext()){
			classComboBox.addItem(studente.next());
		}
		
	}

	private void setTeacherClassesInfo() {
		// TODO Auto-generated method stub
		Teacher teacher1 = (Teacher)ExamMainFrm.userObject;
		Teacher teacher=TeacherDao.findById(teacher1.getId());
		List<Classes> allClasses=ClassesDao.findByTeacherId(teacher1.getId());
		
		for(int i=0;i<allClasses.size();i++){
			classComboBox.addItem(allClasses.get(i).getClassesName());
		}
	}
	
	
}
