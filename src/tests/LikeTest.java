package tests;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

public class LikeTest {

	@Test 
	public void testAddLike() throws SQLException {
		try {
			Connection c = BD.Database.getMySQLConnection();
			services.User.creatUser("vg_test", "vg_test@gmail.com", "Vg_test92@", "vg_test", "vg_test", Date.valueOf("1990-02-13"));
			services.Connexion.login("vg_test", "Vg_test92@");
			services.Message.addMessage(tools.ConnexionTools.getSessionIDByUser("vg_test", c), "vg_testvg_testvg_testvg_testvg_testvg_test");
			JSONObject o = (JSONObject) ((JSONArray)(tools.MessageTools.getMessagesProfile("vg_test").get("messages"))).get(0);
			System.out.println(services.Like.doLike(tools.ConnexionTools.getSessionIDByUser("vg_test", c), o.get("_id").toString()));
			assertEquals(((JSONArray) tools.LikeTools.getLikes(o.get("_id").toString()).get("likes")).toString().contains("vg_test"),true);		
			services.Message.deleteMessage(tools.ConnexionTools.getSessionIDByUser("vg_test", c), o.get("_id").toString());

			services.User.deleteUser("vg_test");
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	@Test 
	public void testDeleteLike() throws SQLException {
		try {
			Connection c = BD.Database.getMySQLConnection();
			services.User.creatUser("vg_test", "vg_test@gmail.com", "Vg_test92@", "vg_test", "vg_test", Date.valueOf("1990-02-13"));
			services.Connexion.login("vg_test", "Vg_test92@");
			services.Message.addMessage(tools.ConnexionTools.getSessionIDByUser("vg_test", c), "vg_testvg_testvg_testvg_testvg_testvg_test");
			JSONObject o = (JSONObject) ((JSONArray)(tools.MessageTools.getMessagesProfile("vg_test").get("messages"))).get(0);
			services.Like.doLike(tools.ConnexionTools.getSessionIDByUser("vg_test", c), o.get("_id").toString());
			System.out.println(services.Like.disLike(tools.ConnexionTools.getSessionIDByUser("vg_test", c), o.get("_id").toString()));
			assertEquals(((JSONArray) tools.LikeTools.getLikes(o.get("_id").toString()).get("likes")).toString().contains("vg_test"),false);
			services.Message.deleteMessage(tools.ConnexionTools.getSessionIDByUser("vg_test", c), o.get("_id").toString());

			services.User.deleteUser("vg_test");
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	@Test 
	public void testGetLikes() throws SQLException {
		try {
			Connection c = BD.Database.getMySQLConnection();
			services.User.creatUser("vg_test", "vg_test@gmail.com", "Vg_test92@", "vg_test", "vg_test", Date.valueOf("1990-02-13"));
			services.Connexion.login("vg_test", "Vg_test92@");
			services.Message.addMessage(tools.ConnexionTools.getSessionIDByUser("vg_test", c), "vg_testvg_testvg_testvg_testvg_testvg_test");
			JSONObject o = (JSONObject) ((JSONArray)(tools.MessageTools.getMessagesProfile("vg_test").get("messages"))).get(0);
			services.Like.doLike(tools.ConnexionTools.getSessionIDByUser("vg_test", c), o.get("_id").toString());
			System.out.println(services.Like.getLikes(tools.ConnexionTools.getSessionIDByUser("vg_test", c), o.get("_id").toString()));
			services.Message.deleteMessage(tools.ConnexionTools.getSessionIDByUser("vg_test", c), o.get("_id").toString());

			services.User.deleteUser("vg_test");
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}


}
