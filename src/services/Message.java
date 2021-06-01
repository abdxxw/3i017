package services;

import java.sql.Connection;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

public class Message {

	public static JSONObject addMessage(String sessionID, String text) {

		if(sessionID == null || text == null)
			return tools.ErrorJSON.ServiceRefused("Missing information.", 6901);
		
		Connection connection = null;
		try {
			connection = BD.Database.getMySQLConnection();

			if(!tools.ConnexionTools.checkConnected_session(sessionID, connection))
				return tools.ErrorJSON.ServiceRefused("You are no longer connected", 6908);

			String user = tools.ConnexionTools.getUserBySessionID(sessionID, connection);
			if(tools.ConnexionTools.checkTimeout(user, connection))
				return tools.ErrorJSON.ServiceRefused("TimedOut Error", 6900);
			tools.MessageTools.addMessage(user,text);
			return tools.ErrorJSON.ServiceAccepted();
		}catch (SQLException e) {
			return  tools.ErrorJSON.ServiceRefused("SQL Error", 6903);
		}finally{

			BD.Database.closeConnection(connection);
		}
			
			
	}
	
	public static JSONObject deleteMessage(String sessionID, String messageID) {
		Connection connection = null;
		if(sessionID == null || messageID == null)
			return tools.ErrorJSON.ServiceRefused("Missing information.", 6901);
		
		try {
			connection = BD.Database.getMySQLConnection();
			if(!tools.ConnexionTools.checkConnected_session(sessionID, connection))
				return tools.ErrorJSON.ServiceRefused("You are no longer connected", 6908);
			
			String user = tools.ConnexionTools.getUserBySessionID(sessionID, connection);
			if(tools.ConnexionTools.checkTimeout(user, connection))
				return tools.ErrorJSON.ServiceRefused("TimedOut Error", 6900);
			
			tools.MessageTools.deleteMessage(user,messageID);
			
			
			return tools.ErrorJSON.ServiceAccepted();
		}catch (SQLException e) {
			return  tools.ErrorJSON.ServiceRefused("SQL Error", 6903);
		}finally {
			BD.Database.closeConnection(connection);
		}
	}
	

	
	public static JSONObject getMessages(String sessionID ,String friend_user,boolean profile) {
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
			
			if(friend_user != null) {
				if(tools.UserTools.checkExist(friend_user, connection))
					tools.ErrorJSON.ServiceRefused("Friend doesn't exist", 2);
				
				return tools.MessageTools.getMessagesFriend(user,friend_user);
			}
			
			if(profile)
				return tools.MessageTools.getMessagesProfile(user);
			
			
			return tools.MessageTools.getMessagesAll(user);
			
		}catch (SQLException e) {
			return  tools.ErrorJSON.ServiceRefused("SQL Error", 6903);
		}catch (JSONException e) {
			return  tools.ErrorJSON.ServiceRefused("Erreur JSON", 6909);
		}finally {
			BD.Database.closeConnection(connection);
		}


			
		
		
	
	}
}
