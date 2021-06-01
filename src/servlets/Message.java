package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;


public class Message extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//getmessages
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");

		String sessionID = request.getParameter("sessionID");
		String friend_user = request.getParameter("friend_user");
		boolean profile = request.getParameter("profile") != null;

		PrintWriter out = response.getWriter();
		JSONObject json;
		json = services.Message.getMessages(sessionID, friend_user, profile);
		out.print(json.toString()); 
	}

	//addmessage
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		
		String sessionID = request.getParameter("sessionID");
		String text = request.getParameter("text");

		PrintWriter out = response.getWriter();
		JSONObject json;
		json = services.Message.addMessage(sessionID, text);
		out.print(json.toString()); 
	}

	//deletemessage
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		
		String sessionID = request.getParameter("sessionID");
		String messageID = request.getParameter("messageID");

		PrintWriter out = response.getWriter();
		JSONObject json;
		json = services.Message.deleteMessage(sessionID, messageID);
		out.print(json.toString()); 
	}
}
