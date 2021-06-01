package tools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.UUID;

public class ConnexionTools {


	private static String generateSessionID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	public static boolean checkConnected(String user,Connection c) throws SQLException {
		String requet = "SELECT * FROM SESSION WHERE user_id = ?;";
		PreparedStatement statement = c.prepareStatement(requet);
		statement.setString(1, user);
		//Statement statement = c.createStatement();
		ResultSet result = statement.executeQuery();
		boolean out = result.next();
		statement.close();
		result.close();
		return out;
	}

	public static boolean checkConnected_session(String sessionID,Connection c) throws SQLException {
		//String requet = "SELECT * FROM SESSION WHERE session_id =\""+sessionID+"\";";
		//Statement statement = c.createStatement();		
		String requet = "SELECT * FROM SESSION WHERE session_id = ? ;";
		PreparedStatement statement = c.prepareStatement(requet);
		statement.setString(1, sessionID);
		ResultSet result = statement.executeQuery();
		boolean out = result.next();
		statement.close();
		result.close();
		return out;
	}

	public static void endSession(String user,Connection c) throws SQLException {
		//String requet = "DELETE FROM SESSION WHERE user_id =\""+user+"\";";
		//Statement statement = c.createStatement();
		String requet = "DELETE FROM SESSION WHERE user_id = ? ;";
		PreparedStatement statement = c.prepareStatement(requet);
		statement.setString(1, user);
		statement.executeUpdate();
		statement.close();
	}

	public static String startSession(String user,Connection c) throws SQLException {
		String sessionID = generateSessionID();
		Calendar calendar = Calendar.getInstance();
		Timestamp t = new Timestamp(calendar.getTimeInMillis());
		//String requet = "INSERT INTO SESSION VALUES('"+user+"','"+sessionID+"','"+t+"')";
		//Statement statement = c.createStatement();
		String requet = "INSERT INTO SESSION VALUES(?,?,?);";
		PreparedStatement statement = c.prepareStatement(requet);
		statement.setString(1, user);
		statement.setString(2, sessionID);
		statement.setTimestamp(3, t);
		statement.executeUpdate();
		statement.close();
		return sessionID;
	}

	public static boolean isTimedOut(String user,Connection c) throws SQLException {
		//String requet = "SELECT * FROM SESSION WHERE user_id =\""+user+"\";";
		Calendar calendar = Calendar.getInstance();
		Timestamp t1 = new Timestamp(calendar.getTimeInMillis());
		Timestamp t2;
		//Statement statement = c.createStatement();
		String requet = "SELECT * FROM SESSION WHERE user_id = ? ;";
		PreparedStatement statement = c.prepareStatement(requet);
		statement.setString(1, user);
		ResultSet result = statement.executeQuery();
		boolean out = false;
		if (result.next()) {
			t2 = result.getTimestamp("date");
			long x = t1.getTime()-t2.getTime();
			int minutes = (int) (x) / 60000;
			out = minutes > 30;
		}
		statement.close();
		result.close();
		return out;
	}

	public static void updateSessionTime(String user,Connection c) throws SQLException {
		Calendar calendar = Calendar.getInstance();
		Timestamp t = new Timestamp(calendar.getTimeInMillis());
		//String requet = "UPDATE SESSION SET date=\""+t+"\" WHERE user_id =\""+user+"\";";
		//Statement statement = c.createStatement();
		String requet = "UPDATE SESSION SET date = ? WHERE user_id = ? ;";
		PreparedStatement statement = c.prepareStatement(requet);
		statement.setTimestamp(1, t);
		statement.setString(2, user);
		statement.executeUpdate();
		statement.close();
	}
	
	public static boolean checkTimeout(String user,Connection c) throws SQLException {
		if(isTimedOut(user,c)) {
			endSession(user,c);
			return true;
		}
		updateSessionTime(user,c);
		return false;
	}
	
	public static String getUserBySessionID(String sessionID,Connection c) throws SQLException {
		//String requet = "SELECT * FROM SESSION WHERE session_id =\""+sessionID+"\";";
		//Statement statement = c.createStatement();
		String requet = "SELECT * FROM SESSION WHERE session_id = ? ;";
		PreparedStatement statement = c.prepareStatement(requet);
		statement.setString(1, sessionID);
		ResultSet result = statement.executeQuery();	
		String out = null;
		if (result.next()) 
			out = result.getString("user_id");
		statement.close();
		result.close();
		return out;
	}
	
	public static String getSessionIDByUser(String user,Connection c) throws SQLException {
		//String requet = "SELECT * FROM SESSION WHERE user_id =\""+user+"\";";
		//Statement statement = c.createStatement();
		String requet = "SELECT * FROM SESSION WHERE user_id = ? ;";
		PreparedStatement statement = c.prepareStatement(requet);
		statement.setString(1, user);
		ResultSet result = statement.executeQuery();	
		String out = null;
		if (result.next()) 
			out = result.getString("session_id");
		statement.close();
		result.close();
		return out;
	}
	
}
