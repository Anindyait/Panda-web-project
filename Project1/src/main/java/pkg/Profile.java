package pkg;

import java.io.*;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Profile
 */
@WebServlet("/Profile")
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Profile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * 
	 */
    
    String first_name=null;
    String last_name=null;
    String email = null;
    String phone = null;
    String dob = null;
    String gender = null;
    String address = null;
    
    void DB_Access(String user_id)
    {
    	try {
			Connection con;
			PreparedStatement pstm;
			
			int uid = Integer.parseInt(user_id);
			System.out.println(uid*10);
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet", "root", "abcd"); //DriverManager is a class 
			
			
			pstm = con.prepareStatement("select first_name, last_name, email, phone, dob, gender, address from user_table where user_id = ?;");
	
			pstm.setString(1, user_id);
			
			ResultSet rs = pstm.executeQuery();
			
			if(rs.next())
			{
				first_name = rs.getString("first_name");
				last_name = rs.getString("last_name");
				email = rs.getString("email");
				phone = rs.getString("phone");
				dob = new SimpleDateFormat("dd-MM-yyyy").format(rs.getDate("dob"));
				gender = rs.getString("gender");
				address = rs.getString("address");
			}
			
    	}catch(Exception e) {}
    }
    
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");

		PrintWriter pw = response.getWriter();
		System.out.println("get");
		
		
		Cookie ck[] = request.getCookies();
		int flag = 0;
		String user_id = Utilities.GetUID(request);
		
		
		if(user_id == null)
		{
			request.getRequestDispatcher("Login").include(request, response);
		}
		else
		{
			System.out.println("From Cookies "+user_id);
			DB_Access(user_id);
		   	request.setAttribute("first_name", first_name);
		   	request.setAttribute("last_name", last_name);
		   	request.setAttribute("email", email);
		   	request.setAttribute("phone", phone);
		   	request.setAttribute("dob", dob);
		   	request.setAttribute("gender", gender);
		   	request.setAttribute("address", address);
			request.getRequestDispatcher("profile.jsp").include(request, response);

			
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");

		PrintWriter pw = response.getWriter();
		
		String new_address = request.getParameter("new_address");
		System.out.println(new_address);
		
		String user_id = Utilities.GetUID(request);
		
		try 
		{
			Connection con;
			PreparedStatement pstm; 			       //class to prepare statement
			
			Class.forName("com.mysql.cj.jdbc.Driver"); //Class is a class
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet", "root", "abcd"); //DriverManager is a class 
														//jdbc:mysql then ip address then port no. then db name
			Statement stmt = con.createStatement();
			
			pstm = con.prepareStatement("update user_table set address = (?) where user_id = (?)");
			pstm.setString(1, new_address);
			pstm.setString(2, user_id);
			
			pstm.executeUpdate();
			
			pw.println("1");
		
		
		}catch(Exception e) {
			pw.println("0");
			System.out.println(e);
		}
		
	}
}
