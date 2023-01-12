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
    
    String productCardTemplate = "<span class=\"similar-product\">\r\n"
			+ "                <a href=\"Product?pid=!P_ID!&category=!CAT!\" class=\"product-link\" data-toggle=\"tooltip\" title=\"!PRODUCT_NAME!\">\r\n"
			+ "                    <div class=\"product\">\r\n"
			+ "                        <img src=\"Pics/!IMAGE!.jpg\" class=\"card-img-top round\" alt=\"...\">\r\n"
			+ "                        <div class=\"card-body\">\r\n"
			+ "                                <h5 class=\" list-title\">!PRODUCT_NAME!</h5>\r\n"
			+ "                                <p class=\"card-text list-price\">&#8377;!PRICE!<sup>.00</sup></p>\r\n"
			+ "                            </div>\r\n"
			+ "                    </div>\r\n"
			+ "                </a>\r\n"
			+ "            </span>"
			+ "					";
    
    String sizeSelectorTemplate = "<input type=\"radio\" class=\"btn-check\" name=\"btnradio\" id=\"!SIZE!\" autocomplete=\"off\">\r\n"
    		+ "                                    <label class=\"btn btn-outline-dark size-selector\" for=\"!SIZE!\" >!SIZE!</label>";
    
    String allProductCards;
    
    String query = "select product_id, p_name, price, imgs, sizes, cat1 from product_table where (cat3 like(?) or cat2 like(?) or cat1 like(?) or p_name like(?) or descr like(?)) and product_id != ? order by product_id desc limit 8";
    
    //Method to get product info from pid.
    protected boolean getProductDetails(String p_id, String cat, HttpServletRequest request)
    {
    	
    	String catLike = Utilities.LikeString(cat);
    	
    	System.out.println(catLike);
    	
    	allProductCards = " ";
    	
    	String user_id = Utilities.GetUID(request);
    	
    	if(user_id == null)
    	{
    		request.setAttribute("disable_buy", "disabled");
    		request.setAttribute("disable_add_to_cart", "disabled");
    	}
    	else
    	{
    		request.setAttribute("disable_buy", "");
    		request.setAttribute("disable_add_to_cart", "");
    	}
    	
    	
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
				
				//Getting price in String with the intent of remove dat ugly ".0".
				String price = rs.getString("price");
				
				
				String title = rs.getString("p_name");
				
				String pid = rs.getString("product_id");
				
				System.out.println(title);
				
				
				
				//Putting data in the jsp page.
				request.setAttribute("pid", pid);
				request.setAttribute("title", title);
				request.setAttribute("price", price.substring(0, price.length() - 2));
				request.setAttribute("desc", rs.getString("descr"));
				request.setAttribute("img1", image[0]);
				request.setAttribute("img2", image[1]);

				String allSizes = rs.getString("sizes");
				String[] size = allSizes.split(",");
				String allSizeSelectors = " ";
				
				for(int i=0; i < size.length; i++)
				{
					String eachSizeSelector = sizeSelectorTemplate.replaceAll("!SIZE!", size[i]);
					
					allSizeSelectors = allSizeSelectors.concat(eachSizeSelector);
				}
				
				
				
				request.setAttribute("size_selectors", allSizeSelectors);
				//Product details done
				//////////////////////////////////////////////////////
				
				//--------------------------------------------------------------------//
				
				//////////////////////////////////////////////////////
				//From here on, similar products
				if(cat.equals("All"))
				{
					pstm = con.prepareStatement("select product_id, p_name, price, imgs, sizes, cat1 from product_table where product_id != ? order by product_id DESC limit 8");
					pstm.setString(1, pid);

				}
				if(cat.equals("All Panda Shop Products"))
				{
					pstm = con.prepareStatement("select product_id, p_name, price, imgs, sizes, cat1 from product_table where product_id != ? order by product_id DESC limit 8");
					pstm.setString(1, pid);

				}
				else if(cat.equals("New Arrivals"))
				{
					pstm = con.prepareStatement("select product_id, p_name, price, imgs, sizes, cat1 from product_table where product_id != ? order by product_id DESC limit 8");
					pstm.setString(1, pid);

				}
				
				else {
						pstm = con.prepareStatement(query);
						pstm.setString(1, catLike);
						pstm.setString(2, catLike);
						pstm.setString(3, catLike);
						pstm.setString(4, catLike);
						pstm.setString(5, catLike);
						pstm.setString(6, pid);

				}
				
				
				rs = pstm.executeQuery();
				
				while(rs.next())
				{
					
					//Splitting the image address.
					String[] s_image = rs.getString("imgs").split(",");
					
					//Getting price in String with the intent of remove dat ugly ".0".
					String s_price = rs.getString("price");
					
					//Each Card String.
					String eachProductCard = productCardTemplate;
					
					//putting the data in the template.
					eachProductCard = eachProductCard.replaceAll("!PRICE!", s_price.substring(0, s_price.length() - 2));
					
					eachProductCard = eachProductCard.replaceAll("!PRODUCT_NAME!", rs.getString("p_name"));
					
					eachProductCard = eachProductCard.replaceAll("!IMAGE!", s_image[0]);
					
					eachProductCard = eachProductCard.replaceAll("!P_ID!", rs.getString("product_id"));
										
					eachProductCard = eachProductCard.replaceAll("!CAT!", cat);


					//concat each card.
					allProductCards = allProductCards.concat(eachProductCard);

				}
				
				//putting the Cards in product.jsp.
				

				request.setAttribute("similar_product_cards", allProductCards);
				
				
				return true;
				
			}
			
			
    	}catch(Exception e) {System.out.println(e);}
		return false;
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		ServletContext context=getServletContext();  

		//Getting pid from productList.jsp.
		String p_id = request.getParameter("pid");
		
		String cat = request.getParameter("category");
		
		System.out.println("Category from Product page = " + cat);
		
		if(getProductDetails(p_id, cat, request))
		{
			//Loading product.jsp will all the data.
			request.getRequestDispatcher("product.jsp").include(request, response);
		}
		else
		{
			request.getRequestDispatcher("error.html").include(request, response);

		}
			
		
        pw.close();
		
			
	}

}
