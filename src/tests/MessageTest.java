package tests;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

public class MessageTest {

	@Test 
	public void testAddMessage() throws SQLException {
		try {
			Connection c = BD.Database.getMySQLConnection();
			services.User.creatUser("vg_test", "vg_test@gmail.com", "Vg_test92@", "vg_test", "vg_test", Date.valueOf("1990-02-13"));
			services.Connexion.login("vg_test", "Vg_test92@");
			System.out.println(services.Message.addMessage(tools.ConnexionTools.getSessionIDByUser("vg_test", c), "vg_testvg_testvg_testvg_testvg_testvg_test"));
			JSONObject o = (JSONObject) ((JSONArray)(tools.MessageTools.getMessagesProfile("vg_test").get("messages"))).get(0);
			assertEquals(tools.MessageTools.checkExist(o.get("_id").toString()),true);
			services.Message.deleteMessage(tools.ConnexionTools.getSessionIDByUser("vg_test", c), o.get("_id").toString());

			services.User.deleteUser("vg_test");
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	@Test 
	public void testDeleteMessage() throws SQLException {
		try {
			Connection c = BD.Database.getMySQLConnection();
			services.User.creatUser("vg_test", "vg_test@gmail.com", "Vg_test92@", "vg_test", "vg_test", Date.valueOf("1990-02-13"));
			services.Connexion.login("vg_test", "Vg_test92@");
			services.Message.addMessage(tools.ConnexionTools.getSessionIDByUser("vg_test", c), "vg_testvg_testvg_testvg_testvg_testvg_test");
			JSONObject o = (JSONObject) ((JSONArray)(tools.MessageTools.getMessagesProfile("vg_test").get("messages"))).get(0);
			System.out.println(services.Message.deleteMessage(tools.ConnexionTools.getSessionIDByUser("vg_test", c), o.get("_id").toString()));
			assertEquals(tools.MessageTools.checkExist(o.get("_id").toString()),false);

			services.User.deleteUser("vg_test");
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	@Test 
	public void testgetMessageProfile() throws SQLException {
		try {
			Connection c = BD.Database.getMySQLConnection();
			services.User.creatUser("vg_test", "vg_test@gmail.com", "Vg_test92@", "vg_test", "vg_test", Date.valueOf("1990-02-13"));
			services.Connexion.login("vg_test", "Vg_test92@");
			services.Message.addMessage(tools.ConnexionTools.getSessionIDByUser("vg_test", c), "vg_testvg_testvg_testvg_testvg_testvg_test");
			System.out.println(services.Message.getMessages(tools.ConnexionTools.getSessionIDByUser("vg_test", c), null, true));
			JSONObject o = (JSONObject) ((JSONArray)(tools.MessageTools.getMessagesProfile("vg_test").get("messages"))).get(0);
			services.Message.deleteMessage(tools.ConnexionTools.getSessionIDByUser("vg_test", c), o.get("_id").toString());

			services.User.deleteUser("vg_test");
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	@Test 
	public void testgetMessageAll() throws SQLException {
		try {
			Connection c = BD.Database.getMySQLConnection();
			services.User.creatUser("vg_test", "vg_test@gmail.com", "Vg_test92@", "vg_test", "vg_test", Date.valueOf("1990-02-13"));
			services.Connexion.login("vg_test", "Vg_test92@");
			services.Message.addMessage(tools.ConnexionTools.getSessionIDByUser("vg_test", c), "vg_testvg_testvg_testvg_testvg_testvg_test");
			System.out.println(services.Message.getMessages(tools.ConnexionTools.getSessionIDByUser("vg_test", c), null, false));
			JSONObject o = (JSONObject) ((JSONArray)(tools.MessageTools.getMessagesProfile("vg_test").get("messages"))).get(0);
			services.Message.deleteMessage(tools.ConnexionTools.getSessionIDByUser("vg_test", c), o.get("_id").toString());

			services.User.deleteUser("vg_test");
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}	
	@Test 
	public void testgetMessageFriend() throws SQLException {
		try {
			Connection c = BD.Database.getMySQLConnection();
			services.User.creatUser("vg_test", "vg_test@gmail.com", "Vg_test92@", "vg_test", "vg_test", Date.valueOf("1990-02-13"));
			services.User.creatUser("friend_vg_test", "vg_test@gmail.com", "Vg_test92@", "vg_test", "vg_test", Date.valueOf("1990-02-13"));
			services.Connexion.login("vg_test", "Vg_test92@");
			services.Connexion.login("friend_vg_test", "Vg_test92@");
			services.Message.addMessage(tools.ConnexionTools.getSessionIDByUser("friend_vg_test", c), "vg_testvg_testvg_testvg_testvg_testvg_test");
			System.out.println(services.Message.getMessages(tools.ConnexionTools.getSessionIDByUser("vg_test", c), "friend_vg_test", false));
			JSONObject o = (JSONObject) ((JSONArray)(tools.MessageTools.getMessagesProfile("friend_vg_test").get("messages"))).get(0);
			services.Message.deleteMessage(tools.ConnexionTools.getSessionIDByUser("friend_vg_test", c), o.get("_id").toString());
			services.User.deleteUser("vg_test");
			services.User.deleteUser("friend_vg_test");
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

}
