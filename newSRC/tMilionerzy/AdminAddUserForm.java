package tMilionerzy;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

public class AdminAddUserForm extends JFrame implements ActionListener, KeyListener
{

	
	//ZMIENNE
	private String Nick;
	private alerts Alert;
	private JLabel NickLabel,PasswordLabel,RePasswordLabel,SurnameLabel,EmailLabel,TypeLabel;
	private JTextField NickBox,SurnameBox,EmailBox,TypeBox;
	private JPasswordField PasswordBox,RePasswordBox;
	private JButton ConfirmButton,ClearButton,BackButton;
	DBConnection dbc=new DBConnection();
	
	//FORMATKA
	public AdminAddUserForm()
	{
		this.setLayout(null);
		
		NickLabel=new JLabel(": Nick");
		NickLabel.setBounds(170, 20, 50, 30);
		add(NickLabel);
		
		PasswordLabel=new JLabel(": Hasło");
		PasswordLabel.setBounds(170,60,100,30);
		add(PasswordLabel);
		
		RePasswordLabel=new JLabel(": Powtórz hasło");
		RePasswordLabel.setBounds(170,100,100,30);
		add(RePasswordLabel);
		
		SurnameLabel=new JLabel(": Imię");
		SurnameLabel.setBounds(170,140,100,30);
		add(SurnameLabel);
		
		EmailLabel=new JLabel(": Email");
		EmailLabel.setBounds(170,180,100,30);
		add(EmailLabel);
		
		TypeLabel=new JLabel(": Typ konta");
		TypeLabel.setBounds(170,220,100,30);
		add(TypeLabel);
		
		NickBox=new JTextField("");
		NickBox.setBounds(30,20,120,30);
		add(NickBox);
		
		PasswordBox=new JPasswordField("");
		PasswordBox.setBounds(30,60,120,30);
		add(PasswordBox);
		
		RePasswordBox=new JPasswordField("");
		RePasswordBox.setBounds(30,100,120,30);
		add(RePasswordBox);
		
		SurnameBox=new JTextField("");
		SurnameBox.setBounds(30,140,120,30);
		add(SurnameBox);
		
		EmailBox=new JTextField("");
		EmailBox.setBounds(30,180,120,30);
		add(EmailBox);
		
		TypeBox=new JTextField("");
		TypeBox.setBounds(30,220,120,30);
		add(TypeBox);
		
		BackButton=new JButton("Wróć");
		BackButton.setBounds(12,270,100,30);
		add(BackButton);
		BackButton.addActionListener(this);
		BackButton.addKeyListener(this);
		
		ClearButton=new JButton("Wyczyść");
		ClearButton.setBounds(117,270,100,30);
		add(ClearButton);
		ClearButton.addActionListener(this);
		ClearButton.addKeyListener(this);
		
		ConfirmButton=new JButton("Wyślij");
		ConfirmButton.setBounds(222,270,100,30);
		add(ConfirmButton);
		ConfirmButton.addActionListener(this);
		ConfirmButton.addKeyListener(this);
		
	}
	
	
	//FUNCTION
	
	private void Rejestruj()
	{
		String passText = new String(PasswordBox.getPassword());
		String pass2Text = new String(RePasswordBox.getPassword());
		RegisterCheck rc = new RegisterCheck();
		if(passText.equals(pass2Text))
		{
			if (rc.sprawdzNick(NickBox.getText()))
			{
				if (rc.sprawdzHaslo(passText,pass2Text))
				{
					if (rc.sprawdzEmail(EmailBox.getText()))
					{
						dbc.EstablishConnection();
						if(dbc.RegisterFunction(NickBox.getText(),SurnameBox.getText(), passText, EmailBox.getText(),TypeBox.getText()))
						{
							Cofnij();
						}
						
					}
					else
					{
						alerts.iB("Podano niewłaściwy email!", "ERROR");
					}
				}
				else
				{
					alerts.iB("Hasło nie spełnia wymagań!", "ERROR");
				}
			}
			else
			{
				alerts.iB("Podano niewłaściwy nick!", "ERROR");
			}
		}
		else
		{
			alerts.iB("Hasła nie zgadzają się!", "ERROR");
		}
	}
	
	public void setNick(String Nick)
	{
		this.Nick=Nick;
		setTitle("Admin: "+this.Nick);
	}
	private void Cofnij()
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
	private void Close()
	{
		this.setVisible(false);
		this.dispose();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int key = e.getKeyCode();
		if (key==KeyEvent.VK_ENTER)
		{
			Rejestruj();
		}

	}
	private void Wyczysc()
	{
		NickBox.setText("");
		SurnameBox.setText("");
		EmailBox.setText("");
		PasswordBox.setText("");
		RePasswordBox.setText("");
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (ConfirmButton.hasFocus())
		{
			Rejestruj();
		}
		if(ClearButton.hasFocus())
		{
			Wyczysc();
		}
		if(BackButton.hasFocus())
		{
			Cofnij();
		}
		
	}

}
