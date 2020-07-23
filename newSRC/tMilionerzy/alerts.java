package tMilionerzy;

import javax.swing.JOptionPane;

public class alerts {

	private static int dialogResult;
	public static void iB(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
    }

	public static boolean qB(String question,String title)
	{
		dialogResult = JOptionPane.showConfirmDialog (null, question,title,JOptionPane.YES_NO_OPTION);
		if(dialogResult == JOptionPane.YES_OPTION)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
}
