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
import java.text.ParseException;
import java.util.List;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.ClassesDao;
import entity.Classes;
import entity.ExamSch;
import entity.Teacher;
import exam.Yuejuan;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class needyuejuanListFrm extends JInternalFrame  {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					needyuejuanListFrm frame = new needyuejuanListFrm();
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
	public needyuejuanListFrm() {
		setTitle("需要阅卷考试列表");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 731, 508);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setClosable(true);
		setIconifiable(true);
		
		JLabel lblNewLabel = new JLabel("需要阅卷的试题列表");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton button = new JButton("进行阅卷");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				yuejuan(e);
			}
		});
		button.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(28)
							.addComponent(lblNewLabel))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 687, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(572, Short.MAX_VALUE)
					.addComponent(button)
					.addGap(44))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 383, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(button)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u8BD5\u9898id", "\u73ED\u7EA7", "\u8BD5\u9898\u540D\u79F0", "\u7ED3\u675F\u65F6\u95F4", "\u603B\u5206"
			}
		));
		table.getColumnModel().getColumn(0).setMinWidth(60);
		table.getColumnModel().getColumn(0).setMaxWidth(60);
		table.getColumnModel().getColumn(1).setMinWidth(30);
		table.getColumnModel().getColumn(1).setMaxWidth(75);
		table.getColumnModel().getColumn(2).setMinWidth(50);
		table.getColumnModel().getColumn(2).setMaxWidth(150);
		table.getColumnModel().getColumn(4).setMinWidth(75);
		table.getColumnModel().getColumn(4).setMaxWidth(75);
		table.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		setTable();
		
	}

	protected void yuejuan(ActionEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		if(row == -1){
			JOptionPane.showMessageDialog(this, "请选中查看的考试信息！");
			return;
		}
		
		int yuejuanexamid=Integer.parseInt(table.getValueAt(row, 0).toString());
		String name=table.getValueAt(row, 2).toString();
		String classn=table.getValueAt(row, 1).toString();
		YuejuanFrm yuejuan=new YuejuanFrm(yuejuanexamid,name,classn);
		yuejuan.setVisible(true);
	}

	private void setTable() {
		// TODO Auto-generated method stub
		Teacher teacher1 = (Teacher)ExamMainFrm.userObject;
		try {
			List<ExamSch> yuejuan=Yuejuan.Yuejuan(teacher1.getId());
			
			DefaultTableModel dft = (DefaultTableModel) table.getModel();
			dft.setRowCount(0);
			
			for(ExamSch es:yuejuan){
				Vector v = new Vector();
				v.add(es.getExamId());
				Classes clas=ClassesDao.findByClasses( es.getClasses());
				v.add(clas.getClassesName());
				v.add(es.getExamName());
				v.add(es.getTimeEnd());
				v.add(es.getSumScore());
				dft.addRow(v);
			}
			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
