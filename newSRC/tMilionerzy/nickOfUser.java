package tMilionerzy;

public class nickOfUser 
{
	public static String setNick(String Nick,int Type)
	{
		if(Type==1)
		{
			return "Admin: "+Nick;
		}
		else
		{
			return "Zalogowano jako: "+Nick;
		}
	}
}
