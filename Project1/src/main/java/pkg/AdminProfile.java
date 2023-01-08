package pkg;

import java.io.*;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletContext;
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
    
    String first_name = null;
    String last_name = null;
    String email = null;
    String phone = null;
    
    String job = null;
    String pid_list = " ";
    String prod_list = " ";
    
    int post_status;

    //HTML template for each delete option
    //Note everything that is put from the DBMS is in the form !<NAME_IN_CAPS>! form,
    String productOptionTemplate = "<option value=\"!PID!\">!PID!</option>";
    String productRowTemplate = "<tr>\r\n"
    		+ "							      <th scope=\"row\">!PID!</th>\r\n"
    		+ "							      <td>!TITLE!</td>\r\n"
    		+ "							      <td>!PRICE!</td>\r\n"
    		+ "							      <td>!SIZES!</td>\r\n"
    		+ "							      <td>!STOCK!</td>\r\n"
    		+ "							      <td>!DESC!</td>\r\n"
    		+ "							    </tr>";
        
    void DB_Access(String admin_id, String job, String pid)
    {
    	try {
			Connection con;
			PreparedStatement pstm;
			
			int aid = Integer.parseInt(admin_id);
			System.out.println(aid*10);
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet", "root", "abcd"); //DriverManager is a class 
			
			// Getting Admin Profile details
			if (job == null)
			{
				pstm = con.prepareStatement("select first_name, last_name, email, phone from admin_table where admin_id = ?;");
				
				pstm.setString(1, admin_id);
				
				ResultSet rs = pstm.executeQuery();
				
				if(rs.next())
				{
					first_name = rs.getString("first_name");
					last_name = rs.getString("last_name");
					email = rs.getString("email");
					phone = rs.getString("phone");
				}
			}
			
			// To get delete product page details
			else if (job.equals("delete product"))
			{

				if (pid == " ")
				{
					pstm = con.prepareStatement("select product_id from product_table;");
					
					ResultSet rs = pstm.executeQuery();

				    while (rs.next ())
				    {
				        String eachProductOption = productOptionTemplate;
				    	eachProductOption = eachProductOption.replaceAll("!PID!", rs.getString ("product_id"));
				    	pid_list = pid_list.concat(eachProductOption);
				    }
				}
				
				// To delete a product
				else
				{
					pstm = con.prepareStatement("delete from product_table where product_id = ?;");
					pstm.setString(1, pid);
					int rs = pstm.executeUpdate();
					System.out.println(pid + " was deleted");
					post_status = 200;
					
					String eachProductOption = productOptionTemplate;
			    	eachProductOption = eachProductOption.replaceAll("!PID!", pid);
			    	pid_list = pid_list.replace(eachProductOption, "");
					
				}
				
			}
			else if (job.equals("see product"))
			{
				pstm = con.prepareStatement("select product_id, p_name, price, sizes, stock, descr from product_table order by product_id;");
				
				ResultSet rs = pstm.executeQuery();

			    while (rs.next ())
			    {
			        String eachProduct = productRowTemplate;
			    	eachProduct = eachProduct.replaceAll("!PID!", rs.getString ("product_id"));
			    	eachProduct = eachProduct.replaceAll("!TITLE!", rs.getString ("p_name"));
			    	eachProduct = eachProduct.replaceAll("!PRICE!", rs.getString ("price"));
			    	eachProduct = eachProduct.replaceAll("!SIZES!", rs.getString ("sizes"));
			    	eachProduct = eachProduct.replaceAll("!STOCK!", rs.getString ("stock"));
			    	eachProduct = eachProduct.replaceAll("!DESC!", rs.getString ("descr"));

			    	prod_list = prod_list.concat(eachProduct);
			    }
			}
			else 
			{
				
			}
			
			
    	}catch(Exception e) {
    		post_status = 300;
    	}
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
			// If no cookie for admin_id is set
			request.getRequestDispatcher("AdminLogin").include(request, response);
		}
		else
		{
			job = request.getParameter("job");
			
			// Just to display Admin Profile
			if (job == null)
			{
				System.out.println("From Cookies " + admin_id);
				DB_Access(admin_id, job, " ");
			   	request.setAttribute("first_name", first_name);
			   	request.setAttribute("last_name", last_name);
			   	request.setAttribute("email", email);
			   	request.setAttribute("phone", phone);
				request.getRequestDispatcher("adminProfile.jsp").include(request, response);
				
			}
			
			// Admin Delete product page
			else if (job.equals("delete product"))
			{
				 System.out.println("Product Deletion page");
				 DB_Access(admin_id, job, " ");
				 request.setAttribute("pid_list", pid_list);
				 request.getRequestDispatcher("delProduct.jsp").include(request, response);
			}
			else if (job.equals("see product"))
			{
				 System.out.println("See Product page");
				 DB_Access(admin_id, job, " ");
				 request.setAttribute("prod_list", prod_list);
				 request.getRequestDispatcher("seeProduct.jsp").include(request, response);
				
			}
			else
			{
				
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PrintWriter pw=response.getWriter();
		response.setContentType("text/html");
		
		ServletContext context=getServletContext();
		
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
			String job = request.getParameter("job");
			
			// Js requests for new pid_list
			if (job.equals("delete product"))
			{
				String pid = request.getParameter("pid");
				
				System.out.println("Post PID "+ pid);
				System.out.println("Post Job "+ job);

				DB_Access(admin_id, job, pid);
				
				response.setStatus(post_status);
				response.setHeader("Content-Type", "text/html");
				response.getWriter().println(pid_list);
				
			}
		}
		
	}
}
