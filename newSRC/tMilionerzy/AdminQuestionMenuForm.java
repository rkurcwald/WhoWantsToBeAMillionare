package tMilionerzy;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class AdminQuestionMenuForm extends JFrame implements ActionListener
{

	//Zmienne
	private String Nick;
	private alerts Alert;
	private JButton EditDeleteButton,AddUserButton,BackButton;
	
	//FORMATKA
	public AdminQuestionMenuForm()
	{
		setLayout(null);
		
		AddUserButton=new JButton("Dodaj pytanie");
		AddUserButton.setBounds(10,17,150,30);
		add(AddUserButton);
		AddUserButton.addActionListener(this);
		
		EditDeleteButton=new JButton("Edytuj pytanie");
		EditDeleteButton.setBounds(170,17,180,30);
		add(EditDeleteButton);
		EditDeleteButton.addActionListener(this);
		
		BackButton=new JButton("Cofnij");
		BackButton.setBounds(360,17,120,30);
		add(BackButton);
		BackButton.addActionListener(this);
		
	}
	//FUNCTION
	public void setNick(String Nick)
	{
		this.Nick=Nick;
		setTitle("Admin: "+this.Nick);
	}
	private void Close()
	{
		this.setVisible(false);
		this.dispose();
	}
	private void Cofnij()
	{
		Close();
		AfterLoginAdminForm alaf=new AfterLoginAdminForm();
		alaf.setNick(Nick);
		alaf.setSize(500,100);
		alaf.setLocationRelativeTo(null);
		alaf.setResizable(false); 
		alaf.setBackground(Color.GRAY);
		alaf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		alaf.setVisible(true);
	}
	private void Dodaj()
	{
		Close();
		AdminAddQuestionForm aaqf=new AdminAddQuestionForm();
		aaqf.setNick(Nick);
		aaqf.setSize(340,350);
		aaqf.setLocationRelativeTo(null);
		aaqf.setResizable(false); 
		aaqf.setBackground(Color.GRAY);
		aaqf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		aaqf.setVisible(true);
	}
	private void Edytuj()
	{
		Close();
		AdminEditQuestionForm aeqf=new AdminEditQuestionForm();
		aeqf.setNick(Nick);
		aeqf.setSize(340,380);
		aeqf.setLocationRelativeTo(null);
		aeqf.setResizable(false); 
		aeqf.setBackground(Color.GRAY);
		aeqf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		aeqf.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		if(BackButton.hasFocus())
		{
			Cofnij();
		}
		if(EditDeleteButton.hasFocus())
		{
			Edytuj();
		}
		if(AddUserButton.hasFocus())
		{
			Dodaj();
		}
		
	}
}
