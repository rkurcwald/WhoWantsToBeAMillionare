package tMilionerzy;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class AdminUserMenuForm extends JFrame implements ActionListener
{

	//Zmienne
	private String Nick;
	private alerts Alert;
	private JButton EditDeleteButton,AddUserButton,BackButton;
	
	//FORMATKA
	public AdminUserMenuForm()
	{
		setLayout(null);
		
		AddUserButton=new JButton("Dodaj użytkownika");
		AddUserButton.setBounds(10,17,150,30);
		add(AddUserButton);
		AddUserButton.addActionListener(this);
		
		EditDeleteButton=new JButton("Edytuj użytkowników");
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
		AdminAddUserForm aauf=new AdminAddUserForm();
		aauf.setNick(Nick);
		aauf.setSize(340,350);
		aauf.setLocationRelativeTo(null);
		aauf.setResizable(false); 
		aauf.setBackground(Color.GRAY);
		aauf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		aauf.setVisible(true);
	}
	private void Edytuj()
	{
		Close();
		AdminEditUserForm aeuf=new AdminEditUserForm();
		aeuf.setNick(Nick);
		aeuf.setSize(340,350);
		aeuf.setLocationRelativeTo(null);
		aeuf.setResizable(false); 
		aeuf.setBackground(Color.GRAY);
		aeuf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		aeuf.setVisible(true);
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
