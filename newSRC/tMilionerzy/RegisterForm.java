package tMilionerzy;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;


public class RegisterForm extends JFrame implements ActionListener,KeyListener
{
	//ZMIENNE
	private JLabel NickLabel,PasswordLabel,RePasswordLabel,SurnameLabel,EmailLabel;
	private JTextField NickBox,SurnameBox,EmailBox;
	private JPasswordField PasswordBox,RePasswordBox;
	private JButton ConfirmButton,ClearButton,BackButton;
	DBConnection dbc=new DBConnection();
	//FORMATKA
	public RegisterForm()
	{
		setTitle("Milionerzy");
		setLayout(null);
		
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
		
		BackButton=new JButton("Wróć");
		BackButton.setBounds(3,230,100,30);
		add(BackButton);
		BackButton.addActionListener(this);
		BackButton.addKeyListener(this);
		
		ClearButton=new JButton("Wyczyść");
		ClearButton.setBounds(107,230,100,30);
		add(ClearButton);
		ClearButton.addActionListener(this);
		ClearButton.addKeyListener(this);
		
		ConfirmButton=new JButton("Wyślij");
		ConfirmButton.setBounds(210,230,100,30);
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
						if(dbc.RegisterFunction(NickBox.getText(),SurnameBox.getText(), passText, EmailBox.getText(),"0"))
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
	private void Wyczysc()
	{
		NickBox.setText("");
		SurnameBox.setText("");
		EmailBox.setText("");
		PasswordBox.setText("");
		RePasswordBox.setText("");
	}
	private void Cofnij()
	{
		this.setVisible(false);
		this.dispose();
		LoginForm lf = new LoginForm();
		lf.setSize(320,320);
		lf.setLocationRelativeTo(null);
		lf.setResizable(false); 
		lf.setBackground(Color.LIGHT_GRAY);
		lf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		lf.setVisible(true);
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
