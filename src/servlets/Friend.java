package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;


public class Friend extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//get friends
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		
		String sessionID = request.getParameter("sessionID");

		PrintWriter out = response.getWriter();
		JSONObject json;
		json = services.Friend.getFriends(sessionID);
		out.print(json.toString()); 
	}

	//addfriend
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		
		String sessionID = request.getParameter("sessionID");
		String friend_user = request.getParameter("friend_user");

		PrintWriter out = response.getWriter();
		JSONObject json;
		json = services.Friend.followFriend(sessionID, friend_user);
		out.print(json.toString());
	}
	
	//deletefriend
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		
		String sessionID = request.getParameter("sessionID");
		String friend_user = request.getParameter("friend_user");

		PrintWriter out = response.getWriter();
		JSONObject json;
		json = services.Friend.unFollowFriend(sessionID, friend_user);
		out.print(json.toString()); 
	}

}
