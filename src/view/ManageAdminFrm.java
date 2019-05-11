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
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;

import dao.StudentDao;
import exam.StringUtil;
import view.ExamMainFrm;

import entity.Student;
import entity.Teacher;
import dao.TeacherDao;
import entity.Institution;

import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class ManageAdminFrm extends JInternalFrame {

	private JPanel contentPane;
	private JTextField searchTeacherNameField;
	private static JComboBox searchTeacherInstitutionComboBox;
	private static JComboBox editTeacherInstituionComboBox;
	private JButton searchButton;
	private JTable teacherListTable;
	private JTextField editTeacherNameField;
	private JTextField editTeacherTeleField;
	private JPasswordField editTeacherPasswordField;
	private JLabel label_6 ;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ManageAdminFrm frame = new ManageAdminFrm();
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
	public ManageAdminFrm() {
		setTitle("教师信息管理");
		setFrameIcon(new ImageIcon(ManageAdminFrm.class.getResource("/image/老师.png")));
//		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 10, 900, 660);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setClosable(true);
		setIconifiable(true);
		
		JLabel label = new JLabel("教师姓名");
		label.setIcon(new ImageIcon(ManageAdminFrm.class.getResource("/image/老师.png")));
		label.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		searchTeacherNameField = new JTextField();
		searchTeacherNameField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		searchTeacherNameField.setColumns(10);
		
		JLabel label_1 = new JLabel("所属学院");
		label_1.setIcon(new ImageIcon(ManageAdminFrm.class.getResource("/image/班级名称.png")));
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		searchTeacherInstitutionComboBox = new JComboBox();
		
		searchButton = new JButton("查找");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchTeacher(e);
			}
		});
		searchButton.setIcon(new ImageIcon(ManageAdminFrm.class.getResource("/image/搜索.png")));
		searchButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel label_2 = new JLabel("教师姓名");
		label_2.setIcon(new ImageIcon(ManageAdminFrm.class.getResource("/image/老师.png")));
		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JLabel label_3 = new JLabel("所属学院");
		label_3.setIcon(new ImageIcon(ManageAdminFrm.class.getResource("/image/学院 (2).png")));
		label_3.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		editTeacherNameField = new JTextField();
		editTeacherNameField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		editTeacherNameField.setColumns(10);
		
		JLabel label_4 = new JLabel("教师电话");
		label_4.setIcon(new ImageIcon(ManageAdminFrm.class.getResource("/image/电话 (2).png")));
		label_4.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		editTeacherInstituionComboBox = new JComboBox();
		editTeacherInstituionComboBox.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		List<Institution> allInstitution=TeacherDao.findAllInstitution("西南财经大学");
		
		System.out.println(allInstitution.get(0).getInstitution());
		System.out.println(allInstitution.get(0).getCount());
		System.out.println(allInstitution.get(1).getInstitution());
		System.out.println(allInstitution.get(1).getCount());
		System.out.println(allInstitution.size());
		
		editTeacherTeleField = new JTextField();
		editTeacherTeleField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		editTeacherTeleField.setColumns(10);
		
		editTeacherPasswordField = new JPasswordField();
		editTeacherPasswordField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JLabel label_5 = new JLabel("登录密码");
		label_5.setIcon(new ImageIcon(ManageAdminFrm.class.getResource("/image/password.png")));
		label_5.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JButton editConfirmTeacherButton = new JButton("确认修改");
		editConfirmTeacherButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				submitTeacherEditAct(e);
			}
		});
		editConfirmTeacherButton.setIcon(new ImageIcon(ManageAdminFrm.class.getResource("/image/确认.png")));
		editConfirmTeacherButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JButton deleteTeacherButton = new JButton("删除信息");
		deleteTeacherButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteTeacher(e);
			}
		});
		deleteTeacherButton.setIcon(new ImageIcon(ManageAdminFrm.class.getResource("/image/删除.png")));
		deleteTeacherButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		label_6 = new JLabel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(102)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 631, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(label)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(searchTeacherNameField, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
							.addGap(40)
							.addComponent(label_1)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(searchTeacherInstitutionComboBox, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
							.addGap(48)
							.addComponent(searchButton))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(label_3)
								.addComponent(label_2))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(editTeacherInstituionComboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(editTeacherNameField, GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(label_4)
								.addComponent(label_5))
							.addGap(30)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(editTeacherPasswordField)
								.addComponent(editTeacherTeleField))
							.addPreferredGap(ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(deleteTeacherButton)
								.addComponent(editConfirmTeacherButton))
							.addGap(106)))
					.addContainerGap())
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(668, Short.MAX_VALUE)
					.addComponent(label_6)
					.addGap(152))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(36)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(searchTeacherNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_1)
						.addComponent(searchTeacherInstitutionComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(searchButton))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 375, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(label_6)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_2)
						.addComponent(editTeacherNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(editTeacherTeleField, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_4)
						.addComponent(editConfirmTeacherButton))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_3)
						.addComponent(editTeacherInstituionComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(editTeacherPasswordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_5)
						.addComponent(deleteTeacherButton))
					.addContainerGap(73, Short.MAX_VALUE))
		);
		
		teacherListTable = new JTable();
		teacherListTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u6559\u5E08ID", "\u6559\u5E08\u59D3\u540D", "\u6559\u5E08\u7535\u8BDD", "\u767B\u5F55\u5BC6\u7801", "\u8BFE\u7A0B\u4EE3\u7801", "\u6240\u5C5E\u5B66\u9662", "\u6240\u5C5E\u5B66\u6821"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				true, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		teacherListTable.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		scrollPane.setViewportView(teacherListTable);
		contentPane.setLayout(gl_contentPane);
