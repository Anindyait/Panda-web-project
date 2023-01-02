package pkg;

import java.io.*;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AdminProfile
 */
@WebServlet("/AdminProfile")
public class AdminProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminProfile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * 
	 */
    
    String first_name=null;
    String last_name=null;
    
    void DB_Access(String admin_id)
    {
    	try {
			Connection con;
			PreparedStatement pstm;
			
			int aid = Integer.parseInt(admin_id);
			System.out.println(aid*10);
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet", "root", "abcd"); //DriverManager is a class 
			
			
			pstm = con.prepareStatement("select first_name, last_name from admin_table where admin_id = ?;");
	
			pstm.setString(1, admin_id);
			
			ResultSet rs = pstm.executeQuery();
			
			if(rs.next())
			{
				first_name = rs.getString("first_name");
				last_name = rs.getString("last_name");
			}
			
    	}catch(Exception e) {}
    }
    
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");

		PrintWriter pw = response.getWriter();
		System.out.println("get");
		
		
		Cookie ck[] = request.getCookies();
		String admin_id = null;

		if(ck!=null)
		{
			for(Cookie cookie: ck)
			{
				if(cookie.getName().equals("AID"))
				{
					admin_id = cookie.getValue();
					System.out.println(admin_id);
	
				}
			}
		}
		
		
		if(admin_id == null)
		{
			request.getRequestDispatcher("AdminLogin").include(request, response);
		}
		else
		{
			System.out.println("From Cookies " + admin_id);
			DB_Access(admin_id);
		   	request.setAttribute("first_name", first_name);
			request.getRequestDispatcher("adminProfile.jsp").include(request, response);

			
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	

	}
}
