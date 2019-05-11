package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.ClassesDao;
import dao.ExamScoreDao;
import dao.SchDao;
import dao.StudentDao;
import dao.TeacherDao;
import entity.Admin;
import entity.Classes;
import entity.ExamSch;
import entity.Examscore;
import entity.Student;
import entity.Teacher;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class ExamListFrm extends JInternalFrame {

	private JPanel contentPane;
	private JTable examListTable;
	private JButton button_1;
	

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ExamListFrm frame = new ExamListFrm();
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
	public ExamListFrm() {
		setTitle("考试安排详情");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 682, 507);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setClosable(true);
		setIconifiable(true);
		
		JLabel label = new JLabel("考试安排详情");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton button = new JButton();
		if("教师".equals(ExamMainFrm.userType.getName())){
			button.setText("查看详情");
		}else if("学生".equals(ExamMainFrm.userType.getName())){
			button.setText("进入考试");
		}
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if("教师".equals(ExamMainFrm.userType.getName())){
					examTeacherDetails(e);
				}else if("学生".equals(ExamMainFrm.userType.getName())){
					examStudentDetails(e);
				}
			}
		});
		button.setFont(new Font("微软雅黑", Font.PLAIN, 14));;
		
		button_1 = new JButton();
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(446, Short.MAX_VALUE)
					.addComponent(button_1)
					.addGap(18)
					.addComponent(button)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(48)
					.addComponent(label)
					.addContainerGap(524, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(45, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 558, GroupLayout.PREFERRED_SIZE)
					.addGap(53))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(4)
					.addComponent(label)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 380, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(button)
						.addComponent(button_1)))
		);
		
		examListTable = new JTable();
		examListTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u8BD5\u9898\u7F16\u53F7", "\u73ED\u7EA7", "\u8BD5\u9898\u540D\u79F0", "\u5F00\u59CB\u65F6\u95F4", "\u7ED3\u675F\u65F6\u95F4", "\u603B\u5206"
			}
		));
		examListTable.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		scrollPane.setViewportView(examListTable);
		contentPane.setLayout(gl_contentPane);
		setTable();
	}

	protected void examStudentDetails(ActionEvent e) {
		// TODO Auto-generated method stub
		//学生对应的查看详情
		int row = examListTable.getSelectedRow();
		button_1.setVisible(false);
		if(row == -1){
			JOptionPane.showMessageDialog(this, "请选中查看的考试信息！");
			return;
		}
		String endTime=examListTable.getValueAt(row, 4).toString();
		String startTime=examListTable.getValueAt(row, 3).toString();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowTime=df.format(new Date()).toString();
		
		Integer i=nowTime.compareTo(endTime);
		Integer abc=startTime.compareTo(nowTime);
		Student student = (Student)ExamMainFrm.userObject;
		Examscore examstu=ExamScoreDao.findByTestsStuId(Integer.parseInt(examListTable.getValueAt(row, 0).toString()), student.getId());
//		System.out.println();
//		if(examstu.getSelect_answer()==null){
//			System.out.println("空的");
//		}else{
//			System.out.println("已完成");
//		}
		if(i>=0){
			JOptionPane.showMessageDialog(this,"该考试已截止");
		}else if(examstu!=null){
			JOptionPane.showMessageDialog(this,"已完成考试");
		}else if(abc>0){
			JOptionPane.showMessageDialog(this,"未到考试时间,请耐心等待");
		}else{
			int n=JOptionPane.showConfirmDialog(null, "可以进行考试，是否开始考试","提示",JOptionPane.YES_NO_OPTION);
			if(n==0){
				System.out.println("可以进入");
				exam exampage=new exam(examListTable.getValueAt(row, 2).toString(),endTime,Integer.parseInt(examListTable.getValueAt(row, 0).toString()));
				exampage.setVisible(true);
			}else{
				return;
			}
			
			
		}
		
	}

	protected void examTeacherDetails(ActionEvent e) {
		// TODO Auto-generated method stub
		//老师对应的查看详情
		button_1.setVisible(true);
		int row = examListTable.getSelectedRow();
		if(row == -1){
			JOptionPane.showMessageDialog(this, "请选中查看的考试信息！");
			return;
		}
		String endTime=examListTable.getValueAt(row, 4).toString();
		String startTime=examListTable.getValueAt(row, 3).toString();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowTime=df.format(new Date()).toString();
		
		exam exampage=new exam(examListTable.getValueAt(row, 2).toString(),endTime,Integer.parseInt(examListTable.getValueAt(row, 0).toString()));
		exampage.setVisible(true);
		
	}

	private void setTable() {
		
		DefaultTableModel dft = (DefaultTableModel) examListTable.getModel();
		dft.setRowCount(0);
		
		if("教师".equals(ExamMainFrm.userType.getName())){
			
			Teacher teacher1 = (Teacher)ExamMainFrm.userObject;
			List<Classes> cla=ClassesDao.findByTeacherId(teacher1.getId());
			for(int j=0;j<cla.size();j++) {
				List<ExamSch> sch=SchDao.findByClassesId(cla.get(j).getClassesId());
				for(ExamSch es:sch){
					Vector v = new Vector();
					Classes clas=ClassesDao.findByClasses( es.getClasses());
					v.add(es.getExamId());
					v.add(clas.getClassesName());
					v.add(es.getExamName());
					v.add(es.getTimeBeg());
					v.add(es.getTimeEnd());
					v.add(es.getSumScore());
					dft.addRow(v);
				}
			}
			button_1.setText("删除考试");
			button_1.setVisible(true);
			button_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					deletExam(e);
				}
			});
		}
		else if("学生".equals(ExamMainFrm.userType.getName())){
			Student student = (Student)ExamMainFrm.userObject;
			
			Student stu=StudentDao.findById(student.getId());//1为学生ID
			List<ExamSch> examStu=new ArrayList<ExamSch>();
			for(int i=0;i<stu.getAllClasses().size();i++) {
				List<ExamSch> sinExamStu=new ArrayList<ExamSch>();
				sinExamStu=SchDao.findByClassesId(stu.getAllClasses().get(i));
				examStu.addAll(sinExamStu);
			}
			button_1.setVisible(false);
			
			for(ExamSch es:examStu){
				Vector v = new Vector();
				Classes clas=ClassesDao.findByClasses( es.getClasses());
				v.add(es.getExamId());
				v.add(clas.getClassesName());
				v.add(es.getExamName());
				v.add(es.getTimeBeg());
				v.add(es.getTimeEnd());
				v.add(es.getSumScore());
				dft.addRow(v);
			}
			

		}
		
		

		
	}

	protected void deletExam(ActionEvent e) {
		// TODO Auto-generated method stub
		int row = examListTable.getSelectedRow();
		if(row == -1){
			JOptionPane.showMessageDialog(this, "请选中要删除的数据！");
			return;
		}
		if(JOptionPane.showConfirmDialog(this, "您确定删除么？") != JOptionPane.OK_OPTION){
			return;
		}
		if(JOptionPane.showConfirmDialog(this, "您确定删除么？")== JOptionPane.OK_OPTION){
			if(JOptionPane.showConfirmDialog(this, "请再次确认是否要删除本次考试？")== JOptionPane.OK_OPTION){
				if(SchDao.deleteExam(Integer.parseInt(examListTable.getValueAt(row, 0).toString()))){
					JOptionPane.showMessageDialog(this, "删除成功！");
				}else{
					JOptionPane.showMessageDialog(this, "删除失败！");
				}
			}
		}
		
		setTable();
	}
}
