package tMilionerzy;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

public class AfterLoginAdminForm extends JFrame implements ActionListener
{
	//ZMIENNE
	private String Nick="";
	private alerts Alert;
	private JButton UserButton,AddQuestionButton,LogoutButton;
	
	//FORMATKA
	public AfterLoginAdminForm()
	{
		setLayout(null);
		
		UserButton=new JButton("Menu użytkownika");
		UserButton.setBounds(10, 17, 150, 30);
		add(UserButton);
		UserButton.addActionListener(this);
		
		AddQuestionButton=new  JButton("Menu pytania");
		AddQuestionButton.setBounds(170, 17, 150, 30);
		add(AddQuestionButton);
		AddQuestionButton.addActionListener(this);
		
		LogoutButton=new  JButton("Wyloguj");
		LogoutButton.setBounds(330, 17, 150, 30);
		add(LogoutButton);
		LogoutButton.addActionListener(this);
	}
	
	
	//FUNCTION
	public void setNick(String Nick)
	{
		this.Nick=Nick;
		setTitle("Admin: "+this.Nick);
	}

	private void MenuUzytkownika()
	{
		Close();
		AdminUserMenuForm aum=new AdminUserMenuForm();
		aum.setSize(500,100);
		aum.setNick(Nick);
		aum.setLocationRelativeTo(null);
		aum.setResizable(false); 
		aum.setBackground(Color.LIGHT_GRAY);
		aum.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		aum.setVisible(true);

	}
	private void MenuPytania()
	{
		Close();
		AdminQuestionMenuForm aqm=new AdminQuestionMenuForm();
		aqm.setSize(500,100);
		aqm.setNick(Nick);
		aqm.setLocationRelativeTo(null);
		aqm.setResizable(false); 
		aqm.setBackground(Color.LIGHT_GRAY);
		aqm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		aqm.setVisible(true);
	}
	private void Wyloguj()
	{
		Nick="";
		Close();
		LoginForm lf = new LoginForm();
		lf.setSize(320,320);
		lf.setLocationRelativeTo(null);
		lf.setResizable(false); 
		lf.setBackground(Color.LIGHT_GRAY);
		lf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		lf.setVisible(true);
	}
	private void Close()
	{
		this.setVisible(false);
		this.dispose();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(UserButton.hasFocus())
		{
			MenuUzytkownika();
		}
		if(AddQuestionButton.hasFocus())
		{
			MenuPytania();
		}
		if(LogoutButton.hasFocus())
		{
			Wyloguj();
		}
	}

}
