package pkg;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ProductList
 */
@WebServlet("/ProductList")
public class ProductList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductList() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    //For checking whether the page is loading for the 1st time
    //Otherwise reloading will show all products again.
    boolean firstTimeLoad = true;
    
    
    
    //HTML template for each Product Card
    //Note everything that is put from the DBMS is in the form !<NAME_IN_CAPS>! form,
    //this is just a protocol I followed, pls follow this gaiss. 
    String productCardTemplate = "<div class=\"col list-padding\">\r\n"
			+ "                <a href=\"Product?pid=!P_ID!\" class=\"product-link\" data-toggle=\"tooltip\" title=\"!PRODUCT_NAME!\">\r\n"
			+ "                    <div class=\"product\">\r\n"
			+ "                        <img src=\"Pics/!IMAGE!.jpg\" class=\"card-img-top round\" alt=\"...\">\r\n"
			+ "                        <div class=\"card-body\">\r\n"
			+ "                                <h5 class=\" list-title\">!PRODUCT_NAME!</h5>\r\n"
			+ "                                <p class=\"card-text list-price\"><span class=\"rupees\">&#8377;</span>!PRICE!<sup>.00</sup></p>\r\n"
			+ "                            </div>\r\n"
			+ "                    </div>\r\n"
			+ "                </a>\r\n"
			+ "            </div>"
			+ "					";
    
    
    //Will have all the Cards, can't be null.
    String allProductCards = " ";
	
    
    //Method to populate the above string from DBMS.
    protected void GetProductList()
    {
    	try {
    		Connection con;
    		PreparedStatement pstm;
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet", "root", "abcd"); //DriverManager is a class 
			
			//The query has no where now, will change in near future.
			pstm = con.prepareStatement("select product_id, p_name, price, imgs from product_table;");
			
			ResultSet rs = pstm.executeQuery();
			
			while(rs.next())
			{
				
				//Splitting the image address.
				String[] image = rs.getString("imgs").split(",");
				
				
				//Each Card String.
				String eachProductCard = productCardTemplate;
				
				//putting the data in the template.
				eachProductCard = eachProductCard.replaceAll("!PRICE!", rs.getString("price"));
				
				eachProductCard = eachProductCard.replaceAll("!PRODUCT_NAME!", rs.getString("p_name"));
				
				eachProductCard = eachProductCard.replaceAll("!IMAGE!", image[0]);
				
				eachProductCard = eachProductCard.replaceAll("!P_ID!", rs.getString("product_id"));


				//concat each card.
				allProductCards = allProductCards.concat(eachProductCard);

			}
			
    	}catch(Exception e) {System.out.println(e);}
    	
    	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ServletContext context=getServletContext();
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
		//Check for 1st time load.
		if(firstTimeLoad)
		{
			firstTimeLoad = false;
			GetProductList();
		}
		
		//putting the Cards in productList.jsp.
		request.setAttribute("product_cards", allProductCards);
		request.getRequestDispatcher("productList.jsp").include(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
