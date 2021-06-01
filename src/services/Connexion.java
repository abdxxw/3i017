package services;

import java.sql.Connection;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

public class Connexion {
	
	public static JSONObject login(String user,String password) {
		
		if(user==null || password==null) {	
			return tools.ErrorJSON.ServiceRefused("Missing information.", 6901);
		}
		Connection connection = null;
		try {

			connection = BD.Database.getMySQLConnection();
			if(!tools.UserTools.checkExist(user,connection))
				return tools.ErrorJSON.ServiceRefused("Some of the information you have entered are wrong.", 6902);

			if(!tools.UserTools.checkPass(user,password,connection))
				return tools.ErrorJSON.ServiceRefused("Some of the information you have entered are wrong.", 6902);

			JSONObject out = tools.ErrorJSON.ServiceAccepted();
			if(tools.ConnexionTools.checkConnected(user, connection)) {
				tools.ConnexionTools.endSession(user, connection);
			}
			String currentSessionID = tools.ConnexionTools.startSession(user, connection);
			out.put("user", user);
			out.put("sessionID", currentSessionID);
			return out;
		}catch (SQLException e) {
			return  tools.ErrorJSON.ServiceRefused("SQL Error", 6903);
		}catch (JSONException e) {
			return  tools.ErrorJSON.ServiceRefused("Erreur JSON",6909);
		}finally {
			BD.Database.closeConnection(connection);
		}

	}
	

	
	
	public static JSONObject logout(String sessionID) {
		if (sessionID ==null)
			return tools.ErrorJSON.ServiceRefused("Missing information.", 6901);
		Connection connection = null;
		try {
			connection = BD.Database.getMySQLConnection();
			if(!tools.ConnexionTools.checkConnected_session(sessionID, connection))
				return tools.ErrorJSON.ServiceRefused("Already Logged out", 6904);
			String user = tools.ConnexionTools.getUserBySessionID(sessionID, connection);
			tools.ConnexionTools.endSession(user, connection);
			return tools.ErrorJSON.ServiceAccepted();
		}catch (SQLException e) {
			return  tools.ErrorJSON.ServiceRefused("SQL Error", 6903);
		}finally {
			BD.Database.closeConnection(connection);
		}
		
	}
}
