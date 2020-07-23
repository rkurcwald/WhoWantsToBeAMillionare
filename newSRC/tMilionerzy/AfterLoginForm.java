package tMilionerzy;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;

public class AfterLoginForm extends JFrame implements ActionListener
{
	//ZMIENNE
	private JButton ScoreButton,LogoutButton,SettingsButton,GameButton;
	private String Nick="";
	private alerts Alert;
	private boolean IsAbleToPlay=false;
	DBConnection dbc=new DBConnection();
	//FORMATKA
	
	public AfterLoginForm()
	{
		setLayout(null);
	
		dbc.EstablishConnection();
		
		ScoreButton=new JButton("Wyniki");
		ScoreButton.setBounds(120,17,100,30);
		add(ScoreButton);
		ScoreButton.addActionListener(this);
		ScoreButton.setEnabled(true);
		
		GameButton=new JButton("Zagraj");
		GameButton.setBounds(15,17,100,30);
		add(GameButton);
		GameButton.addActionListener(this);

		SettingsButton=new JButton("Ustawienia");
		SettingsButton.setBounds(225,17,100,30);
		add(SettingsButton);
		SettingsButton.addActionListener(this);
		
		LogoutButton=new JButton("Wyloguj");
		LogoutButton.setBounds(330,17,100,30);
		add(LogoutButton);
		LogoutButton.addActionListener(this);
		
		
	}
	
	
	//FUNCTION
	public void setNick(String Nick)
	{
		this.Nick=Nick;
		setTitle("Zalogowano jako: "+this.Nick);
	}
	private void Wyloguj()
	{
		//logout mechanic
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
	public void Graj()
	{
		try {
			if(dbc.howManyQ()-dbc.howManyUsedQ()>=12)
			{
			//	System.out.println(dbc.howManyQ()+" "+dbc.howManyUsedQ()+" "+dbc.getCurrentUser());
				IsAbleToPlay=true;
			}
			else
			{
				IsAbleToPlay=false;
			}
			
		}
		catch (NumberFormatException | SQLException e)
		{
			System.out.println(e);
		}
		if(IsAbleToPlay)
		{
			Close();
			GameForm gf=new GameForm();
			gf.setSize(480,585);
			gf.setNick(Nick);
			gf.setLocationRelativeTo(null);
			gf.setResizable(false); 
			gf.setBackground(Color.LIGHT_GRAY);
			gf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
			gf.setVisible(true);
		}
		else
		{
			alerts.iB("Za mało pytań by rozegrać tą partię. Zrestartuj pytania w ustawieniach", "Mało pytań");
			Ustawienia();
		}

		
	}
	private void Ustawienia()
	{
		Close();
		SettingsForm sf=new SettingsForm();
		sf.setSize(320,280);
		sf.setNick(Nick);
		sf.setLocationRelativeTo(null);
		sf.setResizable(false); 
		sf.setBackground(Color.LIGHT_GRAY);
		sf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		sf.setVisible(true);
		
	//	sf.setTitle(Title);
	}
	private void Wyniki()
	{
		Close();
		ScoreForm scf=new ScoreForm();
		scf.setSize(780,580);
		scf.setNick(Nick);
		scf.setLocationRelativeTo(null);
		scf.setResizable(false); 
		scf.setBackground(Color.LIGHT_GRAY);
		scf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		scf.setVisible(true);
		
//		scf.setTitle(Title);
	}
	private void Close()
	{
		this.setVisible(false);
		this.dispose();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (ScoreButton.hasFocus())
		{
			Wyniki();
		}
		if(SettingsButton.hasFocus())
		{
			Ustawienia();
		}
		if(GameButton.hasFocus())
		{
			Graj();
		}
		if(LogoutButton.hasFocus())
		{
			Wyloguj();
		}
		
	}

}
