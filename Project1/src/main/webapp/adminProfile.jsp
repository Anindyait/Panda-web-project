<!doctype html>
<html lang="en">
<head>
    <meta charset="ISO-8859-1">
    <script src="https://code.jquery.com/jquery-3.3.1.js"
			integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
			crossorigin="anonymous">
	</script>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <link rel="stylesheet" href="Bootstrap/CSS/style1.css">
    
	<script>
		$(function () {
			$("#header").load("header.jsp");
			$("#footer").load("footer.html");
		});
	</script>
	<meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Admin Profile</title>
    <link rel="icon" href="Pics/panda.png">
    
</head>
<body>
    <div class="font">
		<div class="container-fluid">
			<div id="header"></div>
			   <div class="header-adjustment"></div>
                <br>
               <h1 class="profile-heading">Hello Admin <%= request.getAttribute("first_name")%>!</h1>
				<div class="profile-buttons">
					<button type="sub" class="btn bamboo form-submit" onclick="show_details()">Details</button>
					<a href="AdminLogout">
						<button type="sub" class="btn btn-outline-danger form-submit">Logout</button>
					</a>
				</div>
				<div class="admin" style="display:none;">
			   	   <div class="admin-head">
				   	   <div class="row">
					   	   <h2>Here are your Profile details:</h2>
						   <div class="col">
							   <div class="admin-text">
							   		<div class="row">
					    				<div class="col-3 admin-col">Name:</div>
					    				<div class="col-4 admin-col"><%= request.getAttribute("first_name")%> <%= request.getAttribute("last_name")%></div>
				 					</div>
				 					<div class="row">
										<div class="col-3 admin-col">Email:</div>					
					 					<div class="col-4 admin-col"><%= request.getAttribute("email")%></div>
				 					</div>
				 					<div class="row">
					    				<div class="col-3 admin-col">Phone:</div>
					 					<div class="col-4 admin-col"><%= request.getAttribute("phone")%></div>
				 					</div>
							   </div>
						  </div>
					  </div>
				 </div>
			   </div>
			   <br>
			   <div class="admin">
			   	   <div class="admin-head">
			   	   	<div class="row">
				   	   <h2>What would you like to work on today?</h2>
					   <div class="col">
						   <div class="admin-text">
							   	<div class="row justify-content-center">
							   		<div class="col">
								   		<a href="AdminProfile?job=add product">
											<div type="sub" class="btn btn-success form-submit">Add Products</div>
					   					</a>
								   	</div>
								   	<div class="col">
								   		<a href="AdminProfile?job=see product">
											<div type="sub" class="btn btn-primary form-submit">See Products</div>
					   					</a>
								   	</div>
								   	<div class="col">
								   		<a href="AdminProfile?job=edit product">
											<div type="sub" class="btn btn-warning form-submit">Edit Products</div>
					   					</a>
								   	</div>
								   	<div class="col">
								   		<a href="AdminProfile?job=delete product">
											<div type="sub" class="btn btn-danger form-submit">Delete Products</div>
					   					</a>
								   	</div>
				   				</div>
						   </div>
					  </div>
				  </div>
			   	   </div>
			   </div>
		</div>
	</div>
	<div id="footer"></div>
	<script>
	var details = document.getElementsByClassName("admin");

	function show_details()
	{
		console.log("details");
		if(details[0].style.display == "none")
			details[0].style.display="block";
		else
               details[0].style.display = "none";
	}
	</script>
</body>
</html>
