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
    

   String productImgTemplate = "<img src=\"!IMGS!\" >";
   String productTitleTemplate = "<p>!TITLE!</p>";
   String productSizeTemplate = "<p>!SIZE!</p>";
   String productQtyTemplate = "<p>x&nbsp;!QTY!</p>";
   
   String OrderTemplate = "				<div class=\"Cart-Items\">\r\n"
   		+ "                                    <span class=\"order-image\">\r\n"
   		+ "                                        !ALL_IMGS!\r\n"
   		+ "                                    </span>\r\n"
   		+ "                                        <div class=\"row\">\r\n"
   		+ "                                        <div class=\"col\">\r\n"
   		+ "                                            <div class=\"about-order\">\r\n"
   		+ "                                               \r\n"
   		+ "                                                <h6>#!ORDER_NO!</h6>\r\n"
   		+ "                                                <p class=\"order_id\" style=\"display:none;\">!ORDER_NO!</p>\r\n"
   		+ "                                                <div class=\"row\">\r\n"
   		+ "                                                    <div class=\"col-8\" >\r\n"
   		+ "                                                        <div class=\"ordered-items\">\r\n"
   		+ "                                                            !ALL_TITLES!\r\n"
   		+ "                                                        </div>\r\n"
   		+ "                                                    </div>\r\n"
   		+ "                                                    <div class=\"col-1 text-center\">\r\n"
   		+ "                                                        <div class=\"ordered-items-qt\">\r\n"
   		+ "                                                            !ALL_SIZES!\r\n"
   		+ "                                                        </div>\r\n"
   		+ "                                                    </div>\r\n"
   		+ "                                                    <div class=\"col-1 text-center\">\r\n"
   		+ "                                                        <div class=\"ordered-items-qt\">\r\n"
   		+ "                                                            !ALL_QTYS!\r\n"
   		+ "                                                        </div>\r\n"
   		+ "                                                    </div>\r\n"
   		+ "                                                </div>\r\n"
   		+ "                                            </div>\r\n"
   		+ "                                        </div>\r\n"
   		+ "                                        <div class=\"col-md-3 text-center align-self-center\">\r\n"
   		+ "                                            <br>\r\n"
   		+ "                                                <div class=\"delivery-amount\">&#8377;&nbsp;!AMT!</div>\r\n"
   		+ "                                                <h4 class=\"delivery-date\">Delivery: !D_DATE!</h4>	\r\n"
   		+ "                                                <div class=\"payment-method\">Mode of payment: </div>\r\n"
   		+ "                                                <div class=\"payment-method\">!PAYMENT!</div>	\r\n"
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
			String payment = request.getParameter("payment");

			//Dispatch order page directly on COD
			if (payment.equals("Cash On Delivery") || payment.equals("UPI / QR Code") || payment.equals("Net Banking"))
			{
				request.setAttribute("payment", payment);
				request.getRequestDispatcher("paymentGate.jsp").include(request, response);
			}
			else
				request.getRequestDispatcher("error.html").include(request, response);
		}
		else if (job.equals("previous"))
		{
			System.out.println("Previous Orders page");
			String previous_order_list = "";
			
			try {
				Connection con;
				PreparedStatement pstm;
				
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet", "root", "abcd"); //DriverManager is a class 
				
				pstm = con.prepareStatement("select distinct(order_id), d_date, payment from cart_table where user_id = ? order by order_id desc;");
				
				pstm.setString(1, user_id);
				
				ResultSet rs = pstm.executeQuery();
				
			    while (rs.next ())
			    {	
			    	String eachOrder = OrderTemplate;
			    	eachOrder = eachOrder.replaceAll("!ORDER_NO!", rs.getString("order_id"));
			    	eachOrder = eachOrder.replaceAll("!D_DATE!", new SimpleDateFormat("dd-MM-yyyy").format(rs.getDate("d_date")));
			    	eachOrder = eachOrder.replaceAll("!PAYMENT!", rs.getString("payment"));
			    	
			    	PreparedStatement pstm1 = con.prepareStatement("select cart_table.product_id, cart_table.size, cart_table.quantity, "
			    			+ "product_table.price, product_table.p_name, product_table.imgs "
			    			+ "from cart_table inner join product_table on cart_table.product_id = product_table.product_id "
			    			+ "where cart_table.user_id = ? and cart_table.order_id = ?;");
			    	
					pstm1.setString(1, user_id);
					pstm1.setString(2, rs.getString("order_id"));
					
					ResultSet rs1 = pstm1.executeQuery();
					
					String all_imgs = "";
					String all_titles = "";
					String all_sizes = "";
					String all_qtys = "";
					float amt = 0;
					
					while(rs1.next())
					{
						
				    	String[] image = rs1.getString("imgs").split(",");
						
						amt += rs1.getInt("quantity") * rs1.getFloat("price");
						
						String eachProductOption = productImgTemplate;
				    	eachProductOption = eachProductOption.replaceAll("!IMGS!", "Pics/" + image[0] + ".jpg" );
				    	all_imgs = all_imgs.concat(eachProductOption);
				    	
				    	eachProductOption = productTitleTemplate;
				    	eachProductOption = eachProductOption.replaceAll("!TITLE!",rs1.getString ("p_name") );
				    	all_titles = all_titles.concat(eachProductOption);

				    	eachProductOption = productSizeTemplate;
				    	eachProductOption = eachProductOption.replaceAll("!SIZE!", rs1.getString ("size"));
				    	all_sizes = all_sizes.concat(eachProductOption);

				    	eachProductOption = productQtyTemplate;
				    	eachProductOption = eachProductOption.replaceAll("!QTY!", rs1.getString ("quantity"));
				    	all_qtys = all_qtys.concat(eachProductOption);

					}
					
			    	eachOrder = eachOrder.replaceAll("!ALL_IMGS!", all_imgs);
			    	eachOrder = eachOrder.replaceAll("!ALL_TITLES!", all_titles);
			    	eachOrder = eachOrder.replaceAll("!ALL_SIZES!", all_sizes);
			    	eachOrder = eachOrder.replaceAll("!ALL_QTYS!", all_qtys);
			    	eachOrder = eachOrder.replaceAll("!AMT!", String.valueOf(amt));
	
			    	previous_order_list = previous_order_list.concat(eachOrder);
			    }
				    
				    
			}catch(Exception e) {}

			request.setAttribute("previous_order_list", previous_order_list);
			request.getRequestDispatcher("order.jsp").include(request, response);
			
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
				String payment = request.getParameter("payment");

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
	
			    pstm = con.prepareStatement("update cart_table set order_id = ? , d_date = ?, payment = ? where user_id = ? and order_id IS NULL;");
			    pstm.setString(1, order_id);
			    pstm.setString(2, dateString);
			    pstm.setString(3, payment);
			    pstm.setString(4, user_id);
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
