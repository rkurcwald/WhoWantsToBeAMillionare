package tMilionerzy;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

public class AdminAddQuestionForm extends JFrame implements ActionListener, KeyListener
{

	

	
	private static final long serialVersionUID = 1L;
	//ZMIENNE
	private String Nick;
	private alerts Alert;
	private JLabel QuestionLabel,AnswersALabel,AnswersBLabel,AnswersCLabel,AnswersDLabel,CorrectLabel;
	private JTextField QuestionBox,AnswerABox,AnswerBBox,AnswerCBox,AnswerDBox,CorrectBox;
	private JButton ConfirmButton,ClearButton,BackButton;
	DBConnection dbc=new DBConnection();
	
	//FORMATKA
	public AdminAddQuestionForm()
	{
		this.setLayout(null);
		
		QuestionLabel=new JLabel(": Pytanie");
		QuestionLabel.setBounds(170, 20, 50, 30);
		add(QuestionLabel);
		
		
		AnswersALabel=new JLabel(": Odpowiedz A");
		AnswersALabel.setBounds(170,60,100,30);
		add(AnswersALabel);
		
		AnswersBLabel=new JLabel(": Odpowiedz B");
		AnswersBLabel.setBounds(170,100,100,30);
		add(AnswersBLabel);
		
		AnswersCLabel=new JLabel(": Odpowiedz C");
		AnswersCLabel.setBounds(170,140,100,30);
		add(AnswersCLabel);
		
		AnswersDLabel=new JLabel(": Odpowiedz D");
		AnswersDLabel.setBounds(170,180,100,30);
		add(AnswersDLabel);
		
		CorrectLabel=new JLabel(": Poprawna odp");
		CorrectLabel.setBounds(170,220,100,30);
		add(CorrectLabel);
		
		QuestionBox=new JTextField("");
		QuestionBox.setBounds(30,20,120,30);
		add(QuestionBox);
		
		AnswerABox=new JTextField("");
		AnswerABox.setBounds(30,60,120,30);
		add(AnswerABox);
		
		AnswerBBox=new JTextField("");
		AnswerBBox.setBounds(30,100,120,30);
		add(AnswerBBox);
		
		AnswerCBox=new JTextField("");
		AnswerCBox.setBounds(30,140,120,30);
		add(AnswerCBox);
		
		AnswerDBox=new JTextField("");
		AnswerDBox.setBounds(30,180,120,30);
		add(AnswerDBox);
		
		CorrectBox=new JTextField("");
		CorrectBox.setBounds(30,220,120,30);
		add(CorrectBox);
		
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
	private void DodajPytanie()
	{
		dbc.EstablishConnection();
		if(!dbc.AddQuestionToDB(QuestionBox.getText(),AnswerABox.getText(), AnswerBBox.getText(), AnswerCBox.getText(),AnswerDBox.getText(),CorrectBox.getText()))
		{
			Cofnij();
		}
		else
		{

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
		AdminQuestionMenuForm aqm=new AdminQuestionMenuForm();
		aqm.setSize(500,100);
		aqm.setNick(Nick);
		aqm.setLocationRelativeTo(null);
		aqm.setResizable(false); 
		aqm.setBackground(Color.LIGHT_GRAY);
		aqm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		aqm.setVisible(true);
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
			DodajPytanie();
		}

	}
	private void Wyczysc()
	{
		QuestionBox.setText("");
		AnswerABox.setText("");
		AnswerBBox.setText("");
		AnswerCBox.setText("");
		AnswerDBox.setText("");
		CorrectBox.setText("");
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
			DodajPytanie();
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
