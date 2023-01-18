package pkg;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ProductExperiment
 */
@WebServlet("/ProductExperiment")
public class ProductExperiment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductExperiment() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.";
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		ServletContext context=getServletContext();  
		
		String title = request.getParameter("title");
		String desc = request.getParameter("desc");
		
		//request.setAttribute("<Name>", what to replace with);
		//set Title
		request.setAttribute("Title", title);
		
		//set Description
		request.setAttribute("Description", desc);
		
		//disable sizes
		request.setAttribute("disabled_xs", "disabled");
		//request.setAttribute("disabled_s", "disabled");
		//request.setAttribute("disabled_m", "disabled");
		//request.setAttribute("disabled_l", "disabled");
		//request.setAttribute("disabled_xl", "disabled");
		request.getRequestDispatcher("productExperiment.jsp").forward(request, response);

		pw.close();
	}

}