//		setTable(new Teacher());
		
		setTeacherInstitutionInfo();
	}

	//删除选中的教师信息
	protected void deleteTeacher(ActionEvent e) {
		// TODO Auto-generated method stub
		int row = teacherListTable.getSelectedRow();
		if(row == -1){
			JOptionPane.showMessageDialog(this, "请选中要删除的数据！");
			return;
		}
		if(JOptionPane.showConfirmDialog(this, "您确定删除么？") != JOptionPane.OK_OPTION){
			return;
		}
		if(TeacherDao.deleteTeacher(Integer.parseInt(teacherListTable.getValueAt(row, 0).toString()))){
			JOptionPane.showMessageDialog(this, "删除成功！");
		}else{
			JOptionPane.showMessageDialog(this, "删除失败！");
		}
		setTable(new Teacher());
	}

	//提交修改
	protected void submitTeacherEditAct(ActionEvent e) {
		// TODO Auto-generated method stub
		int row = teacherListTable.getSelectedRow();
		if(row == -1){
			JOptionPane.showMessageDialog(this, "请选中要修改的数据！");
			return;
		}
		String editTeacherName = editTeacherNameField.getText().toString();
		String editTeacherPassword = editTeacherPasswordField.getText().toString();
		String editTeacherTel=editTeacherTeleField.getText().toString();
		String editTeacherInstitution=editTeacherInstituionComboBox.getSelectedItem().toString();
		int a=Integer.parseInt(teacherListTable.getValueAt(row, 0).toString());
		
		if(StringUtil.isEmpty(editTeacherName)){
			JOptionPane.showMessageDialog(this, "请填写教师姓名！");
			return;
		}
		if(StringUtil.isEmpty(editTeacherPassword)){
			JOptionPane.showMessageDialog(this, "请填写密码！");
			return;
		}
		if(StringUtil.isEmpty(editTeacherTel)){
			JOptionPane.showMessageDialog(this, "请填写电话号！");
			return;
		}
		
		if(TeacherDao.update(a,editTeacherName,editTeacherTel,editTeacherInstitution,editTeacherPassword)){
			JOptionPane.showMessageDialog(this, "更新成功！");
		}else{
			JOptionPane.showMessageDialog(this, "更新失败！");
		}
		setTable(new Teacher());
	}

	//查找列表
	protected void searchTeacher(ActionEvent e) {
		// TODO Auto-generated method stub
		Teacher teacher = new Teacher();
		
		teacher.setName(searchTeacherNameField.getText().toString());
		teacher.setInstitution(searchTeacherInstitutionComboBox.getSelectedItem().toString());
		setTable(teacher);
		
	}
	
	//设置表格
	private void setTable(Teacher teacher){
//		if("教师".equals(ExamMainFrm.userType.getName())){
//			Teacher s = (Teacher)ExamMainFrm.userObject;
//			teacher.setName(s.getName());
//		}
		DefaultTableModel dft = (DefaultTableModel) teacherListTable.getModel();
		dft.setRowCount(0);
//		TeacherDao teacherDao = new TeacherDao();
//		List<Teacher> teacherList=TeacherDao.getTeacherList(teacher.getName(),teacher.getInstitution());
		List<Teacher> teacherList=find(teacher.getName(),teacher.getInstitution());
//		for(int i=0;i<teacher.size();i++) {
//			System.out.println(teacher.get(i).getName());
//		}
		System.out.println("-----------");
		System.out.println(teacherList.size());
		System.out.println(teacher.getInstitution());
		for(int i=0;i<teacherList.size();i++) {
			System.out.println(teacherList.get(i).getName());
		}
		if(teacherList.size()<1){
			JOptionPane.showMessageDialog(this, "未查到相关信息");
			return;
		}
//		List<Teacher> teacherList = teacherDao.getTeacherList(teacher);
		for (Teacher t : teacherList) {
			Vector v = new Vector();
			v.add(t.getId());
			v.add(t.getName());
			v.add(t.getTele());
			v.add(t.getPass());
			v.add(t.getClasses());
			v.add(t.getInstitution());
			v.add(t.getSchool());
			dft.addRow(v);
		}
		label_6.setText("共"+teacherList.size()+"条记录");
	}

	//设置学院列表
	public static void setTeacherInstitutionInfo() {
		// TODO Auto-generated method stub
		
		List<Institution> allInstitution=TeacherDao.findAllInstitution("西南财经大学");

		for(int i=0;i<allInstitution.size();i++){
			searchTeacherInstitutionComboBox.addItem(allInstitution.get(i).getInstitution());
			editTeacherInstituionComboBox.addItem(allInstitution.get(i).getInstitution());
		}
	}
	
