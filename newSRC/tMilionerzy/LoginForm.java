package tMilionerzy;



import java.awt.Color;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;


public class LoginForm extends JFrame implements ActionListener, KeyListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9093636855818869613L;
	//ZMIENNE
	private JLabel LoginLabel,PasswordLabel,InfoLabel;
	private JTextField LoginBox;
	private JPasswordField PasswordBox;
	private JButton OKButton,RegisterButton;
	private int userType=0;
	private String Nick="";
	DBConnection dbc=new DBConnection();
	//FORMATKA
	LoginForm()
	{
		setTitle("Milionerzy v1"); 
		setLayout(null);
		
		InfoLabel=new JLabel("MILIONERZY");
		InfoLabel.setBounds(120,15,100,20);
		add(InfoLabel);

		LoginLabel=new JLabel("Login: ");
		LoginLabel.setBounds(60, 60, 50, 30);
		add(LoginLabel);
		
		PasswordLabel=new JLabel("Hasło: ");
		PasswordLabel.setBounds(60,110,50,30);
		add(PasswordLabel);
		
		LoginBox=new JTextField("");
		LoginBox.setBounds(120,60,100,30);
		add(LoginBox);
		
		PasswordBox=new JPasswordField("");
		PasswordBox.setBounds(120,110,100,30);
		add(PasswordBox);
	
		OKButton=new JButton("Zaloguj");
		OKButton.setBounds(120,160,100,20);
		add(OKButton);
		OKButton.addActionListener(this);
		OKButton.addKeyListener(this);
		
		RegisterButton=new JButton("Zarejestruj");
		RegisterButton.setBounds(120, 190, 100, 20);
		add(RegisterButton);
		RegisterButton.addActionListener(this);
		RegisterButton.addKeyListener(this);
		
		
		
	}

	
	//FUNCTIONS
	
	public void Logowanie()
	{
		String passText = new String(PasswordBox.getPassword());
		if (LoginBox.getText().length() > 0 && passText.length() > 0)
		{
			dbc.EstablishConnection();
			if(dbc.LoginFunction(LoginBox.getText(), passText))
			{
				LoginBox.setText("");
				PasswordBox.setText("");
				Nick=dbc.getCurrentUser();
				userType= dbc.getUT();
						
				if(userType==0) //User
				{
					CloseWindow();
			//		this.setEnabled(false);
					AfterLoginForm alf=new AfterLoginForm();
					alf.setSize(450,100);
					alf.setNick(Nick);
					alf.setLocationRelativeTo(null);
					alf.setResizable(false); 
					alf.setBackground(Color.LIGHT_GRAY);
					alf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
					alf.setVisible(true);
				}
				else //Admin
				{
					CloseWindow();
					AfterLoginAdminForm alaf=new AfterLoginAdminForm();
					alaf.setNick(Nick);
					alaf.setSize(500,100);
					alaf.setLocationRelativeTo(null);
					alaf.setResizable(false); 
					alaf.setBackground(Color.GRAY);
					alaf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
					alaf.setVisible(true);
				}
			}
			else
			{
				alerts.iB("Podałeś nieprawidłowe dane!", "Error");
			}
			
	//		System.out.println(userInfo[0]+" "+userInfo[1]);
		}
		else
		{
			alerts.iB("Najpierw uzupełnij dane!", "LoginFormError");
		}
		

		
		
	}

	public void CloseWindow()
	{
		this.setVisible(false);
		this.dispose();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key==KeyEvent.VK_ENTER)
		{
			Logowanie();
		}	
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (OKButton.hasFocus())
		{
			Logowanie();
		}
		
		if (RegisterButton.hasFocus())
		{
			CloseWindow();
			RegisterForm r = new RegisterForm();
			r.setSize(320,320);
			r.setLocationRelativeTo(null);
			r.setResizable(false); 
			r.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
			r.setVisible(true);
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

}
