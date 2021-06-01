package tools;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserTools {

	public static boolean checkExist(String user,Connection c) throws SQLException {
		

		//String requet = "SELECT * FROM USER WHERE user_id =\""+user+"\";";
		//Statement statement = c.createStatement();
		String requet = "SELECT * FROM USER WHERE user_id = ? ;";
		PreparedStatement statement = c.prepareStatement(requet);
		statement.setString(1, user);
		ResultSet result = statement.executeQuery();
		boolean out = result.next();
		statement.close();
		result.close();
		return out;
	}
		

	public static void addUser(String user, String email, String password,String fName,String lName, Date DNN, Connection c) throws SQLException {
		
		//String requet = "INSERT INTO USER VALUES('"+user+"','"+email+"','"+password+"','"+fName+"','"+lName+"','"+DNN+"')";
		//Statement statement = c.createStatement();
		String requet = "INSERT INTO USER VALUES(?,?,?,?,?,?);";
		PreparedStatement statement = c.prepareStatement(requet);
		statement.setString(1, user);
		statement.setString(2, email);
		statement.setString(3, password);
		statement.setString(4, fName);
		statement.setString(5, lName);
		statement.setDate(6, DNN);
		statement.executeUpdate();
		statement.close();
	}

	public static void deleteUser(String user, Connection c) throws SQLException {
		
		//String requet = "DELETE FROM USER WHERE user_id =\""+user+"\";";
		//Statement statement = c.createStatement();
		String requet = "DELETE FROM USER WHERE user_id = ? ;";
		PreparedStatement statement = c.prepareStatement(requet);
		statement.setString(1, user);
		statement.executeUpdate();
		statement.close();
	}
	

	public static boolean checkPass(String user,String password,Connection c) throws SQLException {
		//String requet = "SELECT * FROM USER WHERE user_id =\""+user+"\" AND pass =\""+password+"\";";
		//Statement statement = c.createStatement();
		String requet = "SELECT * FROM USER WHERE user_id = ? AND pass = ? ;";
		PreparedStatement statement = c.prepareStatement(requet);
		statement.setString(1, user);
		statement.setString(2, password);
		ResultSet result = statement.executeQuery();
		boolean out = result.next();
		statement.close();
		result.close();

		return out;
	}	

	public static List<String> getUserInfo(String user,Connection c) throws SQLException {
		//String requet = "SELECT * FROM USER WHERE user_id =\""+user+"\";";
		//Statement statement = c.createStatement();
		String requet = "SELECT * FROM USER WHERE user_id = ? ;";
		PreparedStatement statement = c.prepareStatement(requet);
		statement.setString(1, user);
		ResultSet result = statement.executeQuery();
		
		List<String> out = new ArrayList<String>();
		if(result.next()) {
			out.add(result.getString("fname"));
			out.add(result.getString("lname"));
			out.add(result.getString("email"));
			out.add(result.getDate("DNN").toString());
		}
		statement.close();
		result.close();

		return out;
	}
	
	public static boolean isEmailAdress(String email){
		Pattern p = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$");
		Matcher m = p.matcher(email.toUpperCase());
		return m.matches();
	}	

	public static boolean isGoodPass(String pass){
		String regex = "((?=.*[a-z])"+ 		//contient au moins une minuscule
					"(?=.*\\d)"		 + 		//contient au moins un chiffre
					"(?=.*[A-Z])"	 + 		//contient au moins une majuscule
					"(?=.*[@#$%!/*])"+ 		//contient au moins un caractère spécial @ # $ % ! / *
					".{8,32})";				//doit etre entre 8 et 32
		 
		Pattern p = Pattern.compile(regex);
	    Matcher m = p.matcher(pass); 
		return m.matches();
	}
	
	public static boolean isAboveAge(Date DNN){
		Calendar c = Calendar.getInstance();
		java.util.Date now = c.getTime();
		long x = DNN.getTime() - now.getTime();
		return (int) x >= 16;
	}
	public static int CheckInfo(String email,String password,Date DNN) {
		if(!isEmailAdress(email))
			return 1;
		if(!isGoodPass(password))
			return 2;
		if(!isAboveAge(DNN))
			return 3;
		
		return 0;
		
	}
	
}
