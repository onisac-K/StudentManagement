package com.onisac.login;
import com.onisac.mysql.operator.DBoperator;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class PageOFlogin {
	public static void ShowPageOFlogin(){
		JFrame frame = new JFrame("学生管理系统");
		Container c = frame.getContentPane();
		JTextField username = new JTextField();
		JPasswordField password = new JPasswordField();
		JButton ok = new JButton("确定");
		JButton cancel = new JButton("取消");
		JButton register = new JButton("注册");
		frame.setSize(300,200);
		c.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		//顶部
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new FlowLayout());
		titlePanel.add(new JLabel("系统登录"));
		c.add(titlePanel,"North");

		//中部表单
		JPanel fieldPanel = new JPanel();
		fieldPanel.setLayout(null);
		JLabel a1 = new JLabel("用户名:");
		a1.setBounds(50,20,50,20);
		
		JLabel a2 = new JLabel("密  码:");
		a2.setBounds(50,60,50,20);
		fieldPanel.add(a1);
		fieldPanel.add(a2);
		username.setBounds(110,20,120,20);
		
		password.setBounds(110,60,120,20);
		fieldPanel.add(username);
		fieldPanel.add(password);
		c.add(fieldPanel,"Center");

		//底部按钮
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		ok.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				do_okButton_actionPerformed(e);
			}
			private void do_okButton_actionPerformed(ActionEvent e){
				String UserName = new String(username.getText());
				String PassWord = new String(password.getPassword());
				//System.out.println(UserName + "hello" + PassWord);
				if(UserName.isEmpty() || PassWord.isEmpty()){
					JOptionPane.showMessageDialog(null, "账号或密码不得为空！");
					return;
				}
				String CheckAnswer = new String (DBoperator.Check(UserName,PassWord));
				if(CheckAnswer.equals("null")){
					JOptionPane.showMessageDialog(null, "账号或密码错误！");
				}else {
					recent_user.user = UserName;
					recent_user.password = PassWord;
					if(CheckAnswer.equals("student")){
						frame.dispose();
						PageOfStudent.showPageOfStrudent();
					}else{
						frame.dispose();
						PageOfTeacher.showPageOfTeacher();
					}
				}
			}
		});
		cancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				do_cancelButton_actionPerformed(e);
			}
			private void do_cancelButton_actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
		register.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				do_registerButton_actionPerformed(e);
			}
			private void do_registerButton_actionPerformed(ActionEvent e){
				frame.dispose();
				ShowRegisterFrame();
			}
		});
		buttonPanel.add(ok);
		buttonPanel.add(cancel);
		buttonPanel.add(register);
		c.add(buttonPanel,"South");
		frame.setLocation(com.onisac.login.SwingUtil.centreContainer(frame.getSize()));
		frame.setVisible(true);
	}	
	private static void ShowRegisterFrame() {
		JFrame frame = new JFrame("学生管理系统");
		JFrame frameOfRegister = new JFrame("注册");
		JTextField usernameOfRegister = new JTextField();
		JPasswordField passwordOfRegister = new JPasswordField();
		JPasswordField password2OfRegister = new JPasswordField();
		JTextField emailOfRegister = new JTextField();
		JTextField priorityOfRegister = new JTextField();
		JButton okOfRegister = new JButton("确定");
		JButton cancelOfRegister = new JButton("取消");
		frame.setVisible(false);
		frameOfRegister.setSize(300,320);
		frameOfRegister.setResizable(false);
		Container a = frameOfRegister.getContentPane();
		a.setLayout(new BorderLayout());
		frameOfRegister.setLocation(com.onisac.login.SwingUtil.centreContainer(frameOfRegister.getSize()));
		//frame.setVisible(true);
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new FlowLayout());
		titlePanel.add(new JLabel("用户注册"));
		a.add(titlePanel,"North");

		//中部表单
		JPanel fieldPanel = new JPanel();
		fieldPanel.setLayout(null);
		JLabel a1 = new JLabel("用户名:");
		a1.setBounds(50,20,50,20);
		
		JLabel a2 = new JLabel("密  码:");
		a2.setBounds(50,60,50,20);
		
		JLabel a3 = new JLabel("重复密码:");
		a3.setBounds(50,100,60,20);
		
		JLabel a4 = new JLabel("邮  箱:");
		a4.setBounds(50,140,50,20);
		
		JLabel a5 = new JLabel("身  份:");
		a5.setBounds(50,180,50,20);
		fieldPanel.add(a1);
		fieldPanel.add(a2);
		fieldPanel.add(a3);
		fieldPanel.add(a4);
		fieldPanel.add(a5);
		
		usernameOfRegister.setBounds(110,20,120,20);
		usernameOfRegister.setText("学生请用学号注册");
		passwordOfRegister.setBounds(110,60,120,20);
		password2OfRegister.setBounds(110,100,120,20);
		emailOfRegister.setBounds(110,140,120,20);
		priorityOfRegister.setBounds(110,180,120,20);
		priorityOfRegister.setText("'student' or 'teacher'");
		fieldPanel.add(usernameOfRegister);
		fieldPanel.add(passwordOfRegister);
		fieldPanel.add(password2OfRegister);
		fieldPanel.add(emailOfRegister);
		fieldPanel.add(priorityOfRegister);
		a.add(fieldPanel,"Center");
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		
		okOfRegister.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				do_okButton_Regiester_actionPerformed(e);
			}

			private void do_okButton_Regiester_actionPerformed(ActionEvent e) {
				String U = new String(usernameOfRegister.getText());
				String P = new String(passwordOfRegister.getPassword());
				String P2 = new String(password2OfRegister.getPassword());
				String E = new String(emailOfRegister.getText());
				String S = new String(priorityOfRegister.getText());
				if(U.isEmpty() || P.isEmpty() || P2.isEmpty() || 
						E.isEmpty() || S.isEmpty()){
					JOptionPane.showMessageDialog(null, "五项栏目不得为空！");
				}
				else if(!P.equals(P2)){
					JOptionPane.showMessageDialog(null, "两次密码不同，请重新输入！");
				}
				else if(!S.equals("student") && !S.equals("teacher")){
					JOptionPane.showMessageDialog(null, "身份只能填‘student’或者‘teacher’！");
				}
				else{
					DBoperator.add(U,P,E,S);
					JOptionPane.showMessageDialog(null, "注册成功！");
					frameOfRegister.dispose();
					ShowPageOFlogin();
				}
			}
		});
		cancelOfRegister.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				do_cancelButton_Register_actionPerformed(e);
			}
			private void do_cancelButton_Register_actionPerformed(ActionEvent e) {
				frameOfRegister.dispose();
				ShowPageOFlogin();
			}
		});
		
		buttonPanel.add(okOfRegister);
		buttonPanel.add(cancelOfRegister);
		a.add(buttonPanel,"South");
		
		frameOfRegister.setVisible(true);
	}
}

