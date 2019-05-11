package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.ClassesDao;
import dao.StudentDao;
import dao.TeacherDao;
import entity.Classes;
import entity.Student;
import entity.Teacher;
import exam.StringUtil;

import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class ManageStudentFrm extends JInternalFrame {

	private JPanel contentPane;
	private JTextField studentNameField;
	private JTable studentListTable;
	private JPasswordField editStudentPasswordField;
	private JComboBox classesComboBox;
	private JLabel lblNewLabel_1; 

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ManageStudentFrm frame = new ManageStudentFrm();
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
	public ManageStudentFrm() {
		setFrameIcon(new ImageIcon(ManageStudentFrm.class.getResource("/image/学生管理.png")));
		setTitle("学生信息");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 10, 900, 641);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setClosable(true);
		setIconifiable(true);
		
		JLabel label = new JLabel("学生姓名");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		studentNameField = new JTextField();
		studentNameField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		studentNameField.setColumns(10);
		
		JLabel label_1 = new JLabel("所属班级");
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		classesComboBox = new JComboBox();
		classesComboBox.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JButton searchStudentButton = new JButton("查找");
		searchStudentButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchStudentList(e);
			}
		});
		searchStudentButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JButton classListButton = new JButton("班级列表");
		classListButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		classListButton.setVisible(false);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblNewLabel = new JLabel("登录密码");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		editStudentPasswordField = new JPasswordField();
		
		JButton editConfirmButton = new JButton("确认修改");
		editConfirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmEditStudent(e);
			}
		});
		editConfirmButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JButton deleteStudentButton = new JButton("删除信息");
		deleteStudentButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delectStudent(e);
			}
		});
		deleteStudentButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		lblNewLabel_1 = new JLabel("");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(74)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 694, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(label, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(studentNameField, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
									.addGap(35)
									.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
									.addGap(10)
									.addComponent(classesComboBox, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
									.addGap(40)
									.addComponent(searchStudentButton, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(classListButton))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(162)
							.addComponent(lblNewLabel)
							.addGap(18)
							.addComponent(editStudentPasswordField, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
							.addGap(89)
							.addComponent(editConfirmButton)
							.addGap(46)
							.addComponent(deleteStudentButton)))
					.addContainerGap(106, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(685, Short.MAX_VALUE)
					.addComponent(lblNewLabel_1)
					.addGap(135))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(36)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
							.addComponent(studentNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(classesComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(searchStudentButton, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
							.addComponent(classListButton)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 432, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblNewLabel_1)
					.addGap(15)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(editStudentPasswordField, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(editConfirmButton)
						.addComponent(deleteStudentButton))
					.addContainerGap(29, Short.MAX_VALUE))
		);
		
		studentListTable = new JTable();
		studentListTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u5B66\u751FID", "\u5B66\u751F\u59D3\u540D", "\u5B66\u751F\u7535\u8BDD", "\u767B\u5F55\u5BC6\u7801",  "\u8003\u8BD5\u72B6\u6001"
			}
		));
		studentListTable.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		scrollPane.setViewportView(studentListTable);
		contentPane.setLayout(gl_contentPane);
		setClosable(true);
		setIconifiable(true);
		
		setStudentClassesInfo();
		
	}

	protected void searchStudentList(ActionEvent e) {
		// TODO Auto-generated method stub
		Student student = new Student();
		
		student.setName(studentNameField.getText().toString());
		student.setClasses(classesComboBox.getSelectedItem().toString());
		
		setTable(student);
	}

	private void setStudentClassesInfo() {
		// TODO Auto-generated method stub
		Teacher teacher1 = (Teacher)ExamMainFrm.userObject;
		Teacher teacher=TeacherDao.findById(teacher1.getId());
		List<Classes> allClasses=ClassesDao.findByTeacherId(teacher1.getId());
		if(allClasses.size()<1){
			JOptionPane.showMessageDialog(this, "没有开班信息");
			return;
		}
		
		for(int i=0;i<allClasses.size();i++){
			classesComboBox.addItem(allClasses.get(i).getClassesName());
		}
		
//		String[] student1 = allClasses.get(0).getStudentId().split(",");
//		Student student2=StudentDao.findById(Integer.parseInt(student1[0]));
//		System.out.println(student2.getName());
		
//		String[] student = AllClasses.get(0).getStudentId().split(",");
//		Student student2=StudentDao.findById(Integer.parseInt(student[0]));
//		System.out.println(student2.getName());
	}

	protected void delectStudent(ActionEvent e) {
		// TODO Auto-generated method stub
		int row = studentListTable.getSelectedRow();
		if(row == -1){
			JOptionPane.showMessageDialog(this, "请选中要删除的数据！");
			return;
		}
		if(JOptionPane.showConfirmDialog(this, "您确定删除么？") != JOptionPane.OK_OPTION){
			return;
		}
		if(StudentDao.deleteStudent(Integer.parseInt(studentListTable.getValueAt(row, 0).toString()))){
			JOptionPane.showMessageDialog(this, "删除成功！");
		}else{
			JOptionPane.showMessageDialog(this, "删除失败！");
		}
		setTable(new Student());
	}

	protected void confirmEditStudent(ActionEvent e) {
		// TODO Auto-generated method stub
		int row = studentListTable.getSelectedRow();
		if(row == -1){
			JOptionPane.showMessageDialog(this, "请选中要修改的数据！");
			return;
		}
		String editStudentPassword = editStudentPasswordField.getText().toString();
		int a=Integer.parseInt(studentListTable.getValueAt(row, 0).toString());
		
		if(StringUtil.isEmpty(editStudentPassword)){
			JOptionPane.showMessageDialog(this, "请填写密码！");
			return;
		}
		if(editStudentPassword.length()<6||editStudentPassword.length()>12){
			JOptionPane.showMessageDialog(this, "请确认新密码长度！");
			return;
		}
		
		if(StudentDao.update(a,editStudentPassword)){
			JOptionPane.showMessageDialog(this, "更新成功！");
		}else{
			JOptionPane.showMessageDialog(this, "更新失败！");
		}
		setTable(new Student());
	}
	
	private void setTable(Student student){
		DefaultTableModel dft = (DefaultTableModel) studentListTable.getModel();
		dft.setRowCount(0);
		
//		System.out.println("-------------------------");
//		System.out.println(student.getClasses());
//		System.out.println("-------------------------");
		
		Teacher teacher1 = (Teacher)ExamMainFrm.userObject;
		Teacher teacher=TeacherDao.findById(teacher1.getId());
		List<Classes> allClasses=ClassesDao.findByTeacherId(teacher.getId());
		System.out.println(allClasses.size());
		
		String[] student1 = allClasses.get(0).getAllStudent().split(",");
//		System.out.println("-------------------------");
//		for(int ib=0;ib<student1.length;ib++){
//			System.out.println(student1[ib]);
//		}
//		
		Student student2=StudentDao.findById(Integer.parseInt(student1[0]));
//		System.out.println(student2.getName());
//		System.out.println(student.getClasses());
		
		List<Student> results=match(studentNameField.getText().toString(), teacher1.getId(),student.getClasses());
//		System.out.println(results.size());
		if(results.size()<1){
			JOptionPane.showMessageDialog(this, "未查到相关信息");
			return;
		}
//		
//		for(int i=0;i<results.size();i++) {
//			System.out.println(results.get(i).getName());
//			System.out.println(results.get(i).getClasses());
//			System.out.println(results.get(i).getExam_state());
//		}
		for (Student s : results) {
			Vector v = new Vector();
			v.add(s.getId());
			v.add(s.getName());
			v.add(s.getTele());
			v.add(s.getPass());
//			v.add(s.getClasses());
			v.add(s.getExam_state());
			dft.addRow(v);
		}
		lblNewLabel_1.setText("共"+results.size()+"条记录");
	}
	
	public static List<Student> match(String name,int id,String className){
		List<Student> results=new ArrayList<Student>();
		Classes AllClasses=ClassesDao.findByTeacherIdClassesName(id, className);
		//选定课程，学生的模糊查找：获得对应老师的学生列表
		for(int i=0;i<AllClasses.getStudent().size();i++) {
			Student student;
			if(AllClasses.getStudent().get(i).getName().contains(name)) {
				student=new Student();
				student=AllClasses.getStudent().get(i);
		        results.add(student);
			}
		}
		return results;
	}
}
