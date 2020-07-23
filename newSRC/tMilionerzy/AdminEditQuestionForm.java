package tMilionerzy;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

public class AdminEditQuestionForm extends JFrame implements ActionListener, KeyListener
{
	
	//ZMIENNE
	private String Nick;
	private alerts Alert;
	private JComboBox QuestionList;
	private JLabel ansALabel,ansBLabel,ansCLabel,ansDLabel,CorrectLabel;
	private JTextField ansABox,ansBBox,ansCBox,ansDBox,CorrectBox;
	private JButton ConfirmButton,BackButton;
	private DBConnection dbc=new DBConnection();
	private String[] arrayOfQuestion;
	private String[] QuestionFromDB;
	//FORMATKA
	public AdminEditQuestionForm()
	{
		this.setLayout(null);
		
		dbc.EstablishConnection();
		
		arrayOfQuestion=dbc.getQArray();
				
		QuestionList=new JComboBox(arrayOfQuestion);
		QuestionList.setSelectedIndex(0);
		QuestionList.setBounds(20,10,300,30);
		QuestionList.addActionListener(this);
		add(QuestionList);
		
		
		
		ansALabel=new JLabel(": Odp A");
		ansALabel.setBounds(200,50,100,30);
		add(ansALabel);
		
		ansBLabel=new JLabel(": Odp B");
		ansBLabel.setBounds(200,100,100,30);
		add(ansBLabel);
		
		ansCLabel=new JLabel(": Odp C");
		ansCLabel.setBounds(200,150,100,30);
		add(ansCLabel);
		
		ansDLabel=new JLabel(": Odp D");
		ansDLabel.setBounds(200,200,100,30);
		add(ansDLabel);
		
		CorrectLabel=new JLabel(": Poprawna");
		CorrectLabel.setBounds(200,250,100,30);
		add(CorrectLabel);
		
		ansABox=new JTextField("");
		ansABox.setBounds(20,50,150,30);
		add(ansABox);
		
		ansBBox=new JTextField("");
		ansBBox.setBounds(20,100,150,30);
		add(ansBBox);
		
		ansCBox=new JTextField("");
		ansCBox.setBounds(20,150,150,30);
		add(ansCBox);
		
		ansDBox=new JTextField("");
		ansDBox.setBounds(20,200,150,30);
		add(ansDBox);
		
		CorrectBox=new JTextField("");
		CorrectBox.setBounds(20,250,150,30);
		add(CorrectBox);
		
		ConfirmButton=new JButton("Zatwierdz");
		ConfirmButton.setBounds(20,300,100,30);
		ConfirmButton.addActionListener(this);
		add(ConfirmButton);
		
		BackButton=new JButton("Cofnij");
		BackButton.setBounds(150,300,100,30);
		BackButton.addActionListener(this);
		add(BackButton);
		
		
		updateV();
	}
	
	
	//FUNCTION
	public void updateV()
	{
		QuestionFromDB=dbc.getAnswers(QuestionList.getSelectedIndex()+1);
		ansABox.setText(QuestionFromDB[0]);
		ansBBox.setText(QuestionFromDB[1]);
		ansCBox.setText(QuestionFromDB[2]);
		ansDBox.setText(QuestionFromDB[3]);
		CorrectBox.setText(QuestionFromDB[4]);
	}
	
	private void Cofnij()
	{
		Close();
		AdminQuestionMenuForm aqmf=new AdminQuestionMenuForm();
		aqmf.setNick(Nick);
		aqmf.setSize(500,100);
		aqmf.setLocationRelativeTo(null);
		aqmf.setResizable(false); 
		aqmf.setBackground(Color.GRAY);
		aqmf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		aqmf.setVisible(true);
	}
	private void Close()
	{
		this.setVisible(false);
		this.dispose();
	}
	public void setNick(String Nick)
	{
		this.Nick=Nick;
		setTitle("Admin: "+this.Nick);
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		if(BackButton.hasFocus())
		{
			Cofnij();
		}
		if(QuestionList.hasFocus())
		{
			updateV();
		}
		if(ConfirmButton.hasFocus())
		{
			String Answers=ansABox.getText()+";"+ansBBox.getText()+";"+ansCBox.getText()+";"+ansDBox.getText()+";";
		//	System.out.println(Answers);
			int ques=QuestionList.getSelectedIndex()+1;
			if(dbc.updateQuestionInfo(ques,Answers,CorrectBox.getText()))
			{
				alerts.iB("Informacje zaktualizowano", "Aktualizacja danych");
				updateV();
			}
			else
			{
				alerts.iB("Informacje nie zostały zaktualizowane", "Error");
			}
		}
	}

}
