package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;


public class Like extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
    //getlikes
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");

		String sessionID = request.getParameter("sessionID");
		String messageID = request.getParameter("messageID");

		PrintWriter out = response.getWriter();
		JSONObject json;
		json = services.Like.getLikes(sessionID, messageID);
		out.print(json.toString()); 
	}


	//like
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");

		String sessionID = request.getParameter("sessionID");
		String messageID = request.getParameter("messageID");

		PrintWriter out = response.getWriter();
		JSONObject json;
		json = services.Like.doLike(sessionID, messageID);
		out.print(json.toString()); 
	}

	//dislike
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		
		String sessionID = request.getParameter("sessionID");
		String messageID = request.getParameter("messageID");

		PrintWriter out = response.getWriter();
		JSONObject json;
		json = services.Like.disLike(sessionID, messageID);
		out.print(json.toString()); 
	}
}
