package tMilionerzy;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

public class AdminEditUserForm extends JFrame implements ActionListener, KeyListener
{
	
	//ZMIENNE
	private String Nick;
	private alerts Alert;
	private JComboBox UserList;
	private JLabel SurnameLabel,emailLabel,passwordLabel,TypeLabel;
	private JTextField SurnameBox,EmailBox,PasswordBox,TypeBox;
	private JButton ConfirmButton,BackButton;
	private DBConnection dbc=new DBConnection();
	private String[] arrayOfNick;
	//FORMATKA
	public AdminEditUserForm()
	{
		this.setLayout(null);
		
		dbc.EstablishConnection();
		
		arrayOfNick=dbc.getUArray();
				
		UserList=new JComboBox<>(arrayOfNick);
		UserList.setSelectedIndex(0);
		UserList.setBounds(20,10,150,30);
		UserList.addActionListener(this);
		add(UserList);
		
		
		
		SurnameLabel=new JLabel(": Imię");
		SurnameLabel.setBounds(200,50,100,30);
		add(SurnameLabel);
		
		emailLabel=new JLabel(": e-mail");
		emailLabel.setBounds(200,100,100,30);
		add(emailLabel);
		
		passwordLabel=new JLabel(": Hasło");
		passwordLabel.setBounds(200,150,100,30);
		add(passwordLabel);
		
		TypeLabel=new JLabel(": Typ");
		TypeLabel.setBounds(200,200,100,30);
		add(TypeLabel);
		
		SurnameBox=new JTextField("");
		SurnameBox.setBounds(20,50,150,30);
		add(SurnameBox);
		
		EmailBox=new JTextField("");
		EmailBox.setBounds(20,100,150,30);
		add(EmailBox);
		
		PasswordBox=new JTextField("");
		PasswordBox.setBounds(20,150,150,30);
		add(PasswordBox);
		
		TypeBox=new JTextField("");
		TypeBox.setBounds(20,200,150,30);
		add(TypeBox);
		
		ConfirmButton=new JButton("Zatwierdz");
		ConfirmButton.setBounds(20,250,100,30);
		ConfirmButton.addActionListener(this);
		add(ConfirmButton);
		
		BackButton=new JButton("Cofnij");
		BackButton.setBounds(150,250,100,30);
		BackButton.addActionListener(this);
		add(BackButton);
		
		
		updateV();
	}
	
	
	//FUNCTION
	public void updateV()
	{
		String name=arrayOfNick[UserList.getSelectedIndex()];
		SurnameBox.setText(dbc.getSurname(name));
		EmailBox.setText(dbc.getEmail(name));
		PasswordBox.setText(dbc.getPassword(name));
		TypeBox.setText(dbc.getType(name));
	}
	
	private void Cofnij()
	{
		Close();
		AdminUserMenuForm aumf=new AdminUserMenuForm();
		aumf.setNick(Nick);
		aumf.setSize(500,100);
		aumf.setLocationRelativeTo(null);
		aumf.setResizable(false); 
		aumf.setBackground(Color.GRAY);
		aumf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		aumf.setVisible(true);
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
		if(UserList.hasFocus())
		{
			updateV();
		}
		if(ConfirmButton.hasFocus())
		{
			if(dbc.updateUserInfo(arrayOfNick[UserList.getSelectedIndex()],SurnameBox.getText(),EmailBox.getText(),PasswordBox.getText(),TypeBox.getText()))
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
