package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;


public class Connexion extends HttpServlet {
	private static final long serialVersionUID = 1L;


	//login
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		
		String user = request.getParameter("user");
		String pass = request.getParameter("pass");

		PrintWriter out = response.getWriter();
		JSONObject json;
		json = services.Connexion.login(user, pass);
		out.print(json.toString()); 
	}


	//logout
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		
		String sessionID = request.getParameter("sessionID");

		PrintWriter out = response.getWriter();
		JSONObject json;
		json = services.Connexion.logout(sessionID);
		out.print(json.toString()); 
	}

}
