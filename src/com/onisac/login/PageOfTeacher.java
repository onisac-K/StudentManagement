package com.onisac.login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class PageOfTeacher {
	public static  void showPageOfTeacher(){
		JFrame frameOfStudent = new JFrame("管理页面");
		JButton inf_change = new JButton("学生信息管理");
		JButton grade_manage= new JButton("学生成绩管理");
		JButton grade_spread= new JButton("成绩分布");
		JButton quit = new JButton("退出");
		frameOfStudent.getContentPane().setBackground(Color.WHITE);
		frameOfStudent.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		ImageIcon pic = new ImageIcon("./src/image/manager.png");
		JLabel label2=new JLabel(pic);  
		label2.setBounds(0,0, pic.getIconWidth(), pic.getIconHeight());
        frameOfStudent.add(label2,"North");
        frameOfStudent.setSize(775, 400);
        frameOfStudent.setResizable(false);
        //System.out.println(bg.getIconWidth()+ " " + bg.getIconHeight());
        frameOfStudent.setLocation(com.onisac.login.SwingUtil.centreContainer(frameOfStudent.getSize()));
        frameOfStudent.setVisible(true);    
        Container s = frameOfStudent.getContentPane();
        JPanel bottom = new JPanel(new FlowLayout());
        
        inf_change.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				do_inf_change_actionPerformed(e);
			}
			private void do_inf_change_actionPerformed(ActionEvent e) {
				frameOfStudent.dispose();
				ShowinfchangeFrame();
			}
			
		});
        grade_manage.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				do_grade_manage_actionPerformed(e);
			}
			private void do_grade_manage_actionPerformed(ActionEvent e) {
				frameOfStudent.dispose();
				ShowgrademanageFrame();
			}
		});
        grade_spread.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				do_grade_spread_actionPerformed(e);
			}
			private void do_grade_spread_actionPerformed(ActionEvent e) {
				frameOfStudent.dispose();
				ShowgradespeadFrame();
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
        bottom.add(inf_change);
        bottom.add(grade_manage);
        bottom.add(grade_spread);
        bottom.add(quit);
        s.add(bottom, "South");
        //bottom.add(change_person_inf);
        //bottom.add(change_password);   
	}
	private static void ShowgradespeadFrame() {
		JFrame gradespead = new JFrame();
		CategoryDataset dataset = getDataSet(); 
		
	    JFreeChart chart = ChartFactory.createBarChart3D( 
	              "成绩分布", // 图表标题 
	              "平均成绩", // 目录轴的显示标签 
	              "分数", // 数值轴的显示标签 
	              dataset, // 数据集 
	              PlotOrientation.VERTICAL, // 图表方向：水平、垂直 
	              true,      // 是否显示图例(对于简单的柱状图必须是false) 
	              false,     // 是否生成工具 
	              false      // 是否生成URL链接 
	              ); 
	    //从这里开始 
	    CategoryPlot plot=chart.getCategoryPlot();//获取图表区域对象 
	    CategoryAxis domainAxis=plot.getDomainAxis();     //水平底部列表 
	    domainAxis.setLabelFont(new Font("黑体",Font.BOLD,14));     //水平底部标题 
	    domainAxis.setTickLabelFont(new Font("宋体",Font.BOLD,12)); //垂直标题 
	    ValueAxis rangeAxis=plot.getRangeAxis();//获取柱状 
	    rangeAxis.setLabelFont(new Font("黑体",Font.BOLD,15)); 
	    chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15)); 
	    chart.getTitle().setFont(new Font("宋体",Font.BOLD,20));//设置标题字体 
	    ChartPanel frame1 = new ChartPanel(chart,true);
	    
	    gradespead.setLayout(new BorderLayout());
	    gradespead.setVisible(true);
	    gradespead.add(frame1,"North");
	    gradespead.setSize(800, 500);
	    gradespead.setLocation(com.onisac.login.SwingUtil.centreContainer(gradespead.getSize()));
	    gradespead.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    JButton quit = new JButton("退出");

	    quit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				do_cancel1_actionPerformed(e);
			}
			private void do_cancel1_actionPerformed(ActionEvent e) {
				gradespead.dispose();
				showPageOfTeacher();
			}
		});
	    gradespead.add(quit,"South");
	}
	private static CategoryDataset getDataSet() { 
	      DefaultCategoryDataset dataset = new DefaultCategoryDataset(); 
	      String[][] answer = com.onisac.mysql.operator.DBoperatorOfManager.average();
	      for(int i=0;i<answer.length;++i){
	    	  dataset.addValue(Float.valueOf(answer[i][1]), "考试平均成绩", answer[i][0]); 
	    	  dataset.addValue(Float.valueOf(answer[i][2]), "平时平均成绩", answer[i][0]); 
	    	  dataset.addValue(Float.valueOf(answer[i][3]), "总评平均成绩", answer[i][0]); 
	    	  
	      }
	      return dataset; 
	} 
	private static void ShowinfchangeFrame() {
		JFrame infchange = new JFrame("学生信息管理");
		infchange.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		infchange.setResizable(false);
		
		JButton New = new JButton("新增学生信息");
		JButton change = new JButton("修改学生信息");
		JButton clear = new JButton("清空");
		JButton quit = new JButton("退出");
		
		JTextField sno = new JTextField();
		JTextField sname = new JTextField();
		JTextField age = new JTextField();
		JTextField sex = new JTextField();
		JTextField sdept = new JTextField();
		
		//Container a = query_grade.getContentPane();
		
		infchange.setSize(350, 500);
		infchange.setLocation(com.onisac.login.SwingUtil.centreContainer(infchange.getSize()));
		infchange.setLayout(null);
		JLabel title = new JLabel("学生信息");
		//query_grade.setLayout(new FlowLayout());
		title.setBounds(0,0,100,20);
		infchange.add(title);
		JTable table  = com.onisac.mysql.operator.DBoperatorOfManager.query_student();
		
		JScrollPane table_pan = new JScrollPane (table);
		table_pan.setBounds(0,25,350,200);
		infchange.add(table_pan);	
		JLabel a1 = new JLabel("学  号:");
		a1.setBounds(20,250,50,20);	
		JLabel a2 = new JLabel("性  名:");
		a2.setBounds(20,290,50,20);
		JLabel a3 = new JLabel("年  龄:");
		a3.setBounds(20,330,60,20);
		JLabel a4 = new JLabel("姓  别:");
		a4.setBounds(20,370,50,20);
		JLabel a5 = new JLabel("专  业:");
		a5.setBounds(20,410,50,20);
		infchange.add(a1);
		infchange.add(a2);
		infchange.add(a3);
		infchange.add(a4);
		infchange.add(a5);
		sno.setBounds(80,250,120,20);
		sname.setBounds(80,290,120,20);
		age.setBounds(80,330,120,20);
		sex.setBounds(80,370,120,20);
		sdept.setBounds(80,410,120,20);
		infchange.add(sno);
		infchange.add(sname);
		infchange.add(age);
		infchange.add(sex);
		infchange.add(sdept);
	    table.addMouseListener(new MouseListener(){
	    	public void mousePressed(MouseEvent e) {
	    		int selectRows=table.getSelectedRows().length;// 取得用户所选行的行数
				DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
				if(selectRows==1){
				  int selectedRowIndex = table.getSelectedRow(); // 取得用户所选单行 
				  String Sno = (String)tableModel.getValueAt(selectedRowIndex, 0);
				  String Sname = (String)tableModel.getValueAt(selectedRowIndex, 1);
				  String Age = (String)tableModel.getValueAt(selectedRowIndex, 2);
				  String Sex = (String)tableModel.getValueAt(selectedRowIndex, 3);
				  String Sdept = (String)tableModel.getValueAt(selectedRowIndex, 4);
				  sno.setText(Sno);
				  sname.setText(Sname);
				  age.setText(Age);
				  sex.setText(Sex);
				  sdept.setText(Sdept);
				}
	    	}
			public void mouseClicked(MouseEvent arg0) {
				// TODO 自动生成的方法存根
				
			}	
			public void mouseEntered(MouseEvent arg0) {
				// TODO 自动生成的方法存根
				
			}		
			public void mouseExited(MouseEvent arg0) {
				// TODO 自动生成的方法存根
				
			}		
			public void mouseReleased(MouseEvent arg0) {
				// TODO 自动生成的方法存根
				
			}
	    });
		New.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				do_New_actionPerformed(e);
			}
			private void do_New_actionPerformed(ActionEvent e) {
				String Sno = new String(sno.getText());
				String Sname = new String(sname.getText());
				String Age = new String(age.getText());
				String Sex = new String(sex.getText());
				String Sdept = new String(sdept.getText());	
				if(sno.getText().isEmpty() || Sname.isEmpty() || Age.isEmpty()
						|| Sex.isEmpty() || Sdept.isEmpty()){
					JOptionPane.showMessageDialog(null, "栏目不得为空！");	
				}
				else if(com.onisac.mysql.operator.DBoperatorOfManager.exitOfStudent(Sno)){
					JOptionPane.showMessageDialog(null, "该学号已存在无法新增！");	
				}
				else {
					com.onisac.mysql.operator.DBoperatorOfManager.insert_inf_student(Sno,Sname,Integer.valueOf(Age),Sex,Sdept);
					JOptionPane.showMessageDialog(null, "新增成功！");	
				}
				sno.setText(null);
				sname.setText(null);
				age.setText(null);
				sex.setText(null);
				sdept.setText(null);
				infchange.dispose();
				showPageOfTeacher();
			}
		});
		quit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				do_cancel1_actionPerformed(e);
			}
			private void do_cancel1_actionPerformed(ActionEvent e) {
				infchange.dispose();
				showPageOfTeacher();
			}
		});
		clear.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				do_clear_actionPerformed(e);
			}
			private void do_clear_actionPerformed(ActionEvent e) {
				  sno.setText("");
				  sname.setText("");
				  age.setText("");
				  sex.setText("");
				  sdept.setText("");
			}
		});
		change.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				do_change_actionPerformed(e);
			}
			private void do_change_actionPerformed(ActionEvent e) {
				String Sno = new String(sno.getText());
				String Sname = new String(sname.getText());
				String Age = new String(age.getText());
				String Sex = new String(sex.getText());
				String Sdept = new String(sdept.getText());	
				if(sno.getText().isEmpty() || Sname.isEmpty() || Age.isEmpty()
						|| Sex.isEmpty() || Sdept.isEmpty()){
					JOptionPane.showMessageDialog(null, "栏目不得为空！");	
				}
				else if(com.onisac.mysql.operator.DBoperatorOfManager.exitOfStudent(Sno)){
					com.onisac.mysql.operator.DBoperatorOfManager.update_inf_student(Sno,Sname,Integer.valueOf(Age),Sex,Sdept);
					JOptionPane.showMessageDialog(null, "修改成功！");	
				}
				else{
					JOptionPane.showMessageDialog(null, "未检测到该学生无法修改！");	
				}
				sno.setText(null);
				sname.setText(null);
				age.setText(null);
				sex.setText(null);
				sdept.setText(null);
				infchange.dispose();
				ShowinfchangeFrame();
			}
		});
		clear.setBounds(220,270,120,20);
		New.setBounds(220,310,120,20);
		change.setBounds(220,350,120,20);
		quit.setBounds(220,390,120,20);
		infchange.add(clear);
		infchange.add(New);
		infchange.add(change);
		infchange.add(quit);
		infchange.setVisible(true);			
	}
	private static void ShowgrademanageFrame() {
		JFrame infchange = new JFrame("学生成绩管理");
		infchange.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		infchange.setResizable(false);
		JButton change = new JButton("修改学生成绩");
		JButton clear = new JButton("清空");
		JButton quit = new JButton("退出");
		
		JTextField sno = new JTextField();
		JTextField sname = new JTextField();
		JTextField cno = new JTextField();
		JTextField grade = new JTextField();
		JTextField egrade = new JTextField();
		JTextField pgrade = new JTextField();
		infchange.setSize(380, 550);
		infchange.setLocation(com.onisac.login.SwingUtil.centreContainer(infchange.getSize()));
		infchange.setLayout(null);
		JLabel title = new JLabel("学生成绩信息");
		//query_grade.setLayout(new FlowLayout());
		title.setBounds(0,0,100,20);
		infchange.add(title);
		JTable table  = com.onisac.mysql.operator.DBoperatorOfManager.query_gradeOfstudent();
		
		JScrollPane table_pan = new JScrollPane (table);
		table_pan.setBounds(0,25,380,200);
		infchange.add(table_pan);	
		JLabel a1 = new JLabel("学  号:");
		a1.setBounds(20,250,50,20);	
		JLabel a5 = new JLabel("姓  名:");
		a5.setBounds(20,290,50,20);
		JLabel a2 = new JLabel("课  号:");
		a2.setBounds(20,330,50,20);
		JLabel a3 = new JLabel("考试成绩:");
		a3.setBounds(20,370,60,20);
		JLabel a4 = new JLabel("平时成绩:");
		a4.setBounds(20,410,60,20);
		JLabel a6 = new JLabel("总评成绩:");
		a6.setBounds(20,450,60,20);
		infchange.add(a1);
		infchange.add(a2);
		infchange.add(a3);
		infchange.add(a4);
		infchange.add(a5);
		infchange.add(a6);
		sno.setBounds(80,250,120,20);
		sname.setBounds(80,290,120,20);
		cno.setBounds(80,330,120,20);
		grade.setBounds(80,370,120,20);
		pgrade.setBounds(80,410,120,20);
		egrade.setBounds(80,450,120,20);
		infchange.add(sno);
		infchange.add(sname);
		infchange.add(cno);
		infchange.add(grade);
		infchange.add(pgrade);
		infchange.add(egrade);
		
	    table.addMouseListener(new MouseListener(){
	    	public void mousePressed(MouseEvent e) {
	    		int selectRows=table.getSelectedRows().length;// 取得用户所选行的行数
				DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
				if(selectRows==1){
					 int selectedRowIndex = table.getSelectedRow(); // 取得用户所选单行 
					 String Sno = (String)tableModel.getValueAt(selectedRowIndex, 0);
					 String Sname = (String)tableModel.getValueAt(selectedRowIndex, 1);
					 String Cno = (String)tableModel.getValueAt(selectedRowIndex, 2);
					 String Grade = (String)tableModel.getValueAt(selectedRowIndex, 3);
					 String Pgrade = (String)tableModel.getValueAt(selectedRowIndex, 4);
					 String Egrade = (String)tableModel.getValueAt(selectedRowIndex, 5);
					 sno.setText(Sno);
					 sname.setText(Sname);
					 cno.setText(Cno);
					 grade.setText(Grade);
					 pgrade.setText(Pgrade);
					 egrade.setText(Egrade);
				}
	    	}
			public void mouseClicked(MouseEvent arg0) {
				// TODO 自动生成的方法存根
				
			}	
			public void mouseEntered(MouseEvent arg0) {
				// TODO 自动生成的方法存根
				
			}		
			public void mouseExited(MouseEvent arg0) {
				// TODO 自动生成的方法存根
				
			}		
			public void mouseReleased(MouseEvent arg0) {
				// TODO 自动生成的方法存根
				
			}
	    });
		
		quit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				do_cancel1_actionPerformed(e);
			}
			private void do_cancel1_actionPerformed(ActionEvent e) {
				infchange.dispose();
				showPageOfTeacher();
			}
		});
		clear.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				do_clear_actionPerformed(e);
			}
			private void do_clear_actionPerformed(ActionEvent e) {
				  sno.setText("");
				  sname.setText("");
				  cno.setText("");
				  grade.setText("");
				  egrade.setText("");
				  pgrade.setText("");	  
			}
		});
		change.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				do_change_actionPerformed(e);
			}
			private void do_change_actionPerformed(ActionEvent e) {
				String Sno = new String(sno.getText());
				String Sname = new String(sname.getText());
				String Cno = new String(cno.getText());
				String Grade = new String(grade.getText());
				String Pgrade = new String(pgrade.getText());
				String Egrade = new String(egrade.getText());			
				if(sno.getText().isEmpty() || Sname.isEmpty() || Cno.isEmpty()
						|| Grade.isEmpty() || Pgrade.isEmpty() || Egrade.isEmpty()){
					JOptionPane.showMessageDialog(null, "栏目不得为空！");	
				}
				else if(!com.onisac.mysql.operator.DBoperatorOfManager.exitinSC(Sno, Cno)){
					JOptionPane.showMessageDialog(null, "未检测到该学生选课信息无法修改！");	
				}
				else{
					com.onisac.mysql.operator.DBoperatorOfManager.update_infInSC_student(Sno, Cno, Float.valueOf(Grade),Float.valueOf(Pgrade),Float.valueOf(Egrade));
					JOptionPane.showMessageDialog(null, "修改成功！");	
				}
				sno.setText("");
				sname.setText("");
				cno.setText("");
				grade.setText("");
				egrade.setText("");
				pgrade.setText("");	
				infchange.dispose();
				ShowgrademanageFrame();
			}
		});
		clear.setBounds(220,310,120,20);
		change.setBounds(220,350,120,20);
		quit.setBounds(220,390,120,20);
		infchange.add(clear);
		infchange.add(change);
		infchange.add(quit);
		infchange.setVisible(true);	
	}
	
}
