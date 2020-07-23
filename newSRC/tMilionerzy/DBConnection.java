package tMilionerzy;

import java.sql.*;
import java.util.ArrayList;



public class DBConnection 
{
	//ZMIENNE
	public Connection con;
	public Statement st;
	public ResultSet rs;
	public PreparedStatement pstmt;
	private String UserInfo;
	private boolean NOE_Var,ResetV=false;
	private String QFromDB="",AFromDB="";
	public static String currentUser="";
	private int UT=0;
	private ArrayList<String> questions,users;
	private String[] array;
	//FUNKCJE
	public void EstablishConnection()
	{
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/milionerzy","root","");
			st=con.createStatement();
		}
		catch(Exception ex) 
		{
			System.out.println("Błąd połączenia z bazą danych: " + ex);
		}
		
	}
	public String[] getQArray()
	{
		getQuestions();
		array=questions.toArray(new String[questions.size()]);
		return array;
	}
	public String[] getUArray()
	{
		getUsers();
		array=users.toArray(new String[users.size()]);
		return array;
	}
	public int CountNonAdmin()
	{
		int x=0;
		try 
		{
			String query = "select Count(*) from users where Type=0";
			rs = st.executeQuery(query);
			while(rs.next()) 
			{
				x=Integer.parseInt(rs.getString("Count(*)"));
			}
			rs.beforeFirst();
			
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
		
		return x;
	}
	public int[] SelectHMWSort()
	{
		int howManyPlayers=CountNonAdmin();
		int[] HMW = new int[howManyPlayers];
		
		int i = 0;
		try
		{
			String query = "SELECT HowManyWins FROM users WHERE Type=0 ORDER BY bestScore DESC;";
			rs = st.executeQuery(query);
			while(rs.next()) 
			{
		//		System.out.println("Tu dziala HMW "+HMW[i]);

				HMW[i] = Integer.parseInt(rs.getString("HowManyWins"));
				i++;
			}
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		return HMW;
		
	}
	public int[] SelectGamesSort()
	{
		int howManyPlayers=CountNonAdmin();
		int[] games = new int[howManyPlayers];
		
		int i = 0;
		try
		{
			String query = "SELECT games FROM users WHERE Type=0 ORDER BY bestScore DESC;";
			rs = st.executeQuery(query);
			while(rs.next()) 
			{
		//		System.out.println("Tu dziala HMG "+games[i]);
				games[i] = Integer.parseInt(rs.getString("games"));
				i++;
			}
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		return games;
		
	}
	public String[] SelectUsersSort()
	{
		int howManyPlayers=CountNonAdmin();
		String[] nick = new String[howManyPlayers];
		
		int i = 0;
		try
		{
			String query = "SELECT Nick FROM users WHERE Type=0 ORDER BY bestScore DESC;";
			rs = st.executeQuery(query);
			while(rs.next()) 
			{
				nick[i] = rs.getString("Nick");
				i++;
			}
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		return nick;
		
	}
	public int[] SelectScoresSort()
	{
		int howManyPlayers=CountNonAdmin();
		int[] score = new int[howManyPlayers];
		
		int i = 0;
		try
		{
			String query = "SELECT bestScore FROM users WHERE Type=0 ORDER BY bestScore DESC;";
			rs = st.executeQuery(query);
			while(rs.next()) 
			{
			//	System.out.println("Tu dziala Score "+score[i]);

				score[i] = Integer.parseInt(rs.getString("bestScore"));
				i++;
			}
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		return score;
	}
	public String[] getAnswers(int index)
	{
		String returnV="", query;;
		String[] returnArrayV;
		try
		{
			query = "SELECT Answers FROM questions WHERE ID=" + "'" +index + "'";
			rs = st.executeQuery(query);
			while(rs.next())
			{
				AFromDB=rs.getString("Answers");
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
		try
		{
			query = "SELECT CorrectAnswer FROM questions WHERE ID=" + "'" +index + "'";
			rs = st.executeQuery(query);
			while(rs.next())
			{
				AFromDB+=rs.getString("CorrectAnswer");
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}

		returnArrayV=AFromDB.split(";");
	//	System.out.println(returnArrayV.length);
		return returnArrayV;
	}

	public String getSurname(String name)
	{
		String returnV="";
		try
		{
			String query = "SELECT Surname FROM users WHERE Nick=" + "'" +name + "'";
			rs = st.executeQuery(query);
			while(rs.next()) 
			{
				returnV = rs.getString("Surname");
			}

		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		return returnV;
	}
	public String getEmail(String name)
	{
		String returnV="";
		try
		{
			String query = "SELECT Email FROM users WHERE Nick=" + "'" +name + "'";
			rs = st.executeQuery(query);
			while(rs.next()) 
			{
				returnV = rs.getString("Email");
			}

		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		return returnV;
	}
	public String getPassword(String name)
	{
		String returnV="";
		try
		{
			String query = "SELECT password FROM users WHERE Nick=" + "'" +name + "'";
			rs = st.executeQuery(query);
			while(rs.next()) 
			{
				returnV = rs.getString("password");
			}

		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		return returnV;
	}
	public String getType(String name)
	{
		String returnV="";
		try
		{
			String query = "SELECT Type FROM users WHERE Nick=" + "'" +name + "'";
			rs = st.executeQuery(query);
			while(rs.next()) 
			{
				returnV = rs.getString("Type");
			}

		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		return returnV;
	}
	private void getUsers()
	{
		users=new ArrayList<String>();
		try
		{
			String query="Select Nick From users";
			rs = st.executeQuery(query);
			while(rs.next())
				users.add(rs.getString("Nick"));
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
	}
	private boolean getQuestions()
	{
		questions=new ArrayList<String>();
		try
		{
			String query="Select Question From questions";
			rs = st.executeQuery(query);
			while(rs.next())
				questions.add(rs.getString("Question"));
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
		return true;
	}
	public boolean AddQuestionToDB(String Question,String A,String B,String C,String D,String Correct)
	{
		boolean sem=false;
		getQuestions();
		for(int i=0;i<questions.size();i++) //Potem
		{
			if(questions.get(i).equals(Question))
			{
				alerts.iB("To pytanie jest już w bazie!", "TryAgain");
				sem= true;
			}
		}
		
		if(!sem)
		{
			try
			{
				String answersOut=A+";"+B+";"+C+";"+D+";",QuestionOut=Question+";",WUUI="";
				String query="INSERT INTO questions (ID,Question,Answers,CorrectAnswer,WUUI) VALUES (null,"+"'"+QuestionOut+"'"+","+"'"+answersOut+"'"+","+"'"+Correct+"'"+","+"'"+WUUI+"')";
				pstmt=con.prepareStatement(query);
				pstmt.executeUpdate();
			}
			catch(Exception ex)
			{
				System.out.println(ex);
			}
		}
		
		return sem;
	}
	public boolean updateUserInfo(String nick,String surname,String email,String pass,String Type)
	{
		try
		{
			String query="UPDATE users SET Surname="+"'"+surname+"'"+", email="+"'"+email+"'"+", password="+"'"+pass+"'"+", Type="+"'"+Type+"'"+"WHERE Nick="+"'"+nick+"'";
			pstmt=con.prepareStatement(query);
			pstmt.executeUpdate();
			return true;
		}
		catch(Exception ex)
		{
			System.out.println(ex);
			return false;
		}
		
	}
	public boolean updateQuestionInfo(int ques,String answers,String correctAns)
	{
		try
		{
			String query="UPDATE questions SET Answers="+"'"+answers+"'"+", CorrectAnswer="+"'"+correctAns+"'"+"WHERE ID="+"'"+ques+"'";
			pstmt=con.prepareStatement(query);
			pstmt.executeUpdate();
			return true;
		}
		catch(Exception ex)
		{
			System.out.println(ex);
			return false;
		}
		
	}
	public boolean RegisterFunction(String login,String Surname,String password,String email,String typ)
	{
		if(!IsUserExist(login,email))
		{
			
			if(typ.equals("1"))
			{
				try
				{
					String query="INSERT INTO users (ID,Nick,password,email,Surname,bestScore,games,HowManyWins,Type,HMUQ) VALUES (null,"+"'"+login+"'"+","+"'"+password+"'"+","+"'"+email+"'"+","+"'"+Surname+"'"+",0,0,0,1,0)";
					pstmt=con.prepareStatement(query);
					pstmt.executeUpdate();
				}
				catch(Exception ex)
				{
					System.out.println(ex);
				}
				return true;
				
			}
			else
			{
				try
				{
					String query="INSERT INTO users (ID,Nick,password,email,Surname,bestScore,games,HowManyWins,Type,HMUQ) VALUES (null,"+"'"+login+"'"+","+"'"+password+"'"+","+"'"+email+"'"+","+"'"+Surname+"'"+",0,0,0,0,0)";
					pstmt=con.prepareStatement(query);
					pstmt.executeUpdate();
				}
				catch(Exception ex)
				{
					System.out.println(ex);
				}
				return true;
			}
		}
		else
		{
			if(NOE_Var==true)
			{
				alerts.iB("Konto o podanym loginie już istnieje. Popraw dane", "EXISTS");
			}
			else
			{
				alerts.iB("Konto o podanym emailu już istnieje. Popraw dane", "EXISTS");

			}
			return false;
		}
	}
//	public boolean LoginFunction(String login,String password)
//	{
//		return true;
//	}
	public void setCurrentUser(String currentUser)
	{
		this.currentUser=currentUser;
	}

	public String getCurrentUser()
	{
		return currentUser;
	}
	
	public boolean LoginFunction(String login, String password)
	{
		boolean userExist = false;
		String log = "";
		String pass = "";
		
		try
		{
			String query = "SELECT Nick, Password,Type FROM users WHERE Nick=" + "'" +login + "'" + " AND Password=" + "'" + password + "'";
			rs = st.executeQuery(query);
			while(rs.next()) 
			{
				log = rs.getString("Nick");
				pass = rs.getString("Password");
				UT=Integer.parseInt(rs.getString("Type"));
			}
			if (login.equals(log) && password.equals(pass))
			{
				setCurrentUser(log);
			//	System.out.println(currentUser);
				
				userExist = true;
			}
			rs.beforeFirst();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		return userExist;
	}
	public int getUT()
	{
		return UT;
	}
	public int howManyQ() throws NumberFormatException, SQLException
	{
		int x=0;
		String query = "SELECT Count(*) FROM questions";
		rs = st.executeQuery(query);
		while(rs.next())
		x=Integer.parseInt(rs.getString("Count(*)"));
		return x;
	}
/*	public boolean allowGame()
	{
		String query = "SELECT Count(*) FROM questions WHERE ID=" + "'" +index + "'";

		if()
		{
			
		}
		else
		{
			
		}
	}*/
	public boolean IsUsedQuestion(int index) throws SQLException
	{
		
		String[] WUUI_Table;
		String FromDB="";
		boolean out=false;
		String query = "SELECT WUUI FROM questions WHERE ID=" + "'" +index + "'";
		rs = st.executeQuery(query);
		while(rs.next())
		FromDB=rs.getString("WUUI");
	

		
		if(FromDB.isEmpty())
		{
		//	System.out.println("SHIT");
			out=false;
		}
		else
		{
		//	System.out.println("SHIT2");
			if(FromDB.substring(0, 1).equals(";"))
			{
				FromDB=FromDB.substring(1, FromDB.length());
		//		System.out.println(1+" "+FromDB);
			}
			WUUI_Table=FromDB.split(";");
			for(int i=0;i<WUUI_Table.length;i++)
			{
				if(WUUI_Table[i].equals(currentUser))
				{
					out=true;
					break;
				}
				else
				{
				
					out=false;
				}
			}
		}
		
		if(!out && !ResetV)
		{
		//	System.out.println("SHIT2");
			FromDB+=currentUser+";";
			try
			{
				int hmuq=0;
				query="UPDATE questions SET WUUI="+"'"+FromDB+"'"+"WHERE ID="+"'"+index+"'";
				pstmt=con.prepareStatement(query);
				pstmt.executeUpdate();
				
				query = "SELECT HMUQ FROM users WHERE Nick=" + "'" +currentUser + "'";
				rs = st.executeQuery(query);
				while(rs.next())
				hmuq=Integer.parseInt(rs.getString("HMUQ"))+1;
				
				query="UPDATE users SET HMUQ="+"'"+hmuq+"'"+"WHERE Nick="+"'"+currentUser+"'";
				pstmt=con.prepareStatement(query);
				pstmt.executeUpdate();
			}
			catch(Exception ex)
			{
				System.out.println(ex);
			}
			try
			{
				query = "SELECT Question FROM questions WHERE ID=" + "'" +index + "'";
				rs = st.executeQuery(query);
				QFromDB="";
				while(rs.next())
				{
					QFromDB+=rs.getString("Question");
				}
			}
			catch(Exception ex)
			{
				System.out.println(ex);
			}
			try
			{
				query = "SELECT Answers FROM questions WHERE ID=" + "'" +index + "'";
				rs = st.executeQuery(query);
				while(rs.next())
				{
					QFromDB+=rs.getString("Answers");
				}
			}
			catch(Exception ex)
			{
				System.out.println(ex);
			}
			try
			{
				query = "SELECT CorrectAnswer FROM questions WHERE ID=" + "'" +index + "'";
				rs = st.executeQuery(query);
				while(rs.next())
				{
					QFromDB+=rs.getString("CorrectAnswer");
				}
			}
			catch(Exception ex)
			{
				System.out.println(ex);
			}
	//		QFromDB+="test";
	//		System.out.println(QFromDB);
			
		}
		else if(ResetV) //Usuwanie danego uzytkownika czyli przeszukiwanie po tablicy i usuwanie wartosci (Lepiej ArrayList)
		{
			//int indexOfUser=0;
		//	System.out.println("SHIT3");
			for(int k=0;k<howManyQ();k++)
			{
			//	System.out.println("HMUQ test");

			try
			{
				String[] test=FromDB.split(";");
				//System.out.println(test[k]);
				FromDB="";
				
				for(int i=0;i<test.length;i++)
				{
					if(test[i].equals(currentUser))
					{
						FromDB+="";
					}
					else
					{
						FromDB+=test[i]+";";
					}
				}
				
				int ret0=0,newIndex=k+1;
				query="UPDATE users SET HMUQ="+"'"+ret0+"'"+"WHERE Nick="+"'"+currentUser+"'";
				pstmt=con.prepareStatement(query);
				pstmt.executeUpdate();
			//	System.out.println("HMUQ done");
				query="UPDATE questions SET WUUI="+"'"+FromDB+"'"+"WHERE ID="+"'"+newIndex+"'";
				pstmt=con.prepareStatement(query);
				pstmt.executeUpdate();
				
				
			}
			catch(Exception ex)
			{
				System.out.println(ex);
			}
			}
		}
		else
		{
	//		System.out.println("SHIT4 "+ResetV+" "+out);
		}

		return out;
		
	}
	
	public int howManyUsedQ()
	{
		int returnV=0;
		try
		{
		String query = "SELECT HMUQ FROM users WHERE Nick=" + "'" +currentUser + "'";
		rs = st.executeQuery(query);
		while(rs.next())
		returnV=Integer.parseInt(rs.getString("HMUQ"));
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
		return returnV;
	}
	public String[] GetQuestionFromDB()
	{
		String[] FromDB;
		FromDB=QFromDB.split(";");
//		System.out.println(FromDB.length);
		return FromDB;
	}
	public boolean resetHMUQ() throws NumberFormatException, SQLException
	{
		

		boolean sem=false;
		ResetV=true;
		int x=howManyQ();
	//	System.out.println("Test "+x);
		
			try
			{
			//	System.out.println("HMUQ t2");
				for(int i=1;i<x+1;i++)
				{
					IsUsedQuestion(i);
		
				
				sem=true;
				break;
				}
				ResetV=false;
			}
			catch(Exception ex)
			{
				System.out.println(ex);
				sem=false;
			//	break;
			}
		
		
		return sem;
		
		
	}
	public boolean NickOrEmail(boolean alert)
	{
		if(alert)
		{
			NOE_Var=true; //Login
		}
		else
		{
			NOE_Var=false; //Email
		}
		return NOE_Var;
	}
	public boolean UpdateWins()
	{
		String wins="0";
		try
		{
			String query = "SELECT HowManyWins FROM users WHERE Nick=" + "'" +currentUser + "'";
			rs = st.executeQuery(query);
			while(rs.next()) 
			{
				wins = rs.getString("HowManyWins");
			}

		}
		catch (Exception e)
		{
			System.out.println(e);
		}
			try
			{
		//	System.out.println(currentUser);
				int newWins=Integer.parseInt(wins)+1;
				String query="UPDATE users SET HowManyWins="+"'"+newWins+"'"+"WHERE Nick="+"'"+currentUser+"'";
				pstmt=con.prepareStatement(query);
				pstmt.executeUpdate();
			}
			catch(Exception ex)
			{
				System.out.println(ex);
			}
	
		
		return true;
	}
	public boolean UpdateGames()
	{
		String games="0";
		try
		{
			String query = "SELECT games FROM users WHERE Nick=" + "'" +currentUser + "'";
			rs = st.executeQuery(query);
			while(rs.next()) 
			{
				games = rs.getString("games");
			}

		}
		catch (Exception e)
		{
			System.out.println(e);
		}
			try
			{
		//	System.out.println(currentUser);
				int newGames=Integer.parseInt(games)+1;
				String query="UPDATE users SET games="+"'"+newGames+"'"+"WHERE Nick="+"'"+currentUser+"'";
				pstmt=con.prepareStatement(query);
				pstmt.executeUpdate();
			}
			catch(Exception ex)
			{
				System.out.println(ex);
			}
	
		
		return true;
	}
	public boolean UpdateBestScore(String newScore)
	{
		String oldScore="0";
		try
		{
			String query = "SELECT bestScore FROM users WHERE Nick=" + "'" +currentUser + "'";
			rs = st.executeQuery(query);
			while(rs.next()) 
			{
				oldScore = rs.getString("bestScore");
			}

		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		if(Integer.parseInt(newScore)>Integer.parseInt(oldScore))
		{
			try
			{
		//	System.out.println(currentUser);
				String query="UPDATE users SET bestScore="+"'"+newScore+"'"+"WHERE Nick="+"'"+currentUser+"'";
				pstmt=con.prepareStatement(query);
				pstmt.executeUpdate();
			}
			catch(Exception ex)
			{
				System.out.println(ex);
			}
		}
		
		return true;
	}
	public boolean UpdateEmail(String newEmail)
	{
		try
		{
		//	System.out.println(currentUser);
			String query="UPDATE users SET email="+"'"+newEmail+"'"+"WHERE Nick="+"'"+currentUser+"'";
			pstmt=con.prepareStatement(query);
			pstmt.executeUpdate();
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
		return true;
	}
	public boolean UpdatePassword(String newPassword)
	{
		try
		{
		//	System.out.println(currentUser);
			String query="UPDATE users SET password="+"'"+newPassword+"'"+"WHERE Nick="+"'"+currentUser+"'";
			pstmt=con.prepareStatement(query);
			pstmt.executeUpdate();
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
		return true;
	}
	public boolean ResetProgress() //RebuildIt
	{
		try
		{
		//	System.out.println(currentUser);
			int reset=0;
			String query="UPDATE users SET bestScore="+"'"+reset+"',games="+"'"+reset+"',HowManyWins="+"'"+reset+"'"+"WHERE Nick="+"'"+currentUser+"'";
			pstmt=con.prepareStatement(query);
			pstmt.executeUpdate();
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
		return true;
	}
	private boolean IsUserExist(String login, String email)
	{
		boolean UserExists = false;
		String log = "";
		String em = "";
		try
		{
			String query = "SELECT Nick, Email FROM users WHERE Nick=" + "'" +login + "'" + " OR Email=" + "'" + email + "'";
			rs = st.executeQuery(query);
			while(rs.next()) 
			{
				log = rs.getString("Nick");
				em = rs.getString("Email");
			}
			if (login.equals(log))
			{
				UserExists = true;
				NickOrEmail(true);
			}
			if ( email.equals(em))
			{
				UserExists = true;
				NickOrEmail(false);
			}
			rs.beforeFirst();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		return UserExists;
	}
	
	
}
