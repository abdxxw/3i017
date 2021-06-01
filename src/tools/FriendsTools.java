package tools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FriendsTools {

	public static boolean isFollowed(String user, String friend_user, Connection c) throws SQLException {
		//String requet = "SELECT * FROM FRIEND WHERE user_id =\""+user+" AND friend_id =\""+friend_user+"\";";
		//String requet = String.format("SELECT * FROM FRIEND WHERE user_id = '%s' AND friend_id = '%s';",user,friend_user);
		//Statement statement = c.createStatement();
		String requet = "SELECT * FROM FRIEND WHERE user_id = ? AND friend_id = ? ;";
		PreparedStatement statement = c.prepareStatement(requet);
		statement.setString(1, user);
		statement.setString(2, friend_user);
		ResultSet result = statement.executeQuery();
		boolean out = result.next();
		statement.close();
		result.close();
		return out;
	}

	public static void followFriend(String user, String friend_user, Connection c) throws SQLException {
		//String requet = "INSERT INTO FRIEND VALUES('"+user+"','"+friend_user+"')";
		//String requet = String.format("INSERT INTO FRIEND VALUES('%s', '%s');",user,friend_user);
		//Statement statement = c.createStatement();
		String requet = "INSERT INTO FRIEND VALUES(?,?);";
		PreparedStatement statement = c.prepareStatement(requet);
		statement.setString(1, user);
		statement.setString(2, friend_user);
		statement.executeUpdate();
		statement.close();
	}

	public static void UnFollowFriend(String user, String friend_user, Connection c) throws SQLException {
		//String requet = "DELETE FROM FRIEND WHERE user_id =\""+user+" AND friend_id =\""+friend_user+"\";";
		//String requet = String.format("DELETE FROM FRIEND WHERE user_id = '%s' AND friend_id = '%s';",user,friend_user);
		//Statement statement = c.createStatement();
		String requet = "DELETE FROM FRIEND WHERE user_id =? AND friend_id =? ;";
		PreparedStatement statement = c.prepareStatement(requet);
		statement.setString(1, user);
		statement.setString(2, friend_user);
		statement.executeUpdate();
		statement.close();
		
	}

	public static List<String> getFriends(String user, Connection c) throws SQLException {
		//String requet = "SELECT * FROM FRIEND WHERE user_id =\""+user+"\";";
		//Statement statement = c.createStatement();
		String requet = "SELECT * FROM FRIEND WHERE user_id = ? ;";
		PreparedStatement statement = c.prepareStatement(requet);
		statement.setString(1, user);
		ResultSet result = statement.executeQuery();
		List<String> out = new ArrayList<String>();
		while(result.next()){
			out.add(result.getString("friend_id"));
		}
		statement.close();
		result.close();
		return out;
		
	}

}
