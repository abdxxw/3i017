package services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;


public class User {
	
	public static JSONObject creatUser(String user,String email,String password,String fName,String lName,Date DNN) {
		
		if(user==null || email==null || password==null || fName==null || lName==null || DNN==null) {	
			return tools.ErrorJSON.ServiceRefused("Missing information.", 6901);
		}
		switch (tools.UserTools.CheckInfo(email, password, DNN)) {
			case 1 : return  tools.ErrorJSON.ServiceRefused("Wrong email format.", 6911); 
			case 2 : return  tools.ErrorJSON.ServiceRefused("Weak password be smarter.", 6912); 
			case 3 : return  tools.ErrorJSON.ServiceRefused("You're too young for this site go play outside.", 6913); 
			default : // nothing
		}
		Connection connection = null;
		try {
			connection = BD.Database.getMySQLConnection();
			if(tools.UserTools.checkExist(user,connection))
				return tools.ErrorJSON.ServiceRefused("User Already Exists", 6905);
			
			tools.UserTools.addUser(user, email, password, fName, lName, DNN, connection);
			return tools.ErrorJSON.ServiceAccepted();
		}catch (SQLException e) {
			return  tools.ErrorJSON.ServiceRefused("SQL Error", 6903);
		}finally {
			BD.Database.closeConnection(connection);
		}
		
	}
	
	
	public static JSONObject deleteUser(String user) {
		
		if(user==null) {	
			return tools.ErrorJSON.ServiceRefused("Missing information.", 6901);
		}
		
		Connection connection = null;
		try {
			connection = BD.Database.getMySQLConnection();
			if(!tools.UserTools.checkExist(user,connection))
				return tools.ErrorJSON.ServiceRefused("User doesn't Exists", 6906);
			
			tools.UserTools.deleteUser(user,connection);
			return tools.ErrorJSON.ServiceAccepted();
		}catch (SQLException e) {
			return  tools.ErrorJSON.ServiceRefused("SQL Error", 6903);
		}finally {
			BD.Database.closeConnection(connection);
		}
		
	}	
	
	
	public static JSONObject getUserInfo(String user) {
		
		if(user==null) {	
			return tools.ErrorJSON.ServiceRefused("Missing information.", 6901);
		}

		Connection connection = null;
		try {

			connection = BD.Database.getMySQLConnection();
			if(!tools.UserTools.checkExist(user,connection))
				return tools.ErrorJSON.ServiceRefused("User doesn't Exists", 6906);
			List<String> l = tools.UserTools.getUserInfo(user,connection);
			JSONObject json = tools.ErrorJSON.ServiceAccepted();
			json.put("user", user);
			json.put("fName", l.get(0));
			json.put("lName", l.get(1));
			json.put("email", l.get(2));
			json.put("DNN", l.get(3));
			json.put("friends", services.Friend.getFriends(tools.ConnexionTools.getSessionIDByUser(user, connection)).get("friends"));
			json.put("messages", services.Message.getMessages(tools.ConnexionTools.getSessionIDByUser(user, connection), null, true).get("messages"));
			return json;
		}catch (SQLException e) {
			return  tools.ErrorJSON.ServiceRefused("Erreur SQL", 6903);
		}catch (JSONException e) {
			return  tools.ErrorJSON.ServiceRefused("Erreur JSON",6909);
		}finally {
			BD.Database.closeConnection(connection);
		}
		
	}
	

	



	
	
	
	
}
