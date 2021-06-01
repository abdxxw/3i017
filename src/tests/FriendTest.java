package tests;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

import org.json.JSONException;
import org.junit.Test;

public class FriendTest {

	@Test
	public void testFollowFriend() throws SQLException, JSONException{
		
		Connection c = BD.Database.getMySQLConnection();
		services.User.creatUser("vg_test", "vg_test@gmail.com", "Vg_test92@", "vg_test", "vg_test", Date.valueOf("1990-02-13"));
		services.Connexion.login("vg_test", "Vg_test92@");
		services.User.creatUser("vg_test_friend", "vg_test@gmail.com", "Vg_test92@", "vg_test", "vg_test", Date.valueOf("1990-02-13"));
		services.Friend.followFriend(tools.ConnexionTools.getSessionIDByUser("vg_test", c), "vg_test_friend");
		assertEquals(tools.FriendsTools.isFollowed("vg_test", "vg_test_friend", c),true);
		services.User.deleteUser("vg_test");
		services.User.deleteUser("vg_test_friend");
		c.close();
		
	}
	
	@Test
	public void testUnFollowFriend() throws SQLException, JSONException{
		
		Connection c = BD.Database.getMySQLConnection();
		services.User.creatUser("vg_test", "vg_test@gmail.com", "Vg_test92@", "vg_test", "vg_test", Date.valueOf("1990-02-13"));
		services.Connexion.login("vg_test", "Vg_test92@");
		services.User.creatUser("vg_test_friend", "vg_test@gmail.com", "Vg_test92@", "vg_test", "vg_test", Date.valueOf("1990-02-13"));
		services.Friend.followFriend(tools.ConnexionTools.getSessionIDByUser("vg_test", c), "vg_test_friend");
		services.Friend.unFollowFriend(tools.ConnexionTools.getSessionIDByUser("vg_test", c), "vg_test_friend");
		assertEquals(tools.FriendsTools.isFollowed("vg_test", "vg_test_friend", c),false);
		services.User.deleteUser("vg_test");
		services.User.deleteUser("vg_test_friend");
		c.close();
		
	}
	@Test
	public void testGetFriends() throws SQLException, JSONException{
		
		Connection c = BD.Database.getMySQLConnection();
		services.User.creatUser("vg_test", "vg_test@gmail.com", "Vg_test92@", "vg_test", "vg_test", Date.valueOf("1990-02-13"));
		services.Connexion.login("vg_test", "Vg_test92@");
		services.User.creatUser("vg_test_friend1", "vg_test@gmail.com", "Vg_test92@", "vg_test", "vg_test", Date.valueOf("1990-02-13"));
		services.User.creatUser("vg_test_friend2", "vg_test@gmail.com", "Vg_test92@", "vg_test", "vg_test", Date.valueOf("1990-02-13"));
		services.User.creatUser("vg_test_friend3", "vg_test@gmail.com", "Vg_test92@", "vg_test", "vg_test", Date.valueOf("1990-02-13"));
		services.Friend.followFriend(tools.ConnexionTools.getSessionIDByUser("vg_test", c), "vg_test_friend1");
		services.Friend.followFriend(tools.ConnexionTools.getSessionIDByUser("vg_test", c), "vg_test_friend2");
		services.Friend.followFriend(tools.ConnexionTools.getSessionIDByUser("vg_test", c), "vg_test_friend3");
		services.Friend.unFollowFriend(tools.ConnexionTools.getSessionIDByUser("vg_test", c), "vg_test_friend");
		System.out.println(services.Friend.getFriends(tools.ConnexionTools.getSessionIDByUser("vg_test", c)));
		services.User.deleteUser("vg_test");
		services.User.deleteUser("vg_test_friend1");
		services.User.deleteUser("vg_test_friend2");
		services.User.deleteUser("vg_test_friend3");
		c.close();

		
	}
}
