package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import admin.AdminLogin;
import dao.TeacherDao;
import entity.Admin;
import entity.Institution;
import entity.UserType;
import exam.DateUtil;
import net.miginfocom.swing.MigLayout;
//import view.AdminFrm.TimeGo;

public class AdminFrm111 {
	static Admin admin;
	String insiti;

	private JPanel JScrollPane;
	private JTree admintree;
	private JToolBar toolBar;
	private JLabel lblNewLabel;
	private JScrollPane scrollPane_1;
	private JScrollPane scrollPane_2;
	private JButton button_1;
	private JButton button_2;
	private JButton button_3;
	private JButton button;
	private JLabel lblNewLabel_1;
	
	public static UserType userType;
	public static Object userObject;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					AdminFrm111 frame = new AdminFrm111();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
//	
	/**
	 * Create the frame.
	 */
//	public AdminFrm111(UserType userType,Object userObject) 
	public AdminFrm111() {
		this.userType=userType;
//		this.userObject=userObject;
//		setIconImage(Toolkit.getDefaultToolkit().getImage(AdminFrm111.class.getResource("/image/管理员.png")));
//		setTitle("管理员端");
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setExtendedState(JFrame.MAXIMIZED_BOTH);
////		setBounds(0, 0);
//		JScrollPane = new JPanel();
//		JScrollPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		setContentPane(JScrollPane);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setOneTouchExpandable(true);
		System.out.println(userType.getName());
		
		final List<Institution> allInstitution=TeacherDao.findAllInstitution("西南财经大学");
		
		System.out.println(allInstitution.get(0).getInstitution());
		System.out.println(allInstitution.get(0).getCount());
		System.out.println(allInstitution.get(1).getInstitution());
		System.out.println(allInstitution.get(1).getCount());
		System.out.println(allInstitution.size());
	
		
		JScrollPane.setLayout(new MigLayout("", "[1352px]", "[][648.00px][24.00px]"));
		
		scrollPane_1 = new JScrollPane();
		JScrollPane.add(splitPane, "cell 0 1,grow");
		
		admintree = new JTree();
		admintree.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		admintree.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode(admin.getSchool()) {
				{
					DefaultMutableTreeNode node_1;
					for(int i=0;i<allInstitution.size();i++){
						node_1 = new DefaultMutableTreeNode(allInstitution.get(i).getInstitution());
//						for(int j=0;j<allInstitution.get(i).getCount();j++){
//							node_1.add(new DefaultMutableTreeNode(allInstitution.get(i)));
//						}
						add(node_1);
					}
					
//					node_1 = new DefaultMutableTreeNode("colors");
//						node_1.add(new DefaultMutableTreeNode("blue"));
//						node_1.add(new DefaultMutableTreeNode("violet"));
//						node_1.add(new DefaultMutableTreeNode("red"));
//						node_1.add(new DefaultMutableTreeNode("yellow"));
//					add(node_1);
//					node_1 = new DefaultMutableTreeNode("sports");
//						node_1.add(new DefaultMutableTreeNode("basketball"));
//						node_1.add(new DefaultMutableTreeNode("soccer"));
//						node_1.add(new DefaultMutableTreeNode("football"));
//						node_1.add(new DefaultMutableTreeNode("hockey"));
//					add(node_1);
//					node_1 = new DefaultMutableTreeNode("food");
//						node_1.add(new DefaultMutableTreeNode("hot dogs"));
//						node_1.add(new DefaultMutableTreeNode("pizza"));
//						node_1.add(new DefaultMutableTreeNode("ravioli"));
//						node_1.add(new DefaultMutableTreeNode("bananas"));
//					add(node_1);
				}
			}
		));
		splitPane.setLeftComponent(admintree);
		splitPane.setDividerLocation(185);
		
		
		splitPane.setDividerSize(10);
		
//		System.out.println(allInstitution.get(1).getInstitution());
//		System.out.println(allInstitution.get(1).getCount());
//		for(Institution in:allInstitution){
//			System.out.println(in.getInstitution());
//		}
		
		lblNewLabel=new JLabel();
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		toolBar = new JToolBar();
		toolBar.add(lblNewLabel);
		//		jToolBar.setFloatable(false);
				toolBar.setToolTipText("当前时间");
				toolBar.setBorder(BorderFactory.createBevelBorder(1));
				toolBar.add(lblNewLabel, BorderLayout.EAST);
				JScrollPane.add(toolBar, "cell 0 2,grow");
				
				button = new JButton("添加");
				button.setFont(new Font("微软雅黑", Font.PLAIN, 14));
				JScrollPane.add(button, "flowx,cell 0 0");
				
				button_1 = new JButton("删除");
				button_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
				JScrollPane.add(button_1, "cell 0 0");
				
				button_2 = new JButton("修改");
				button_2.setFont(new Font("微软雅黑", Font.PLAIN, 14));
				JScrollPane.add(button_2, "cell 0 0");
				
				button_3 = new JButton("刷新");
				button_3.setFont(new Font("微软雅黑", Font.PLAIN, 14));
				JScrollPane.add(button_3, "cell 0 0");
				
				lblNewLabel_1 = new JLabel("欢迎："+admin.getName());
				lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
				JScrollPane.add(lblNewLabel_1, "cell 0 0");
		new TimeGo().start();

	}

	public AdminFrm111 getContainer() {
		return this;
	}

	// go table time and path
	class TimeGo extends Thread {
		public void run() {
			while (true) {
				// getContainer().setTitle(
				// PropertiesUtil.prop.getProperty("MainFrame.title")
				// + "【" + "当前时间:" + sdf.format(new Date()) + "】");
				getContainer().lblNewLabel.setText("当前时间:" + DateUtil.getNow());
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
