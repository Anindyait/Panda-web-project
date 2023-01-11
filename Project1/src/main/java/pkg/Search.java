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
 * Servlet implementation class Search
 */
@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter pw=response.getWriter();
		response.setContentType("text/html");
		
		ServletContext context=getServletContext();
		
		String searchString = request.getParameter("search");
		searchString = Utilities.LikeString(searchString);
		
		System.out.println("Post "+ searchString);
		
		String suggestionTemplate = "<a href=\"Product?pid=!PID!&category=New Arrivals\">!SUGGESTION!</a>";

		
		String suggestions = " ";
		
		try {
			Connection con;
    		PreparedStatement pstm;
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet", "root", "abcd"); //DriverManager is a class 
			
			pstm = con.prepareStatement("select product_id, p_name from product_table where p_name like (?) order by product_id desc limit 10;");
    		
			pstm.setString(1, searchString);
			
			ResultSet rs = pstm.executeQuery();
			
			
			while(rs.next())
			{
				System.out.println(rs.getString("p_name"));
				
				
				String eachSuggestion = suggestionTemplate.replaceAll("!SUGGESTION!", rs.getString("p_name"));
				eachSuggestion = eachSuggestion.replaceAll("!PID!", rs.getString("product_id"));

				suggestions = suggestions.concat(eachSuggestion);
				
			}
			
			pw.println(suggestions);
			
		}catch(Exception e) {}
		
		
	}

}
