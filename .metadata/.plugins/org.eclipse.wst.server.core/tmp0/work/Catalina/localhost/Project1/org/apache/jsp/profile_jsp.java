/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.69
 * Generated at: 2023-01-06 15:53:21 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class profile_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    if (!javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET, POST or HEAD. Jasper also permits OPTIONS");
        return;
      }
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("<!doctype html>\r\n");
      out.write("<html lang=\"en\">\r\n");
      out.write("<head>\r\n");
      out.write("    <meta charset=\"ISO-8859-1\">\r\n");
      out.write("    <script src=\"https://code.jquery.com/jquery-3.3.1.js\"\r\n");
      out.write("			integrity=\"sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60=\"\r\n");
      out.write("			crossorigin=\"anonymous\">\r\n");
      out.write("	</script>\r\n");
      out.write("	<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65\" crossorigin=\"anonymous\">\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"Bootstrap/CSS/style1.css\">\r\n");
      out.write("    \r\n");
      out.write("	<script>\r\n");
      out.write("		$(function () {\r\n");
      out.write("			$(\"#header\").load(\"header.jsp\");\r\n");
      out.write("			$(\"#footer\").load(\"footer.html\");\r\n");
      out.write("		});\r\n");
      out.write("	</script>\r\n");
      out.write("	<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n");
      out.write("    <title>Profile</title>\r\n");
      out.write("    <link rel=\"icon\" href=\"Pics/panda.png\">\r\n");
      out.write("\r\n");
      out.write("	<script>\r\n");
      out.write("		var details = document.getElementsByClassName(\"admin\");\r\n");
      out.write("\r\n");
      out.write("		\r\n");
      out.write("\r\n");
      out.write("		function show_details()\r\n");
      out.write("		{\r\n");
      out.write("			console.log(\"details\");\r\n");
      out.write("			if(details[0].style.display == \"none\")\r\n");
      out.write("				details[0].style.display=\"block\";\r\n");
      out.write("			else\r\n");
      out.write("                details[0].style.display = \"none\";\r\n");
      out.write("		}\r\n");
      out.write("\r\n");
      out.write("		var new_address;\r\n");
      out.write("		var old_address;\r\n");
      out.write("		var res;\r\n");
      out.write("\r\n");
      out.write("		function address_edit()\r\n");
      out.write("		{\r\n");
      out.write("			var regex = \"^\\\\s+$\";\r\n");
      out.write("			var edit_button = document.getElementById(\"edit-button\");\r\n");
      out.write("			var submit_button = document.getElementById(\"submit-button\");\r\n");
      out.write("			var address_box = document.getElementById(\"new-address-box\");\r\n");
      out.write("			\r\n");
      out.write("			address_box.value = address.innerHTML;\r\n");
      out.write("			old_address = address_box.value;\r\n");
      out.write("			address.innerHTML = '';\r\n");
      out.write("			address_box.style.display = \"block\";\r\n");
      out.write("			address.style.display = \"none\";\r\n");
      out.write("			edit_button.style.display = \"none\";\r\n");
      out.write("			submit_button.style.display = \"block\";\r\n");
      out.write("		}\r\n");
      out.write("\r\n");
      out.write("		function change_address()\r\n");
      out.write("		{\r\n");
      out.write("			var edit_button = document.getElementById(\"edit-button\");\r\n");
      out.write("			var submit_button = document.getElementById(\"submit-button\");\r\n");
      out.write("			var address_box = document.getElementById(\"new-address-box\");\r\n");
      out.write("			\r\n");
      out.write("\r\n");
      out.write("			new_address = address_box.value;\r\n");
      out.write("			address_box.style.display = \"none\";\r\n");
      out.write("			address.style.display = \"block\";\r\n");
      out.write("			edit_button.style.display = \"block\";\r\n");
      out.write("			submit_button.style.display = \"none\";\r\n");
      out.write("			\r\n");
      out.write("			var status = document.getElementById(\"status\");\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("			if(new_address == old_address || new_address ==\"\")\r\n");
      out.write("			{\r\n");
      out.write("				address.innerHTML = old_address;\r\n");
      out.write("			}\r\n");
      out.write("			else\r\n");
      out.write("			{\r\n");
      out.write("				\r\n");
      out.write("\r\n");
      out.write("				address.innerHTML = new_address;\r\n");
      out.write("				var http = new XMLHttpRequest();\r\n");
      out.write("            	http.open(\"POST\", \"Profile\", true);\r\n");
      out.write("            	http.setRequestHeader(\"Content-type\",\"application/x-www-form-urlencoded\");\r\n");
      out.write("            	var params = \"new_address=\" + new_address;\r\n");
      out.write("            	http.send(params);\r\n");
      out.write("            	\r\n");
      out.write("\r\n");
      out.write("				http.onreadystatechange = function() {\r\n");
      out.write("					if (http.readyState == XMLHttpRequest.DONE) {\r\n");
      out.write("						console.log(http.response);\r\n");
      out.write("						\r\n");
      out.write("						res = Number(http.response.toString());\r\n");
      out.write("\r\n");
      out.write("						if (res == 1) \r\n");
      out.write("						{\r\n");
      out.write("							status.style.color = \"green\";\r\n");
      out.write("							status.innerHTML = \"Address changed successfully!\";\r\n");
      out.write("							console.log(res);\r\n");
      out.write("						}\r\n");
      out.write("						else\r\n");
      out.write("						{\r\n");
      out.write("							status.style.color = \"red\";\r\n");
      out.write("							status.innerHTML = \"Sorry, could't change your address.\";\r\n");
      out.write("							console.log(res);\r\n");
      out.write("						}\r\n");
      out.write("					}\r\n");
      out.write("				}\r\n");
      out.write("			}\r\n");
      out.write("		}\r\n");
      out.write("	</script>\r\n");
      out.write("    \r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("    <div class=\"font\">\r\n");
      out.write("		<div class=\"container-fluid\">\r\n");
      out.write("			<div id=\"header\"></div>\r\n");
      out.write("			   <div class=\"header-adjustment\"></div>\r\n");
      out.write("                <br>\r\n");
      out.write("				<br>\r\n");
      out.write("                <h1 class=\"profile-heading\">Welcome ");
      out.print( request.getAttribute("first_name"));
      out.write("!</h1>\r\n");
      out.write("			    <div class=\"profile-buttons\">\r\n");
      out.write("					<button type=\"sub\" class=\"btn bamboo form-submit\" onclick=\"show_details()\">Details</button>\r\n");
      out.write("					\r\n");
      out.write("					<a href=\"#Cart\" type=\"sub\" class=\"btn form-submit bamboo\">View Cart</a>\r\n");
      out.write("					<a href=\"#Orders\" type=\"sub\" class=\"btn form-submit bamboo\">View Orders</a>\r\n");
      out.write("\r\n");
      out.write("					<a href=\"Logout\">\r\n");
      out.write("						<button type=\"sub\" class=\"btn btn-outline-danger form-submit\">Logout</button>\r\n");
      out.write("					</a>\r\n");
      out.write("				</div>\r\n");
      out.write("				\r\n");
      out.write("				<div class=\"admin\" style=\"display:none;\">\r\n");
      out.write("			   	   <div class=\"admin-head\">\r\n");
      out.write("				   	   <div class=\"row\">\r\n");
      out.write("						<h2>Your details...</h2>\r\n");
      out.write("						   <div class=\"col\">\r\n");
      out.write("							   <div class=\"admin-text\">\r\n");
      out.write("							   		<div class=\"row\">\r\n");
      out.write("					    				<div class=\"col-3 admin-col\">Name:</div>\r\n");
      out.write("					    				<div class=\"col-4 admin-col\">");
      out.print( request.getAttribute("first_name"));
      out.write(' ');
      out.print( request.getAttribute("last_name"));
      out.write("</div>\r\n");
      out.write("				 					</div>\r\n");
      out.write("				 					<div class=\"row\">\r\n");
      out.write("										<div class=\"col-3 admin-col\">Email:</div>					\r\n");
      out.write("					 					<div class=\"col-4 admin-col\">");
      out.print( request.getAttribute("email"));
      out.write("</div>\r\n");
      out.write("				 					</div>\r\n");
      out.write("				 					<div class=\"row\">\r\n");
      out.write("					    				<div class=\"col-3 admin-col\">Phone:</div>\r\n");
      out.write("					 					<div class=\"col-4 admin-col\">");
      out.print( request.getAttribute("phone"));
      out.write("</div>\r\n");
      out.write("				 					</div>\r\n");
      out.write("				 					<div class=\"row\">\r\n");
      out.write("					    				<div class=\"col-3 admin-col\">DOB:</div>\r\n");
      out.write("					 					<div class=\"col-4 admin-col\">");
      out.print( request.getAttribute("dob"));
      out.write("</div>\r\n");
      out.write("				 					</div>\r\n");
      out.write("				 					<div class=\"row\">\r\n");
      out.write("					    				<div class=\"col-3 admin-col\">Gender:</div>\r\n");
      out.write("					 					<div class=\"col-4 admin-col\">");
      out.print( request.getAttribute("gender"));
      out.write("</div>\r\n");
      out.write("				 					</div>\r\n");
      out.write("				 					<div class=\"row\">\r\n");
      out.write("					    				<div class=\"col-3 admin-col\">Address:</div>\r\n");
      out.write("					 					<div class=\"col-7 admin-col\">\r\n");
      out.write("\r\n");
      out.write("											<div id=\"address\">");
      out.print( request.getAttribute("address"));
      out.write("</div>\r\n");
      out.write("											<textarea name=\"address\" class=\"form-control\" id=\"new-address-box\" rows=\"3\" style=\"display:none;\" required></textarea>\r\n");
      out.write("											<p id=\"status\" style=\"font-size: 15px;\"></p>\r\n");
      out.write("										</div>\r\n");
      out.write("										<div class=\"col-2\">\r\n");
      out.write("											<i class=\"fa-solid fa-pen-to-square fa-xl\" id=\"edit-button\" onclick=\"address_edit()\"></i>\r\n");
      out.write("											\r\n");
      out.write("											<button type=\"sub\" class=\"btn bamboo\" id=\"submit-button\" style=\"display:none\" onclick=\"change_address()\">Submit</button>\r\n");
      out.write("											\r\n");
      out.write("										</div>\r\n");
      out.write("				 					</div>\r\n");
      out.write("							   </div>\r\n");
      out.write("						  </div>\r\n");
      out.write("					  </div>\r\n");
      out.write("				 </div>\r\n");
      out.write("			   </div>\r\n");
      out.write("			   <br>\r\n");
      out.write("			   \r\n");
      out.write("			   \r\n");
      out.write("		</div>\r\n");
      out.write("	</div>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
