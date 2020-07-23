package tMilionerzy;

import java.awt.Color;

import javax.swing.JFrame;

public class mainMilionerzy {

	public static void main(String[] args) 
	{
		LoginForm lf=new LoginForm();
		lf.setSize(320,320);
		lf.setLocationRelativeTo(null);
		lf.setResizable(false); 
		lf.setBackground(Color.LIGHT_GRAY);
		lf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		lf.setVisible(true);
		
	}

}
