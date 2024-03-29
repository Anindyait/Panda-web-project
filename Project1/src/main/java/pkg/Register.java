package pkg;

import java.io.*;
import java.sql.*;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.*; //To implement hashing algorithms
import java.security.spec.InvalidKeySpecException;

import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * 
	 */
    
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
	   	request.setAttribute("existing_phone", "hidden");
	   	request.setAttribute("existing_email", "hidden");
    	request.getRequestDispatcher("register.jsp").include(request, response);
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		ServletContext context=getServletContext(); 
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		

		
		//getting values from input fields
		String first_name = request.getParameter("first-name");
		String last_name = request.getParameter("last-name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String dd = request.getParameter("dd");
		String mm = request.getParameter("mm");
		String yyyy = request.getParameter("yyyy");
		String gender = request.getParameter("gender");
		String address = request.getParameter("address");
		String password = request.getParameter("password");
		
		String dob = yyyy + "-" + mm + "-" + dd;
		String time_now = java.time.LocalDate.now().toString();
		if (dob.compareTo(time_now) > 0)
		{
			dob = time_now;
		}	
		System.out.println(first_name+ " " + last_name);
		System.out.println(password);
		System.out.println(email);
		System.out.println(dob);

		try 
		{
			Connection con;
			PreparedStatement pstm; 			       //class to prepare statement
			
			Class.forName("com.mysql.cj.jdbc.Driver"); //Class is a class
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet", "root", "abcd"); //DriverManager is a class 
														//jdbc:mysql then ip address then port no. then db name
													

			Statement stmt = con.createStatement();
			
			
			pstm = con.prepareStatement("select email from user_table where email = ?;");
			pstm.setString(1, email);
			ResultSet rs_email = pstm.executeQuery();
			
			pstm = con.prepareStatement("select phone from user_table where phone = ?;");
			pstm.setString(1, phone);
			ResultSet rs_phone = pstm.executeQuery();
			
			password = generateStrongPasswordHash(password);
			
			int flag = 0;
			while(rs_email.next())
			{
				flag=2;
			}
			
			while(rs_phone.next())
			{
				flag=1;
			}
			
			
			if(flag == 1)
			{
				//pw.println("Phone no. already exists!");
			   	request.setAttribute("existing_email", "hidden");
		    	request.getRequestDispatcher("register.jsp").include(request, response);

			}
			else if(flag == 2)
			{
				//pw.println("Email ID already exists!");
			   	request.setAttribute("existing_phone", "hidden");
		    	request.getRequestDispatcher("register.jsp").include(request, response);

			}
			else {
															//mysql username and password
				pstm  = con.prepareStatement("insert into user_table "
											+ "(first_name, last_name, email, phone, dob, gender, address, password) "
											+ "values(?, ?, ?, ?, ?, ?, ?, ?)");
				pstm.setString(1, first_name); 					// goes to 1st q mark
				pstm.setString(2, last_name); 
				pstm.setString(3, email); 
				pstm.setString(4, phone);
				pstm.setString(5, dob);
				pstm.setString(6, gender);
				pstm.setString(7, address);
				pstm.setString(8, password);
				
				pstm.executeUpdate();
				
				
				
		    	request.getRequestDispatcher("postRegister.html").include(request, response);

			}
			
			
			
		}catch(Exception e) {}
	}
	
	private static String generateStrongPasswordHash(String password) throws NoSuchAlgorithmException, InvalidKeySpecException
	{
	    int iterations = 1000;
	    char[] chars = password.toCharArray();
	    byte[] salt = "[B@76ed5528".getBytes(); //Fixed salt to verify Register and Login passwords, 16-byte salt
	    PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8); //Password based encryption 
	    																   //Constructor that takes a password, salt, iteration count, to-be-derived key length for generating PBEKey of variable-key-size PBE ciphers.
	    																   //and to-be-derived key length for generating PBEKey of variable-key-size PBE ciphers.

	    SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1"); //Password based Key Derivation Function 2 
	    																		   //with Secure Hashing Algorithm 1 (reduces brute force attacks)
	    byte[] hash = skf.generateSecret(spec).getEncoded();
	    return iterations + ":" + toHex(salt) + ":" + toHex(hash);
	}
	private static String toHex(byte[] array) throws NoSuchAlgorithmException
	{
	    BigInteger bi = new BigInteger(1, array);
	    String hex = bi.toString(16);
	    int paddingLength = (array.length * 2) - hex.length();
	    if(paddingLength > 0)
	    {
	        return String.format("%0"  +paddingLength + "d", 0) + hex;
	    }else{
	        return hex;
	    }
	}
}