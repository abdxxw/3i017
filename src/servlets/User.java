package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;


public class User extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//getuser
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		
		String user = request.getParameter("user");
		PrintWriter out = response.getWriter();
		JSONObject json;
		json = services.User.getUserInfo(user);
		out.print(json.toString()); 
	}
	

	//creatuser
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		
		String user = request.getParameter("user");
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");
		String fName = request.getParameter("fName");
		String lName = request.getParameter("lName");
		Date DNN = Date.valueOf(request.getParameter("DNN"));
		
		PrintWriter out = response.getWriter();
		JSONObject json;
		json = services.User.creatUser(user, email, pass, fName, lName, DNN);
		out.print(json.toString()); 
	}
	
	//deleteuser 
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		
		String user = request.getParameter("user");

		PrintWriter out = response.getWriter();
		JSONObject json;
		json = services.User.deleteUser(user);
		out.print(json.toString()); 
	}

}
