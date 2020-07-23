package tMilionerzy;

public class RegisterCheck extends RegisterForm
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5081124250745448336L;
	
	String numRegex   = ".*[0-9].*";
	String alphaRegex = ".*[A-Z].*";

	public boolean sprawdzEmail(String email)
	{
		int emailDlugosc = email.length();
		boolean monkey = false;
		if (emailDlugosc >= 8)
		{
			if (email.matches(numRegex) || email.matches(alphaRegex))
			{
				char ch;
				String e = email;
				for (int i = 0; i < emailDlugosc; i++)
				{
					ch = e.charAt(i);
					if (ch == '@')
					{
						monkey = true;
					}
				}
				
				if (monkey == true)
				{
					if (email.substring(emailDlugosc - 3, emailDlugosc - 2).contains(".") 
							&& email.substring(emailDlugosc - 2, emailDlugosc - 1).contains("p") 
							&& email.substring(emailDlugosc - 1, emailDlugosc).contains("l"))
					{
						return true;
					}
					else if (email.substring(emailDlugosc - 4, emailDlugosc - 3).contains(".") 
							&& email.substring(emailDlugosc - 3, emailDlugosc - 2).contains("c") 
							&& email.substring(emailDlugosc - 2, emailDlugosc - 1).contains("o")
							&& email.substring(emailDlugosc - 1, emailDlugosc).contains("m"))
					{
						return true;
					}
					else
					{
						return false;
					}
				}
				else
				{
					return false;
				}
			}
			else
			{
				return false;
			}
			
		}
		else
		{
			return false;
		}
		
	}
	
	private boolean czyAdmin(String string1, String string2)
	{
		if(string1.contains(string2))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public boolean sprawdzNick(String nick)
	{
		int nickDlugosc = nick.length();
		if (nickDlugosc > 3 && nickDlugosc < 25)
		{
			if (nick.matches(numRegex) || nick.matches(alphaRegex))
			{
				String Nick = nick;
				String adminNick = nick.toLowerCase();
				if (czyAdmin(adminNick, "admin") || czyAdmin(adminNick, "root") )
				{
					return false;
				}
				else
				{
					char ch;
					int upperCase = 0;
					for (int i = 0; i < Nick.length(); i++)
					{
						ch = Nick.charAt(i);
						if (Character.isUpperCase(ch))
						{
							upperCase++;
						}
					}
					if (upperCase > 1 || upperCase == 0)
					{
						return false;
					}
					else
					{
						return true;
					}
				}
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}
	
	public boolean sprawdzHaslo(String haslo,String haslo2)
	{
		char ch;
		boolean isDigit = false, upperCase = false, lowerCase = false;
		int hasloDlugosc = haslo.length();
		
		if(haslo.equals(haslo2))
		{
			if (hasloDlugosc > 3)
			{
				if (haslo.matches(numRegex) || haslo.matches(alphaRegex))
				{
					String Haslo = haslo;
					for (int i = 0; i < Haslo.length(); i++)
					{
						ch = Haslo.charAt(i);
						if (Character.isDigit(ch))
						{
							isDigit = true;
						}
						else if (Character.isUpperCase(ch))
						{
							upperCase = true;
						}
						else if (Character.isLowerCase(ch))
						{
							lowerCase = true;
						}
					}
					if (isDigit == true && upperCase == true && lowerCase == true)
					{
						return true;
					}
					else
					{
						return false;
					}
				}
				else
				{
					return false;
				}
			
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}
}
