package services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;


public class Friend {

	public static JSONObject followFriend(String sessionID, String friend_user) {
		Connection connection = null;

		if(sessionID == null || friend_user == null)
			return tools.ErrorJSON.ServiceRefused("Missing information.", 6901);
		
		try {
			connection = BD.Database.getMySQLConnection();
			if(!tools.ConnexionTools.checkConnected_session(sessionID, connection))
				return tools.ErrorJSON.ServiceRefused("You are no longer connected", 6908);

			String user = tools.ConnexionTools.getUserBySessionID(sessionID, connection);
			if(tools.ConnexionTools.checkTimeout(user, connection))
				return tools.ErrorJSON.ServiceRefused("TimedOut Error", 6900);

			if(!tools.UserTools.checkExist(friend_user, connection))
				return tools.ErrorJSON.ServiceRefused("Friend doesn't exist", 6907);

			if(tools.FriendsTools.isFollowed(user, friend_user, connection))
				return tools.ErrorJSON.ServiceRefused("Already followed", 2);
			
			tools.FriendsTools.followFriend(user,friend_user,connection);
			
			return tools.ErrorJSON.ServiceAccepted();
		}catch (SQLException e) {
			return  tools.ErrorJSON.ServiceRefused("SQL Error", 6903);
		}finally {
			BD.Database.closeConnection(connection);
		}
	}
	

	public static JSONObject unFollowFriend(String sessionID, String friend_user) {
		Connection connection = null;

		if(sessionID == null || friend_user == null)
			return tools.ErrorJSON.ServiceRefused("Missing information.", 6901);
		
		try {
			connection = BD.Database.getMySQLConnection();
			if(!tools.ConnexionTools.checkConnected_session(sessionID, connection))
				return tools.ErrorJSON.ServiceRefused("You are no longer connected", 6908);
			
			String user = tools.ConnexionTools.getUserBySessionID(sessionID, connection);
			if(tools.ConnexionTools.checkTimeout(user, connection))
				return tools.ErrorJSON.ServiceRefused("TimedOut Error", 6900);
			
			if(!tools.UserTools.checkExist(friend_user, connection))
				return tools.ErrorJSON.ServiceRefused("Friend doesn't exist", 6907);
			
			if(!tools.FriendsTools.isFollowed(user, friend_user, connection))
				return tools.ErrorJSON.ServiceRefused("You are not following this user", 2);
			
			tools.FriendsTools.UnFollowFriend(user,friend_user,connection);
			
			return tools.ErrorJSON.ServiceAccepted();
			
		}catch (SQLException e) {
			return  tools.ErrorJSON.ServiceRefused("SQL Error", 6903);
		}finally {
			BD.Database.closeConnection(connection);
		}
	}
	

	public static JSONObject getFriends(String sessionID) {
		Connection connection = null;

		if(sessionID == null)
			return tools.ErrorJSON.ServiceRefused("Missing information.", 6901);
		
		try {
			
			connection = BD.Database.getMySQLConnection();
			if(!tools.ConnexionTools.checkConnected_session(sessionID, connection))
				return tools.ErrorJSON.ServiceRefused("You are no longer connected", 6908);
			
			String user = tools.ConnexionTools.getUserBySessionID(sessionID, connection);
			if(tools.ConnexionTools.checkTimeout(user, connection))
				return tools.ErrorJSON.ServiceRefused("TimedOut Error", 6900);
			
			List<String> l =tools.FriendsTools.getFriends(user,connection);	
			JSONObject json = tools.ErrorJSON.ServiceAccepted();
			json.put("user", user);
			json.put("friends", l);
			return json;
		}catch (SQLException e) {
			return  tools.ErrorJSON.ServiceRefused("SQL Error", 6903);
		}catch (JSONException e) {
			return  tools.ErrorJSON.ServiceRefused("Erreur JSON",6909);
		}finally {
			BD.Database.closeConnection(connection);
		}
	}
	
	
	
	
}