//	public static void find(String name,String institution) {
//		List<Teacher> teacher=new ArrayList<Teacher>();
//		if(name!=null) {
//			if(institution!=null)teacher=TeacherDao.findNameIn(name,institution);
//			else teacher=TeacherDao.findIn(institution);
//		}
//		else {
//			if(institution!=null)
//				teacher=TeacherDao.findName(name);
//			else 
//				System.out.println("请输入数据");
//		}
//		for(int i=0;i<teacher.size();i++) {
//			System.out.println(teacher.get(i).getName());
//		}
//		
//	}
	
//	public static List<Teacher> find(String name,String institution) {
//		List<Teacher> teacher=new ArrayList<Teacher>();
//		if(!name.isEmpty()) {
//			if(!institution.isEmpty())teacher=TeacherDao.findNameIn(name,institution);
//			else teacher=TeacherDao.findIn(institution);
//		}
//		else {
//			if(!institution.isEmpty())teacher=TeacherDao.findName(name);
//			else {
//				System.out.println("请输入数值");
//			}
//		}
//		if(teacher.size()==0) System.out.println("无记录");
//		return teacher;
//	}
	
	public static List<Teacher> find(String name,String institution) {
		List<Teacher> teacher=new ArrayList<Teacher>();
//		if(name==null)System.out.println(1);
//		if(name.equals(" "))System.out.println("equals");
//		if(name.equals(""))System.out.println("equals2");
//		if(name.isEmpty())System.out.println("isempty");

		if(!name.isEmpty()&&!name.equals(" ")) {
			if(!institution.isEmpty()&&!institution.equals(" "))
				teacher=TeacherDao.findNameIn(name,institution);
			else teacher=TeacherDao.findName(name);
		}
		else {
			if(!institution.isEmpty()&&!institution.equals(" "))
				teacher=TeacherDao.findIn(institution);
			else {
				System.out.println("请输入数值");
			}
		}
		if(teacher.size()==0) System.out.println("无记录");
		return teacher;
	}
	
}
