package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.ClassesDao;
import dao.QuestionDao;
import dao.TeacherDao;
import entity.Classes;
import entity.Questionselect;
import entity.Questionsystem;
import entity.Student;
import entity.Teacher;

import javax.swing.DefaultCellEditor;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.DefaultComboBoxModel;

public class OnlineChooseFrm extends JInternalFrame {

	private JPanel contentPane;
	private JComboBox classesTypeComboBox ;
	private JTable chooseTable;
	private JTable listTable;
	public static Object userObject;
	private JComboBox typeComboBox;
	int classid;
	private JLabel lblNewLabel_1 ;
	
	List<Integer> chooselist=new ArrayList<Integer>();
	List<Integer> systemlist=new ArrayList<Integer>();
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					OnlineChooseFrm frame = new OnlineChooseFrm();
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
	public OnlineChooseFrm() {
		setTitle("在线出题");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 817, 562);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setClosable(true);
		setIconifiable(true);
		
		JLabel label = new JLabel("请选择考试科目");
		label.setIcon(new ImageIcon(OnlineChooseFrm.class.getResource("/image/课程列表.png")));
		label.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		classesTypeComboBox = new JComboBox();
		classesTypeComboBox.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JButton discussButton = new JButton("查询");
		discussButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				serchList(e);
			}
		});
		discussButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton button_2 = new JButton("确定提交");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirm(e);
			}
		});
		button_2.setIcon(new ImageIcon(OnlineChooseFrm.class.getResource("/image/确认.png")));
		button_2.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JButton button = new JButton("保存已选数据");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save(e);
			}
		});
		button.setIcon(new ImageIcon(OnlineChooseFrm.class.getResource("/image/班级列表.png")));
		button.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JLabel lblNewLabel = new JLabel("试题类型");
		lblNewLabel.setIcon(new ImageIcon(OnlineChooseFrm.class.getResource("/image/类型.png")));
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		typeComboBox = new JComboBox();
		typeComboBox.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		typeComboBox.setModel(new DefaultComboBoxModel(new String[] {"选择题", "程序题"}));
		
		lblNewLabel_1 = new JLabel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(103)
					.addComponent(label)
					.addGap(28)
					.addComponent(classesTypeComboBox, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
					.addComponent(lblNewLabel)
					.addGap(18)
					.addComponent(typeComboBox, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
					.addGap(32)
					.addComponent(discussButton)
					.addGap(86))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(61, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 678, GroupLayout.PREFERRED_SIZE)
					.addGap(52))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(430, Short.MAX_VALUE)
					.addComponent(button)
					.addGap(18)
					.addComponent(button_2)
					.addGap(97))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(662, Short.MAX_VALUE)
					.addComponent(lblNewLabel_1)
					.addGap(75))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(46)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(discussButton)
						.addComponent(label)
						.addComponent(classesTypeComboBox, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(typeComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 369, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblNewLabel_1)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(button_2)
						.addComponent(button))
					.addGap(19))
		);
		
		chooseTable = new JTable();
		chooseTable.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		scrollPane.setColumnHeaderView(chooseTable);
		
		listTable = new JTable();
		listTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u662F\u5426\u9009\u62E9", "\u8BD5\u9898\u7F16\u53F7", "\u9898\u76EE", "\u96BE\u5EA6"
			}
		));
		listTable.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		scrollPane.setViewportView(listTable);
		contentPane.setLayout(gl_contentPane);
		
		
		setStudentClassesInfo();
	}
	
	protected void confirm(ActionEvent e) {
		// TODO Auto-generated method stub
		if(chooselist.size()<1){
			JOptionPane.showMessageDialog(this,"请确认选择题");
		}
		if(systemlist.size()<1){
			JOptionPane.showMessageDialog(this,"请确认程序题");
		}
		if(systemlist.size()>0&&chooselist.size()>0){
			ExamMessageFrm addExam = new ExamMessageFrm(chooselist,systemlist,classid);
			addExam.setVisible(true);
		}
		

		
		
//		desktopPane.add(addExam);
		
	}

	protected void save(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println(listTable.getRowCount());
		
		if("选择题".equals(typeComboBox.getSelectedItem().toString())){
			chooselist.clear();
			for(int i=0;i<listTable.getRowCount();i++){
				 if(((Boolean)listTable.getValueAt(i,0)).booleanValue()){ //原来选中
			         	System.out.println(listTable.getValueAt(i, 1).toString());
			         	chooselist.add((Integer) listTable.getValueAt(i, 1));
			           }
			 }
			 JOptionPane.showMessageDialog(this, "已确认"+chooselist.size()+"条"+typeComboBox.getSelectedItem().toString()+"数据");
		}
		else if("程序题".equals(typeComboBox.getSelectedItem().toString())){
			systemlist.clear();
			for(int i=0;i<listTable.getRowCount();i++){
				 if(((Boolean)listTable.getValueAt(i,0)).booleanValue()){ //原来选中
			         	System.out.println(listTable.getValueAt(i, 1).toString());
			         	systemlist.add((Integer) listTable.getValueAt(i, 1));
			           }
			 }
			 JOptionPane.showMessageDialog(this, "已确认"+systemlist.size()+"条"+typeComboBox.getSelectedItem().toString()+"数据");
		}
		
	}

	protected void serchList(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if("程序题".equals(typeComboBox.getSelectedItem().toString())){
			Teacher teacher1 = (Teacher)ExamMainFrm.userObject;
			Classes classd=ClassesDao.findByTeacherIdClassesName(teacher1.getId(), classesTypeComboBox.getSelectedItem().toString());
			List<Questionsystem> AllquestionSystem=QuestionDao.findAllQuestionSystem(classd.getSituation());
			classid=classd.getClassesId();
			System.out.println(AllquestionSystem.size());
			
			DefaultTableModel dft = (DefaultTableModel) listTable.getModel();
			dft.setRowCount(0);
			
			TableColumn tc=listTable.getColumnModel().getColumn(0);
			tc.setCellEditor(listTable.getDefaultEditor(Boolean.class));
			tc.setCellRenderer(listTable.getDefaultRenderer(Boolean.class));
			if(AllquestionSystem.size()<1){
				JOptionPane.showMessageDialog(this, "未查到相关信息");
				return;
			}
			
			for (Questionsystem qs : AllquestionSystem) {
				Vector v = new Vector();
				v.add(new Boolean(false));
				v.add(qs.getQue_id());
				v.add(qs.getQuestion());
				v.add(qs.getL());
//				v.add(qs.getPoints());
				dft.addRow(v);
			}
			lblNewLabel_1.setText("共"+AllquestionSystem.size()+"条记录");
			
		}
		if("选择题".equals(typeComboBox.getSelectedItem().toString())){
			Teacher teacher1 = (Teacher)ExamMainFrm.userObject;
			Classes classd=ClassesDao.findByTeacherIdClassesName(teacher1.getId(), classesTypeComboBox.getSelectedItem().toString());
			List<Questionselect> AllquestionS=QuestionDao.findAllQuestion(classd.getSituation());
			System.out.println(AllquestionS.size());
			
			DefaultTableModel dft = (DefaultTableModel) listTable.getModel();
			dft.setRowCount(0);
			
			
			TableColumn tc=listTable.getColumnModel().getColumn(0);
			tc.setCellEditor(listTable.getDefaultEditor(Boolean.class));
			tc.setCellRenderer(listTable.getDefaultRenderer(Boolean.class));
			if(AllquestionS.size()<1){
				JOptionPane.showMessageDialog(this, "未查到相关信息");
				return;
			}
			
			for (Questionselect qs : AllquestionS) {
				Vector v = new Vector();
				v.add(new Boolean(false));
				v.add(qs.getQue_id());
				v.add(qs.getQuestion());
				v.add(qs.getL());
//				v.add(qs.getPoints());
				dft.addRow(v);
			}
			lblNewLabel_1.setText("共"+AllquestionS.size()+"条记录");
		}
		
		
	}
	

	private void setStudentClassesInfo() {
		// TODO Auto-generated method stub
		Teacher teacher1 = (Teacher)ExamMainFrm.userObject;
		Teacher teacher=TeacherDao.findById(teacher1.getId());
		List<Classes> allClasses=ClassesDao.findByTeacherId(teacher1.getId());
		
		for(int i=0;i<allClasses.size();i++){
			classesTypeComboBox.addItem(allClasses.get(i).getClassesName());
			
		}
		
//		String[] student1 = allClasses.get(0).getStudentId().split(",");
//		Student student2=StudentDao.findById(Integer.parseInt(student1[0]));
//		System.out.println(student2.getName());
		
//		String[] student = AllClasses.get(0).getStudentId().split(",");
//		Student student2=StudentDao.findById(Integer.parseInt(student[0]));
//		System.out.println(student2.getName());
	}
}
