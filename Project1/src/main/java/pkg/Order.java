package pkg;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Order
 */
@WebServlet("/Order")
public class Order extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Order() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    String order_list = "";
	String address = null;
	String order_id = null;
	String job = null;
    
    String checkoutTemplate = "					<div class=\"Cart-Items\">\r\n"
    		+ "                                    <div class=\"row justify-content-between align-items-center\">\r\n"
    		+ "                                        <div class=\"col-2\" style=\"width: 100px;\">\r\n"
    		+ "                                            <div class=\"image-box\">\r\n"
    		+ "                                                <img src=\"!IMGS!\" style= \"height: 120px\">\r\n"
    		+ "                                            </div>\r\n"
    		+ "                                        </div>                                        \r\n"
    		+ "                                    \r\n"
    		+ "                                        <div class=\"col-5\">\r\n"
    		+ "                                            <div class=\"about-cart\">\r\n"
    		+ "                                                <a href = \"Product?pid=!PID!\">\r\n"
    		+ "                                                    <h5>!TITLE!</h5>\r\n"
    		+ "                                                </a>\r\n"
    		+ "                                                <p class=\"pid\" style=\"display:none;\">!PID!</p>\r\n"
    		+ "                                                <h3 class=\"subtitle-cart\">\r\n"
    		+ "                                                	Size: <span class=\"size\">XS</span>\r\n"
    		+ "                                                	<span class =\"price\">&#8377; !PRICE!</span>\r\n"
    		+ "                                                </h3>\r\n"
    		+ "                                            </div>\r\n"
    		+ "                                        </div>\r\n"
    		+ "                                        <div class=\"col-1\" style=\"width: 40px; padding:0px;\">\r\n"
    		+ "                                            <h4 class=\"qt\">x !QUANTITY!</h4>\r\n"
    		+ "                                        </div>\r\n"
    		+ "                                        <div class=\"col-2\">\r\n"
    		+ "                                            <div class=\"prices\">\r\n"
    		+ "                                                <div class=\"amount\">!AMT!</div>\r\n"
    		+ "                                            </div>\r\n"
    		+ "                                        </div>\r\n"
    		+ "                                    </div>\r\n"
    		+ "                                </div>";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String user_id = Utilities.GetUID(request);
		job = request.getParameter("job");
		
		
		if(user_id == null)
		{
			request.getRequestDispatcher("Login").include(request, response);
		}
		else if (job == null)
		{
			System.out.println("Checkout page");
			order_list = "";
			
			try {
				Connection con;
				PreparedStatement pstm;
				
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet", "root", "abcd"); //DriverManager is a class 
				
					pstm = con.prepareStatement("select imgs, p_name, size, quantity, price, cart_table.product_id "
							+ "from cart_table inner join product_table "
							+ "on cart_table.product_id = product_table.product_id where user_id = ? and order_id IS NULL;");
					// When order_id is null, those items are in the cart
					
					pstm.setString(1, user_id);
					
					ResultSet rs = pstm.executeQuery();
					
				    while (rs.next ())
				    {			    	
						String[] image = rs.getString("imgs").split(",");
						
						float amt = rs.getInt("quantity") * rs.getFloat("price");
						
				    	String eachProductOption = checkoutTemplate;
				    	eachProductOption = eachProductOption.replaceAll("!IMGS!", "Pics/" + image[0] + ".jpg" );
				    	eachProductOption = eachProductOption.replaceAll("!TITLE!",rs.getString ("p_name") );
				    	eachProductOption = eachProductOption.replaceAll("!SIZE!", rs.getString ("size"));
				    	eachProductOption = eachProductOption.replaceAll("!QUANTITY!", rs.getString ("quantity"));
				    	eachProductOption = eachProductOption.replaceAll("!PRICE!", rs.getString ("price"));
				    	eachProductOption = eachProductOption.replaceAll("!PID!", rs.getString ("cart_table.product_id"));
				    	eachProductOption = eachProductOption.replaceAll("!AMT!", String.valueOf(amt) );
		
				    	order_list = order_list.concat(eachProductOption);
				    }
				    
				    // Sending address to checkout
				    pstm = con.prepareStatement("select address from user_table where user_id = ? ;");
				    pstm.setString(1, user_id);
					rs = pstm.executeQuery();
					if (rs.next())
					{
						address = rs.getString("address");
					}
				    
			}catch(Exception e) {}

			request.setAttribute("order_list", order_list);
			request.setAttribute("address", address);
			request.getRequestDispatcher("checkout.jsp").include(request, response);
		}
		else if (job.equals("checkout"))
		{
			System.out.println("Page after checkout: Payment Page");
			request.getRequestDispatcher("paymentGate.html").include(request, response);
		}

		else
		{
			request.getRequestDispatcher("error.html").include(request, response);
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
		
		String user_id = Utilities.GetUID(request);
		
		String job = request.getParameter("job");
				
		if(user_id == null)
		{
			request.getRequestDispatcher("Login").include(request, response);
		}
		
		else if (job.equals("place order"))
		{
			
			try {
				Connection con;
				PreparedStatement pstm;
				
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet", "root", "abcd"); //DriverManager is a class 
	
				// Assigning a new order_id and d_date
			    pstm = con.prepareStatement("select distinct(order_id) from cart_table where user_id = ? order by order_id desc limit 1;");
				pstm.setString(1, user_id);
				ResultSet rs = pstm.executeQuery();
				if (rs.next())
				{
					order_id = rs.getString("order_id");
					if (order_id == null)
					{
						order_id = "OR00001";
					}
					else
					{	
						String numericPart = order_id.substring(2); 
						int orderNumber = Integer.parseInt(numericPart);
						orderNumber += 1;
						String formattedOrderNumber = String.format("%05d", orderNumber);
						order_id = "OR" + formattedOrderNumber;
					}
					System.out.println("Order id: " + order_id);
				}
				
				LocalDate date = LocalDate.now(); // current date
			    Random rand = new Random();
			    int daysToAdd = rand.nextInt(9) + 2; // random number of days between 2 and 10
			    date = date.plusDays(daysToAdd);
	
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			    String dateString = date.format(formatter);
				System.out.println("Delivery date: " + dateString);
	
			    pstm = con.prepareStatement("update cart_table set order_id = ? , d_date = ? where user_id = ? and order_id IS NULL;");
			    pstm.setString(1, order_id);
			    pstm.setString(2, dateString);
			    pstm.setString(3, user_id);
				int rs1 = pstm.executeUpdate();
				
				if (rs1 > 0)
					System.out.println("Order successfully placed!");
				
			}catch(Exception e) {}
			
		}
		else
		{
			
		}
			
	}

}
