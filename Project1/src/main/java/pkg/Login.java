package pkg;

import java.io.*;
import java.sql.*;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setAttribute("wrong_password", "hidden");
	   	request.setAttribute("wrong_email", "hidden");

    	request.getRequestDispatcher("login.jsp").include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ServletContext context=getServletContext();
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
		String email = request.getParameter("email");
		String password  = request.getParameter("password");
		
		
		try {
			Connection con;
			PreparedStatement pstm;
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet", "root", "abcd"); //DriverManager is a class 
			
			
			
			pstm = con.prepareStatement("select user_id, email, password, first_name from user_table where email = ?;");
			pstm.setString(1, email);
			
			ResultSet rs = pstm.executeQuery();
			
			if(rs.next())
			{
				if(rs.getString("password").equals(password))
				{
					System.out.println("Success!");
					request.setAttribute("logged_in", "ticked");
					
					//Cookies
					Cookie ck  =new Cookie("UID", rs.getString("user_id"));
					ck.setMaxAge(60 * 60 * 24);
					response.addCookie(ck);
					
					//Post log in page
				   	request.setAttribute("first_name", rs.getString("first_name"));

			    	request.getRequestDispatcher("profile.jsp").include(request, response);

				}
				else
				{
					request.setAttribute("wrong_email", "hidden");
					request.getRequestDispatcher("login.jsp").include(request, response);
					System.out.println("Wrong Password!");

				}
			}
			else
			{
				request.setAttribute("wrong_password", "hidden");
				request.getRequestDispatcher("login.jsp").include(request, response);
				System.out.println("Email not present!");
			}
			
			
		}catch(Exception e) {}
	}

}
