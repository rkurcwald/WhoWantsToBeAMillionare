package tMilionerzy;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class GameForm extends JFrame implements ActionListener, KeyListener
{
	//ZMIENNE
	private String Nick;
	private alerts Alert;
	private JButton ResignButton,FriendCallButton,FF_Button,PublicCallButton;
	private JLabel[] WLL; //WinLabelList
	private JLabel test;
	private JButton[] ABA; //AnswersButtonArray
	private JLabel Info;
	private JPanel Win,QuestionWithBorder,Rescue,Answers,ResignPanel;
	private int changeLabelY=0,NrOfQuestion=1,Score=-1;
	private String PreParsedQuestion,AnswerButtonString="ABCD";
	private String[] FromDB;
	private boolean[] TableToRand;
	private BarChartFrame BCF;
	private boolean BCF_Exist=false,Error_Code=false,randQ=false;
	DBConnection dbc=new DBConnection();
	
	//Zmienne Diagram
	final static String Pub="Publicznosc";
	//FORMATKA
	public GameForm()
	{
		setLayout(null);
		setWinList();
		dbc.EstablishConnection();
		
		FromDB=new String[6];
		FromDB[0]="aaasasdsadsa";
		FromDB[1]="Test";
		FromDB[2]="Test1";
		FromDB[3]="Test2";
		FromDB[4]="Test3";
		FromDB[5]="Test1";
		setABA();
		globalClear();
	//	setQandA();
		
		
		//--------------------------------------------------------
		Win=new JPanel();
		Win.setLayout(null);
		Win.setBounds(10, 10, 140, 520);
		Win.setBorder(new TitledBorder((Border) new LineBorder(Color.black, 5),"Tabela Wygranych"));
		Win.add(new JLabel(FromDB[0]));
		
		for(int i=11;i>=0;i--)
		{
			WLL[i].setBorder(new BevelBorder(BevelBorder.RAISED));
			WLL[i].setBounds(20,30+changeLabelY , 100, 30);
			
			Win.add(WLL[i]);
			changeLabelY+=40;
		}
		add(Win);
		//--------------------------------------------------------
		Info=new JLabel("Koła ratunkowe",SwingConstants.CENTER);
		Info.setBounds(160,50,300,35);
		Info.setBorder(new LineBorder(Color.black, 5));
		add(Info);
		//--------------------------------------------------------
		Rescue=new JPanel();
		Rescue.setBounds(160, 80, 300, 50);
		Rescue.setBorder(new LineBorder(Color.black, 5));
		
		PublicCallButton=new JButton("Publiczność");
		PublicCallButton.setBounds(180, 90, 80, 30);
		PublicCallButton.addActionListener(this);
		PublicCallButton.addKeyListener(this);
		Rescue.add(PublicCallButton);
		
		FriendCallButton=new JButton("Telefon");
		FriendCallButton.setBounds(270, 90, 80, 30);
		FriendCallButton.addActionListener(this);
		FriendCallButton.addKeyListener(this);
		Rescue.add(FriendCallButton);
		
		FF_Button=new JButton("50/50");
		FF_Button.setBounds(360, 90, 80, 30);
		FF_Button.addActionListener(this);
		FF_Button.addKeyListener(this);
		Rescue.add(FF_Button);
		
		add(Rescue);
		//--------------------------------------------------------
		
		QuestionWithBorder=new JPanel();
		QuestionWithBorder.setBounds(160, 150, 300, 80);
		QuestionWithBorder.setBorder(new TitledBorder((Border) new LineBorder(Color.black, 5),"Pytanie "+NrOfQuestion));
		//QuestionWithBorder.add(new JLabel(FromDB[0]));
		test=new JLabel("test");
		test.setBounds(170,160,250,50);
		add(QuestionWithBorder);
		QuestionWithBorder.add(test);
		//--------------------------------------------------------
		Answers=new JPanel();
		Answers.setLayout(null);
		Answers.setBorder(new LineBorder(Color.black, 5));
		Answers.setBounds(160,250,300,150);
		add(Answers);
		
		ABA[0]=new JButton("A: ");
		ABA[0].setBounds(15,18,130,50);
		ABA[0].addActionListener(this);
		ABA[0].addKeyListener(this);
		Answers.add(ABA[0]);
		
		ABA[1]=new JButton("B: ");
		ABA[1].setBounds(155,18,130,50);
		ABA[1].addActionListener(this);
		ABA[1].addKeyListener(this);
		Answers.add(ABA[1]);
		
		ABA[2]=new JButton("C: ");
		ABA[2].setBounds(15,80,130,50);
		ABA[2].addActionListener(this);
		ABA[2].addKeyListener(this);
		Answers.add(ABA[2]);
		
		ABA[3]=new JButton("D: ");
		ABA[3].setBounds(155,80,130,50);
		ABA[3].addActionListener(this);
		ABA[3].addKeyListener(this);
		Answers.add(ABA[3]);
		//--------------------------------------------------------
		ResignPanel=new JPanel();
		ResignPanel.setLayout(null);
		ResignPanel.setBorder(new LineBorder(Color.black, 5));
		ResignPanel.setBounds(160,420,300,50);
		add(ResignPanel);
		
		ResignButton=new JButton("ZREZYGNUJ");
		ResignButton.setBounds(40,10,220,30);
		ResignButton.addActionListener(this);
		ResignButton.addKeyListener(this);
		ResignPanel.add(ResignButton);
		

		Clear();
		clearAfterGame();


		setQandA();
	}
	
	
	//FUNCTION
	private boolean getFromDB() throws SQLException
	{
		Random rand=new Random();
//		int x=rand.nextInt(3);
		boolean sem=false;
	//	System.out.println(x);
			do
			{
				int x=rand.nextInt(dbc.howManyQ());
			if(!dbc.IsUsedQuestion(x+1))
			{
				String[] QFDB=dbc.GetQuestionFromDB();
				for(int i=0;i<6;i++)
				{
				//	System.out.println(QFDB[i]);
					FromDB[i]=QFDB[i];
		//		System.out.println(dbc.GetQuestionFromDB());
				}
				sem=true;
			}
			}while(sem==false);
			return sem;
		//Question fromDB
		
				
	}
	private void setQandA()
	{
		Random rand = new Random();
		String Numbers="";
		int x=0,Counter=0;
		TableToRand=new boolean[4];
		cleanQ();
		
		try {
			if(getFromDB())
			{
				boolean sem=false;
				int zm=0;
				Counter=0;
				test.setText(FromDB[0]);
				do
				{
					x=rand.nextInt(4);
					if(TableToRand[x]==false)
					{
						TableToRand[x]=true;
						ABA[zm].setText(ABA[zm].getText()+FromDB[x+1]);
					//	ABA[zm].se
					//	System.out.println(x+" "+ABA[x].getText());
						
						zm++;
						Numbers+=x;
						Counter++;
						sem=false;
					}
					if(Counter==4)
					{
						sem=true;
					}
				}while(sem==false);
			}
			else
			{
				Error_Code=true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(Error_Code)
		{

	//		resign();
			//Close();
		}
		
		
	/*	for(int i=0;i<4;i++)
		{
			ABA[i].setText(ABA[i].getText()+FromDB[Integer.parseInt(Numbers.substring(i,i+1))+1]);
		}
		for(int i=0;i<4;i++)
		{
			ABA[i].setText(ABA[i].getText()+FromDB[i+1]);
			System.out.println(i+" "+ABA[i].getText());
		}*/
	}
	private String getPtrOnCorrect()
	{
		String xAsRet="";
		for(int i=0;i<4;i++)
		{
			if(check(ABA[i].getText().substring(3, ABA[i].getText().length())))
			{
			//	System.out.println(ABA[i].getText());
				xAsRet=ABA[i].getText().substring(0, 2);
				break;
			}
		}
		return xAsRet;
	}
	private String getPtrOnWrong()
	{
		String xAsRet="";
		boolean sem=false;
		Random rand=new Random();
		do
		{
		int i=rand.nextInt(3);
			if(ABA[i].getText().substring(3, ABA[i].getText().length()-1)!=FromDB[5] && ABA[i].isEnabled())
			{
				xAsRet=ABA[i].getText().substring(0, 2);
				sem=true;
			}
		}while(sem==false);
		return xAsRet;
	}
/*	public String parsingQuestion(String Question)
	{
		if(Question.length()>=46)
		{
			PreParsedQuestion=Question.substring(0, 45);
		}
		else
		{
			PreParsedQuestion=Question;
		}
		return PreParsedQuestion;
	}*/
	public void setABA()
	{
		ABA=new JButton[4];
		for(int i=0;i<4;i++)
		{
			ABA[i]=new JButton(AnswerButtonString.substring(i,i+1)+": ");
		}
		
	}
	public void setWinList()
	{
		WLL= new JLabel[12];
		for(int i=0;i<12;i++)
		{
			WLL[i]=new JLabel("",SwingConstants.CENTER);
		}
		
		WLL[0].setText("500");
		WLL[1].setText("1000"); //Gwarant
		WLL[2].setText("2000");
		WLL[3].setText("5000");
		WLL[4].setText("10 000");
		WLL[5].setText("20 000");
		WLL[6].setText("40 000"); //Gwarant
		WLL[7].setText("75 000");
		WLL[8].setText("125 000");
		WLL[9].setText("250 000");
		WLL[10].setText("500 000");
		WLL[11].setText("1 000 000");
	}
	private void cleanQ()
	{
		for(int i=0;i<4;i++)
		{
			TableToRand[i]=false;
		}
	}
	public void setNick(String Nick)
	{
		this.Nick=Nick;
		setTitle("Zalogowano jako: "+this.Nick);
	}
	private boolean check(String Answer)
	{
		if(Answer.equals(FromDB[5]))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	private void onClick()
	{
		if(Score==11)
		{
			if(Alert.qB("Wygrana!!! Chcesz zagrać jeszcze raz?", Nick+" wygrałeś!!!!"))
			{
				
				
				dbc.UpdateWins();
				saveScore(WLL[Score].getText());
				resign3();
				
			}
			else
			{
				dbc.UpdateWins();
				resign2();
			}
		}
		else if(Score==1 || Score==6)
		{
			WLL[Score].setBackground(Color.GREEN);
			if(!Alert.qB("Osiągnąłeś próg gwarantowany. Grasz dalej?", Nick+" grasz dalej?"))
			{
				resign();
				randQ=true;
				
			}
			else
			{
				//Score++;
		//		WLL[Score].setBackground(Color.GREEN);
				randQ=false;
				CleanAndPlay();
			}
		}
		else
		{
		//	Score++;
		}
		
		if(Score==-1)
		{
			resign();
		}
		else
		{
			if(NrOfQuestion<=12)
			{
				NrOfQuestion++;
				QuestionWithBorder.setBorder(new TitledBorder((Border) new LineBorder(Color.black, 5),"Pytanie "+NrOfQuestion));
		//		QuestionWithBorder.add(new JLabel(FromDB[0]));
				test.setText(FromDB[0]);
		//		System.out.println(QuestionWithBorder.getTi);
				
			}
			WLL[Score].setBackground(Color.GREEN);
			if(!randQ)
			CleanAndPlay();
		}
		
		
		
	}
private void onClickF()
{
	//	Score--;
		if(Score<0)
		{
		//	resign();
		}
		for(int i=0;i<Score+1;i++)
			WLL[i].setBackground(Color.RED);
		if(Score>=1 && Score<6)
		{
			saveScore(WLL[1].getText());
			Alert.iB("Niestety przegrałeś. Wygrałeś gwarantowane "+WLL[1].getText(), Nick+" wygrałeś gwarantowaną kwotę");
		}
		else if(Score>=6)
		{
			saveScore(WLL[6].getText());
			Alert.iB("Niestety przegrałeś. Wygrałeś gwarantowane "+WLL[6].getText(), Nick+" wygrałeś gwarantowaną kwotę");

		}
		if(Alert.qB("Chcesz zagrać jeszcze raz?", Nick+" niestety przegrałeś!"))
		{
			resign3();
			globalClear();
			setQandA();
		}
		else
		{
			resign();
		}
	
	}

	
	private void pickA()
	{
		if(check(ABA[0].getText().substring(3, ABA[0].getText().length())))
		{
			Score++;
			onClick();
		}
		else
		{
			onClickF();
		}
	}
	private void pickB()
	{
		if(check(ABA[1].getText().substring(3, ABA[1].getText().length())))
		{
			Score++;
			onClick();
		}
		else
		{
			onClickF();
		}
	}
	private void pickC()
	{
		if(check(ABA[2].getText().substring(3, ABA[2].getText().length())))
		{
			Score++;
			onClick();
		}
		else
		{
			onClickF();
		}
	}
	private void pickD()
	{
		if(check(ABA[3].getText().substring(3, ABA[3].getText().length())))
		{
			Score++;
			onClick();
		}
		else
		{
			onClickF();
		}
	}
	private void saveScore(String Value) //Zapis wyniku
	{
		Value=Value.replaceAll("\\s","");
		dbc.UpdateBestScore(Value);
	}
	private void CleanAndPlay()
	{
		
		Clear();
		setQandA();
		
	}
	private void Phone()
	{
		String w=getPtrOnWrong(),c=getPtrOnCorrect();
		
		Random rand =new Random();
		if(rand.nextInt(101)<10)
		{
			while(w.equals(c))
			{
				w=getPtrOnWrong();
			}
			Alert.iB("Nie jestem przekonany, ale chyba będzie to '"+w+"'", "Przyjaciel:");
		}
		else if(rand.nextInt(101)>=10 || rand.nextInt(101)<50 )
		{
			while(w.equals(c))
			{
				w=getPtrOnWrong();
			}
			Random rd=new Random();
			if(rd.nextInt(2)==0)
			{
				Alert.iB("Waham się między '"+w+"', a '"+c+"'", "Przyjaciel:");
			}
			else
			{
				Alert.iB("Waham się między '"+c+"', a '"+w+"'", "Przyjaciel:");

			}
		}
		else
		{
			Alert.iB("Jestem prawie pewien, że będzie to odpowiedz '"+c+"'", "Przyjaciel:");
		}
			
		
	}
	private void FF()
	{
		String w=getPtrOnWrong(),c=getPtrOnCorrect();
		while(w.equals(c))
		{
			w=getPtrOnWrong();
		}
	//	System.out.println(w);
		for(int i=0;i<4;i++)
		{
			if(!ABA[i].getText().substring(0, 2).equals(c) && !ABA[i].getText().substring(0, 2).equals(w))
			{
				ABA[i].setEnabled(false);
			}
		}
		
		
	}
	private void CallPublic()
	{
		String c=getPtrOnCorrect();
		int ptrOnC=0,valueOfW=0,full=100,sem=0;
		int[] tableOfV=new int[4];
		double[] tableOfDV=new double[4];
		Random rand=new Random();
		for(int i=0;i<4;i++)
		{
			if(ABA[i].getText().substring(0, 2).equals(c))
			{
				tableOfV[i]= rand.nextInt(20)+40;
				ptrOnC=i;
				break;
			}
		}
		full-=tableOfV[ptrOnC];
	//	System.out.println(full);
		valueOfW=full/3;
	//	System.out.println(valueOfW);
		for(int i=0;i<4;i++)
		{
			if(i!=ptrOnC)
			{
				if(sem==0)
				{
					tableOfV[i]=valueOfW+2;
				}
				if(sem==1)
				{
					tableOfV[i]=valueOfW-20;
				}
				if(sem==2)
				{
					tableOfV[i]=valueOfW+18;
				}
			}
		}
		full=0;
		for(int i=0;i<4;i++)
		{
			full+=tableOfV[i];
		}
		if(full!=100)
		{
			tableOfV[rand.nextInt(4)]+=(100-full);
		}
		for(int i=0;i<4;i++)
		{
			tableOfDV[i]=1.0*tableOfV[i];
		}
		BCF=new BarChartFrame(tableOfDV,NrOfQuestion);
		BCF_Exist=true;
	//	System.out.println(full);
	}
	private void Clear()
	{
		for(int i=0;i<4;i++)
		{
			ABA[i].setEnabled(true);
		}
		ABA[0].setText("A: ");
		ABA[1].setText("B: ");
		ABA[2].setText("C: ");
		ABA[3].setText("D: ");
	}	
	private void clearAfterGame()
	{
		dbc.UpdateGames();
		FF_Button.setEnabled(true);
		FriendCallButton.setEnabled(true);
		PublicCallButton.setEnabled(true);
		for(int i=0;i<4;i++)
		{
			ABA[i].setEnabled(true);
		}
	}
	private void globalClear()
	{
		Clear();
		NrOfQuestion=1;
		Score=-1;
		
		for(int i=0;i<12;i++)
		{
			if(i==1 || i==6)
			{
				WLL[i].setBackground(Color.YELLOW);
				WLL[i].setForeground(Color.BLUE);
			}
			else
			{
				WLL[i].setBackground(Color.LIGHT_GRAY);
			}
		}
	//	setQandA();
		//labele
	}
	private void resign()
	{
		Close();
		AfterLoginForm alf=new AfterLoginForm();
		alf.setSize(450,100);
		alf.setNick(Nick);
		alf.setLocationRelativeTo(null);
		alf.setResizable(false); 
		alf.setBackground(Color.LIGHT_GRAY);
		alf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		alf.setVisible(true);
	}
	private void resign2()
	{
		
		if(Score<0)
		{
			resign();
		}
		else
		{
			
			String sScore=WLL[Score].getText();
			alerts.iB("Gratulacje. Wygrałeś "+sScore, Nick+" gratulacje!");
			saveScore(sScore);
			resign();
		}
	}
	private void resign3()
	{
		Close();
		AfterLoginForm alf=new AfterLoginForm();
		alf.setNick(Nick);
		alf.setVisible(false);
		alf.Graj();
	}
	private void Close()
	{
		this.setVisible(false);
		this.dispose();
	}	
	@Override
	public void keyPressed(KeyEvent e) {
		// A B C D T Y F  VK_ESCAPE
		
		int key = e.getKeyCode();
		if (key==KeyEvent.VK_A)
		{
			if(BCF_Exist)
			{
				BCF.Close();
				pickA();
			}
			else
			{
				pickA();
			}
		}	
		if (key==KeyEvent.VK_B)
		{
			if(BCF_Exist)
			{
				BCF.Close();
				pickB();
			}
			else
			{
				pickB();
			}
		}	
		if (key==KeyEvent.VK_C)
		{
			if(BCF_Exist)
			{
				BCF.Close();
				pickC();
			}
			else
			{
				pickC();
			}
		}	
		if (key==KeyEvent.VK_D)
		{
			if(BCF_Exist)
			{
				BCF.Close();
				pickD();
			}
			else
			{
				pickD();
			}
		}	
		if (key==KeyEvent.VK_ESCAPE)
		{
			resign2();
		}	
		if (key==KeyEvent.VK_I)
		{
			CallPublic();
			PublicCallButton.setEnabled(false);
		}	
		if (key==KeyEvent.VK_O)
		{
			Phone();
			FriendCallButton.setEnabled(false);
		}
		if (key==KeyEvent.VK_P)
		{
			FF();
			FF_Button.setEnabled(false);
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
		if (ResignButton.hasFocus())
		{
			resign2();
		}
		if (ABA[0].hasFocus())
		{
			if(BCF_Exist)
			{
				BCF.Close();
				pickA();
			}
			else
			{
				pickA();
			}
			
		}
		if (ABA[1].hasFocus())
		{
			if(BCF_Exist)
			{
				BCF.Close();
				pickB();
			}
			else
			{
				pickB();
			}
		}
		if (ABA[2].hasFocus())
		{
			if(BCF_Exist)
			{
				BCF.Close();
				pickC();
			}
			else
			{
				pickC();
			}
		}
		if (ABA[3].hasFocus())
		{
			if(BCF_Exist)
			{
				BCF.Close();
				pickD();
			}
			else
			{
				pickD();
			}
		}
		if (PublicCallButton.hasFocus())
		{
			CallPublic();
			PublicCallButton.setEnabled(false);
		}
		if (FF_Button.hasFocus())
		{
			FF();
			FF_Button.setEnabled(false);
		}
		if (FriendCallButton.hasFocus())
		{
			Phone();
			FriendCallButton.setEnabled(false);
		}
		
	}
	
}
