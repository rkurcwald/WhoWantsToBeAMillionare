package tMilionerzy;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;

import javax.swing.*;

public class SettingsForm extends JFrame implements ActionListener
{
	//ZMIENNE
	private JLabel ChangeEmailLabel,ChangePasswordLabel,ChangeRePasswordLabel;
	private JTextField ChangeEmailBox;
	private JPasswordField ChangePasswordBox,ChangeRePasswordBox; 
	private JButton CancelButton,ConfirmButton,ResetQButton;
	private String Nick, haslo;
	private alerts Alert;
	private RegisterCheck rc=new RegisterCheck();
	private DBConnection dbc=new DBConnection();
	//FORMATKA
	public SettingsForm()
	{
		setLayout(null);
		dbc.EstablishConnection();
		
		
		ChangeEmailLabel=new JLabel(": Nowy email");
		ChangeEmailLabel.setBounds(190,20,100,30);
		add(ChangeEmailLabel);
		
		ChangePasswordLabel=new JLabel(": Nowe hasło");
		ChangePasswordLabel.setBounds(190,60,100,30);
		add(ChangePasswordLabel);
		
		ChangeRePasswordLabel=new JLabel(": Powtórz hasło");
		ChangeRePasswordLabel.setBounds(190,100,100,30);
		add(ChangeRePasswordLabel);
		
		ChangeEmailBox=new JTextField("");
		ChangeEmailBox.setBounds(30,20,135,30);
		ChangeEmailBox.setText("");
		add(ChangeEmailBox);
		
		ChangePasswordBox=new JPasswordField("");
		ChangePasswordBox.setBounds(30,60,135,30);
		ChangePasswordBox.setText("");
		add(ChangePasswordBox);
		
		ChangeRePasswordBox=new JPasswordField("");
		ChangeRePasswordBox.setBounds(30,100,135,30);
		ChangeRePasswordBox.setText("");
		add(ChangeRePasswordBox);
		
		ConfirmButton=new JButton("Zapisz zmiany");
		ConfirmButton.setBounds(60,150,150,30);
		add(ConfirmButton);
		ConfirmButton.addActionListener(this);
		
		CancelButton=new JButton("Odrzuć zmiany");
		CancelButton.setBounds(60,190,150,30);
		add(CancelButton);
		CancelButton.addActionListener(this);
		
		ResetQButton=new JButton("RQ");
		ResetQButton.setBounds(220,150,60,30);
		add(ResetQButton);
		ResetQButton.addActionListener(this);
	}
	
	
	//FUNCTION
	public boolean changeEmail()
	{
		if (rc.sprawdzEmail(ChangeEmailBox.getText()))
		{
			if(alerts.qB("Zmienić email?", "Jesteś pewien?"))
			{
				
				if(dbc.UpdateEmail(ChangeEmailBox.getText()))
				{
					return true;
				}
				else
				{
					return false;
				}
				
			}
			return false;
			
		}
		else if(ChangeEmailBox.getText().equals(""))
		{
			return false;
		}
		else
		{
			alerts.iB("Podano niewłaściwy email!", "ERROR");
			return false;
		}
	}
	public boolean changePassword()
	{
		String passText= new String(ChangePasswordBox.getPassword()),pass2Text= new String(ChangePasswordBox.getPassword());
		if(passText.equals(pass2Text))
		{
			if (rc.sprawdzHaslo(passText,pass2Text))
			{
				if(alerts.qB("Zmienić hasło?", "Jesteś pewien?"))
				{
					dbc.EstablishConnection();
					if(dbc.UpdatePassword(passText))
					{
						 return true;
					}
					else
					{
						return false;
					}
				}
				else
				{
					alerts.iB("Hasło nie spełnia wymagań!", "ERROR");
					return false;
				}
			}
			return false;
		}
		else
		{
			alerts.iB("Hasła nie zgadzają się!", "ERROR");
			return false;
		}
	}
	public void setNick(String Nick)
	{
		this.Nick=Nick;
		setTitle(this.Nick);
	}
	public void Zatwierdz()
	{
		if (changeEmail() || changePassword())
		{
			alerts.iB("Pomyślnie zaktualizowano dane", "SettingsInfo");
			bALF();
		}

	}
	public void Cofnij()
	{
		haslo=new String(ChangePasswordBox.getPassword());
		if(ChangeEmailBox.getText().length()==0 && haslo.length()==0)//&& ChangePasswordBox.toString().length()==0)
		{
			
			bALF();
		}
		else
		{
			if(Alert.qB("Wyjść bez zapisywania?", Nick+" definitywnie?")==true)
			{
				haslo="";
				bALF();
			}
		}
	}
	private void bALF()
	{
		this.setVisible(false);
		this.dispose();
		AfterLoginForm alf=new AfterLoginForm();
		alf.setSize(450,100);
		alf.setNick(Nick);
		alf.setLocationRelativeTo(null);
		alf.setResizable(false); 
		alf.setBackground(Color.LIGHT_GRAY);
		alf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		alf.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (ConfirmButton.hasFocus())
		{
			Zatwierdz();
		}
		if(CancelButton.hasFocus())
		{
			//dbc.ResetProgress();
			Cofnij();
			
		}
		if(ResetQButton.hasFocus())
		{
			//System.out.println("HMUQ t1");

			try {
				if(dbc.resetHMUQ())
				{
					alerts.iB("Pytania zostały zresetowane", "Great Job");
					Cofnij();
				}
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
}
