package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import exam.DateUtil;
import view.AdminFrm111.TimeGo;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Toolkit;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JRadioButton;

public class examTestFrm extends JFrame {

	private JPanel contentPane;
	private JLabel label;
	private JLabel label_2;
	private JLabel lblNewLabel;
	private ButtonGroup buttongroup;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					examTestFrm frame = new examTestFrm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public examTestFrm() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(examTestFrm.class.getResource("/image/试题.png")));
		setTitle("考试");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 737, 487);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		label = new JLabel();
		label.setIcon(new ImageIcon(examTestFrm.class.getResource("/image/时间 (1).png")));
		label.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JLabel label_1 = new JLabel("数学3.26");
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		
		label_2 = new JLabel("距离考试结束还差");
		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JButton beforeButton = new JButton("上一题");
		beforeButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		beforeButton.setEnabled(false);
		
		JButton button_1 = new JButton("下一题");
		button_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JButton button_2 = new JButton("提交");
		button_2.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JPanel select_panel = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(162)
							.addComponent(beforeButton)
							.addGap(107)
							.addComponent(button_1)
							.addGap(62)
							.addComponent(button_2))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(249)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(label_1)
									.addGap(130)
									.addComponent(label)
									.addGap(49))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(label_2)
									.addGap(37))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(71)
							.addComponent(select_panel, GroupLayout.PREFERRED_SIZE, 564, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(76, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(28)
							.addComponent(label)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(label_2))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(label_1)))
					.addGap(18)
					.addComponent(select_panel, GroupLayout.PREFERRED_SIZE, 234, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(button_2)
						.addComponent(button_1)
						.addComponent(beforeButton))
					.addGap(23))
		);
		
		lblNewLabel = new JLabel("（单选题）1.蓝蓝的一片云啊");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JRadioButton a_rdbtnNewRadioButton = new JRadioButton("慢慢的走过来");
		a_rdbtnNewRadioButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JRadioButton b_rdbtnNewRadioButton = new JRadioButton("慢慢的飘过来");
		b_rdbtnNewRadioButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JRadioButton c_rdbtnNewRadioButton = new JRadioButton("慢慢的走下来");
		c_rdbtnNewRadioButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		buttongroup=new ButtonGroup();
		
		buttongroup.add(a_rdbtnNewRadioButton);
		buttongroup.add(b_rdbtnNewRadioButton);
		buttongroup.add(c_rdbtnNewRadioButton);
		
		GroupLayout gl_select_panel = new GroupLayout(select_panel);
		gl_select_panel.setHorizontalGroup(
			gl_select_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_select_panel.createSequentialGroup()
					.addGroup(gl_select_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_select_panel.createSequentialGroup()
							.addGap(148)
							.addGroup(gl_select_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(c_rdbtnNewRadioButton)
								.addComponent(a_rdbtnNewRadioButton)
								.addComponent(b_rdbtnNewRadioButton)))
						.addGroup(gl_select_panel.createSequentialGroup()
							.addGap(35)
							.addComponent(lblNewLabel)))
					.addContainerGap(237, Short.MAX_VALUE))
		);
		gl_select_panel.setVerticalGroup(
			gl_select_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_select_panel.createSequentialGroup()
					.addGap(25)
					.addComponent(lblNewLabel)
					.addGap(18)
					.addComponent(a_rdbtnNewRadioButton)
					.addGap(18)
					.addComponent(b_rdbtnNewRadioButton)
					.addGap(18)
					.addComponent(c_rdbtnNewRadioButton)
					.addContainerGap(55, Short.MAX_VALUE))
		);
		select_panel.setLayout(gl_select_panel);
		contentPane.setLayout(gl_contentPane);
		
		
		
		new TimeGo().start();
		
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String nowTime=df.format(new Date()).toString();
	}
	
	public examTestFrm getContainer() {
		return this;
	}
	
	class TimeGo extends Thread {
		public void run() {
			while (true) {
				// getContainer().setTitle(
				// PropertiesUtil.prop.getProperty("MainFrame.title")
				// + "【" + "当前时间:" + sdf.format(new Date()) + "】");
				getContainer().label.setText("当前时间:" + DateUtil.getNow());
				getContainer().label_2.setText("距离考试结束还有60分钟");
				
//				long c = Math.abs( d.getTime() - d2.getTime()) / (1000 * 1000);//计算相隔多久
				
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
