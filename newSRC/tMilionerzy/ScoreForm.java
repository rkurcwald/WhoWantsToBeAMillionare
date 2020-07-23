package tMilionerzy;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

public class ScoreForm extends JFrame implements ActionListener
{
	//ZMIENNE
	private String Nick="";
	private alerts Alert;
	private JLabel UserInfoLabel,ScoreInfoLabel,HMGInfoLabel,HMWInfoLabel,YourScoreLabel,YourNickLabel,ScoreSpaceLabel,NickSpaceLabel;
	private JLabel[] UsersLabels,ScoresLabels,HMGValueLabels,HMWValueLabels;
	private JButton BackButton;
	private String[] UsersTxt;
	private int[] ScoresValues,HowManyGamesValues,HowManyWinsValues;
	private DBConnection dbc=new DBConnection();
	//FORMATKA
	public ScoreForm()
	{
		setLayout(null);
		
		WypiszLD();
		
		
	}
	
	
	//FUNCTION
	private void WypiszLD()
	{
		dbc.EstablishConnection();
		UsersTxt = dbc.SelectUsersSort();
		ScoresValues = dbc.SelectScoresSort();
		HowManyGamesValues = dbc.SelectGamesSort();
		HowManyWinsValues = dbc.SelectHMWSort();
		CreateLabels(UsersTxt.length);
		
		BackButton = new JButton("Wróć");
		BackButton.setBounds(320, 470, 80, 30);
		add(BackButton);
		BackButton.addActionListener(this);
	}
	private void CreateLabels(int ile)
	{
	//	String urNick = Nick;
		boolean thisUser = false;
		UserInfoLabel = new JLabel("Gracz:");
		UserInfoLabel.setBounds(60, 40, 120, 30);
		add(UserInfoLabel);
		
		ScoreInfoLabel = new JLabel("Wynik:");
		ScoreInfoLabel.setBounds(300, 40, 120, 30);
		add(ScoreInfoLabel);
		
		HMGInfoLabel = new JLabel("Gry:");
		HMGInfoLabel.setBounds(450, 40, 120, 30);
		add(HMGInfoLabel);
		
		HMWInfoLabel = new JLabel("Ilość wygranych:");
		HMWInfoLabel.setBounds(600, 40, 120, 30);
		add(HMWInfoLabel);
		
		int wysokosc = 60;
		UsersLabels = new JLabel[ile];
		ScoresLabels = new JLabel[ile];
		HMGValueLabels= new JLabel[ile];
		HMWValueLabels= new JLabel[ile];
		if(ile<10)
		{
			for (int i = 0; i < ile; i++)
			{
				if (thisUser == false)
				{
					if (Nick.equals(UsersTxt[i]))
					{
						thisUser = true;
						UsersLabels[i] = new JLabel("<HTML><U>" + (i + 1) + ". " + UsersTxt[i] + "</U></HTML>");
						ScoresLabels[i] = new JLabel("<HTML><U>" + Integer.toString(ScoresValues[i]) + "</U></HTML>");
						HMGValueLabels[i]=  new JLabel("<HTML><U>" + Integer.toString(HowManyGamesValues[i]) + "</U></HTML>");
						HMWValueLabels[i]=  new JLabel("<HTML><U>" + Integer.toString(HowManyWinsValues[i]) + "</U></HTML>");
					}
					else
					{
						UsersLabels[i] = new JLabel( + (i + 1) + ". " + UsersTxt[i]);
						ScoresLabels[i] = new JLabel(Integer.toString(ScoresValues[i]));
						HMGValueLabels[i]=  new JLabel("<HTML><U>" + Integer.toString(HowManyGamesValues[i]) + "</U></HTML>");
						HMWValueLabels[i]=  new JLabel("<HTML><U>" + Integer.toString(HowManyWinsValues[i]) + "</U></HTML>");

					}
				}
				else
				{
					UsersLabels[i] = new JLabel( + (i + 1) + ". " + UsersTxt[i]);
					ScoresLabels[i] = new JLabel(Integer.toString(ScoresValues[i]));
					HMGValueLabels[i]=  new JLabel("<HTML><U>" + Integer.toString(HowManyGamesValues[i]) + "</U></HTML>");
					HMWValueLabels[i]=  new JLabel("<HTML><U>" + Integer.toString(HowManyWinsValues[i]) + "</U></HTML>");

				}
				UsersLabels[i].setBounds(60, wysokosc, 120, 30);
				ScoresLabels[i].setBounds(300, wysokosc, 120, 30);
				HMGValueLabels[i].setBounds(450,wysokosc,120,30);
				HMWValueLabels[i].setBounds(600,wysokosc,120,30);
				
				add(UsersLabels[i]);
				add(ScoresLabels[i]);
				add(HMGValueLabels[i]);
				add(HMWValueLabels[i]);
				wysokosc += 20;
			
			}
		}
		else
		{
			for (int i = 0; i < 10; i++)
			{
				if (thisUser == false)
				{
					if (Nick.equals(UsersTxt[i]))
					{
						thisUser = true;
						UsersLabels[i] = new JLabel("<HTML><U>" + (i + 1) + ". " + UsersTxt[i] + "</U></HTML>");
						ScoresLabels[i] = new JLabel("<HTML><U>" + Integer.toString(ScoresValues[i]) + "</U></HTML>");
						HMGValueLabels[i]=  new JLabel("<HTML><U>" + Integer.toString(HowManyGamesValues[i]) + "</U></HTML>");
						HMWValueLabels[i]=  new JLabel("<HTML><U>" + Integer.toString(HowManyWinsValues[i]) + "</U></HTML>");

					}
					else
					{
						UsersLabels[i] = new JLabel( + (i + 1) + ". " + UsersTxt[i]);
						ScoresLabels[i] = new JLabel(Integer.toString(ScoresValues[i]));
						HMGValueLabels[i]=  new JLabel("<HTML><U>" + Integer.toString(HowManyGamesValues[i]) + "</U></HTML>");
						HMWValueLabels[i]=  new JLabel("<HTML><U>" + Integer.toString(HowManyWinsValues[i]) + "</U></HTML>");

					}
				}
				else
				{
					UsersLabels[i] = new JLabel( + (i + 1) + ". " + UsersTxt[i]);
					ScoresLabels[i] = new JLabel(Integer.toString(ScoresValues[i]));
					HMGValueLabels[i]=  new JLabel("<HTML><U>" + Integer.toString(HowManyGamesValues[i]) + "</U></HTML>");
					HMWValueLabels[i]=  new JLabel("<HTML><U>" + Integer.toString(HowManyWinsValues[i]) + "</U></HTML>");

				}
				UsersLabels[i].setBounds(60, wysokosc, 120, 30);
				ScoresLabels[i].setBounds(300, wysokosc, 120, 30);
				HMGValueLabels[i].setBounds(450,wysokosc,120,30);
				HMWValueLabels[i].setBounds(600,wysokosc,120,30);
			
				add(UsersLabels[i]);
				add(ScoresLabels[i]);
				add(HMGValueLabels[i]);
				add(HMWValueLabels[i]);
				wysokosc += 20;
			
			}
		}
		if (thisUser == false)
		{
/*			for(int i=0;i<ile;i++)
			{
				System.out.println(UsersTxt[i]);
			}
	*/		
			
			for (int i = 0; i < ile; i++)
			{
				if (Nick.equals(UsersTxt[i]))
				{
				//	System.out.println("COS11");
					NickSpaceLabel = new JLabel("...");
					ScoreSpaceLabel = new JLabel("...");
					NickSpaceLabel.setBounds(60, wysokosc, 40, 30);
					ScoreSpaceLabel.setBounds(300, wysokosc, 40, 30);
					
					YourNickLabel = new JLabel("<HTML><U>" + (i + 1) + ". " + UsersTxt[i] + "</U></HTML>");
					YourScoreLabel = new JLabel("<HTML><U>" + Integer.toString(ScoresValues[i]) + "</U></HTML>");
					YourNickLabel.setBounds(60, wysokosc + 20, 120, 30);
					YourScoreLabel.setBounds(300, wysokosc + 20, 120, 30);
					
					add(NickSpaceLabel);
					add(ScoreSpaceLabel);
					
					add(YourNickLabel);
					add(YourScoreLabel);
					i = ile;
				}
				else
				{
				//	System.out.println("COS");
				}
			}
		}
	}
	
	
	public void setNick(String Nick)
	{
		this.Nick=Nick;
		setTitle("Zalogowano jako: "+this.Nick);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(BackButton.hasFocus())
		{
			this.dispose();
			this.setVisible(false);
			AfterLoginForm alf=new AfterLoginForm();
			alf.setSize(450,100);
			alf.setNick(Nick);
			alf.setLocationRelativeTo(null);
			alf.setResizable(false); 
			alf.setBackground(Color.LIGHT_GRAY);
			alf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
			alf.setVisible(true);
		}
		
	}
}