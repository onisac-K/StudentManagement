package com.onisac.login;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import com.onisac.mysql.operator.DBoperator;

public class PageOfStudent {

	public static  void showPageOfStrudent(){
		JFrame frameOfStudent = new JFrame("学生页面");
		JButton query_grade = new JButton("查询成绩");
		JButton query_course = new JButton("课程修改");
		JButton quit = new JButton("退出");
		frameOfStudent.getContentPane().setBackground(Color.WHITE);
		frameOfStudent.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameOfStudent.setResizable(false);
		ImageIcon pic = new ImageIcon("./src/image/SHU.png");
		JLabel label2=new JLabel(pic);  
		label2.setBounds(0,0, pic.getIconWidth(), pic.getIconHeight());
        frameOfStudent.add(label2,"North");
        frameOfStudent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameOfStudent.setSize(775, 400);
        frameOfStudent.setLocation(com.onisac.login.SwingUtil.centreContainer(frameOfStudent.getSize()));
        
        //JLabel a1 = new JLabel("学生详细信息:");
        Container s = frameOfStudent.getContentPane();
        frameOfStudent.setVisible(true);    
        JPanel bottom = new JPanel(new FlowLayout());
        
        query_grade.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				do_query_grade_actionPerformed(e);
			}
			private void do_query_grade_actionPerformed(ActionEvent e) {
				frameOfStudent.dispose();
				ShowquerygradeFrame();
			}
		});
        query_course.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				do_query_course_actionPerformed(e);
			}
			private void do_query_course_actionPerformed(ActionEvent e) {
				frameOfStudent.dispose();
				ShowqueryCourseFrame();
			}
		});
        quit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				do_quit_actionPerformed(e);
			}
			private void do_quit_actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
        bottom.add(query_grade);
        bottom.add(query_course);
        bottom.add(quit);
        s.add(bottom, "South");
        //bottom.add(change_person_inf);
        //bottom.add(change_password);   
	}
	private static void ShowquerygradeFrame() {
		JFrame query_grade = new JFrame("查询成绩");
		query_grade.setResizable(false);
		query_grade.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JButton ok = new JButton("确定");
		//Container a = query_grade.getContentPane();
		String title = new String();
		String name = com.onisac.mysql.operator.DBoperatorOfStudent.getName(com.onisac.login.recent_user.user);
		title = name + "的成绩";
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new FlowLayout());
		titlePanel.add(new JLabel(title));
		//query_grade.setLayout(new FlowLayout());
		query_grade.add(titlePanel,"North");

		JTable table = 
				com.onisac.mysql.operator.DBoperatorOfStudent.query_grade(com.onisac.login.recent_user.user);
		JScrollPane table_pan = new JScrollPane (table);
		query_grade.add(table_pan, "Center");
		//Panel.add(Course);
		//System.out.println(Panel.getWidth() + "&&" + Panel.getHeight());
		query_grade.setSize(380, 250);
		query_grade.setLocation(com.onisac.login.SwingUtil.centreContainer(query_grade.getSize()));
		//query_grade.add(Panel);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		ok.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				do_cancel_actionPerformed(e);
			}
			private void do_cancel_actionPerformed(ActionEvent e) {
				query_grade.dispose();
				showPageOfStrudent();
			}
		});
		
		buttonPanel.add(ok);
		query_grade.add(buttonPanel,"South");
		query_grade.setVisible(true);
	}
	private static void ShowqueryCourseFrame() {
		JFrame query_course = new JFrame("查询课表");
		query_course.setResizable(false);
		query_course.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
		JButton ok = new JButton("选课");
		JButton quit = new JButton("退出");
		JButton cancel = new JButton("退课");
		
		String title = new String();
		String name = com.onisac.mysql.operator.DBoperatorOfStudent.getName(com.onisac.login.recent_user.user);
		title = name + "的课程:";
		query_course.setBackground(Color.WHITE);
		query_course.setSize(350, 500);
		query_course.setLocation(com.onisac.login.SwingUtil.centreContainer(query_course.getSize()));
		query_course.setLayout(null);
		Container s = query_course.getContentPane();
		s.setBackground(Color.WHITE);
		s.setLayout(null);
		JLabel titlep = new JLabel(title);
		titlep.setBounds(0,0,100,20);
		s.add(titlep);
		JTable table  = 
				com.onisac.mysql.operator.DBoperatorOfStudent.query_course(com.onisac.login.recent_user.user);
		JScrollPane table_pan = new JScrollPane (table);
		table_pan.setBounds(0,25,350,100);		
		s.add(table_pan );
		JLabel titlep2 = new JLabel("检索可选课程：");
		titlep2.setBounds(0,130,100,20);
		JLabel cno = new JLabel("课程号");
		JLabel cname = new JLabel("课程名");
		JLabel credit = new JLabel("学分");
		JLabel cdept = new JLabel("院系");
		JLabel tname = new JLabel("教师名");
		cno.setBounds(20,160,50,20);
		cname.setBounds(20,160+25,50,20);
		credit.setBounds(20,160+50,50,20);
		cdept.setBounds(20,160+75,50,20);
		tname.setBounds(20,160+100,50,20);
		s.add(cno);
		s.add(cname);
		s.add(credit);
		s.add(cdept);
		s.add(tname);
		s.add(titlep2);
		JTextField Cno = new JTextField();
		JTextField Cname = new JTextField();
		JTextField Credit = new JTextField();
		JTextField Cdept = new JTextField();
		JTextField Tname = new JTextField();
		Cno.setBounds(80,160,120,20);
		Cname.setBounds(80,160+25,120,20);
		Credit.setBounds(80,160+50,120,20);
		Cdept.setBounds(80,160+75,120,20);
		Tname.setBounds(80,160+100,120,20);
		s.add(Cno);
		s.add(Cname);
		s.add(Credit);
		s.add(Cdept);
		s.add(Tname);
		Cno.setText("null");
		Cname.setText("null");
		Credit.setText("null");
		Cdept.setText("null");
		Tname.setText("null");
		JButton clear = new JButton("清空");
		JButton search = new JButton("检索");
		clear.setBounds(220,160+25,80,20);
		search.setBounds(220,160+75,80,20);
		s.add(clear);
		s.add(search);
		clear.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				do_clear_actionPerformed(e);
			}
			private void do_clear_actionPerformed(ActionEvent e) {
				Cno.setText("");
				Cname.setText("");
				Credit.setText("");
				Cdept.setText("");
				Tname.setText("");
			}
		});
		
		
		JTable table2 = 
				com.onisac.mysql.operator.DBoperatorOfStudent.query_other_course(com.onisac.login.recent_user.user);
		JScrollPane table_pan2 = new JScrollPane (table2);
		table_pan2.setBounds(0,290,350,120);
		s.add(table_pan2);	
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		quit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				do_quit_actionPerformed(e);
			}
			private void do_quit_actionPerformed(ActionEvent e) {
				query_course.dispose();
				showPageOfStrudent();
			}
		});
		search.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				do_search_actionPerformed(e);
			}
			private void do_search_actionPerformed(ActionEvent e) {
				String CNO = Cno.getText();
				String CNAME = Cname.getText();
				String CREDIT = Credit.getText();
				String CDEPT = Cdept.getText();
				String TNAME = Tname.getText();
				DefaultTableModel tableModel = (DefaultTableModel) table2.getModel();
				tableModel.setRowCount(0);
				String[][] answer = com.onisac.mysql.operator.DBoperatorOfStudent.query_other_speacial_course
						(com.onisac.login.recent_user.user,CNO,CNAME,CREDIT,CDEPT,TNAME);
						//com.onisac.mysql.operator.DBoperatorOfStudent.query_other_course(com.onisac.login.recent_user.user);
				for(int i=0;i<answer.length;++i){
					tableModel.addRow(answer[i]);
				}
			}
		});
		ok.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				do_ok_actionPerformed(e);
			}
			private void do_ok_actionPerformed(ActionEvent e) {
				int selectRows=table2.getSelectedRows().length;// 取得用户所选行的行数
				DefaultTableModel tableModel = (DefaultTableModel) table2.getModel();
				if(selectRows!=1){
					JOptionPane.showMessageDialog(null, "未选择课程！");		
				}
				if(selectRows==1){
				  int selectedRowIndex = table2.getSelectedRow(); // 取得用户所选单行 
				  String cno = (String)tableModel.getValueAt(selectedRowIndex, 0);
				  String answer = com.onisac.mysql.operator.DBoperatorOfStudent.select(cno);
					if(answer.equals("ok")){
						JOptionPane.showMessageDialog(null, "选课成功！");		
					}
					else if(answer.equals("twice")){
						JOptionPane.showMessageDialog(null, "你已经选了这门课！");
					}
					else{
						JOptionPane.showMessageDialog(null, "系统未监测到此课程！");
					}
				}
				query_course.dispose();
				ShowqueryCourseFrame();
			}
		});
		cancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				do_cancel_actionPerformed(e);
			}
			private void do_cancel_actionPerformed(ActionEvent e) {
				int selectRows=table.getSelectedRows().length;// 取得用户所选行的行数
				DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
				if(selectRows!=1){
					JOptionPane.showMessageDialog(null, "未选择已有课程！");		
				}
				if(selectRows==1){
				  int selectedRowIndex = table.getSelectedRow(); // 取得用户所选单行 
				  String cno = (String)tableModel.getValueAt(selectedRowIndex, 0);
				  com.onisac.mysql.operator.DBoperatorOfStudent.cancel_course(cno,com.onisac.login.recent_user.user);
				  JOptionPane.showMessageDialog(null, "退课成功！");			
				}
				query_course.dispose();
				ShowqueryCourseFrame();
			}
		});
		//buttonPanel.add(ok);
		//buttonPanel.add(quit);
		ok.setBounds(30,430,80,20);
		cancel.setBounds(30+80+20,430,80,20);
		quit.setBounds(50+80+80+20,430,80,20);
		//buttonPanel.setBounds(0, 340, 50, 100);
		s.add(ok);
		s.add(cancel);
		s.add(quit);
		//s.add(buttonPanel,"South");
		query_course.setVisible(true);
		
	}

}




