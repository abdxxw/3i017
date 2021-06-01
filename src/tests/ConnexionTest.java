package tests;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

import org.json.JSONException;
import org.junit.Test;

public class ConnexionTest {

	@Test
	public void testLogin() throws SQLException, JSONException{
		
		Connection c = BD.Database.getMySQLConnection();
		services.User.creatUser("vg_test", "vg_test@gmail.com", "Vg_test92@", "vg_test", "vg_test", Date.valueOf("1990-02-13"));
		System.out.println(services.Connexion.login("vg_test", "Vg_test92@"));
		assertEquals(tools.ConnexionTools.checkConnected("vg_test", c),true);
		services.User.deleteUser("vg_test");
		c.close();
		
	}
	
	@Test
	public void testLogout() throws SQLException, JSONException{
		
		Connection c = BD.Database.getMySQLConnection();
		services.User.creatUser("vg_test", "vg_test@gmail.com", "Vg_test92@", "vg_test", "vg_test", Date.valueOf("1990-02-13"));
		services.Connexion.login("vg_test", "Vg_test92@");
		System.out.println(services.Connexion.logout(tools.ConnexionTools.getSessionIDByUser("vg_test",c)));
		assertEquals(tools.ConnexionTools.checkConnected("vg_test", c),false);
		c.close();
		
	}
}
