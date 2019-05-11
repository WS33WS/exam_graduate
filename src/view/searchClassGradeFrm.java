package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import dao.ClassesDao;
import dao.ExamSchDao;
import dao.TeacherDao;
import entity.Classes;
import entity.ExamSch;
import entity.Teacher;
import exam.WorkWithData;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class searchClassGradeFrm extends JInternalFrame {

	private JPanel contentPane;
	private JTable table;
	private JComboBox classComboBox;
	private JLabel classLabel ;
	private JScrollPane scrollPane;
	private JLabel label;
	private Set classset;
	private Iterator studente;
	private Map classmap;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					searchClassGradeFrm frame = new searchClassGradeFrm();
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
	public searchClassGradeFrm() {
		setFocusable(true);
		setTitle("成绩查询");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
//		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 888, 519);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setClosable(true);
		setIconifiable(true);
		
		
		classLabel = new JLabel("班级");
		classLabel.setIcon(new ImageIcon(searchStudentGradeFrm.class.getResource("/image/班级列表.png")));
		classLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		classComboBox = new JComboBox();
		
		JButton button = new JButton("查询");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				serchClassGrade();
			}
		});
		button.setIcon(new ImageIcon(searchStudentGradeFrm.class.getResource("/image/搜索.png")));
		button.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		scrollPane = new JScrollPane();
		
		label = new JLabel();
		label.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(89)
					.addComponent(classLabel)
					.addGap(133)
					.addComponent(classComboBox, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 368, Short.MAX_VALUE)
					.addComponent(button)
					.addGap(41))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGap(23)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 819, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(label)
					.addGap(40))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(6)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(classLabel)
						.addComponent(button)
						.addComponent(classComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(373)
							.addComponent(label))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 416, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(20, Short.MAX_VALUE))
		);
		
		table = new JTable();
		table.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u8BD5\u9898", "\u6210\u7EE9\u60C5\u51B5"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(39);
		table.getColumnModel().getColumn(1).setPreferredWidth(376);
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		
		setTeacherClassesInfo();
			
		
	}

	private void setTeacherClassesInfo() {
		// TODO Auto-generated method stub
		Teacher teacher1 = (Teacher)ExamMainFrm.userObject;
		Teacher teacher=TeacherDao.findById(teacher1.getId());
		List<Classes> allClasses=ClassesDao.findByTeacherId(teacher1.getId());
		classmap=new HashMap();
		for(int i=0;i<allClasses.size();i++){
			classComboBox.addItem(allClasses.get(i).getClassesName());
			classmap.put(allClasses.get(i).getClassesName(), allClasses.get(i).getClassesId());
		}
	}
	

	protected void serchClassGrade() {
		// TODO Auto-generated method stub
		Object stuobj=classComboBox.getSelectedItem();
		
		TableColumn firsetColumn = table.getColumnModel().getColumn(0);
		firsetColumn.setPreferredWidth(70);
		firsetColumn.setMaxWidth(70);
		firsetColumn.setMinWidth(70);
		
		DefaultTableModel dft = (DefaultTableModel) table.getModel();
		dft.setRowCount(0);
		if(classmap.containsKey(stuobj)){
			int classid=(int)classmap.get(stuobj);
			System.out.println(classid);
			List<ExamSch> allexam=ExamSchDao.findByClassesId(classid);
			if(allexam.size()==0){
				JOptionPane.showMessageDialog(this,"没有相关数据");
			}
			
			for(int i=0;i<allexam.size();i++){
				Vector v = new Vector();
				v.add(allexam.get(i).getExamName());
				v.add(WorkWithData.anal(allexam.get(i).getExamId()));
				dft.addRow(v);
				System.out.println(allexam.get(i).getExamName());
				System.out.println(allexam.get(i).getExamId());
				System.out.println(WorkWithData.anal(allexam.get(i).getExamId()));
			}
			
		}
	}

	

}
