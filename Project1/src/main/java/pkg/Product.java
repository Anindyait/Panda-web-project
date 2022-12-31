package pkg;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Product
 */
@WebServlet("/Product")
public class Product extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Product() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    //Method to get product info from pid.
    protected void getProductDetails(String p_id, HttpServletRequest request)
    {
    	try {
    		
    		Connection con;
    		PreparedStatement pstm;
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet", "root", "abcd"); //DriverManager is a class 
			
			//Query by pid
			pstm = con.prepareStatement("select * from product_table where product_id = ?;");
    		
			pstm.setString(1, p_id);
			
			ResultSet rs = pstm.executeQuery();
			
			
			
			if(rs.next())
			{
				//Splitting the image addresses.
				String[] image = rs.getString("imgs").split(",");
				
				//Getting price in String with the intend of remove dat ugly ".0".
				String price = rs.getString("price");
				
				//Putting data in the jsp page.
				request.setAttribute("title", rs.getString("p_name"));
				request.setAttribute("price", price.substring(0, price.length() - 2));
				request.setAttribute("desc", rs.getString("descr"));
				request.setAttribute("img1", image[0]);
				request.setAttribute("img2", image[1]);


				
			}
			
    	}catch(Exception e) {System.out.println(e);}
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		ServletContext context=getServletContext();  

		//Getting pid from productList.jsp.
		String p_id = request.getParameter("pid");
		
		
		getProductDetails(p_id, request);
		
		//Loading product.jsp will all the data.
		request.getRequestDispatcher("product.jsp").include(request, response);

		
        pw.close();
		
			
	}

}
