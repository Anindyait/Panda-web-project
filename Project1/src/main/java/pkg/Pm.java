package pkg;

import java.io.*;
import java.sql.*; //import for sql
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Pm")
public class Pm extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Pm() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#getServletConfig()
	 */
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see Servlet#getServletInfo()
	 */
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null; 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ServletContext context=getServletContext(); 

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String name = request.getParameter("uname");
		String email = request.getParameter("uemail");
		String phno = request.getParameter("uphno");
		String password = request.getParameter("upass");
		System.out.print("Hello");
		try 
		{
			Connection con;
			PreparedStatement pstm; 			       //class to prepare statement
			Class.forName("com.mysql.cj.jdbc.Driver"); //Class is a class
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet", "root", "anindya"); //DriverManager is a class 
														//jdbc:mysql then ip address then port no. then db name
														//mysql username and password
			Statement stmt = con.createStatement();
			String sql = "create table User " +
			   "(user_id integer , " +
			   " first_name varchar(255), " + 
			   " last_name varchar(255), " + 
			   " email varchar(255) unique, " + 
			   " phone integer unique, " + 
			   " address varchar(255), " + 
			   " password varchar(255), " + 
			   " primary key ( user_id ))"; 
			System.out.println(sql);
	        stmt.executeUpdate(sql);
	        
	        pw.println("Created table in given database...");
	        
			pstm  = con.prepareStatement("insert into User values(?, ?, ?, ?, ?, ?, ?)");
			pstm.setString(2, name); 					// goes to 1st q mark
			pstm.setString(4, email); 
			pstm.setString(5, phno); 
			pstm.setString(7, password);
			pstm.executeUpdate();
			pw.println("Successfully Registered");
		}catch(Exception e) {}
	}

}
