package services;

import java.sql.Connection;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

public class Like {

	public static JSONObject doLike(String sessionID, String messageID) {

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
			tools.LikeTools.addLike(user, messageID);
			return tools.ErrorJSON.ServiceAccepted();
		}catch (SQLException e) {
			return  tools.ErrorJSON.ServiceRefused("SQL Error", 6903);
		}catch (JSONException e) {
			return  tools.ErrorJSON.ServiceRefused("Erreur JSON", 6909);
		}finally{

			BD.Database.closeConnection(connection);
		}
		
	}
	
	public static JSONObject disLike(String sessionID, String messageID) {
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
			tools.LikeTools.deleteLike(user, messageID);
			return tools.ErrorJSON.ServiceAccepted();
		}catch (SQLException e) {
			return  tools.ErrorJSON.ServiceRefused("SQL Error", 6903);
		}catch (JSONException e) {
			return  tools.ErrorJSON.ServiceRefused("Erreur JSON", 6909);
		}finally {
			BD.Database.closeConnection(connection);
		}
		
	}
	
	public static JSONObject getLikes(String sessionID, String messageID) {
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
			
			return tools.LikeTools.getLikes(messageID);
			
		}catch (SQLException e) {
			return  tools.ErrorJSON.ServiceRefused("SQL Error", 6903);
		}catch (JSONException e) {
			return  tools.ErrorJSON.ServiceRefused("Erreur JSON", 6909);
		}finally {
			BD.Database.closeConnection(connection);
		}
		
	}
}
